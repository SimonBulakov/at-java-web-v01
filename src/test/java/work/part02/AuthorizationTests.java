package work.part02;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class AuthorizationTests {
    @Test
    public void test01LoginSuccess() {
        //Открытие страницы
        open("https://slqamsk.github.io/cases/slflights/v01/");

        //Поверка видимости поля ввода имени пользователя
        $("input[id=username]").shouldBe(visible);
        //Ввод имени пользователя "standard_user"
        $("input[id=username]").setValue("standard_user");

        //Поверка видимости поля ввода пароля
        $("input[id=password]").shouldBe(visible);
        //Ввод пароля
        $("input[id=password]").setValue("stand_pass1");

        //Поверка видимости кнопки Logib ("Войти")
        $("button[id=loginButton]").shouldBe(visible);
        //Нажатие кнопки "Войти"
        $("button[id=loginButton]").click();

        //Поверка успешного входа (Видимость кнопки "Logout")
        $("button[id=logoutButton]").shouldBe(visible);
    }

    @Test
    public void test02LoginWrongPassword() {
        //Открытие страницы
        open("https://slqamsk.github.io/cases/slflights/v01/");

        //Поверка видимости поля ввода имени пользователя
        $("input[id=username]").shouldBe(visible);
        //Ввод имени пользователя "standard_user"
        $("input[id=username]").setValue("standard_user");

        //Поверка видимости поля ввода пароля
        $("input[id=password]").shouldBe(visible);
        //Ввод пароля
        $("input[id=password]").setValue("stand_pass1_err");

        //Поверка видимости кнопки Logib ("Войти")
        $("button[id=loginButton]").shouldBe(visible);
        //Нажатие кнопки "Войти"
        $("button[id=loginButton]").click();

        //Поверка вывода сообщения об ошибке при некорректноп пароле
        $("div[id=message]").shouldHave(exactOwnText("Неверное имя пользователя или пароль."));
    }
}