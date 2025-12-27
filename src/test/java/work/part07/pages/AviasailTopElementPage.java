package work.part07.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assumptions;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

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
       this.cookiesUseButton.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        if (this.cookiesUseButton.isDisplayed()) {
           this.cookiesUseButton.click();
       }

    }

    @Step("Ввод аэропорта отправления")
    public void selectAirportFrom(String airport) {
        this.airportFrom.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.airportFrom.click();
        sleep(3_000);
        this.airportFrom.setValue("");
        sleep(3_000);
        this.airportFrom.type(airport);
    }

    @Step("Ввод аэропорта прибытия")
    public void selectAirportTo(String airport) {
        this.airportTo.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.airportTo.setValue(airport);
    }

    @Step("Ввод в поле Когда")
    public void setDepartureDate(String date) {
        this.departureDate.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.departureDate.sendKeys(date);
    }


}