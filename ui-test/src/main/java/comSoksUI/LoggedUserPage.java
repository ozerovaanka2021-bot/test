package comSoksUI;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class LoggedUserPage {

    public SelenideElement profile(){return $("a[data-qa-id='profile_page_button']"); }
    public SelenideElement logoutBtn(){return $("button[data-qa-id='logout_button']"); }

}
