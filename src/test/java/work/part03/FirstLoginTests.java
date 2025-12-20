package work.part03;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//Задание 3.1
public class FirstLoginTests {
    @Test
    void test01_correct_login() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible);
    }
    @Test
    void test02_incorrect_login() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce222");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Invalid username or password."));
    }
    @Test
    void test03_correct_login_use_enter() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").pressEnter();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible);
    }
    @Test
    void test04_correct_login_and_logout() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//button[@id='logoutButton']").shouldBe(visible).pressEnter();
        $x("//button[@id='loginButton']").shouldBe(visible);
    }
    @Test
    void test05_incorrect_login02() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user333");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Invalid username or password."));
    }
    @Test
    void test06_incorrect_login03() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("user06_password");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Invalid username or password."));
    }
    @Test
    void test07_use_blocked_user() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("locked_out_user");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Пользователь заблокирован."));
    }
    @Test
    void test08_use_void_user() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("");
        $x("//input[@id='password']").sendKeys("secret_sauce");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Username is required."));
    }
    @Test
    void test09_use_void_password() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Password is required."));
    }
    @Test
    void test10_use_void_login_and_password() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("");
        $x("//input[@id='password']").sendKeys("");
        $x("//button[@id='loginButton']").click();
        sleep(3000);
        $x("//div[@class='message error']").shouldHave(text("Username and Password are required."));
    }
    @Test
    void test11_use_mask_password() {
        open("https://slqa.ru/cases/ChatGPTLogin/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").type("secret_sauce");
        //Вариант 1
        $x("//input[@id='password']").shouldHave(exactValue("secret_sauce"));
        //Вариант 1
        $x("//input[@id='password']").shouldHave(attribute("type","password"));

    }
}
