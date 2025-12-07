package work.part02;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class SearchDemoTestSimon {
    @Test
    public void testElementSearchMethods() {
        open("https://slqamsk.github.io/demo/search-demo/");

        //Поиск элемента по идентификатору
        $(By.id("submit-button")).shouldBe(visible);
        $(By.id("user-password")).shouldBe(visible);

        //Поиск по атрибуту name (поиск по имени элемента)
        $(By.name("interests")).shouldBe(visible);
        $(By.name("user_email")).shouldBe(visible);
        $(("[name=user_email]")).shouldBe(visible); //cssSelector для name

        //Поиск по имени класса
        $(By.className("nav-link")).shouldBe(visible);
        $(By.className("btn")).shouldBe(visible);
        $(".btn").shouldBe(visible); //cssSelector для класса

        //Поиск по имени тега
        $(By.tagName("input")).shouldBe(visible);
        $(By.tagName("h3")).shouldBe(visible);

        //Поиск по полному тексту ссылки
        $(By.linkText("Контакты и обратная связь")).shouldBe(visible);
        $(By.linkText("Регистрация нового пользователя в системе")).shouldBe(visible);

        //Поиск по частичному тексту ссылки
        $(By.partialLinkText("длинный")).shouldBe(visible);
        $(By.partialLinkText("Контакты")).shouldBe(visible);


    }
}