package work.part02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class TestFindElementsUsageXpathOrDLLSelenide {
    @BeforeEach
    void setUp() {
        open("https://slqa.ru/cases/xPathSimpleForm/");
    }

    @Test
    public void testContainTextMoscowUsageXPath() {
    //xPath. Поиск элемента, который содержит текст "Москва" и проверка, что он содержит текст "250 единиц")
        $x("//*[contains(., 'Москва')]").shouldHave(text("250 единиц"));
    }

    @Test
    public void testContainTextMoscowUsageDllSelenide() {
    //Библиотека Selenide. Поиск элемента, который содержит текст "Москва" и проверка, что он содержит текст "250 единиц")
        $(withText("Москва")).shouldHave(text("250 единиц"));
    }

    @Test
    public void testContainTextPiterUsageXPath() {
    //xPath. Поиск элемета, который содержит текст "Питер" и проверка, что это элемент содержит текст "180 единиц"
        $x("//*[contains(., 'Питер')]").shouldHave(text("180 единиц"));
    }

    @Test
    public void testStartWithTextKzUsageXPath() {
    //xPath. Поиск элемета, начинающийся с текста "Казахстан" и проверка, что это элемент содержит текст "площадь 2 724 902"
        $x("//*[starts-with(., 'Казахстан')]").shouldHave(text("площадь 2 724 902"));
    }
}
