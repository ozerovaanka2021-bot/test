package comSoksUI;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CatalogPage {
    public static CatalogPage open() {
        Selenide.open("https://dev-cinescope.t-qa.ru/movies");
        //String name = "refresh_token";
        //getWebDriver().manage().addCookie(new Cookie(name, refreshToken));
        return page(CatalogPage.class);
    }

    public MovePage goToMovePage(){
        String number = "movie_more_86";
        $("a[data-qa-id=\""+number+"\"] button").click();
        return page(MovePage.class);
    }

}
