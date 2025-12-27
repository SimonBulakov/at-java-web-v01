package work.part07;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import work.part07.pages.AviasailTopElementPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;


import static com.codeborne.selenide.Selenide.*;

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class AviaSalesTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        Configuration.pageLoadTimeout = 180_000; // Увеличиваем время для полной загрузки до 1 минут.
        Configuration.browser = "chrome"; //"firefox "
        //webdriver().object().manage().window().maximize();
        open("https://www.aviasales.ru/");
    }

    @Test
    void test01AiroportFromSelection() {
        Configuration.pageLoadTimeout = 60_000; // Увеличиваем время для полной загрузки до 1 минут.
        AviasailTopElementPage atePage = new AviasailTopElementPage();
        atePage.selectAirportFrom("Владивосток");
        sleep(3000);
    }

    @Test
    void test02AiroporToSelection() {
        AviasailTopElementPage atePage = new AviasailTopElementPage();
        atePage.selectAirportTo("Сочи");
        sleep(3000);
    }

    @Test
    void test03setDateWhen() {
        AviasailTopElementPage atePage = new AviasailTopElementPage();
        atePage.setDepartureDate("1 января, чт");
        sleep(3000);
    }

}

