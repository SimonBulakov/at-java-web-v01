package work.part07.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    SelenideElement
        username = $("#username"),
        password = $("#password"),
        loginButton = $("#loginButton"),
        errorMessage = $("#message"),
        greeting = $("#greeting");

    @Step("Вход в систему")
    public void login(String username, String password) {
        this.username.setValue(username);
        this.password.setValue(password);
        this.loginButton.click();
    }

    @Step("Неуспешный логин")
    public void isLoginUnsuccessful() {
        this.errorMessage.shouldHave(text("Неверное имя пользователя или пароль."));
    }

    @Step("Пустой логин")
    public void isEmptyLogin() {
        this.errorMessage.shouldHave(text("Username is required."));
    }

    @Step("Пустой пароль")
    public void isEmptyPassword() {
        this.errorMessage.shouldHave(text("Password is required."));
    }

    @Step("Пустой логин и пароль")
    public void isEmptyLoginAndPassword() {
        this.errorMessage.shouldHave(text("Username and Password are required."));
    }

    @Step("Заблокированный пользователь")
    public void isBlockedUser() {
        this.errorMessage.shouldHave(text("Пользователь заблокирован."));
    }

    @Step("Успешный логин")
    public void isLoginSuccessful(String fio) {
        this.greeting.shouldHave(text("Добро пожаловать, " + fio + "!"));
    }

}