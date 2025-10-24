package comSoksUITest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import comSoksUI.CatalogPage;
import comSoksUI.MainPage;
import comSoksUI.MovePaymentPage;
import comSoksUI.SuccessfulPaymentPage;
import comsocksapi.payloads.LoginPayload;
import comsocksapi.payloads.PaymentPayload;
import comsocksapi.payloads.PaymentPayloadCard;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ShoppingCardTest extends BaseUITest{

    @Test
    public void userCanPayToCart(){

        //создание объекта карты
        PaymentPayloadCard payloadCard = new PaymentPayloadCard()
                .cardNumber("4242424242424242")
                .cardHolder("dfgdfg hfghf")
                .expirationDate("12/25")
                .securityCode(123)
                .expirationMonth("12")
                .expirationYear("25");

        PaymentPayload payload = new PaymentPayload()
                .movieId(86)
                .amount(1)
                .card(payloadCard);

        //создание объеста юзер и авторизация
        LoginPayload user = new LoginPayload()
                .email("annatesttesttesttest@gmail.ru")
                .password("123hblernjQ");

        MainPage.open()
                .loginAs(user.email(), user.password());
        try
        {

            Thread.sleep(2000);

        }
        catch (Exception e){

        }
        //покупка фильма с объектом карты
       // CatalogPage.open()
        at(CatalogPage.class)
                .goToMovePage()
               .goToMovePaymentPage()
               .filligOutTheFormPayment(payload.amount(), payload.card().cardNumber(), payload.card().cardHolder(), payload.card().expirationMonth(), payload.card().expirationYear(),  payload.card().securityCode());

        MovePaymentPage.createPayment();
        SuccessfulPaymentPage.notificationAppearance();



    }
}
