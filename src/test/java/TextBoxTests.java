import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {
    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFullTextBoxTest() {
        open("/text-box");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=userName]").setValue("Renata Fatykhova");
        $("[id=userEmail]").setValue("ren.fatykhova@gmail.com");
        $("[id=currentAddress]").setValue("Test Street one");
        $("[id=permanentAddress]").setValue("Test Street two");
        $("[id=submit]").click();

        $("[id=name]").shouldHave(text("Renata Fatykhova"));
        $("[id=email]").shouldHave(text("ren.fatykhova@gmail.com"));
        $(".border [id=currentAddress]").shouldHave(text("Test Street one"));
        $(".border [id=permanentAddress]").shouldHave(text("Test Street two"));
    }

    @Test
    @DisplayName("Ввод невалидного email")
    public void invalidEmailTextBoxTest() {
        open("/text-box");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=userName]").setValue("Renata Fatykhova");
        $("[id=userEmail]").setValue("invalid.email");
        $("[id=currentAddress]").setValue("Test Street one");
        $("[id=permanentAddress]").setValue("Test Street two");
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave((cssValue("border", "1px solid rgb(255, 0, 0)")));
    }
}
