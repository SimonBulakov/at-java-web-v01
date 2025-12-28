package work.part09;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;

import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import work.part07.pages.FlightsListPage;
import work.part07.pages.LoginPage;
import work.part07.pages.RegistrationPage;
import work.part07.pages.SearchPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

@TestMethodOrder(MethodOrderer.DisplayName.class)

public class POMFlightsTestsYaDriver {
    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        /*
        // Устанавливаем ChromeDriver (совместим с Яндекс.Браузером)
        WebDriverManager.chromedriver().setup();
        // Указываем путь к Яндекс.Браузеру
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/Simon_Dev/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        Configuration.browserCapabilities = options;
        Configuration.browser = "chrome";
        Configuration.headless = false;
        */
     }


    @BeforeEach
    void setUp() {
        open("https://slqamsk.github.io/cases/slflights/v01/");
    }

    @Test
    void checkBrowser() {
        Configuration.reportsFolder = "test-results/reports";
        Configuration.timeout = 10000;
        open("chrome://version/");
        // Должна отобразиться информация о Яндекс.Браузере
    }

    // ... Автотесты
    // 1. Неуспешный логин
    @Test
    void test01WrongPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "WrongPassword");
        loginPage.isLoginUnsuccessful();
        sleep(10_000);
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
    //Успешный логин - Поиск рейса - Рейсы не найденны - возврат на страницу поиска
    //Новый поиск - регистраиця на первый найденный - провевка корректности рейса - ввод пустого паспорта (сообщение о ошибке)
    //Ввод корректных данных с последующей завершением регистрации
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
        flightsList.backButton();
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

    //Домашнее задание

    //1. Тестирование авторизации
    //1.3 Проверить вход с пустыми полями
    //Пустой Логин
    //Пустой пароль
    //Пустой логин и пароль
    @Test
    void test08EmptyLoginOrPassword() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        //Пустой логин
        loginPage.login("", "stand_pass1");
        loginPage.isEmptyLogin();
        //Пустой пароль
        loginPage.login("standard_user", "");
        loginPage.isEmptyPassword();
        //Пустой логин и пароль
        loginPage.login("", "");
        loginPage.isEmptyLoginAndPassword();
    }

    //1.4 Проверить вход заблокированного пользователя (locked_out_user)
    @Test
    void test09BlockedUser() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        //Вводим данные заблокированнго пользователя
        loginPage.login("locked_out_user", "lock_pass2");
        loginPage.isBlockedUser();
    }

    //1.5 Проверить кнопку выхода (Logout)
    @Test
    void test10ButtonLogout() {
        // Страница логина
        LoginPage loginPage = new LoginPage();
        //Вводим данные заблокированнго пользователя
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов - нажатие на logout и проверка перехода на страницу авторизации (Видимость эемента с тектом Аутентификация)
        SearchPage searchPage = new SearchPage();
        searchPage.isLogoutButtonUse();
    }

    //3. Тестирование списка рейсов
    //3.2 Проверить сортировку по времени вылета по возрастанию
    @Test
    void test11FlightsListSortByTime(){
        // Страница логина
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "stand_pass1");
        loginPage.isLoginSuccessful("Иванов Иван Иванович");

        // Страница поиска рейсов
        SearchPage searchPage = new SearchPage();
        searchPage.search("16/03/2026", "Москва", "Нью-Йорк");

        // Страница со списком найденных рейсов (Рейсы не  найдены)
        FlightsListPage flightsList = new FlightsListPage();

        //Простые проверки сортировки по цене
        //Нажатие кнопки сортировки по цене и по возрастанию
        flightsList.sortByPriceAsc();
        //Нажатие кнопки сортировки по цене и по возрастанию
        flightsList.sortByPriceDesc();

        //Нажатие кнопки сортировки времени вылета и по убыванию
        flightsList.sortByTimeDesc();

        //Нажатие кнопки времени вылета и по возрастанию и проверка, что время вылета отсортированно по возрастанию (HappyPath)
        flightsList.sortByTimeAsc();
        flightsList.testDepartureTimeSorting();

        //Для проверки, что если сортировка времени не по возрастанию, то получим ошибку выполнения теста (корректность метода testDepartureTimeSorting)
        // "Времена вылетов не отсортированы правильно!"
        //!!! Расскоментировать для проверки
        /*
        //Нажатие кнопки сортировки времени вылета и по убыванию
        flightsList.sortByTimeDesc();
        flightsList.testDepartureTimeSorting();
        */
     }

}