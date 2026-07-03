package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    // Elements
    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement userCurrentAddressInput = $("#currentAddress");
    private final SelenideElement userPermanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement border = $(".border");

    // Actions
    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    public TextBoxPage preparePage() {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);
        return this;
    }

    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }

    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    public TextBoxPage typeCurrentAddress(String value) {
        userCurrentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage typePermanentAddress(String value) {
        userPermanentAddressInput.setValue(value);
        return this;
    }

    public TextBoxPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public TextBoxPage checkUserNameField(String key, String value) {
        $(byId(key)).shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkUserEmailField(String key, String value) {
        $(byId(key)).shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkCurrentAddressField(String value) {
        border.find("#currentAddress").shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkPermanentAddressField(String value) {
        border.find("#permanentAddress").shouldHave(text(value));
        return this;
    }

    public TextBoxPage checkUserEmailBorder() {
        userEmailInput.shouldHave((cssValue("border", "1px solid rgb(222, 226, 230)")));
        return this;
    }

}
