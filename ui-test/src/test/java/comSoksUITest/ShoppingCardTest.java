package comSoksUITest;

import com.codeborne.selenide.Condition;
import comSoksUI.CatalogPage;
import comSoksUI.ShoppingCartPage;
import org.testng.annotations.Test;

public class ShoppingCardTest extends BaseUITest{

    @Test
    public void userCanPayToCart(){
                CatalogPage.open()
                .addItemByIndex(0)
                .goToCart();

                at(ShoppingCartPage.class).totalAmount().shouldHave(Condition.exactText("100"));
    }
}
