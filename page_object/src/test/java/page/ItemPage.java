package page;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ItemPage extends BasePage {

    private By imageContainer = By.className("picture-container__picture");
    private By statusLabel = By.xpath("//rz-status-label/p");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void checkItemAvailability() {
        waiter.until(ExpectedConditions.elementToBeClickable(imageContainer));
        WebElement status = driver.findElement(statusLabel);
        Assertions.assertEquals("Заканчивается", status.getText());
    }
}
