package page;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactsPage extends BasePage {

    @FindBy(tagName = "rz-map")
    private WebElement map;
    @FindBy(css = "div.contacts-main__cell > a")
    private List<WebElement> mainContacts;

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public void checkContactsPresence() {
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return map.isDisplayed();
            }
        });
        Assertions.assertTrue(mainContacts.size() >= 1);
    }
}
