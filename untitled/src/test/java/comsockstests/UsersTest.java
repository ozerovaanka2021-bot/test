package comsockstests;

import com.github.javafaker.Faker;
import comsocksapi.ProjectConfig;
import comsocksapi.assertions.AssertableResponse;
import comsocksapi.conditions.Condition;
import comsocksapi.conditions.Conditions;
import comsocksapi.conditions.StatusCodeCondition;
import comsocksapi.payloads.LoginPayload;
import comsocksapi.responses.ErrorRegisterResponse;
import comsocksapi.responses.LoginResponse;
import comsocksapi.responses.LoginUnauthorized;
import comsocksapi.responses.UserRegisterResponse;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import comsocksapi.payloads.UserPayload;
import comsocksapi.services.UserApiServices;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Locale;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsersTest {

    private UserApiServices userApiServices;
    private Faker faker;

    @BeforeAll
    public void setUp(){

        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
        userApiServices = new UserApiServices(config.authBaseURL());
        faker = new Faker(new Locale(config.locale()));


    }

    @Test
    public void testRegisterUser(){

        UserPayload user = new UserPayload()
                .email("annatesttesttesttest@gmail.ru")
                .fullName(faker.name().fullName())
                .password("123hblernjQ")
                .passwordRepeat("123hblernjQ");

        UserRegisterResponse response =  userApiServices.registerUser(user)
                .soudHave(Conditions.statusCode(201))
                .asPojo(UserRegisterResponse.class);

        response.getId();

    }
    @Test
    public void testCanNotRegisterSameUserTwice(){
        UserPayload user = new UserPayload()
                .email("ara.feest@gmail.com")
                .fullName(faker.name().fullName())
                .password("123hblernjQ")
                .passwordRepeat("123hblernjQ");

        userApiServices.registerUser(user)
                .soudHave(Conditions.statusCode(409))
                .asPojo(ErrorRegisterResponse.class);


    }
    @Test
    public void testConfirm(){
        String userToken = "a62a85a0-1ed6-430d-bbf5-3b3810413bb5";
        userApiServices.confirmUser(userToken)
                .soudHave(Conditions.statusCode(400));
    }
    @Test
    public void userLogin(){
        LoginPayload user = new LoginPayload()
                .email("aozerova1234@gmail.com")
                .password("123hblernjQ");
        userApiServices.loginUser(user)
                .soudHave(Conditions.statusCode(200))
                .asPojo(LoginResponse.class);



    }
    @Test
    public void userLogin401(){
        LoginPayload user = new LoginPayload()
                .email("aozerova17234@gmail.com")
                .password("123hblernjQ");
        userApiServices.loginUser(user)
                .soudHave(Conditions.statusCode(400))
                .asPojo(LoginUnauthorized.class);
}
}
