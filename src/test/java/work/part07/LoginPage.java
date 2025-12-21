package work.part07;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    SelenideElement
            username =  $x("//input[@id='username']"),
            password =  $x("//input[@id='password']"),
            loginButton =  $x("//button[@id='loginButton']"),
            errorMessage =  $x("//div[@class='message error' and @id='message']"),
            greeting =  $x("//span[@id='greeting']");

    @Step("Вход в систему")
    public void login (String username, String password) {
        this.username.setValue(username);
        this.password.setValue(password);
        this.loginButton.click();
    }

}
