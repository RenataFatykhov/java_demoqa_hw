package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage {

    // Elements
    CalendarComponent calendar = new CalendarComponent();
    ResultTableComponent table = new ResultTableComponent();

    private final SelenideElement nameInput = $("#firstName");
    private final SelenideElement surnameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderRadio = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectInput = $(".subjects-auto-complete__input");
    private final SelenideElement hobbiesCheckbox = $("#hobbiesWrapper");
    private final SelenideElement imageUploader = $("#uploadPicture");
    private final SelenideElement addressInput = $("#currentAddress");
    private final SelenideElement citySelect = $("#react-select-4-input");
    private final SelenideElement stateSelect = $("#react-select-3-input");
    private final SelenideElement resultModal = $(".modal-content");
    private final SelenideElement correctMessage = $("#example-modal-sizes-title-lg");
    private final SelenideElement modalButton = $("#closeLargeModal");

    // Actions
    public StudentRegistrationFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public StudentRegistrationFormPage preparePage() {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);
        return this;
    }

    public StudentRegistrationFormPage typeName(String value) {
        nameInput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typeSurname(String value) {
        surnameInput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage typeEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage setGender(String value) {
        genderRadio.$("[value=" + value + "]").click();
        return this;
    }

    public StudentRegistrationFormPage checkStateOfGenderRadio(String male, String female) {
        genderRadio.$("[value=" + male + "]").shouldNotBe(selected);
        genderRadio.$("[value=" + female + "]").shouldBe(selected);
        return this;
    }

    public StudentRegistrationFormPage typeUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public StudentRegistrationFormPage setDateOfBirth(int day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    public StudentRegistrationFormPage typeSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public StudentRegistrationFormPage setHobbies(String value) {
        hobbiesCheckbox.$(byText(value)).click();
        return this;
    }

    public StudentRegistrationFormPage uploadPicture(String value) {
        imageUploader.uploadFromClasspath(value);
        return this;
    }

    public StudentRegistrationFormPage typeAddress(String value) {
        addressInput.setValue(value).pressEnter();
        return this;
    }

    public StudentRegistrationFormPage setState(String value) {
        stateSelect.setValue(value).pressEnter();
        return this;
    }

    public StudentRegistrationFormPage setCity(String value) {
        citySelect.setValue(value).pressEnter();
        return this;
    }

    public StudentRegistrationFormPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);
        return this;
    }

    public StudentRegistrationFormPage clickSubmitButton() {
        executeJavaScript("arguments[0].click();", $("button[id=submit]"));
        return this;
    }

    public StudentRegistrationFormPage checkVisibleModal() {
        resultModal.shouldBe(visible);
        return this;
    }

    public StudentRegistrationFormPage checkNotVisibleModal() {
        resultModal.shouldNotBe(visible);
        return this;
    }

    public StudentRegistrationFormPage closeModal() {
        modalButton.click();
        return this;
    }

    public StudentRegistrationFormPage checkMessage(String value) {
        correctMessage.shouldHave(text(value));
        return this;
    }

    public StudentRegistrationFormPage checkStateForm(String border, String color, String female) {
        nameInput.shouldHave(cssValue(border, color));
        surnameInput.shouldHave(cssValue(border, color));
        genderRadio.$("[value=" + female + "]").shouldHave(cssValue(border, color));
        userNumberInput.shouldHave(cssValue(border, color));
        return this;
    }


    public StudentRegistrationFormPage checkResultTable(String key, String value) {
        table.checkRow(key, value);
        return this;
    }

    public StudentRegistrationFormPage checkEmptyResultTable(String key) {
        table.checkEmptyRow(key);
        return this;
    }

    public StudentRegistrationFormPage checkStateModalButton() {
        modalButton.shouldBe(Condition.clickable);
        return this;
    }


}
