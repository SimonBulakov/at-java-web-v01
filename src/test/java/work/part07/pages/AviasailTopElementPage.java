package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assumptions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AviasailTopElementPage {
    SelenideElement
        cookiesUseButton = $x("//button[@data-test-id='accept-cookies-button']"),
        airportFrom = $x("//input[@aria-autocomplete='list' and @id='avia_form_origin-input']"),
        airportTo = $x("//input[@aria-autocomplete='list' and @id='avia_form_destination-input']"),
        departureDate = $x("//div[@data-test-id='start-date-value']"),
        arrivalDate = $x("//div[@data-test-id='end-date-value']"),
        findButton = $x("//div[@data-test-id='text' and text()='Найти билеты']");

    @Step("Нажатие на кнопку подтверждения использовани я куки")
    public void cookiesUseButton() {
       if (this.cookiesUseButton.isDisplayed()) {
           this.cookiesUseButton.click();
       }

    }

    @Step("Ввод аэропорта отправления")
    public void selectAirportFrom(String airportTo) {
        this.airportFrom.setValue(airportTo);
    }


}