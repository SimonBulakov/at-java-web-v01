package work.part07;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import work.part07.pages.FlightsListPage;
import work.part07.pages.LoginPage;
import work.part07.pages.RegistrationPage;
import work.part07.pages.SearchPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class POMFlightsTests {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        Configuration.browser = "chrome"; //"firefox "
        open("https://slqamsk.github.io/cases/slflights/v01/");
        getWebDriver().manage().window().maximize();
    }
    // ... Автотесты
    // 1. Неуспешный логин
    @Test
    void test01WrongPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "WrongPassword");
        loginPage.isLoginUnsuccessful();
    }

    // 2. Не задана дата
    @Test
    void test02NoDate() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("");
        searchPage.isDepartureDateEmpty();
    }
    // 3. Не найдены рейсы
    @Test
    void test03FlightsNotFound() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Казань", "Париж");

        FlightsListPage flightsList = new FlightsListPage();
        flightsList.isNoFlights();
    }

    //4. Успешная регистрация с данными по умолчанию
    @Test
    void test04SuccessRegistrationDefault() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Страница регистрации на рейс
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.successDefaultRegistration();
    }

    // 5. Пустые поля
    @Test
    void test05EmptyField() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16.03.2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.registerToFirstFlight();

        // Страница регистрации на рейс
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");
        registrationPage.registration("", "", "", "");
        registrationPage.isErrorFillAllFied();
    }

    // 6. для даты в прошлом отображается ошибка
    @Test
    void test06ExpiredDate() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("20/12/2025", "Казань", "Париж");
        searchPage.isDepartureDateExpired();
    }

    // 7. Цепочка тестов
    @Test
    void test07ChainTests(){
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16/03/2026", "Казань", "Париж");

        // Страница со списком найденных рейсов (Рейсы не  найдены)
        FlightsListPage flightsList = new FlightsListPage();
        flightsList.isNoFlights();

        //Возврат на страницу поиска и новый поиск
        searchPage.backButton();
        searchPage.search("16/03/2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов и регистрация по первмоу найденному
        flightsList.registerToFirstFlight();

        // Страница регистрации на рейс - проверка корректности рейса
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.isFlightDataCorrect("Москва", "Нью-Йорк");

        // Страница регистрации на рейс - ввод данных с пустым паспортом (с проверкой на сообщение об ошибке)
        registrationPage.registration("Иванов Иван Иванович","", "ivanov@example.com", "+7 (123) 456-7890");
        registrationPage.isErrorFillAllFied();
        // Страница регистрации на рейс - ввод корректных данных (с последующей завершением регистрации)
        registrationPage.successRegistration("Иванов Иван Иванович","1234 567890", "ivanov@example.com", "+7 (123) 456-7890");

    }

}