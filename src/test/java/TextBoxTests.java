import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import testdata.TestData;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class TextBoxTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFullTextBoxTest() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            textBoxPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            textBoxPage
                    .typeUserName(data.userName)
                    .typeUserEmail(data.userEmail)
                    .typeCurrentAddress(data.currentAddress)
                    .typePermanentAddress(data.permanentAddress)
                    .clickSubmitButton();
        });

        step("Проверка модального окна с результатами", () -> {
            textBoxPage
                    .checkUserNameField("name", data.userName)
                    .checkUserEmailField("email", data.userEmail)
                    .checkCurrentAddressField(data.currentAddress)
                    .checkPermanentAddressField(data.permanentAddress);
        });

    }

    @Test
    @DisplayName("Ввод невалидного email")
    public void invalidEmailTextBoxTest() {
        TestData data = new TestData();

        step("Открыть страницу регистрации", () -> {
            textBoxPage
                    .openPage()
                    .preparePage();
        });

        step("Заполнение формы", () -> {
            textBoxPage
                    .typeUserName(data.userName)
                    .typeUserEmail(data.notValidEmail)
                    .typeCurrentAddress(data.currentAddress)
                    .typePermanentAddress(data.permanentAddress)
                    .clickSubmitButton();
        });

        step("Проверка модального окна с результатами", () -> {
            textBoxPage
                    .checkUserEmailBorder();
        });

    }
}
