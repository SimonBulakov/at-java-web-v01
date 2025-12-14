package work.part04;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.*;

public class AlertsTests {
    @BeforeAll
    static void beforeAll() { Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void beforeEachTest() {
        open("https://practice-automation.com/popups/");
    }
    @Test
    void test01SimpleAlert() {
        $("#alert").click();
        sleep(2_000);
        switchTo().alert().accept();
        sleep(2_000);
    }
    @Test
    void test02ConfirmOk() {
        $("#confirm").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        sleep(2_000);
        alert.accept();
        sleep(2_000);
    }
    @Test
    void test03ConfirmCancel() {
        open("https://practice-automation.com/popups/");
        $("#confirm").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        sleep(2_000);
        alert.dismiss();
        sleep(2_000);
    }
    @Test
    void test04PromptOk() {
        open("https://practice-automation.com/popups/");
        $("#prompt").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("Сергей");
        sleep(2_000);
        alert.accept();
        sleep(2_000);
    }
    @Test
    void test05PromptCancel() {
        open("https://practice-automation.com/popups/");
        $("#prompt").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        sleep(2_000);
        alert.dismiss();
        sleep(2_000);
    }
}

