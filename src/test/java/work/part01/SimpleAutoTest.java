package work.part01;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SimpleAutoTest {
    @Test
    void test01() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1280x1024";
        Configuration.browserPosition = "1x1";

        open("http://92.51.36.108:7777/sl.qa");
        SelenideElement a = $("body");
        a.shouldHave(text("Учебные приложения"));
        sleep(4000);
        $("body").shouldHave(text("Учебные приложения"));
    }
    @Test
    void test02() {
        open("http://92.51.36.108:7777/sl.qa");
        SelenideElement a = $("body");
        $("body").shouldHave(text("Занятия по тестированию"));
    }
}
