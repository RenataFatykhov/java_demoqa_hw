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
                .setGender(genderF)
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
                .checkResultTable(
                        name,
                        surname,
                        email,
                        genderF,
                        phoneNumber,
                        dayOfBirth,
                        monthOfBirth,
                        yearOfBirth,
                        subjects,
                        hobbies,
                        imgText,
                        address,
                        state,
                        city)
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
                .setGender(genderF)
                .typeUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .clickSubmitButton()
                .checkVisibleModal()
                .checkMessage(successfulMessage)
                .checkEmptyResultTable(
                        name,
                        surname,
                        genderF,
                        phoneNumber,
                        dayOfBirth,
                        monthOfBirth,
                        yearOfBirth
                )
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
                        border,
                        borderColor,
                        genderF
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
                .typeEmail(email)
                .setGender(genderF)
                .typeUserNumber(invalidPhoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .clickSubmitButton()
                .checkVisibleModal()
                .checkMessage(successfulMessage)
                .checkEmptyResultTable(
                        name,
                        surname,
                        genderF,
                        phoneNumber,
                        dayOfBirth,
                        monthOfBirth,
                        yearOfBirth
                )
                .checkStateModalButton();
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
                .typeEmail(email)
                .setGender(genderF)
                .typeUserNumber(phoneNumber)
                .setDateOfBirth(dayOfBirth, monthOfBirth, yearOfBirth)
                .clickSubmitButton()
                .checkVisibleModal()
                .closeModal()
                .checkStateModalButton()
                .checkNotVisibleModal();
    }
}
