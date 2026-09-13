package comsockstests;

import comsocksapi.ProjectConfig;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;
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
        Allure.step("Получение токена авторизации", () -> {
            authToken = userApiServices.getAuthToken(config.paymentEmail(), config.paymentPassword());
            Allure.addAttachment("Токен авторизации", "text/plain", authToken);
        });
    }

    @Test
    @DisplayName("Успешная оплата фильма банковской картой")
    @Description("Тест проверяет, что авторизованный пользователь может создать платёж банковской картой с валидными данными и получить статус 201")
    public void testCanCreatePayment() {


        // 🔹 Объявляем переменные ДО шагов
        PaymentPayloadCard payloadCard = new PaymentPayloadCard()
                .cardNumber("4242424242424242")
                .cardHolder("dfgdfg hfghf")
                .expirationDate("12/25")
                .securityCode(123);

        PaymentPayload payload = new PaymentPayload()
                .movieId(2143)
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

        // 🟢 Шаг 3: Отправка запроса
        Allure.step("Отправка платежа с токеном " + authToken.substring(0, 8) + "...", () -> {
            payment.payment(payload, authToken)
                    .shouldHave(Conditions.statusCode(201));

            Allure.addAttachment("Заголовки запроса", "text/plain", "Authorization: Bearer " + authToken);
        });
    }
}