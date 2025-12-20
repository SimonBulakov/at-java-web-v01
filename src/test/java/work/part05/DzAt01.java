package work.part05;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

//Занятие-3 - ДЗ - автотестирование. AT-1 (обязательное). Слайд 47
@DisplayName("Тестовый набор: Занятие-3 - ДЗ - автотестирование. АТ-1 (обязательное)")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DzAt01 {
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
        System.out.println("Начали тест: " + test_info.getDisplayName() + " _браузер: " +  randomBrowser);
        open("https://www.specialist.ru/");
    }

    @AfterEach
    void tearDown(TestInfo test_info) {
        System.out.println("Закончили тест: " + test_info.getDisplayName() );
        closeWindow();
    }

    @Test
    @DisplayName("АТ1. простой вариант проверки элементов на сайте www.specialist.ru")
    //просто для ДЗ, без явных дополнительных проверок
    void test01_use_site_specialist() {
        $x("//button[@id='cookieConsent__ok']").click(); //в окне "Наш сайт использует файлы cookie" нажать "Согласен"
        $x("//a[@href='/learning-formats']").click(); //Выбрать пункт меню "Форматы обучения"
        $x("//a[@href='/free-learning-new']").click(); //затем "Свободное обучение"
        $x("//a[@class='page-button banner-button']").click(); //затем "Выбрать курс"
        $x("//select[@id='Filter_CategoriesDirectionFilter']/option[@value='ПРГ']").click(); //в поле "Направление" выбрать "Программирование" (имеет value=ПРГ)
        $x("//button[@id='sendBtn']").click(); //нажать кнопку "Применить"
        $x("//body").shouldHave(text("Тестирование ПО")); //убедиться, что на странице есть элемент содержащий текст "Тестирование ПО"

    }
}
