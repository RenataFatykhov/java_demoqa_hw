package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.cssValue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.*;

public class TextBoxPage {
    private final SelenideElement userNameInput = $("#userName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement userCurrentAddressInput = $("#currentAddress");
    private final SelenideElement userPermanentAddressInput = $("#permanentAddress");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement border = $(".border");

    @Step("Открыть страницу /text-box")
    public TextBoxPage openPage() {
        open("/text-box");
        return this;
    }

    @Step("Подготовить страницу")
    public TextBoxPage preparePage() {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);
        return this;
    }

    @Step("Ввод имени \"{value}\"")
    public TextBoxPage typeUserName(String value) {
        userNameInput.setValue(value);
        return this;
    }

    @Step("Ввод email \"{value}\"")
    public TextBoxPage typeUserEmail(String value) {
        userEmailInput.setValue(value);
        return this;
    }

    @Step("Ввод current address \"{value}\"")
    public TextBoxPage typeCurrentAddress(String value) {
        userCurrentAddressInput.setValue(value);
        return this;
    }

    @Step("Ввод permanent address \"{value}\"")
    public TextBoxPage typePermanentAddress(String value) {
        userPermanentAddressInput.setValue(value);
        return this;
    }

    @Step("Клик по кнопке подтверждения")
    public TextBoxPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    @Step("Проверка поля \"{key}\" со значением \"{value}\"")
    public TextBoxPage checkUserNameField(String key, String value) {
        $(byId(key)).shouldHave(text(value));
        return this;
    }

    @Step("Проверка поля \"{key}\" со значением \"{value}\"")
    public TextBoxPage checkUserEmailField(String key, String value) {
        $(byId(key)).shouldHave(text(value));
        return this;
    }

    @Step("Проверка поля current address с \"{value}\"")
    public TextBoxPage checkCurrentAddressField(String value) {
        border.find("#currentAddress").shouldHave(text(value));
        return this;
    }

    @Step("Проверка поля permanent address с \"{value}\"")
    public TextBoxPage checkPermanentAddressField(String value) {
        border.find("#permanentAddress").shouldHave(text(value));
        return this;
    }

    @Step("Проверка, что поле ввода email подсветилось красным")
    public TextBoxPage checkUserEmailBorder() {
        userEmailInput.shouldHave((cssValue("border", "1px solid rgb(222, 226, 230)")));
        return this;
    }

}
