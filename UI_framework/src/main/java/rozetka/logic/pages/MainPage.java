package rozetka.logic.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {

    private static final By allItems = By.xpath("//a[@class='tile__title']");
    private static final By allCategories = By.xpath("//ul[@class='menu-categories ng-star-inserted']/li");

    public static void selectItemByIndex(int index) {
        $$(allItems).get(index).click();
    }

    public static void checkAmountOfCategories() {
        List<SelenideElement> categories = $$(allCategories);
        Assert.assertEquals(categories.size(), 19);
    }

}
