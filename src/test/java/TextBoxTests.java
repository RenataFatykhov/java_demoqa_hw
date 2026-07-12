import com.github.javafaker.Faker;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import java.util.Locale;

import static testdata.TestData.*;
import static utils.RandomUtils.getRandomString;

public class TextBoxTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();
    @Test
    @DisplayName("Заполнение всех полей формы")
    public void successfulFullTextBoxTest_with_utils() {

        String userName = getRandomString(10);
        String userEmail = "renata.fatykhova@gmail.com";
        String currentAddress = "Test Street one";
        String permanentAddress = "Test Street two";

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

//    @Test
//    @DisplayName("Заполнение всех полей формы")
//    public void successfulFullTextBoxTest() {
//
//        Faker fakerRu = new Faker(new Locale("ru"));
//        Faker faker = new Faker();
//        String userName = fakerRu.name().fullName();
//        String userEmail = faker.internet().emailAddress();
//        String currentAddress = fakerRu.address().fullAddress();
//        String permanentAddress = fakerRu.address().fullAddress();
//
//        textBoxPage.openPage()
//                .preparePage()
//                .typeUserName(userName)
//                .typeUserEmail(userEmail)
//                .typeCurrentAddress(currentAddress)
//                .typePermanentAddress(permanentAddress)
//                .clickSubmitButton()
//                .checkUserNameField("name", userName)
//                .checkUserEmailField("email", userEmail)
//                .checkCurrentAddressField(currentAddress)
//                .checkPermanentAddressField(permanentAddress);
//    }
//    @Test
//    @DisplayName("Заполнение всех полей формы")
//    public void successfulFullTextBoxTest() {
//        textBoxPage.openPage()
//                .preparePage()
//                .typeUserName(userName)
//                .typeUserEmail(userEmail)
//                .typeCurrentAddress(currentAddress)
//                .typePermanentAddress(permanentAddress)
//                .clickSubmitButton()
//                .checkUserNameField("name", userName)
//                .checkUserEmailField("email", userEmail)
//                .checkCurrentAddressField(currentAddress)
//                .checkPermanentAddressField(permanentAddress);
//    }

    @Test
    @DisplayName("Ввод невалидного email")
    public void invalidEmailTextBoxTest() {
        textBoxPage.openPage()
                .preparePage()
                .typeUserName(userName)
                .typeUserEmail(notValidEmail)
                .typeCurrentAddress(currentAddress)
                .typePermanentAddress(permanentAddress)
                .clickSubmitButton()
                .checkUserEmailBorder();

    }
}
