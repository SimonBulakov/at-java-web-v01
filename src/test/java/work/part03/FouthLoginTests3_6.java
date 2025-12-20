package work.part03;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//Задание 3.6
@DisplayName("Тестовый набор FouthLoginTests - Задание 3.6")
@TestMethodOrder(MethodOrderer.MethodName.class)
public class FouthLoginTests3_6 {
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
        open("https://slqa.ru/cases/ChatGPTLogin/");
    }

    @AfterEach
    void tearDown(TestInfo test_info) {
        System.out.println("Закончили тест: " + test_info.getDisplayName() );
        //sleep(1000);//Что бы отследить вывод в консоль после завершения каждого теста
        closeWindow();
    }

    @ParameterizedTest(name="01. Корректные логин и пароль - успешный вход в систему по нажатию кнопки Login, #{index}, username: {0}")
    @ValueSource(strings = {"standard_user", "problem_user", "performance_glitch_user", "error_user", "visual_user"})
    void test01_success_login_button(String username) {
        $x("//input[@id='username']").sendKeys(username);
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible);
    }
    @Test
    @DisplayName("02. Корректный логин, пароль не соответствует логину - ошибка")
    void test02_incorrect_login() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce222");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Invalid username or password."));
    }
    @Test
    @DisplayName("03. Корректные логин и пароль - успешный вход в систему по нажатию клавиши Enter на клавиатуре")
    void test03_correct_login_use_enter() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").pressEnter();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible);
    }
    @Test
    @DisplayName("04. Выход из системы")
    void test04_correct_login_and_logout() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible).pressEnter();
        $x("//button[@id='loginButton']").shouldBe(visible);
    }
    @Test
    @DisplayName("05. Некорректный логин, пароль от корректного логина - ошибка")
    void test05_incorrect_login02() {
        $x("//input[@id='username']").sendKeys("standard_user333");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Invalid username or password."));
    }
    @Test
    @DisplayName("06. Корректный логин, пароль от другого корректного логина - ошибка")
    void test06_incorrect_login03() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("user06_password");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Invalid username or password."));
    }
    @Test
    @DisplayName("07. Проверить, что под заблокированным пользователем нельзя войти в систему (пользователь locked_out_user)")
    void test07_use_blocked_user() {
        $x("//input[@id='username']").sendKeys("locked_out_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Пользователь заблокирован."));
    }
    @Test
    @DisplayName("08. Пустой логин, пароль от корректного логина")
    void test08_use_void_user() {
        $x("//input[@id='username']").sendKeys("");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Username is required."));
    }
    @Test
    @DisplayName("09. Пустой пароль, корректный логин")
    void test09_use_void_password() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Password is required."));
    }
    @Test
    @DisplayName("10. Пустые логин и пароль")
    void test10_use_void_login_and_password() {
        $x("//input[@id='username']").sendKeys("");
        $x("//input[@id='password']").sendKeys("");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Username and Password are required."));
    }
    @Test
    @DisplayName("11. Проверить, что при вводе пароль скрыт за звёздочками\n")
    void test11_use_mask_password() {
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").type("secret_sauce");
        //Вариант 1
        $x("//input[@id='password']").shouldHave(exactValue("secret_sauce"));
        //Вариант 2
        $x("//input[@id='password']").shouldHave(attribute("type","password"));
    }
    @Test
    @DisplayName("12. Проверить вход в систему под несколькими разными логинами")
    void test12_use_many_login() {
        success_login("standard_user");
        success_login("performance_glitch_user");
    }
    void success_login(String username) {
        $x("//input[@id='username']").sendKeys(username);
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible).pressEnter();
        $x("//button[@id='loginButton']").shouldBe(visible);
    }
}
