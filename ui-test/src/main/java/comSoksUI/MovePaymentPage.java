package comSoksUI;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class MovePaymentPage {


    @Step
    public MovePaymentPage filligOutTheFormPayment(int amount, String cardNumber, String cardHolder, String expirationMonth, String expirationYear, int securityCode){

        Selenide.$("[data-qa-id=\"payment_amount_input\"]").click();
        Selenide.$("[data-qa-id=\"payment_amount_input\"]").setValue(String.valueOf(amount));
        Selenide.$("[data-qa-id=\"payment_card_number_input\"]").click();
        Selenide.$("[data-qa-id=\"payment_card_number_input\"]").setValue(String.valueOf(cardNumber));
        Selenide.$("[data-qa-id=\"payment_card_holder_input\"]").click();
        Selenide.$("[data-qa-id=\"payment_card_holder_input\"]").setValue(String.valueOf(cardHolder));
        Selenide.$("[data-qa-id=\"payment_card_month_select\"]").click();
        //div[role='option']:nth-child(12)
        Selenide.$("div[role='option']:nth-child("+expirationMonth+")").click();
        Selenide.$("[data-qa-id=\"payment_card_year_select\"]").click();
        Selenide.$$("div[role='option']").findBy(text(expirationYear)).click();
        Selenide.$("[data-qa-id=\"payment_card_cvc_input\"]").setValue(String.valueOf(securityCode));
        Selenide.$("[data-qa-id=\"payment_card_cvc_input\"]").click();
    return page(MovePaymentPage.class);


    }
    @Step
    public static MovePaymentPage createPayment(){
        $("[data-qa-id=\"payment_submit_button\"]").click();
        return page(MovePaymentPage.class);
    }

}
