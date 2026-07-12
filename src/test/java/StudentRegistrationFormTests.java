import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.StudentRegistrationFormPage;

import static testdata.TestData.*;

public class StudentRegistrationFormTests extends TestBase {
    StudentRegistrationFormPage studentRegistrationFormPage = new StudentRegistrationFormPage();

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFillAllFiledsFormTest() {
        studentRegistrationFormPage
                .openPage()
                .preparePage()
                .typeName(name)
                .typeSurname(surname)
                .typeEmail(email)
                .setGender(gender)
                .typeUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .typeSubject(subjects)
                .setHobbies(hobbies)
                .uploadPicture(imgText)
                .typeAddress(address)
                .setStateAndCity(state, city)
                .clickSubmitButton()
                .checkVisibleModal()
                .checkMessage(successfulMessage)
                .checkResultTable("Student Name", name + " " + surname)
                .checkResultTable("Student Email", email)
                .checkResultTable("Gender", gender)
                .checkResultTable("Mobile", phoneNumber)
                .checkResultTable("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkResultTable("Subjects", subjects)
                .checkResultTable("Hobbies", hobbies)
                .checkResultTable("Picture", imgText)
                .checkResultTable("Address", address)
                .checkResultTable("State and City", state + " " + city)
                .checkStateModalButton();
    }

    @Test
    @DisplayName("Заполнение только обязательных полей формы")
    public void successfulFillOnlyRequiredFieldsFormTest() {
        studentRegistrationFormPage
                .openPage()
                .preparePage()
                .typeName(name)
                .typeSurname(surname)
                .setGender(gender)
                .typeUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .clickSubmitButton()
                .checkVisibleModal()
                .checkMessage(successfulMessage)
                .checkResultTable("Student Name", name + " " + surname)
                .checkEmptyResultTable("Student Email")
                .checkResultTable("Gender", gender)
                .checkResultTable("Mobile", phoneNumber)
                .checkResultTable("Date of Birth", dayOfBirth + " " + monthOfBirth + "," + yearOfBirth)
                .checkEmptyResultTable("Subjects")
                .checkEmptyResultTable("Hobbies")
                .checkEmptyResultTable("Picture")
                .checkEmptyResultTable("Address")
                .checkEmptyResultTable("State and City")
                .checkStateModalButton();

    }

    @Test
    @DisplayName("Отправка пустой формы")
    public void sendEmptyFormTest() {
        studentRegistrationFormPage
                .openPage()
                .preparePage()
                .clickSubmitButton()
                .checkNotVisibleModal()
                .checkStateForm(
                        "border-color",
                        "rgb(220, 53, 69)",
                        gender
                );
    }

    @Test
    @DisplayName("Ввод недопустимого количества символов в поле Number")
    public void sendInvalidMobileNumberInFormTest() {
        studentRegistrationFormPage
                .openPage()
                .preparePage()
                .typeName(name)
                .typeSurname(surname)
                .setGender(gender)
                .typeUserNumber(invalidPhoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .clickSubmitButton()
                .checkNotVisibleModal();
    }

    @Test
    @DisplayName("В группе радиобатонов 'Gender' можно выбрать только один вариант")
    public void onlyOneGenderCanBeSelectedAtATime() {
        studentRegistrationFormPage
                .openPage()
                .preparePage()
                .typeName(name)
                .typeSurname(surname)
                .setGender(genderM)
                .setGender(genderF)
                .checkStateOfGenderRadio(genderM, genderF);
    }

    @Test
    @DisplayName("Модальное окно исчезает после нажатия на кнопку закрытия")
    public void modalWindowDisappearsAfterClosing() {
        studentRegistrationFormPage
                .openPage()
                .preparePage()
                .typeName(name)
                .typeSurname(surname)
                .setGender(gender)
                .typeUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .clickSubmitButton()
                .checkVisibleModal()
                .closeModal()
                .checkStateModalButton()
                .checkNotVisibleModal();
    }
}
