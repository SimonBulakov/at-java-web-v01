package work.part07;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import java.util.Random;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

//Модуль7. Задание Слайд 5
@DisplayName("Тестовый набор: Модуль7. Задание Слайд 5")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class SlflightsTestV02 {
    String[] browsers = {"chrome"}; //edge на виртуалке не срабатывает, разобраться потом с обновлением
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
        open("https://slqamsk.github.io/cases/slflights/v01/");
    }

    @AfterEach
    void tearDown(TestInfo test_info) {
        System.out.println("Закончили тест: " + test_info.getDisplayName() );
        closeWindow();
    }

    @Test
    @DisplayName("Тест01. Неуспешный логин")
    void test01_wrong_login() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("stand_pass222");
        $x("//button[@id='loginButton']").click();
        $x("//div[@class='message error' and @id='message']").shouldHave(text("Неверное имя пользователя или пароль."));
    }

    @Test
    @DisplayName("Тест02. Поиск - дата не задана")
    void test02_void_date() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("stand_pass1");
        $x("//button[@id='loginButton']").click();
        $x("//div[@class='message success' and @id='message']").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $x("//button[@id='logoutButton']").shouldBe(visible);
        $x("//input[@id='departureDate']").clear();
        $x("//button[@onclick='findFlights()']").click();
        $x("//div[@class='search-message' and @id='searchMessage']").shouldHave(text("Пожалуйста, укажите дату вылета."));
    }

    @Test
    @DisplayName("Тест03. Поиск - не найдены рейсы")
    void test03_no_flights_found() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("stand_pass1");
        $x("//button[@id='loginButton']").click();
        $x("//div[@class='message success' and @id='message']").shouldHave(text("Вход в систему выполнен успешно! Загрузка..."));
        $x("//button[@id='logoutButton']").shouldBe(visible);
        $x("//select[@id='departureCity']").selectOption("Казань");
        $x("//select[@id='arrivalCity']").selectOption("Париж");
        $x("//input[@id='departureDate']").sendKeys("22/12/2025");
        $x("//button[@onclick='findFlights()']").click();
        $x("//td").shouldHave(text("Рейсов по вашему запросу не найдено."));
    }


}
