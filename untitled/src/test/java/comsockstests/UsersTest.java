package comsockstests;

import com.github.javafaker.Faker;
import comsocksapi.ProjectConfig;
import comsocksapi.assertions.AssertableResponse;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.LoginPayload;
import comsocksapi.payloads.UserPayload;
import comsocksapi.responses.ErrorRegisterResponse;
import comsocksapi.responses.LoginResponse;
import comsocksapi.responses.LoginUnauthorized;
import comsocksapi.responses.UserRegisterResponse;
import comsocksapi.services.UserApiServices;
import io.qameta.allure.Allure;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Locale;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsersTest {

    private UserApiServices userApiServices;
    private Faker faker;

    @BeforeAll
    public void setUp() {
        Allure.step("Инициализация конфигурации и сервисов", () -> {
            ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
            userApiServices = new UserApiServices(config.authBaseURL());
            faker = new Faker(new Locale(config.locale()));
        });
    }

    @Test
    public void testRegisterUser() {
        UserPayload user = new UserPayload()
                .email("annatesttestte88867675sttest@gmail.ru")
                .fullName(faker.name().fullName())
                .password("123hblernjQ")
                .passwordRepeat("123hblernjQ");

        Allure.step("Регистрация нового пользователя", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
        });

        AssertableResponse response = userApiServices.registerUser(user);

        Allure.step("Проверка статуса 201 и извлечение ID", () -> {
            response.soudHave(Conditions.statusCode(201)); // ← оставлено как есть

            UserRegisterResponse registerResponse = response.asPojo(UserRegisterResponse.class);
            Allure.addAttachment("Ответ сервера", "application/json", registerResponse.toString());
            Allure.addAttachment("ID пользователя", registerResponse.getId().toString());
        });
    }

    @Test
    public void testCanNotRegisterSameUserTwice() {
        UserPayload user = new UserPayload()
                .email("ara.feest@gmail.com")
                .fullName(faker.name().fullName())
                .password("123hblernjQ")
                .passwordRepeat("123hblernjQ");

        Allure.step("Попытка повторной регистрации существующего пользователя", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
        });

        AssertableResponse response = userApiServices.registerUser(user);

        Allure.step("Проверка статуса 409 (конфликт)", () -> {
            response.soudHave(Conditions.statusCode(409)); // ← оставлено как есть

            ErrorRegisterResponse errorResponse = response.asPojo(ErrorRegisterResponse.class);
            Allure.addAttachment("Ответ об ошибке", "application/json", errorResponse.toString());
        });
    }

    @Test
    public void testConfirm() {
        String userToken = "a62a85a0-1ed6-430d-bbf5-3b3810413bb5";

        Allure.step("Подтверждение пользователя с токеном: " + userToken, () -> {
            AssertableResponse response = userApiServices.confirmUser(userToken);
            response.soudHave(Conditions.statusCode(400)); // ← оставлено как есть

            Allure.addAttachment("Статус ответа", "text/plain", "400 Bad Request");
        });
    }

    @Test
    public void userLogin() {
        LoginPayload user = new LoginPayload()
                .email("annatesttesttesttest@gmail.ru")
                .password("123hblernjQ");

        Allure.step("Авторизация существующего пользователя", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
        });

        AssertableResponse response = userApiServices.loginUser(user);

        Allure.step("Проверка успешного входа (статус 200)", () -> {
            response.soudHave(Conditions.statusCode(200)); // ← оставлено как есть

            LoginResponse loginResponse = response.asPojo(LoginResponse.class);
            Allure.addAttachment("Ответ сервера", "application/json", loginResponse.toString());
            Allure.addAttachment("Access Token", loginResponse.getAccessToken());
        });
    }

    @Test
    public void userLogin401() {
        LoginPayload user = new LoginPayload()
                .email("aozerova17234@gmail.com")
                .password("123hblernjQ");

        Allure.step("Попытка входа с неверными данными", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
        });

        AssertableResponse response = userApiServices.loginUser(user);

        Allure.step("Проверка ошибки 400", () -> {
            response.soudHave(Conditions.statusCode(400)); // ← оставлено как есть

            LoginUnauthorized error = response.asPojo(LoginUnauthorized.class);
            Allure.addAttachment("Ответ об ошибке", "application/json", error.toString());
        });
    }
}