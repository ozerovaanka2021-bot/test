package comSoksUI;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;

public class MainPage {
    private SelenideElement emailInput = Selenide.$("[data-qa-id='login_email_input']");
    private SelenideElement passwordInput = Selenide.$("[data-qa-id='login_password_input']");
    private SelenideElement submitButton = Selenide.$("[data-qa-id='login_submit_button']");

    public static MainPage open() {
        clearBrowserCookies();
        Selenide.open("/login"); // относительный путь, baseUrl из конфига
        // Ждём, что страница загрузилась и поле email доступно
        Selenide.$("[data-qa-id='login_email_input']").shouldBe(interactable);
        return new MainPage();
    }

    public void loginAs(String email, String password) {
        emailInput.setValue(email);
        passwordInput.setValue(password);
        submitButton.click();
    }
}