package comsockstests;

import com.github.javafaker.Faker;
import comsocksapi.ProjectConfig;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.LoginPayload;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;
import comsocksapi.payloads.UserPayload;
import comsocksapi.responses.LoginResponse;
import comsocksapi.services.PaymentApiService;
import comsocksapi.services.UserApiServices;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PaymentsTest {

    private ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private PaymentApiService payment = new PaymentApiService(config.paymentBaseURL());
    private UserApiServices userApiServices = new UserApiServices(config.authBaseURL());
    private String authToken;

    @BeforeAll
    public void setup() {
        Allure.step("Создание и регистрация тестового пользователя для оплаты", () -> {
            Faker faker = new Faker();
            String email = faker.internet().emailAddress();
            String password = "Test123!";
            String login = email.split("@")[0];

            UserPayload newUser = new UserPayload()
                    .login(login)
                    .email(email)
                    .fullName(faker.name().fullName())
                    .password(password)
                    .passwordRepeat(password);

            userApiServices.registerUser(newUser).shouldHave(Conditions.statusCode(201));

            LoginPayload loginPayload = new LoginPayload()
                    .login(login)
                    .password(password);

            LoginResponse loginResponse = userApiServices.loginUser(loginPayload).asPojo(LoginResponse.class);
            this.authToken = loginResponse.getAccessToken();
            
            Allure.addAttachment("Токен авторизации", "text/plain", this.authToken.substring(0, 8) + "...");
        });
    }

    @Test
    @DisplayName("Успешная оплата фильма банковской картой")
    @Description("Тест проверяет, что авторизованный пользователь может создать платёж банковской картой с валидными данными и получить статус 201")
    public void testCanCreatePayment() {

        // 🔹 Объявляем переменные ДО шагов
        PaymentPayloadCard payloadCard = new PaymentPayloadCard()
                .cardNumber("4242424242424242")
                .cardHolder("John Doe")
                .expirationDate("12/26")
                .securityCode(123);

        PaymentPayload payload = new PaymentPayload()
                .movieId(900000002)
                .amount(1)
                .card(payloadCard);

        // 🟢 Шаг 1: Создание данных карты
        Allure.step("Создание платежной карты", () -> {
            Allure.addAttachment("Данные карты", "application/json", payloadCard.toString());
        });

        // 🟢 Шаг 2: Создание тела платежа
        Allure.step("Создание тела платежа", () -> {
            Allure.addAttachment("Тело платежа", "application/json", payload.toString());
        });

        //  Шаг 3: Отправка запроса
        Allure.step("Отправка платежа с токеном " + authToken.substring(0, 8) + "...", () -> {
            payment.payment(payload, authToken)
                    .shouldHave(Conditions.statusCode(201));
        });
    }
}
