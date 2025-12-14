package work.part04;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class IFramesTestMy {
    @Test
    void test01IFrame() {
        Configuration.pageLoadStrategy = "eager";

        open("https://demoqa.com/frames");
        getWebDriver().manage().window().maximize();
        switchTo().frame($x("//iframe[@id='frame1']"));
        //$("#sampleHeading").shouldHave(text("This is a sample page"));
        $x("//h1[@id='sampleHeading']").shouldHave(text("This is a sample page"));
        switchTo().defaultContent();
        $x("//header/a").click();

    }
}