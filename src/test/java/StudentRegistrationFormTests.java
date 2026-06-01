import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulStudentRegistrationFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=userEmail]").setValue("ren.fatykhova@gmail.com");
        $("[id=genterWrapper] [value=Female]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        $(".subjects-auto-complete__input").setValue("Maths");
        $(".subjects-auto-complete__option").click();
        $("[id=hobbiesWrapper]").$(byText("Reading")).click();

        $("[id=uploadPicture]").uploadFromClasspath(String.valueOf("for_test.jpeg"));


        $("[id=currentAddress]").setValue("The best street 2");

        $("[id=react-select-3-input]").setValue("NCR").pressEnter();
        $("[id=react-select-4-input]").setValue("Delhi").pressEnter();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").$(byText("Student Name")).parent().
                shouldHave(text("Renata Fatykhova"));
        $(".table-responsive").$(byText("Student Email")).
                parent().shouldHave(text("ren.fatykhova@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth")).
                parent().shouldHave(text("4 November,1997"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("for_test.jpeg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("he best street 2"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);

    }

    @Test
    @DisplayName("Заполнение только обязательных полей формы")
    public void successfulOnlyRequiredFiledsStudentRegistrationFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=genterWrapper] [value=Female]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").$(byText("Student Name")).parent().
                shouldHave(text("Renata Fatykhova"));
        $(".table-responsive").$(byText("Student Email")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth")).
                parent().shouldHave(text("4 November,1997"));
        $(".table-responsive").$(byText("Subjects")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Hobbies")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Picture")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("Address")).sibling(0).shouldBe(empty);
        $(".table-responsive").$(byText("State and City")).sibling(0).shouldBe(empty);

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
    }
    @Test
    @DisplayName("Отправка пустой формы")
    public void emptyStudentRegistrationFormTest() {
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
    @Test
    @DisplayName("Ввод недопустимого количества символов в поле 'Mobile'")
    public void invalidMobileNumberStudentRegistrationFormTest() {
        open("/automation-practice-form");
        executeJavaScript("""
                document.getElementById('fixedban')?.remove();
                document.querySelector('footer')?.remove();
                document.querySelectorAll('[class*="ad"], [class*="banner"], iframe').forEach(el => el.remove());
                """);

        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=genterWrapper] [value=Female]").click();
        $("[id=userNumber]").setValue("12345678901"); // more than 10 digits

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $(".table-responsive").$(byText("Student Name")).parent().
                shouldHave(text("Renata Fatykhova"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Female"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("1234567890"));
        $(".table-responsive").$(byText("Date of Birth")).
                parent().shouldHave(text("4 November,1997"));

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

        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=genterWrapper] [value=Male]").click();
        $("[id=genterWrapper] [value=Female]").click();

        $("[id=genterWrapper] [value=Male]").shouldHave
                (cssValue("background-color", "rgba(255, 255, 255, 1)"));
        $("[id=genterWrapper] [value=Female]").shouldHave
                (cssValue("background-color", "rgba(13, 110, 253, 1)"));
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

        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=genterWrapper] [value=Female]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        executeJavaScript("arguments[0].click();", $("button[id=submit]"));
        $("[id=closeLargeModal]").click();

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
        $(".modal-content").shouldBe(not(visible));
    }
    }
