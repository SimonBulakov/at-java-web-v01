package work.part07.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class FlightsListPage {
    SelenideElement
            flightsTable = $("#flightsTable"),
            backButton = $x("//button[.='Новый поиск']"),
            registerButton = $x("//button[.='Зарегистрироваться']"),
            sortByTimeSelectButton = $x("//select[@id='sortField']/option[@value='time']"),
            sortByPriceSelectButton = $x("//select[@id='sortField']/option[@value='price']"),
            sortByAscButton = $x("//select[@id='sortDirection']/option[@value='asc']"),
            sortByDescButton = $x("//select[@id='sortDirection']/option[@value='desc']");

    ElementsCollection findFlights = $$x("//table/tbody/tr");


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

    @Step("Сортировка по времени вылета по возрастанию")
    public void sortByTimeAsc() {
        this.sortByTimeSelectButton.click();
        this.sortByAscButton.click();
    }

    @Step("Сортировка по времени вылета по убыванию")
    public void sortByTimeDesc() {
        this.sortByTimeSelectButton.click();
        this.sortByDescButton.click();
    }

    @Step("Сортировка по цене по возрастанию")
    public void sortByPriceAsc() {
        this.sortByPriceSelectButton.click();
        this.sortByAscButton.click();
    }

    @Step("Сортировка по цене по убыванию")
    public void sortByPriceDesc() {
        this.sortByPriceSelectButton.click();
        this.sortByDescButton.click();
    }

    @Step("Проверка сортировки по времени вылета по возрастанию")
    public void  testDepartureTimeSorting() {
        List<LocalTime> departureTimes = new ArrayList<>();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Парсим время из 5-й ячейки каждой строки
        for (SelenideElement flight : findFlights) {
            String timeText = flight.$x("./td[5]").getText().trim();
            try {
                LocalTime time = LocalTime.parse(timeText, timeFormatter);
                departureTimes.add(time);
            } catch (Exception e) {
                // Если формат времени отличается, можно добавить логирование
                System.out.println("Не удалось распарсить время: " + timeText);
            }
        }
        // Проверяем, что времена отсортированы по возрастанию
        List<LocalTime> sortedTimes = new ArrayList<>(departureTimes);
        sortedTimes.sort(Comparator.naturalOrder());

        // Сравниваем исходный список с отсортированным
        assert departureTimes.equals(sortedTimes) :
                "Времена вылетов не отсортированы правильно!\n" +
                "Фактический порядок: " + departureTimes + "\n" +
                "Ожидаемый порядок: " + sortedTimes;

        /*for (SelenideElement currentFlight : findFlights) {
            System.out.println(currentFlight.$x("./td[5]").text());
        }*/
    }

}