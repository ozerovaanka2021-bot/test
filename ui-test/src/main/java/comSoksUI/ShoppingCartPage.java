package comSoksUI;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;


public class ShoppingCartPage {
    @Step
    public SelenideElement totalAmount(){return $("#orderGrandTotal");}

    @Step
    public static ShoppingCartPage open(){
        Selenide.open("");
        return new ShoppingCartPage();
    }

    @Step
    public ShoppingCartPage deleteItem(){
        $("#card-list > tr > td:nth-child(8) > a > i").click();
        return this;
    }
}
