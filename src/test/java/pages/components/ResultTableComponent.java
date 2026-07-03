package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static testdata.TestData.*;


public class ResultTableComponent {
    private final SelenideElement resultTable = $(".table-responsive");

    private void checkRow(String rowName, String expectedValue) {
        resultTable.$(byText(rowName)).parent().shouldHave(text(expectedValue));
    }

    private void checkEmptyRow(String rowName) {
        resultTable.$(byText(rowName)).sibling(0).shouldBe(empty);
    }

    public void checkResult(
            String name,
            String surname,
            String email,
            String gender,
            String phoneNumber,
            int day,
            String month,
            String year,
            String subjects,
            String hobbies,
            String imgText,
            String address,
            String state,
            String city
    ) {

        checkRow(nameRow, name + " " + surname);
        checkRow(emailRow, email);
        checkRow(genderRow, gender);
        checkRow(phoneNumberRow, phoneNumber);
        checkRow(dateOfBirthRow, day + " " + month + "," + year);
        checkRow(subjectsRow, subjects);
        checkRow(hobbiesRow, hobbies);
        checkRow(pictureRow, imgText);
        checkRow(addressRow, address);
        checkRow(stateAnsCityRow, state + " " + city);
    }

    public void checkEmptyResult(
            String name,
            String surname,
            String gender,
            String phoneNumber,
            int day,
            String month,
            String year
    ) {

        checkRow(nameRow, name + " " + surname);
        checkEmptyRow(emailRow);
        checkRow(genderRow, gender);
        checkRow(phoneNumberRow, phoneNumber);
        checkRow(dateOfBirthRow, day + " " + month + "," + year);
        checkEmptyRow(subjectsRow);
        checkEmptyRow(hobbiesRow);
        checkEmptyRow(pictureRow);
        checkEmptyRow(addressRow);
        checkEmptyRow(stateAnsCityRow);
    }


}
