package comSoksUI;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class SuccessfulPaymentPage {

    public static SuccessfulPaymentPage notificationAppearance() {

        // Проверка, что уведомление появилось и содержит нужный текст
        $("div[role='status']")
                .shouldBe(visible)
                .shouldHave(text("Оплата прошла успешно"));

        // Опционально: проверить, что элемент исчезает (если он удаляется из DOM)
        $("div[role='status']").shouldNot(exist);
        return page(SuccessfulPaymentPage .class);
    }

}
