package comSoksUITest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import comSoksUI.MainPage;
import comsocksapi.ProjectConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class BaseUITest {
    @BeforeSuite
    public void setUp() {
        // MainPage.open();
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, System.getProperties());

        RestAssured.baseURI = config.loginBaseURL();
        Configuration.baseUrl = config.loginBaseURL();

    }

    protected <T> T at(Class<T> pageClass) {
        return Selenide.page(pageClass);
    }

    @AfterTest
    void tearDown() {
        Selenide.closeWebDriver();
    }
}
