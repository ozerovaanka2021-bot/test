package comSoksUITest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import comsocksapi.ProjectConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class BaseUITest {
    @BeforeSuite
    public static void setUp() {
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

        // API
        RestAssured.baseURI = config.loginBaseURL();

        // UI
        Configuration.baseUrl = config.loginBaseURL();
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;

        // WebDriver setup: локально или удалённо (Selenoid)
        if (config.remoteUrl().isBlank()) {
            WebDriverManager.chromedriver().setup();
        } else {
            Configuration.remote = config.remoteUrl();
        }

        // Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        if (config.headless()) {
            options.addArguments("--headless");
        }
        Configuration.browserCapabilities = options;
    }

    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }

    @AfterTest
    void tearDown() {
        Selenide.closeWebDriver();
    }
}