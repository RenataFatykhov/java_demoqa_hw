import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static testdata.TestData.*;

public class TextBoxTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFullTextBoxTest() {
        textBoxPage.openPage()
                .preparePage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .typeCurrentAddress(currentAddress)
                .typePermanentAddress(permanentAddress)
                .clickSubmitButton()
                .checkUserNameField("name", userName)
                .checkUserEmailField("email", userEmail)
                .checkCurrentAddressField(currentAddress)
                .checkPermanentAddressField(permanentAddress);
    }

    @Test
    @DisplayName("Ввод невалидного email")
    public void invalidEmailTextBoxTest() {
        textBoxPage.openPage()
                .preparePage()
                .typeUserName(userName)
                .typeUserEmail(userEmail)
                .typeCurrentAddress(currentAddress)
                .typePermanentAddress(permanentAddress)
                .clickSubmitButton()
                .checkUserEmailBorder();

    }
}
