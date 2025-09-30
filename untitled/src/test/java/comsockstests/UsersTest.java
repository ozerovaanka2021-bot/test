package comsockstests;

import com.github.javafaker.Faker;
import comsocksapi.conditions.Condition;
import comsocksapi.conditions.Conditions;
import comsocksapi.conditions.StatusCodeCondition;
import comsocksapi.responses.ErrorRegisterResponse;
import comsocksapi.responses.UserRegisterResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import comsocksapi.payloads.UserPayload;
import comsocksapi.services.UserApiServices;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.not;

public class UsersTest {

    private final UserApiServices userApiServices = new UserApiServices();
    private final Faker faker = new Faker();

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "https://auth.cinescope.krisqa.ru";

    }

    @Test
    public void testRegisterUser(){

            UserPayload user = new UserPayload()
                    .email(faker.name().username()+"@gmail.com")
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
}
