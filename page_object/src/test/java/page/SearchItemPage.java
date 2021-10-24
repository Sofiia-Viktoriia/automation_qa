package page;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SearchItemPage {

    WebDriver driver;
    WebDriverWait waiter;

    private By itemImage = By.xpath("//rz-grid/ul/li[1]/app-goods-tile-default/div/div/a/img");
    @FindBy(xpath = "//h1[@class='catalog-heading ng-star-inserted']")
    private WebElement catalogTitle;
    private By logoImage = By.cssSelector("body > app-root > div > div > rz-header > header > div > div > a > picture > img");

    public SearchItemPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(this.driver, this);
    }

    public void checkTitle(String title) {
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(itemImage).isDisplayed();
            }
        });
        Assertions.assertEquals(title, catalogTitle.getText());
    }

    public MainPage returnToMainPage() {
        waiter.until(ExpectedConditions.elementToBeClickable(logoImage)).click();
        return new MainPage(driver);
    }
}
