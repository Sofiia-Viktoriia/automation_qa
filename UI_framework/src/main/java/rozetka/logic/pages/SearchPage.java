package rozetka.logic.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class SearchPage {

    private static final By firstItemImage = By.xpath("//rz-grid/ul/li[1]/app-goods-tile-default/div/div/a/img");
    private static final By searchTitle = By.xpath("//h1[@class='catalog-heading ng-star-inserted']");

    public static void checkSearchTitle(String title) {
        $(firstItemImage).shouldBe(Condition.visible);
        Assert.assertEquals($(searchTitle).getText(), title);
    }
}
