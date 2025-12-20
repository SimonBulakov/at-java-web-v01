package work.part05;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

//Занятие-3 - ДЗ - автотестирование. AT- 5 (*). Слайд 47
@DisplayName("Тестовый набор: Занятие-3 - ДЗ - автотестирование. АТ-5 (*). Авиасейлс ")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DzAt05 {
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
        open("https://www.aviasales.ru/");
    }

    @AfterEach
    void tearDown(TestInfo test_info) {
        System.out.println("Закончили тест: " + test_info.getDisplayName() );
        closeWindow();
    }

    @Test
    void test01_use_aviasels() {
        $x("//input[@aria-labelledby='avia_form_origin-label']").click();
        //$x("//span[@data-test-id='text']").shouldHave(text("Шереметьево"));
        //sleep(2000);
        //Дальше то работает то не работает, пока не понял
    }

}
