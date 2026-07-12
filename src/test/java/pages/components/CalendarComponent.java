package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CalendarComponent {
    private final SelenideElement monthSelect = $(".react-datepicker__month-select");
    private final SelenideElement yearSelect = $(".react-datepicker__year-select");

    public void setDate(int day, String month, String year) {
        monthSelect.selectOption(month);
        yearSelect.selectOption(year);
        daySelect(day).click();
    }

    private SelenideElement daySelect(int day) {
        return $$(".react-datepicker__day:not(.react-datepicker__day--outside-month)")
                .findBy(exactText(String.valueOf(day)));
    }

}
