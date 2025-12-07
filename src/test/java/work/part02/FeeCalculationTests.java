package work.part02;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class FeeCalculationTests {
    @Test
    public void test01() {
        open("https://slqa.ru/cases/fc/v01/");
        $(By.name("sum")).sendKeys("Просто текст");
        $(By.name("sum")).sendKeys("Еще текст");
        $(By.name("sum")).clear();
        $(By.name("sum")).setValue("100");
        $(By.name("sum")).setValue("222");
        $(By.name("sum")).type("50000");
        $(By.name("sum")).type("62600");
        $(By.name("submit")).click();
    }
}