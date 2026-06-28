import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static testdata.TestData.*;
import static testdata.TestData.name;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFillAllFiledsFormTest() {

        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=userEmail]").setValue(email);
        $("[id=genterWrapper] [value=" + genderF + "]").click();
        $("[id=userNumber]").setValue(phoneNumber);

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--00" + dayOfBirth).click();

        $(".subjects-auto-complete__input").setValue(subjects);
        $(".subjects-auto-complete__option").click();
        $("[id=hobbiesWrapper]").$(byText(hobbies)).click();

        $("[id=uploadPicture]").uploadFromClasspath("for_test.jpeg");


        $("[id=currentAddress]").setValue(address);

        $("[id=react-select-3-input]").setValue(state).pressEnter();
        $("[id=react-select-4-input]").setValue(city).pressEnter();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text(successfulMessage));

        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(name + " " + surname));
        $(".table-responsive").$(byText("Student Email"))
                .parent().shouldHave(text(email));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genderF));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $(".table-responsive").$(byText("Date of Birth"))
                .parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbies));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("for_test.jpeg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(address));
        $(".table-responsive").$(byText("State and City"))
                .parent().shouldHave(text(state + " " + city));

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);

    }

    @Test
    @DisplayName("Заполнение только обязательных полей формы")
    public void successfulFillOnlyRequiredFieldsFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + genderF + "]").click();
        $("[id=userNumber]").setValue(phoneNumber);

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--00" + dayOfBirth).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text(successfulMessage));

        $(".table-responsive").$(byText("Student Name")).parent().
                shouldHave(text(name + " " + surname));
        $(".table-responsive").$(byText("Student Email")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genderF));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $(".table-responsive").$(byText("Date of Birth")).
                parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Hobbies")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Picture")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Address")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("State and City")).sibling(0).shouldBe(empty);

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
    }

    @Test
    @DisplayName("Отправка пустой формы")
    public void sendEmptyFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(not(visible));
        $("[id=firstName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=lastName]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=genterWrapper] [value=Female]").shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
        $("[id=userNumber]").shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
    }

    @Test
    @DisplayName("Ввод недопустимого количества символов в поле Number")
    public void sendInvalidMobileNumberInFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + genderF + "]").click();
        $("[id=userNumber]").setValue(invalidPhoneNumber); // more than 10 digits

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--00" + dayOfBirth).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text(successfulMessage));

        $(".table-responsive").$(byText("Student Name")).parent().
                shouldHave(text(name + " " + surname));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genderF));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(phoneNumber));
        $(".table-responsive").$(byText("Date of Birth")).
                parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
    }

    @Test
    @DisplayName("В группе радиобатонов 'Gender' можно выбрать только один вариант")
    public void onlyOneGenderCanBeSelectedAtATime() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + genderM + "]").click();
        $("[id=genterWrapper] [value=" + genderF + "]").click();

        $("[id=genterWrapper] [value=" + genderM + "]").shouldBe(not(focused));
        $("[id=genterWrapper] [value=" + genderF + "]").shouldBe(focused);
    }

    @Test
    @DisplayName("Модальное окно исчезает после нажатия на кнопку закрытия")
    public void modalWindowDisappearsAfterClosing() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + genderF + "]").click();
        $("[id=userNumber]").setValue(phoneNumber);

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--00" + dayOfBirth).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));
        $("[id=closeLargeModal]").click();

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
        $(".modal-content").shouldBe(not(visible));
    }
}
