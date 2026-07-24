package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormPage {

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

    @Step("Открыть страницу /automation-practice-form")
    public StudentRegistrationFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step("Подготовить страницу")
    public StudentRegistrationFormPage preparePage() {
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);
        return this;
    }

    @Step("Ввод имени \"{value}\"")
    public StudentRegistrationFormPage typeName(String value) {
        nameInput.setValue(value);
        return this;
    }

    @Step("Ввод фамилии \"{value}\"")
    public StudentRegistrationFormPage typeSurname(String value) {
        surnameInput.setValue(value);
        return this;
    }

    @Step("Ввод email \"{value}\"")
    public StudentRegistrationFormPage typeEmail(String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step("Выбор gender \"{value}\"")
    public StudentRegistrationFormPage setGender(String value) {
        genderRadio.$("[value=" + value + "]").click();
        return this;
    }

    @Step("Определение состояния радиокнопок \"{male}\" и \"{female}\"")
    public StudentRegistrationFormPage checkStateOfGenderRadio(String male, String female) {
        genderRadio.$("[value=" + male + "]").shouldNotBe(selected);
        genderRadio.$("[value=" + female + "]").shouldBe(selected);
        return this;
    }

    @Step("Ввод номера телефона \"{value}\"")
    public StudentRegistrationFormPage typeUserNumber(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    @Step("Установка дня \"{day}\", месяца \"{month}\" и года рождения \"{year}\"")
    public StudentRegistrationFormPage setDateOfBirth(int day, String month, String year) {
        dateOfBirthInput.click();
        calendar.setDate(day, month, year);
        return this;
    }

    @Step("Ввод предмета \"{value}\"")
    public StudentRegistrationFormPage typeSubject(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Установка хобби \"{value}\"")
    public StudentRegistrationFormPage setHobbies(String value) {
        hobbiesCheckbox.$(byText(value)).click();
        return this;
    }

    @Step("Загрузка файла \"{value}\"")
    public StudentRegistrationFormPage uploadPicture(String value) {
        imageUploader.uploadFromClasspath(value);
        return this;
    }

    @Step("Ввод адреса \"{value}\"")
    public StudentRegistrationFormPage typeAddress(String value) {
        addressInput.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбор штата \"{value}\"")
    public StudentRegistrationFormPage setState(String value) {
        stateSelect.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбор города \"{value}\"")
    public StudentRegistrationFormPage setCity(String value) {
        citySelect.setValue(value).pressEnter();
        return this;
    }

    @Step("Выбор штата \"{state}\" и города \"{city}\"")
    public StudentRegistrationFormPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);
        return this;
    }

    @Step("Клик по кнопке подтверждения")
    public StudentRegistrationFormPage clickSubmitButton() {
        executeJavaScript("arguments[0].click();", $("button[id=submit]"));
        return this;
    }

    @Step("Проверка видимости модального окна: отображается")
    public StudentRegistrationFormPage checkVisibleModal() {
        resultModal.shouldBe(visible);
        return this;
    }

    @Step("Проверка видимости модального окна: не отображается")
    public StudentRegistrationFormPage checkNotVisibleModal() {
        resultModal.shouldNotBe(visible);
        return this;
    }

    @Step("Закрытие модального окна")
    public StudentRegistrationFormPage closeModal() {
        modalButton.click();
        return this;
    }

    @Step("Проверка наличия текста \"{value}\" об успехе заполнения формы")
    public StudentRegistrationFormPage checkMessage(String value) {
        correctMessage.shouldHave(text(value));
        return this;
    }

    @Step("Проверка состояния формы по рамке \"{border}\", цвету \"{color}\" и гендеру \"{female}\"")
    public StudentRegistrationFormPage checkStateForm(String border, String color, String female) {
        nameInput.shouldHave(cssValue(border, color));
        surnameInput.shouldHave(cssValue(border, color));
        genderRadio.$("[value=" + female + "]").shouldHave(cssValue(border, color));
        userNumberInput.shouldHave(cssValue(border, color));
        return this;
    }

    @Step("Проверка непустого поля с ключом \"{key}\" и знчением \"{value}\"")
    public StudentRegistrationFormPage checkResultTable(String key, String value) {
        table.checkRow(key, value);
        return this;
    }

    @Step("Проверка пустого значения в ключе \"{key}\"")
    public StudentRegistrationFormPage checkEmptyResultTable(String key) {
        table.checkEmptyRow(key);
        return this;
    }

    @Step("Проверка кликабельности кнопки закрытия модального окна")
    public StudentRegistrationFormPage checkStateModalButton() {
        modalButton.shouldBe(Condition.clickable);
        return this;
    }


}
