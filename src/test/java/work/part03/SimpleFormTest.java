package work.part03;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

//Задание 3.1
public class SimpleFormTest {
    @Test
    void test01_specific_commands() {
        open("https://slqa.ru/cases/SimpleForm/");
        //Вариант проверок через команды Selenide
        $(By.id("unique_id")).sendKeys("просто текст 1");
        $(By.name("unique_name")).sendKeys("просто текст 2");
        $(By.tagName("blockquote")).shouldHave(text("спрашивает"));
        $(By.className("unique_class")).shouldBe(visible);

        //Вариант проверок через xPath
        $x("//input[@id='unique_id']").sendKeys("просто текст 1");
        $x("//input[@name='unique_name']").sendKeys("просто текст 2");
        $x("//blockquote").shouldHave(text("спрашивает"));
        $x("//div[@class='unique_class']").shouldBe(visible);
    }

    @Test
    void test02_CSS_selectors() {
        open("https://slqa.ru/cases/SimpleForm/");
        //Вариант проверок через CSS-локаторы
        $("#unique_id").sendKeys("просто текст 1");
        $("[name=unique_name]").sendKeys("просто текст 2");
        $("blockquote").shouldHave(text("спрашивает"));
        $(".unique_class").shouldBe(visible);
    }
}
