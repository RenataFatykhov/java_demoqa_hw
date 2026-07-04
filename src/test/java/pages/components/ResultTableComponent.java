package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class ResultTableComponent {
    private final SelenideElement resultTable = $(".table-responsive");

    public ResultTableComponent checkRow(String rowName, String expectedValue) {
        resultTable.$(byText(rowName)).parent().shouldHave(text(expectedValue));
        return this;
    }

    public ResultTableComponent checkEmptyRow(String rowName) {
        resultTable.$(byText(rowName)).sibling(0).shouldBe(empty);
        return this;
    }


}
