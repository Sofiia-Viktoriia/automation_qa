import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class seleniumTests {

    WebDriver driver;
    WebDriverWait waiter;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "../driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("https://rozetka.com.ua/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        waiter = new WebDriverWait(driver, 10);
    }

    @After
    public void quit() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        driver.findElement(By.xpath("/html/body/app-root/div/div/rz-header/header/div/div/ul/li[3]/rz-user/button")).click();
        waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[@class='modal__heading']")));
        WebElement password = driver.findElement(By.id("auth_pass"));
        password.sendKeys("123456789");
        driver.findElement(By.xpath("/html/body/app-root/single-modal-window/div[2]/div[2]/rz-user-identification/rz-auth/div/form/fieldset/div[2]/div/div/button")).click();
        assert password.getAttribute("type").equals("text");
    }

    @Test
    public void secondTest() {
        driver.findElement(By.xpath("/html/body/app-root/div/div/rz-header/header/div/div/ul/li[3]/rz-user/button")).click();
        WebElement email = driver.findElement(By.xpath("//input[@id='auth_email']"));
        email.sendKeys("someone");
        driver.findElement(By.xpath("/html/body/app-root/single-modal-window/div[2]/div[2]/rz-user-identification/rz-auth/div/form/fieldset/div[4]/button[1]")).click();
        WebElement error = waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error-message ng-star-inserted']")));
        assert error.getText().equals("Введен неверный адрес эл.почты или номер телефона");
    }

    @Test
    public void thirdTest() {
        WebElement input = driver.findElement(By.name("search"));
        input.sendKeys("вафельница");
        driver.findElement(By.xpath("//button[text()=' Найти ']")).click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElements(By.xpath("//div/div/a/img")).get(0).isDisplayed();
            }
        });
        WebElement title = driver.findElement(By.xpath("//h1[@class='catalog-heading ng-star-inserted']"));
        assert title.getText().equals("Вафельницы");
    }

    @Test
    public void fourthTest() {
        WebElement input = driver.findElement(By.name("search"));
        input.sendKeys("вафельница");
        driver.findElement(By.xpath("//button[text()=' Найти ']")).click();
        waiter.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > app-root > div > div > rz-header > header > div > div > a > picture > img"))).click();
        WebElement text = driver.findElement(By.className("main-slider__pagination-link"));
        assert text.getText().contains("Все акции");
    }

    @Test
    public void fifthTest() {
        driver.findElement(By.xpath("/html/body/app-root/div/div/rz-header/header/div/div/ul/li[1]/rz-lang/ul/li[2]/a")).click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.className("ng-star-inserted")).isDisplayed();
            }
        });
        String input = driver.findElement(By.name("search")).getAttribute("placeholder");
        assert input.equals("Я шукаю...");
    }

    @Test
    public void sixthTest() {
        driver.findElement(By.cssSelector("body > app-root > div > div > rz-header > header > div > div > ul > li.header-actions__item.header-actions__item--cart > rz-cart > button")).click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.xpath("/html/body/app-root/single-modal-window/div[2]")).isDisplayed();
            }
        });
        WebElement text = driver.findElement(By.cssSelector("h4.cart-dummy__heading"));
        assert text.getText().equals("Корзина пуста");
    }

    @Test
    public void seventhTest() {
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.tagName("app-slider")).isDisplayed();
            }
        });
        List<WebElement> categories = driver.findElements(By.xpath("//ul[@class='menu-categories ng-star-inserted']/li"));
        Assert.assertEquals(19, categories.size());
    }

    @Test
    public void eightTest() {
        driver.findElement(By.partialLinkText("Ноутбуки и")).click();
        WebElement image = waiter.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='Ноутбуки']")));
        String src = image.getAttribute("src");
        assert src.contains("png");
    }

    @Test
    public void ninthTest() {
        driver.findElement(By.xpath("//a[text()=' Контакты ']")).click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(By.tagName("rz-map")).isDisplayed();
            }
        });
        List<WebElement> contacts = driver.findElements(By.cssSelector("div.contacts-main__cell > a"));
        assert contacts.size() >= 1;
    }

    @Test
    public void tenthTest() {
        driver.findElement(By.cssSelector("body > app-root > div > div > rz-main-page > div > main > main-page-content > goods-sections > section:nth-child(1) > goods-section > ul > li:nth-child(1) > app-tile > div > div > a.tile__title")).click();
        waiter.until(ExpectedConditions.elementToBeClickable(By.className("picture-container__picture")));
        WebElement status = driver.findElement(By.xpath("//rz-status-label/p"));
        assert status.getText().equals("Есть в наличии");
    }
}
