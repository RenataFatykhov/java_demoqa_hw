import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;
import testdata.TestData;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class StudentRegistrationFormTests extends TestBase {
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFillAllFiledsFormTest() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            studentRegistrationFormPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            studentRegistrationFormPage
                    .typeName(data.name)
                    .typeSurname(data.surname)
                    .typeEmail(data.email)
                    .setGender(data.gender)
                    .typeUserNumber(data.phoneNumber)
                    .setDateOfBirth(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                    .typeSubject(data.subjects)
                    .setHobbies(data.hobbies)
                    .uploadPicture(data.imgText)
                    .typeAddress(data.address)
                    .setStateAndCity(data.state, data.city)
                    .clickSubmitButton();
        });

        step("Проверка введенных данных на модальном окне", () -> {
            studentRegistrationFormPage
                    .checkVisibleModal()
                    .checkMessage(data.successfulMessage)
                    .checkResultTable("Student Name", data.name + " " + data.surname)
                    .checkResultTable("Student Email", data.email)
                    .checkResultTable("Gender", data.gender)
                    .checkResultTable("Mobile", data.phoneNumber)
                    .checkResultTable("Date of Birth", data.dayOfBirth + " " + data.monthOfBirth + "," + data.yearOfBirth)
                    .checkResultTable("Subjects", data.subjects)
                    .checkResultTable("Hobbies", data.hobbies)
                    .checkResultTable("Picture", data.imgText)
                    .checkResultTable("Address", data.address)
                    .checkResultTable("State and City", data.state + " " + data.city)
                    .checkStateModalButton();
        });


    }

    @Test
    @DisplayName("Заполнение только обязательных полей формы")
    public void successfulFillOnlyRequiredFieldsFormTest() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            studentRegistrationFormPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            studentRegistrationFormPage
                    .typeName(data.name)
                    .typeSurname(data.surname)
                    .setGender(data.gender)
                    .typeUserNumber(data.phoneNumber)
                    .setDateOfBirth(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                    .clickSubmitButton();
        });

        step("Проверка введенных данных на модальном окне", () -> {
            studentRegistrationFormPage
                    .checkVisibleModal()
                    .checkMessage(data.successfulMessage)
                    .checkResultTable("Student Name", data.name + " " + data.surname)
                    .checkEmptyResultTable("Student Email")
                    .checkResultTable("Gender", data.gender)
                    .checkResultTable("Mobile", data.phoneNumber)
                    .checkResultTable("Date of Birth", data.dayOfBirth + " " + data.monthOfBirth + "," + data.yearOfBirth)
                    .checkEmptyResultTable("Subjects")
                    .checkEmptyResultTable("Hobbies")
                    .checkEmptyResultTable("Picture")
                    .checkEmptyResultTable("Address")
                    .checkEmptyResultTable("State and City")
                    .checkStateModalButton();
        });

    }

    @Test
    @DisplayName("Отправка пустой формы")
    public void sendEmptyFormTest() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            studentRegistrationFormPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            studentRegistrationFormPage
                    .clickSubmitButton();
        });

        step("Проверка введенных данных на модальном окне", () -> {
            studentRegistrationFormPage
                    .checkNotVisibleModal()
                    .checkStateForm(
                            "border-color",
                            "rgb(220, 53, 69)",
                            data.gender
                    );
        });
    }

    @Test
    @DisplayName("Ввод недопустимого количества символов в поле Number")
    public void sendInvalidMobileNumberInFormTest() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            studentRegistrationFormPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            studentRegistrationFormPage
                    .typeName(data.name)
                    .typeSurname(data.surname)
                    .setGender(data.gender)
                    .typeUserNumber(data.invalidPhoneNumber)
                    .setDateOfBirth(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                    .clickSubmitButton();
        });

        step("Проверка состояния модального окна", () -> {
            studentRegistrationFormPage
                    .checkNotVisibleModal();
        });

    }

    @Test
    @DisplayName("В группе радиобатонов 'Gender' можно выбрать только один вариант")
    public void onlyOneGenderCanBeSelectedAtATime() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            studentRegistrationFormPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            studentRegistrationFormPage
                    .typeName(data.name)
                    .typeSurname(data.surname)
                    .setGender(data.genderM)
                    .setGender(data.genderF);
        });

        step("Проверка работы радиобатонов", () -> {
            studentRegistrationFormPage
                    .checkStateOfGenderRadio(data.genderM, data.genderF);
        });

    }

    @Test
    @DisplayName("Модальное окно исчезает после нажатия на кнопку закрытия")
    public void modalWindowDisappearsAfterClosing() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            studentRegistrationFormPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            studentRegistrationFormPage
                    .typeName(data.name)
                    .typeSurname(data.surname)
                    .setGender(data.gender)
                    .typeUserNumber(data.phoneNumber)
                    .setDateOfBirth(data.dayOfBirth, data.monthOfBirth, data.yearOfBirth)
                    .clickSubmitButton();
        });

        step("Проверка состояния модального окна", () -> {
            studentRegistrationFormPage
                    .checkVisibleModal()
                    .closeModal()
                    .checkStateModalButton()
                    .checkNotVisibleModal();
        });


    }
}
