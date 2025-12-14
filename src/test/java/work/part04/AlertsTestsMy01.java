package work.part04;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;

import static com.codeborne.selenide.Selenide.*;

public class AlertsTestsMy01 {
    @BeforeAll
    static void beforeAll() { Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void beforeEachTest() {
        open("https://demoqa.com/alerts");
    }

    @Test
    void test01SimpleAlert() {
        $x("//*[@id='alertButton']").click();
        sleep(2_000);
        switchTo().alert().accept();
        sleep(2_000);
    }
    @Test
    void test02ConfirmOk() {
        $x("//*[@id='timerAlertButton']").click();
        sleep(6_000);
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        sleep(2_000);
        alert.accept();
        sleep(2_000);
    }
    @Test
    void test03ConfirmCancel() {
        $x("//*[@id='confirmButton']").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        sleep(2_000);
        alert.dismiss();
        sleep(2_000);
    }
    @Test
    void test04PromptOk() {
        $x("//*[@id='promtButton']").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        alert.sendKeys("Сергей");
        sleep(2_000);
        alert.accept();
        sleep(2_000);
    }
    @Test
    void test05PromptCancel() {
        $x("//*[@id='promtButton']").click();
        Alert alert = switchTo().alert();
        System.out.println(alert.getText());
        sleep(2_000);
        alert.dismiss();
        sleep(2_000);
    }
}

