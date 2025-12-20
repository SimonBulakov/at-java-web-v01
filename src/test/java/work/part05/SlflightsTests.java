package work.part05;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SlflightsTests {
    @Test
    void test01SuccessLoginSimple() {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        $x("//input[@id='username']").sendKeys("standard_user");
        $x("//input[@id='password']").sendKeys("stand_pass1");
        $x("//button[@id='loginButton']").click();
        $x("//button[@id='logoutButton']").shouldBe(visible);
    }

    @ParameterizedTest (name = "blabla")
    @CsvFileSource(resources = "/users.csv", numLinesToSkip=1)
    //в текущем файле users.csv две первых УЗ с доступом, третья без доступа
    void test02SuccessLoginUseCsv(String login, String password) {
        open("https://slqamsk.github.io/cases/slflights/v01/");
        $x("//input[@id='username']").sendKeys(login);
        $x("//input[@id='password']").sendKeys(password);
        $x("//button[@id='loginButton']").click();
        sleep(2_000);
        $x("//button[@id='logoutButton']").shouldBe(visible);
    }
}
