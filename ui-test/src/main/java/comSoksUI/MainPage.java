package comSoksUI;

import com.codeborne.selenide.Selenide;


public class MainPage {
    public static MainPage open() {
        Selenide.open("https://dev-cinescope.t-qa.ru");
        return new MainPage();
    }
    public void loginAs(String username, String password){
     Selenide.$("#login > a").click();
     Selenide.$("#username-modal").setValue(username);
     Selenide.$("#password-modal").setValue(password);
     Selenide.$("#login-modal p button").click();
    }
}
