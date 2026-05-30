import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    public void successfulStudentRegistrationFormTest() {

        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=userEmail]").setValue("ren.fatykhova@gmail.com");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        $(".subjects-auto-complete__input").setValue("Maths");
        $(".subjects-auto-complete__option").click();
        $("[id=hobbies-checkbox-2]").click();

        File imageFile = new File("/Users/renatamusenova/Downloads/for_test.jpeg");
        $("[id=uploadPicture]").uploadFile(imageFile);


        $("[id=currentAddress]").setValue("The best street 2");

        $("[id=react-select-3-input]").setValue("NCR").pressEnter();
        $("[id=react-select-4-input]").setValue("Delhi").pressEnter();

        $("button[id=submit]").scrollTo().click();


        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $("tbody tr:nth-child(1) td:nth-child(1)").shouldHave(text("Student Name"));
        $("tbody tr:nth-child(1) td:nth-child(2)").shouldHave(text("Renata Fatykhova"));

        $("tbody tr:nth-child(2) td:nth-child(1)").shouldHave(text("Student Email"));
        $("tbody tr:nth-child(2) td:nth-child(2)").shouldHave(text("ren.fatykhova@gmail.com"));

        $("tbody tr:nth-child(3) td:nth-child(1)").shouldHave(text("Gender"));
        $("tbody tr:nth-child(3) td:nth-child(2)").shouldHave(text("Female"));

        $("tbody tr:nth-child(4) td:nth-child(1)").shouldHave(text("Mobile"));
        $("tbody tr:nth-child(4) td:nth-child(2)").shouldHave(text("1234567890"));

        $("tbody tr:nth-child(5) td:nth-child(1)").shouldHave(text("Date of Birth"));
        $("tbody tr:nth-child(5) td:nth-child(2)").shouldHave(text("4 November,1997"));

        $("tbody tr:nth-child(6) td:nth-child(1)").shouldHave(text("Subjects"));
        $("tbody tr:nth-child(6) td:nth-child(2)").shouldHave(text("Maths"));

        $("tbody tr:nth-child(7) td:nth-child(1)").shouldHave(text("Hobbies"));
        $("tbody tr:nth-child(7) td:nth-child(2)").shouldHave(text("Reading"));

        $("tbody tr:nth-child(8) td:nth-child(1)").shouldHave(text("Picture"));
        $("tbody tr:nth-child(8) td:nth-child(2)").shouldHave(text("for_test.jpeg"));

        $("tbody tr:nth-child(9) td:nth-child(1)").shouldHave(text("Address"));
        $("tbody tr:nth-child(9) td:nth-child(2)").shouldHave(text("The best street 2"));

        $("tbody tr:nth-child(10) td:nth-child(1)").shouldHave(text("State and City"));
        $("tbody tr:nth-child(10) td:nth-child(2)").shouldHave(text("NCR Delhi"));

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);

    }

    @Test
    public void successfulStudentRegistrationFormOnlyRequiredFiledsTest() {
        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        $("button[id=submit]").scrollTo().click();

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $("tbody tr:nth-child(1) td:nth-child(1)").shouldHave(text("Student Name"));
        $("tbody tr:nth-child(1) td:nth-child(2)").shouldHave(text("Renata Fatykhova"));

        $("tbody tr:nth-child(2) td:nth-child(1)").shouldHave(text("Student Email"));
        $("tbody tr:nth-child(2) td:nth-child(2)").shouldBe(empty);

        $("tbody tr:nth-child(3) td:nth-child(1)").shouldHave(text("Gender"));
        $("tbody tr:nth-child(3) td:nth-child(2)").shouldHave(text("Female"));

        $("tbody tr:nth-child(4) td:nth-child(1)").shouldHave(text("Mobile"));
        $("tbody tr:nth-child(4) td:nth-child(2)").shouldHave(text("1234567890"));

        $("tbody tr:nth-child(5) td:nth-child(1)").shouldHave(text("Date of Birth"));
        $("tbody tr:nth-child(5) td:nth-child(2)").shouldHave(text("4 November,1997"));

        $("tbody tr:nth-child(6) td:nth-child(1)").shouldHave(text("Subjects"));
        $("tbody tr:nth-child(6) td:nth-child(2)").shouldBe(empty);

        $("tbody tr:nth-child(7) td:nth-child(1)").shouldHave(text("Hobbies"));
        $("tbody tr:nth-child(7) td:nth-child(2)").shouldBe(empty);

        $("tbody tr:nth-child(8) td:nth-child(1)").shouldHave(text("Picture"));
        $("tbody tr:nth-child(8) td:nth-child(2)").shouldBe(empty);

        $("tbody tr:nth-child(9) td:nth-child(1)").shouldHave(text("Address"));
        $("tbody tr:nth-child(9) td:nth-child(2)").shouldBe(empty);

        $("tbody tr:nth-child(10) td:nth-child(1)").shouldHave(text("State and City"));
        $("tbody tr:nth-child(10) td:nth-child(2)").shouldBe(empty);

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
    }
    @Test
    public void emptyStudentRegistrationFormTest() {
        $("button[id=submit]").scrollTo().click();

        $(".modal-content").shouldBe(not(visible));
        $("[id=firstName]").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $("[id=lastName]").shouldHave(cssValue("border-color","rgb(220, 53, 69)"));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
        $("[id=userNumber]").shouldHave(cssValue("border-color",
                "rgb(220, 53, 69)"));
    }
    @Test
    public void invalidMobileNumberInStudentRegistrationFormTest() {
        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("12345678901"); // more than 10 digits

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        $("button[id=submit]").scrollTo().click();

        $(".modal-content").shouldBe(visible);
        $("[id=example-modal-sizes-title-lg]").shouldHave(text("Thanks for submitting the form"));

        $("tbody tr:nth-child(1) td:nth-child(1)").shouldHave(text("Student Name"));
        $("tbody tr:nth-child(1) td:nth-child(2)").shouldHave(text("Renata Fatykhova"));

        $("tbody tr:nth-child(3) td:nth-child(1)").shouldHave(text("Gender"));
        $("tbody tr:nth-child(3) td:nth-child(2)").shouldHave(text("Female"));

        $("tbody tr:nth-child(4) td:nth-child(1)").shouldHave(text("Mobile"));
        $("tbody tr:nth-child(4) td:nth-child(2)").shouldHave(text("1234567890"));

        $("tbody tr:nth-child(5) td:nth-child(1)").shouldHave(text("Date of Birth"));
        $("tbody tr:nth-child(5) td:nth-child(2)").shouldHave(text("4 November,1997"));

        $("[id=closeLargeModal]").shouldBe(Condition.clickable);
    }
    @Test
    public void someGendersInStudentRegistrationFormTest() {
        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=gender-radio-1]").click();
        $("[id=gender-radio-2]").click();

        $("[id=gender-radio-1]").shouldHave
                (cssValue("background-color", "rgba(255, 255, 255, 1)"));
        $("[id=gender-radio-2]").shouldHave
                (cssValue("background-color", "rgba(13, 110, 253, 1)"));
    }
    @Test
    public void noModalAfterStudentRegistrationFormTest() {
        $("[id=firstName]").setValue("Renata");
        $("[id=lastName]").setValue("Fatykhova");
        $("[id=gender-radio-2]").click();
        $("[id=userNumber]").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();

        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1997");
        $(".react-datepicker__day.react-datepicker__day--004").click();

        $("button[id=submit]").scrollTo().click();
        $("[id=closeLargeModal]").click();

        $(".modal-content").shouldBe(not(visible));
    }
    }
