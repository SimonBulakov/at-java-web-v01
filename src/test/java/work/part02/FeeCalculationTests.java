package work.part02;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class FeeCalculationTests {
    @Test
    public void test01() {
        open("https://slqa.ru/cases/fc/v01/");
        /*$(By.name("sum")).sendKeys("Просто текст");
        $(By.name("sum")).sendKeys("Еще текст");
        $(By.name("submit")).click();
        $(By.name("sum")).clear();

        $(By.name("sum")).setValue("100");
        $(By.name("sum")).setValue("222");

        $(By.name("sum")).type("50000");
        $(By.name("sum")).type("62600");
        $(By.name("submit")).click();*/

        $("input[name=sum]").sendKeys("Просто текст");
        $("input[name=submit]").click();
        sleep (2_000);
        $("input[name=sum]").clear();

        $("input[name=sum]").setValue("100");
        $("input[name=submit]").click();
        sleep (2_000);
        $("input[name=sum]").clear();

        $("input[name=sum]").type("50000");
        $("input[name=submit]").click();
        sleep (2_000);
        $("input[name=sum]").clear();
        sleep (2_000);

    }
}