
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import page.MainPage;
import java.util.concurrent.TimeUnit;

public class PageObjectTests {

    private final String PASSWORD = "123456789";
    private final String WRONG_EMAIL = "someone";
    private final String SEARCH_INPUT = "вафельница";
    private final String SEARCH_TITLE = "Вафельницы";
    private final int CATEGORY_AMOUNT = 19;

    WebDriver driver;
    MainPage mainPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "../driver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        mainPage.openPage();
        mainPage.openAuthForm();
        mainPage.checkPasswordInputTypeSwitch(PASSWORD);
    }

    @Test
    public void secondTest() {
        mainPage.openPage();
        mainPage.openAuthForm();
        mainPage.checkWrongEmailError(WRONG_EMAIL);
    }

    @Test
    public void thirdTest() {
        mainPage.openPage();
        mainPage.findItem(SEARCH_INPUT)
                .checkTitle(SEARCH_TITLE);
    }

    @Test
    public void fourthTest() {
        mainPage.openPage();
        mainPage.findItem(SEARCH_INPUT)
                .returnToMainPage()
                .checkLinkToAllPromotions();
    }

    @Test
    public void fifthTest() {
        mainPage.openPage();
        mainPage.changeLangToUA();
    }

    @Test
    public void sixthTest() {
        mainPage.openPage();
        mainPage.checkCartIsEmpty();
    }

    @Test
    public void seventhTest() {
        mainPage.openPage();
        mainPage.checkCategoryAmount(CATEGORY_AMOUNT);
    }

    @Test
    public void eightTest() {
        mainPage.openPage();
        mainPage.checkCategoryImage();
    }

    @Test
    public void ninthTest() {
        mainPage.openPage();
        mainPage.openContactsPage()
                .checkContactsPresence();
    }

    @Test
    public void tenthTest() {
        mainPage.openPage();
        mainPage.openFirstItemPage()
                .checkItemAvailability();
    }
}
