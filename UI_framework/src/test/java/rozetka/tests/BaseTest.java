package rozetka.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;
import static rozetka.utils.BrowserProvider.getBrowser;

public class BaseTest {

    protected final int itemIndexToSelect = 0;

    @BeforeSuite
    public void suiteSetUp() {
        Configuration.timeout = 10000;
        Configuration.browser = getBrowser();
        Configuration.startMaximized = true;

    }

    @BeforeMethod
    public void setUp() {
        open("https://rozetka.com.ua/");
    }

    @AfterMethod
    public void cleanUp() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }
}
