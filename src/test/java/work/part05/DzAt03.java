package work.part05;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

//Занятие-3 - ДЗ - автотестирование. AT-3 (*). Слайд 47
@DisplayName("Тестовый набор: Занятие-3 - ДЗ - автотестирование. АТ-3 (*Параметризированное)")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DzAt03 {
    //String[] browsers = {"chrome", "firefox"}; //edge на виртуалке не срабатывает, разобраться потом с обновлением
    String[] browsers = {"chrome"}; //Для текущего только chrome
    String randomBrowser = browsers[new Random().nextInt(browsers.length)];

    @BeforeAll
    static void beforeAll(TestInfo test_info) {
        System.out.println(test_info.getDisplayName() + " - начали выполнение всех тестов.");
    }

    @AfterAll
    static void afterAll(TestInfo test_info) {
        System.out.println(test_info.getDisplayName() + " - закончили выполнение всех тестов.");
    }

    @BeforeEach
    void setUp(TestInfo test_info) {
        Configuration.browser = randomBrowser;
        System.out.println("Начали тест: " + test_info.getDisplayName() + " _браузер: " +  randomBrowser);
        open("http://92.51.36.108:7777/sl.qa/cinema/index.php");
    }

    @AfterEach
    void tearDown(TestInfo test_info) {
        System.out.println("Закончили тест: " + test_info.getDisplayName() );
        closeWindow();
    }

    @ParameterizedTest(name = "АТ3.  Параметризированный тест, #{index}, value: {0}")
    @CsvFileSource(resources = "/at3_source.csv", numLinesToSkip=1)
    void test01_param_and_use_csv(String age, String date, String seans, String film, String message) {
        $x("//input[@name='age']").sendKeys(age != null ? age : "");
        $x("//input[@name='date']").sendKeys(date != null ? date : "");
        $x(String.format("//input[@name='session' and @value='%s']", seans != null ? seans : "")).click();
        if (film != null) {
            $x(String.format("//input[@name='film' and @value='%s']", film)).click();
        } else {
            System.out.println(film + " не заполнен");
        }
        $x("//input[@type='submit' and @value='Рассчитать']").click();
        $x(String.format("//div[contains(text(), '%s')]", message)).shouldBe(visible);
        sleep(3000);
    }
}
