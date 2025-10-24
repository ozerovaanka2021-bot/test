package comSoksUI;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MovePage {
 //   public static MovePage open() {
//        Selenide.open("https://dev-cinescope.t-qa.ru/movies");
   //     return page(MovePage.class);
  //  }

    public MovePaymentPage goToMovePaymentPage () {
        $("button:has(svg.lucide-shopping-cart)").click();
        return page(MovePaymentPage.class);
    }
}