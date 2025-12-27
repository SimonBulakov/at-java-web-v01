package work.part07;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import work.part07.pages.AviasailTopElementPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class AviaSalesTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        //Configuration.pageLoadTimeout = 60_000; // Увеличиваем время для полной загрузки до 1 минут.
        Configuration.browser = "chrome"; //"firefox "
        open("https://www.aviasales.ru/");
        getWebDriver().manage().window().maximize();

    }

    @Test
    void test01AiroportSelection() {

        /*AviasailTopElementPage atePage = new AviasailTopElementPage();
        atePage.cookiesUseButton();
        atePage.selectAirportFrom("Владивосток");
        sleep(3000);*/

        //Configuration.pageLoadStrategy = "eager"; // Без полной загрузки не работает
        Configuration.pageLoadTimeout = 180_000; // Увеличиваем время для полной загрузки до 3 минут.
        // Страница нестабильная: иногда быстро загружается, иногда требуется больше минуты
        open("https://aviasales.ru/");
        SelenideElement se = $("#avia_form_origin-input");
        se.shouldBe(Condition.interactable, Duration.ofSeconds(30)); // Проверяем, что элемент interactable,
        // т.е. с ним можно взаимодействовать - добавил, т.к. возникали ошибки, что элемент не interactable
        se.shouldNotBe(Condition.readonly, Duration.ofSeconds(30)); // Проверяем, что элемент доступен для записи,
        // т.е. с ним можно взаимодействовать - добавил, т.к. возникали ошибки, что элемент readonly
        System.out.println(se.getValue());
        String cityTo = "Новосибирск";
        se.setValue(cityTo); // Иногда срабатывает прямо так
        System.out.println(se.getValue());
        // В цикле мы тупо присваиваем значение, пока оно не присвоится.
        // Конечно, надо добавить ограничение на число попыток, чтобы не было бесконечного цикла
        // Например, цикл for от 1 до 100, а потом, если значение не присвоено, то выбрасывать ошибку
        while (!Objects.equals(se.getValue(), cityTo)) {
            sleep(1_000);
            se.click(); // Если кликнуть по этому полю, а потом перед тем, как присваивать значение очистить его,
            // то через какое-то время поле приходит в состояние, когда ему можно присвоить значение, которое требуется
            se.setValue("").setValue(cityTo);
            System.out.println(se.getValue());
        }
    }


}

