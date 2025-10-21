package comSoksUI;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.*;

public class CatalogPage {
    public static CatalogPage open() {
        Selenide.open("");
        return page(CatalogPage.class);
    }

    public CatalogPage addItemByIndex(int index) {
    $$("#products .product .buttons a.bin-primary").get(index).click();
    return this;
    }
    public ShoppingCartPage goToCart(){
        $("#numItemsInCart").click();
        return page(ShoppingCartPage.class);
    }
}
