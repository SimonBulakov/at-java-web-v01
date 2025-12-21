package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class FlightsListPage {
    SelenideElement
            flightsTable = $("#flightsTable"),
            backButton = $x("//button[.='Новый поиск']"),
            registerButton = $x("//button[.='Зарегистрироваться']");

    @Step("Выбираем первый рейс в списке")
    public void registerToFirstFlight() {
        this.registerButton.click();
    }

    @Step("Проверяем, что рейсов не найдено")
    public void isNoFlights() {
        flightsTable.shouldHave(text("Рейсов по вашему запросу не найдено."));
    }

    @Step("Возврат на новый поиск")
    public void backButton() {
        this.backButton.click();
    }
}