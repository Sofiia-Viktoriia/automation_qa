import com.codeborne.selenide.Configuration;
import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;

public class BaseTest {

    @BeforeSuite
    public void suiteSetUp() {
        System.out.println("Before Suite setUp runs");
        Configuration.startMaximized = true;
    }

    @BeforeTest
    public void testSetUp() {
        System.out.println("Before Test setUp runs");
        Configuration.timeout = 10000;
    }

    @BeforeClass
    public void classSetUp() {
        System.out.println("Before Class setUp runs");
        Configuration.browser = "chrome";
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

    @AfterClass
    public void classCleanUp() {
        System.out.println("After Class cleanUp runs");
    }

    @AfterTest
    public void testCleanUp() {
        System.out.println("After Test CleanUp runs");
    }

    @AfterSuite
    public void suiteCleanUp() {
        System.out.println("After Suite Clean Up runs");
    }
}
