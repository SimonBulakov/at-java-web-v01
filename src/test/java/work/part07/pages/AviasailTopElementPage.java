package work.part07.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AviasailTopElementPage {
    int attempts;
    SelenideElement
        cookiesUseButton = $x("//button[@data-test-id='accept-cookies-button']"),
        airportFrom = $x("//input[@aria-autocomplete='list' and @id='avia_form_origin-input']"),
        airportTo = $x("//input[@aria-autocomplete='list' and @id='avia_form_destination-input']"),
        departureDateButton = $x("//button[@data-test-id='start-date-field']"),
        departureDate,
        oneWayButton =  $x("//span[.='Выбрать в одну сторону']/../.."),
        arrivalDate = $x("//div[@data-test-id='end-date-value']"),
        findButton = $x("//div[@data-test-id='text' and text()='Найти билеты']");

    public AviasailTopElementPage() {
        this.attempts = 5; //Число попыток ввести значение в поле
    }

    @Step("Нажатие на кнопку подтверждения использования куки")
    public void cookiesUseButton() {
       this.cookiesUseButton.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        if (this.cookiesUseButton.isDisplayed()) {
           this.cookiesUseButton.click();
       }
    }

    @Step("Ввод аэропорта отправления")
    public void selectAirportFrom(String airport) {
        this.airportFrom.shouldBe(Condition.interactable, Duration.ofSeconds(30));// Проверяем, что элемент interactable
        this.airportFrom.shouldNotBe(Condition.readonly, Duration.ofSeconds(30));
        this.airportFrom.click();
        this.airportFrom.sendKeys(Keys.DELETE);
        sleep(1_000);
        this.airportFrom.sendKeys(airport);
        sleep(1_000);
    }

    @Step("Ввод аэропорта прибытия")
    public void selectAirportTo(String airport) {
        this.airportTo.shouldBe(Condition.interactable, Duration.ofSeconds(30));// Проверяем, что элемент interactable
        this.airportTo.click();
        this.airportTo.sendKeys(Keys.DELETE);
        this.airportTo.sendKeys(airport);
        sleep(5_000);
        //this.airportTo.sendKeys(Keys.ARROW_DOWN);
        sleep(1_000);
        this.airportTo.sendKeys(Keys.ENTER);
        sleep(1_000);
    }

    @Step("Ввод в поле Когда")
    public void setDepartureDate(String date) {
        //Действия с полем (кнопкой) "Когда". Проверяем, что с элементом можно взаимодействовать.
        //Нажымаем по элементу
        this.departureDateButton.shouldBe(Condition.interactable, Duration.ofSeconds(30));// Проверяем, что элемент interactable
        this.departureDateButton.click();
        this.departureDateButton.shouldBe(Condition.interactable, Duration.ofSeconds(30));// Проверяем, что элемент interactable

        //Выбор конкретной даты отправления в таблице дат
        this.departureDate = $x("//td[@data-day='" + date + "']//button");
        this.departureDate.shouldBe(Condition.exist, Duration.ofSeconds(30));
        this.departureDate.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.departureDate.click();

        //Нажимаем кнопку "Выбрать в одну сторону"
        this.oneWayButton.shouldBe(Condition.exist, Duration.ofSeconds(30));
        this.oneWayButton.shouldBe(Condition.interactable, Duration.ofSeconds(30));
        this.oneWayButton.click();
    }


}