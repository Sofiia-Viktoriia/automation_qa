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

public class MainPage {

    WebDriver driver;
    WebDriverWait waiter;

    private String authPasswordInputId = "auth_pass";
    private By authEmailInput = By.xpath("//input[@id='auth_email']");
    private By authFormTitle = By.xpath("//single-modal-window/div/div/h3[@class='modal__heading']");
    private By switchPasswordInputTypeButton = By.xpath("/html/body/app-root/single-modal-window/div[2]/div[2]/rz-user-identification/rz-auth/div/form/fieldset/div[2]/div/div/button");
    private By logInButton = By.xpath("/html/body/app-root/single-modal-window/div[2]/div[2]/rz-user-identification/rz-auth/div/form/fieldset/div[4]/button[1]");
    private By errorMessage = By.xpath("//p[@class='error-message ng-star-inserted']");
    private By cartWindow = By.xpath("/html/body/app-root/single-modal-window/div[2]");
    private By cartWindowBodyTitle = By.cssSelector("h4.cart-dummy__heading");
    private By laptopCategoryImage = By.xpath("//img[@alt='Ноутбуки']");
    private By promotionSlider = By.className("ng-star-inserted");
    @FindBy(xpath = "/html/body/app-root/div/div/rz-header/header/div/div/ul/li[3]/rz-user/button")
    private WebElement authButton;
    @FindBy(name = "search")
    private WebElement searchInput;
    @FindBy(xpath = "//button[text()=' Найти ']")
    private WebElement searchButton;
    @FindBy(className = "main-slider__pagination-link")
    private WebElement mainSliderLink;
    @FindBy(xpath = "/html/body/app-root/div/div/rz-header/header/div/div/ul/li[1]/rz-lang/ul/li[2]/a")
    private WebElement langUA;
    @FindBy(css = "body > app-root > div > div > rz-header > header > div > div > ul > li.header-actions__item.header-actions__item--cart > rz-cart > button")
    private WebElement cartButton;
    @FindBy(xpath = "//ul[@class='menu-categories ng-star-inserted']/li")
    private List<WebElement> allCategories;
    @FindBy(partialLinkText = "Ноутбуки и")
    private WebElement laptopCategory;
    @FindBy(xpath = "//a[text()=' Контакты ']")
    private WebElement contactsLink;
    @FindBy(css = "body > app-root > div > div > rz-main-page > div > main > main-page-content > goods-sections > section:nth-child(1) > goods-section > ul > li:nth-child(1) > app-tile > div > div > a.tile__title")
    private WebElement firstItemLink;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.waiter = new WebDriverWait(this.driver, 10);
        PageFactory.initElements(this.driver, this);
    }

    public void openPage() {
        driver.get("https://rozetka.com.ua/");
        String title = driver.getTitle();
        Assertions.assertEquals("Интернет-магазин ROZETKA™: официальный сайт самого популярного онлайн-гипермаркета в Украине", title);
    }

    public void openAuthForm() {
        authButton.click();
        WebElement title = waiter.until(ExpectedConditions.presenceOfElementLocated(authFormTitle));
        Assertions.assertEquals("Вход", title.getText());
    }

    public void checkPasswordInputTypeSwitch(String input) {
        WebElement password = driver.findElement(By.id(authPasswordInputId));
        password.sendKeys(input);
        driver.findElement(switchPasswordInputTypeButton).click();
        Assertions.assertEquals("text", password.getAttribute("type"));
    }

    public void checkWrongEmailError(String input) {
        WebElement email = driver.findElement(authEmailInput);
        email.sendKeys(input);
        driver.findElement(logInButton).click();
        WebElement error = waiter.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        Assertions.assertEquals("Введен неверный адрес эл.почты или номер телефона", error.getText());
    }

    public SearchItemPage findItem(String input) {
        searchInput.sendKeys(input);
        searchButton.click();
        return new SearchItemPage(driver);
    }

    public void checkLinkToAllPromotions() {
        Assertions.assertTrue(mainSliderLink.getText().contains("Все акции"));
    }

    public void changeLangToUA() {
        langUA.click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(promotionSlider).isDisplayed();
            }
        });
        Assertions.assertEquals("Я шукаю...", searchInput.getAttribute("placeholder"));
    }

    public void checkCartIsEmpty() {
        cartButton.click();
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(cartWindow).isDisplayed();
            }
        });
        WebElement text = driver.findElement(cartWindowBodyTitle);
        Assertions.assertEquals("Корзина пуста", text.getText());
    }

    public void checkCategoryAmount(int amount) {
        waiter.until(new ExpectedCondition<Boolean>() {
            @NullableDecl
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return driver.findElement(promotionSlider).isDisplayed();
            }
        });
        Assertions.assertEquals(amount, allCategories.size());
    }

    public void checkCategoryImage() {
        laptopCategory.click();
        WebElement image = waiter.until(ExpectedConditions.presenceOfElementLocated(laptopCategoryImage));
        String src = image.getAttribute("src");
        Assertions.assertEquals("png", src.substring(src.length() - 3));
    }

    public ContactsPage openContactsPage() {
        contactsLink.click();
        return new ContactsPage(driver);
    }

    public ItemPage openFirstItemPage() {
        firstItemLink.click();
        return new ItemPage(driver);
    }
}
