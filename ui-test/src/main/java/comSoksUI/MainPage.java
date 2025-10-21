package comSoksUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Condition.interactable;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.clearBrowserCookies;


public class MainPage {
    public static MainPage open() {
       // System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver-win64_\\chromedriver.exe"); // For Windows
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        // 3. Передаём опции в Selenide
        Configuration.browserCapabilities = options;

        // 4. Открываем страницу
        System.out.println("Opening URL: https://dev-cinescope.t-qa.ru/login");
        clearBrowserCookies();
        Selenide.open("https://dev-cinescope.t-qa.ru/login");
try{
    Thread.sleep(1000);
}
catch (Exception e){}

        return new MainPage();
    }
    public void loginAs(String email, String password){

        Selenide.$("[data-qa-id='login_email_input']").click();

        Selenide.$("[data-qa-id='login_email_input']").setValue(email);
     Selenide.$("[data-qa-id='login_password_input']").setValue(password);
     Selenide.$("[data-qa-id=\"login_submit_button\"]").click();


    }
    }

