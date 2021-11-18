import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static pages.BasePage.*;
import static pages.ItemPage.*;
import static pages.MainPage.*;
import static pages.SearchPage.checkSearchTitle;

public class SelenideTests extends BaseTest {

    private final int itemIndexToSelect = 0;

    @DataProvider(name = "searchItemDataUA")
    public Object[][] searchItemDataUA() {
        return new Object[][]{
                {"вафельница", "Вафельниці"},
                {"телефон", "Мобільні телефони"},
                {"ноутбук", "Ноутбуки"},
                {"наушники", "Навушники"}
        };
    }

    @DataProvider(name = "searchItemData")
    public Object[][] searchItemData() {
        return new Object[][]{
                {"вафельница", "Вафельницы"},
                {"телефон", "Мобильные телефоны"},
                {"ноутбук", "Ноутбуки"},
                {"наушники", "Наушники"}
        };
    }

    @DataProvider(name = "itemMenuTitles")
    public Object[][] itemMenuTitles() {
        return new Object[][]{
                {0, "Все о товаре"},
                {1, "Характеристики"},
                {2, "Отзывы"},
                {3, "Вопросы"},
                {4, "Видео"},
                {5, "Фото"},
                {6, "Покупают вместе"}
        };
    }

    @DataProvider(name = "itemMenuTitlesUA")
    public Object[][] itemMenuTitlesUA() {
        return new Object[][]{
                {0, "Усе про товар"},
                {1, "Характеристики"},
                {2, "Відгуки"},
                {3, "Питання"},
                {4, "Відео"},
                {5, "Фото"},
                {6, "Купують разом"}
        };
    }

    @DataProvider(name = "authEmailData")
    public Object[][] authEmailData() {
        return new Object[][]{
                {"email"},
                {"realemail@"},
                {"reallyrealemail@yes"},
                {"@yes.com"}
        };
    }

    @Test
    public void checkItemTitlePresence() {
        selectItemByIndex(itemIndexToSelect);
        checkTitlePresence();
    }

    @Test(dataProvider = "searchItemData")
    public void checkSearchItemsTitle(String search, String title) {
        searchItem(search);
        checkSearchTitle(title);
    }

    @Test
    public void checkReturningToMainPage() {
        selectItemByIndex(itemIndexToSelect);
        returnToMainPage();
    }

    @Test(dataProvider = "authEmailData")
    public void checkInvalidAuthEmail(String email) {
        openAuthForm();
        checkWrongEmailError(email);
    }

    @Test
    public void checkCartIsEmpty() {
        openCart();
        checkEmptyCart();
    }

    @Test
    public void checkCategoryAmount() {
        checkAmountOfCategories();
    }

    @Test
    public void checkAuthFormAppearAfterAddingToWishList() {
        selectItemByIndex(itemIndexToSelect);
        addToWishListWithoutAuthorizing();
    }

    @Test
    public void checkPasswordInputTypeSwitch() {
        openAuthForm();
        checkPasswordToVisible();
    }

    @Test
    public void checkChangingLangToUA() {
        changeLangToUA();
    }

    @Test
    public void checkBuyButtonTitle() {
        selectItemByIndex(itemIndexToSelect);
        checkTitleOfBuyButton();
    }

    @Test
    public void checkBuyButtonTitleInUA() {
        changeLangToUA();
        selectItemByIndex(itemIndexToSelect);
        checkTitleOfBuyButtonInUA();
    }

    @Test(dataProvider = "searchItemDataUA")
    public void checkSearchTitleInUA(String search, String title) {
        searchItem(search);
        changeLangToUA();
        checkSearchTitle(title);
    }

    @Test(dataProvider = "itemMenuTitles")
    public void checkItemMenuTitles(int optionIndex, String expectedTitle) {
        selectItemByIndex(itemIndexToSelect);
        checkItemMenuTitle(optionIndex, expectedTitle);
    }

    @Test(dataProvider = "itemMenuTitlesUA")
    public void checkItemMenuTitlesInUA(int optionIndex, String expectedTitle) {
        changeLangToUA();
        selectItemByIndex(itemIndexToSelect);
        checkItemMenuTitle(optionIndex, expectedTitle);
    }

    @Test
    public void checkAuthRegisterForm() {
        openRegisterForm();
    }
}
