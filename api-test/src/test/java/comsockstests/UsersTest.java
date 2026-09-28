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

import static io.restassured.RestAssured.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class
    UsersTest {

    private UserApiServices userApiServices;
    private Faker faker;
    private ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());


    @BeforeAll
    public void setUp() {
        Allure.step("Инициализация конфигурации и сервисов", () -> {
            ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
            userApiServices = new UserApiServices(config.authBaseURL());
            faker = new Faker(new Locale(config.locale("ru")));
        });
    }

    @Test
    public void testRegisterUser() {
        String uuid = faker.internet().uuid();          // уникальный id от Faker
        String login = "user" + uuid.substring(0, 8);   // логин из него
        String email = uuid + "@example.com";           // email уникален между прогонами
        String password = "Super123!";

        UserPayload user = new UserPayload()
                .login(login)
                .email(email)
                .fullName(faker.name().fullName())
                .password(password)
                .passwordRepeat(password);

        Allure.step("Регистрация нового пользователя", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
        });

        AssertableResponse response = userApiServices.registerUser(user);

        Allure.step("Проверка статуса 201 и извлечение ID", () -> {
            response.shouldHave(Conditions.statusCode(201));

            UserRegisterResponse registerResponse = response.asPojo(UserRegisterResponse.class);
            Allure.addAttachment("Ответ сервера", "application/json", registerResponse.toString());
            Allure.addAttachment("ID пользователя", registerResponse.getId().toString());
        });
    }

    @Test
    public void testCanNotRegisterSameUserTwice() {
        String uuid = faker.internet().uuid();
        String login = "user" + uuid.substring(0, 8);
        String email = uuid + "@example.com";
        String password = "Super123!";

        UserPayload user = new UserPayload()
                .login(login)
                .email(email)
                .fullName(faker.name().fullName())
                .password(password)
                .passwordRepeat(password);

        Allure.step("Первичная регистрация того же пользователя (ожидаем 201)", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
            userApiServices.registerUser(user)
                    .shouldHave(Conditions.statusCode(201));
        });

        Allure.step("Повторная регистрация того же пользователя (ожидаем 409)", () -> {
            AssertableResponse second = userApiServices.registerUser(user);
            second.shouldHave(Conditions.statusCode(409));

            ErrorRegisterResponse errorResponse = second.asPojo(ErrorRegisterResponse.class);
            Allure.addAttachment("Ответ об ошибке", "application/json", errorResponse.toString());
        });
    }

    @Test
    public void userLogin() {
        // 1. Генерируем уникального пользователя
        String uuid = faker.internet().uuid();
        String login = "user" + uuid.substring(0, 8);
        String email = uuid + "@example.com";
        String password = "Super123!";

        UserPayload newUser = new UserPayload()
                .login(login)
                .email(email)
                .fullName(faker.name().fullName())
                .password(password)
                .passwordRepeat(password);

        Allure.step("Регистрация пользователя для теста логина", () -> {
            userApiServices.registerUser(newUser)
                    .shouldHave(Conditions.statusCode(201));
        });

        // 2. Логинимся только что созданным пользователем
        LoginPayload loginPayload = new LoginPayload()
                .email(email)
                .password(password);

        Allure.step("Авторизация только что созданного пользователя", () -> {
            Allure.addAttachment("Тело запроса", "application/json", loginPayload.toString());
        });

        AssertableResponse response = userApiServices.loginUser(loginPayload);

        Allure.step("Проверка успешного входа (статус 200)", () -> {
            response.shouldHave(Conditions.statusCode(200));

            LoginResponse loginResponse = response.asPojo(LoginResponse.class);
            Allure.addAttachment("Ответ сервера", "application/json", loginResponse.toString());
            Allure.addAttachment("Access Token", loginResponse.getAccessToken());
        });
    }

    @Test
    public void userLogin401() {
        LoginPayload user = new LoginPayload()
                .email(config.paymentEmail())
                .password("WrongPassword123!");   // ← заведомо неверный пароль

        Allure.step("Попытка входа с неверным паролем", () -> {
            Allure.addAttachment("Тело запроса", "application/json", user.toString());
        });

        AssertableResponse response = userApiServices.loginUser(user);

        Allure.step("Проверка ошибки 401 Unauthorized", () -> {
            response.shouldHave(Conditions.statusCode(401));

            LoginUnauthorized error = response.asPojo(LoginUnauthorized.class);
            Allure.addAttachment("Ответ об ошибке", "application/json", error.toString());
        });
    }
    }
