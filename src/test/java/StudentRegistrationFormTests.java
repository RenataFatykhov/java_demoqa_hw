import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests extends TestBase {

    @CsvFileSource(resources = "/test_data/successfulFillAllFiledsFormTest.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Заполнение всех полей формы")
    public void successfulFillAllFiledsFormTest(
            String name,
            String surname,
            String email,
            String gender,
            String phoneNumber,
            int dayOfBirth,
            String monthOfBirth,
            String yearOfBirth,
            String subjects,
            String hobbies,
            String address,
            String state,
            String city,
            String successfulMessage
    ) {

        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=userEmail]").setValue(email);
        $("[id=genterWrapper] [value=" + gender + "]").click();
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
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
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

    @CsvFileSource(resources = "/test_data/onlyRequiredFields.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Заполнение только обязательных полей формы")
    public void successfulFillOnlyRequiredFieldsFormTest(
            String name,
            String surname,
            String gender,
            String phoneNumber,
            int dayOfBirth,
            String monthOfBirth,
            String yearOfBirth,
            String successfulMessage
    ) {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + gender +"]").click();
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
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
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
        $("[id=firstName]").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $("[id=lastName]").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $("[id=genterWrapper] [value=Female]").shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
        $("[id=userNumber]").shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
    }


    static Stream<Arguments> sendInvalidMobileNumberInFormTest(){
        return Stream.of(
                Arguments.of(
                        "Renata", "Fatykhova", "Female", "12345678901", 4, "November", "1997",
                        "Thanks for submitting the form", "1234567890"
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Ввод недопустимого количества символов в поле Number")
    public void sendInvalidMobileNumberInFormTest(
            String name,
            String surname,
            String gender,
            String phoneNumber,
            int dayOfBirth,
            String monthOfBirth,
            String yearOfBirth,
            String successfulMessage,
            String validPhoneNumber
    ) {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + gender + "]").click();
        $("[id=userNumber]").setValue(phoneNumber); // more than 10 digits

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day.react-datepicker__day--00" + dayOfBirth).click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text(successfulMessage));

        $(".table-responsive").$(byText("Student Name")).parent().
                shouldHave(text(name + " " + surname));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(validPhoneNumber));
        $(".table-responsive").$(byText("Date of Birth")).
                parent().shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
    }


    static Stream<Arguments> onlyOneGenderCanBeSelectedAtATime(){
        return Stream.of(
                Arguments.of(
                        "Renata", "Fatykhova", "Male", "Female"
                )
        );
    }

    @MethodSource
    @ParameterizedTest(name = "В группе радиобатонов 'Gender' можно выбрать только один вариант")
    public void onlyOneGenderCanBeSelectedAtATime(
            String name,
            String surname,
            String genderM,
            String genderF
    ) {
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


    @CsvSource(value = {
            "Renata, Fatykhova, Female, 1234567890, 4, November, 1997",
            "Renata, Fatykhova, Male, 1234567899, 5, November, 2007"
    })
    @ParameterizedTest(name = "Модальное окно исчезает после нажатия на кнопку закрытия")
    public void modalWindowDisappearsAfterClosing(
            String name,
            String surname,
            String gender,
            String phoneNumber,
            int dayOfBirth,
            String monthOfBirth,
            String yearOfBirth
    ) {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue(name);
        $("[id=lastName]").setValue(surname);
        $("[id=genterWrapper] [value=" + gender + "]").click();
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
