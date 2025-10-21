package comSoksUITest;


import com.codeborne.selenide.Selenide;
import comSoksUI.LoggedUserPage;
import comSoksUI.MainPage;
import comsocksapi.ProjectConfig;
import comsocksapi.conditions.Conditions;
import comsocksapi.payloads.UserPayload;
import comsocksapi.responses.UserRegisterResponse;
import comsocksapi.services.UserApiServices;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.Test;

import java.rmi.server.UID;

import static com.codeborne.selenide.Condition.text;


public class TestLogin extends  BaseUITest{


    private ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());
    private final UserApiServices userApiServices = new UserApiServices(config.authBaseURL());
 @Test
    public void testRegisterUser(){

        UserPayload userPayload = new UserPayload()
                .email(new UID().toString().replace(":", "").replace(":", "") + "tes09ttest@gmail.ru")
                .fullName("Пыфвыв Авыаыв")
                .password("123hblernjQ")
                .passwordRepeat("123hblernjQ");

        UserRegisterResponse response =  userApiServices.registerUser(userPayload)
                .soudHave(Conditions.statusCode(201)).asPojo(UserRegisterResponse.class);


        response.getId();
        MainPage.open()
                .loginAs(userPayload.email(), userPayload.password());
       LoggedUserPage loggedUserPage =  at(LoggedUserPage.class);


     loggedUserPage.profile().shouldHave(text("Профиль")).click();
       loggedUserPage.logoutBtn().shouldHave(text("Выход")).click();



     
    }

    }

