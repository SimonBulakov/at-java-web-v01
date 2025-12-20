package work.part05;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//Занятие-3 - ДЗ - автотестирование. AT-2 (обязательное). Слайд 47
@DisplayName("Тестовый набор: Занятие-3 - ДЗ - автотестирование. АТ-2 (обязательное)")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class DzAt02 {
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

    @Test
    @DisplayName("АТ2.01. простой вариант формы расчёта стоимости билета в кино: Возраст = 12 лет / Дата сеанса = 23.12.2025 / Начало сеанса = 9:30 /Фильм = Назад в будущее. Итоговая стоимость 350р")
    //просто для ДЗ, без явных дополнительных проверок
    void test01_simple_test() {
        $x("//input[@name='age']").sendKeys("12");
        $x("//input[@name='date']").sendKeys("23/12/2025");
        $x("//input[@name='session' and @value='1']").click();
        $x("//input[@name='film' and @value='back']").click();
        $x("//input[@type='submit' and @value='Рассчитать']").click();
        $x("//div").shouldHave(text("Стоимость билета: 350 рублей."));
    }

    @Test
    @DisplayName("АТ2.02. Ошибка по возрастным ограничениям ")
        //просто для ДЗ, без явных дополнительных проверок
    void test02_wrong_age_restrictions() {
        $x("//input[@name='age']").sendKeys("12");
        $x("//input[@name='date']").sendKeys("23/12/2025");
        $x("//input[@name='session' and @value='1']").click();
        $x("//input[@name='film' and @value='killers']").click();
        $x("//input[@type='submit' and @value='Рассчитать']").click();
        $x("//div").shouldHave(text("Этот фильм можно смотреть только с 18 лет")); //Вариант проверки 1
        $x("//div[contains(text(), 'Этот фильм можно смотреть только с 18 лет')]").shouldBe(visible); //Вариант проверки 2
    }

    @Test
    @DisplayName("АТ2.03. Тест на проверку пустого поля возраст")
        //просто для ДЗ, без явных дополнительных проверок
    void test03_void_age_test() {
        $x("//input[@name='age']").sendKeys("");
        $x("//input[@name='date']").sendKeys("25/12/2025");
        $x("//input[@name='session' and @value='1']").click();
        $x("//input[@name='film' and @value='back']").click();
        $x("//input[@type='submit' and @value='Рассчитать']").click();
        $x("//div[contains(text(), 'надо указать возраст для расчёта стоимости билета')]").shouldBe(visible);
    }

    @Test
    @DisplayName("АТ2.04. Тест на проверку пустого поля Дата сеанаса")
        //просто для ДЗ, без явных дополнительных проверок
    void test04_void_date_test() {
        $x("//input[@name='age']").sendKeys("18");
        //$x("//input[@name='date']").sendKeys("");
        $x("//input[@name='session' and @value='1']").click();
        $x("//input[@name='film' and @value='back']").click();
        $x("//input[@type='submit' and @value='Рассчитать']").click();
        $x("//div[contains(text(), 'надо указать дату сеанса для расчёта стоимости билета')]").shouldBe(visible);
    }

    @Test
    @DisplayName("АТ2.05. Тест на то, что не выбран конкретный фильм ")
        //просто для ДЗ, без явных дополнительных проверок
    void test05_simple_test() {
        $x("//input[@name='age']").sendKeys("35");
        $x("//input[@name='date']").sendKeys("26/12/2025");
        $x("//input[@name='session' and @value='4']").click();
        //$x("//input[@name='film' and @value='back']").click();
        $x("//input[@type='submit' and @value='Рассчитать']").click();
        $x("//div[contains(text(), 'надо указать фильм для расчёта стоимости билета')]").shouldBe(visible);
    }
}
