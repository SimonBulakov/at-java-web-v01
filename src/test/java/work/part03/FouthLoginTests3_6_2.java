package work.part03;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//Задание 3.6 Слайд 28
@DisplayName("Тестовый набор FouthLoginTests - Задание 3.6 Слайд 28")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FouthLoginTests3_6_2 {
    String[] browsers = {"chrome", "firefox"}; //edge на виртуалке не срабатывает, разобраться потом с обновлением
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
        System.out.println("Начали тест: " + test_info.getDisplayName() + " браузер: " +  randomBrowser);
        open("http://92.51.36.108:7777/sl.qa/fc/v01/index.php");
    }

    @AfterEach
    void tearDown(TestInfo test_info) {
        System.out.println("Закончили тест: " + test_info.getDisplayName() );
        //sleep(1000);//Что бы отследить вывод в консоль после завершения каждого теста
        closeWindow();
    }

    @ParameterizedTest(name="Рублёвый перевод: расчёт комиссии, #{index}, username: {0}")
    @ValueSource(strings = {"100", "1000", "25600.5", "99999", "10000"})
    //@ValueSource(strings = {"100"})
    void test01_sum_commision(String sum) {
        $x("//input[@name='sum']").sendKeys(sum);
        $x("//input[@name='submit']").click();
        $x("//span[@name='sum']").shouldBe(visible);
        $x("//span[@name='sum']").shouldHave(text(sum));
    }
}
