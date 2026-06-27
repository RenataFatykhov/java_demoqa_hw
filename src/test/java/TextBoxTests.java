import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests extends TestBase {

    String userName;
    String userEmail;
    String currentAddress;
    String permanentAddress;


    @BeforeEach
    public void setUp() {
        userName = "Renata Fatykhova";
        userEmail = "renata.fatykhova@gmail.com";
        currentAddress = "Test Street one";
        permanentAddress = "Test Street two";


    }

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFullTextBoxTest() {
        open("/text-box");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue(userEmail);
        $("[id=currentAddress]").setValue("Test Street one");
        $("[id=permanentAddress]").setValue("Test Street two");
        $("[id=submit]").click();

        $("[id=name]").shouldHave(text(userName));
        $("[id=email]").shouldHave(text(userEmail));
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

        $("[id=userName]").setValue(userName);
        $("[id=userEmail]").setValue("invalid.email");
        $("[id=currentAddress]").setValue("Test Street one");
        $("[id=permanentAddress]").setValue("Test Street two");
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave((cssValue("border", "1px solid rgb(255, 0, 0)")));
    }
}
