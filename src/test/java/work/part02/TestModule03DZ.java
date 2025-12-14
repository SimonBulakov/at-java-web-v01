package work.part02;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class TestModule03DZ {
    @Test
    public void testSearchAndUseElementsOfSpecialist() {
        //Шаг1.1 Окрыть страницу
        open("https://specialist.ru");


        //Шаг1.2 Поиск нужного элемнта "Курс -  Каталог крсов" (без явной проверки наличия выпадающего элемента) и нажать
        $x("//a[contains(text(), 'Курсы')]").click();
        $x("//a[contains(text(), 'Каталог курсов')]").click();
        sleep(3000);



    }
}
