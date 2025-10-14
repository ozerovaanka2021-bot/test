package comSoksUITest;


import comSoksUI.MainPage;
import comsocksapi.ProjectConfig;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.UserPayload;
import comsocksapi.responses.UserRegisterResponse;
import comsocksapi.services.UserApiServices;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;




public class TestLogin {


    private ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private final UserApiServices userApiServices = new UserApiServices(config.authBaseURL());
 @Test
    public void testRegisterUser(){

        UserPayload userPayload = new UserPayload()
                .email("annatesttesttesttest@gmail.ru")
                .fullName("Пыфвыв Авыаыв")
                .password("123hblernjQ")
                .passwordRepeat("123hblernjQ");

        UserRegisterResponse response =  userApiServices.registerUser(user)
                .soudHave(Conditions.statusCode(201)).asPojo(UserRegisterResponse.class);


        response.getId();
        MainPage.open()
                .loginAs(userPayload.username(), userPayload.pasword());
    }

    }
}
