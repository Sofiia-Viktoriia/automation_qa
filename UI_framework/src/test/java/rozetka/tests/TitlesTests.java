package rozetka.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static rozetka.logic.pages.HeaderMenu.*;
import static rozetka.logic.pages.ItemPage.*;
import static rozetka.logic.pages.MainPage.*;
import static rozetka.logic.pages.SearchPage.*;

public class TitlesTests extends BaseTest {

    @DataProvider(name = "searchItemDataUA")
    public static Object[][] searchItemDataUA() {
        return new Object[][]{
                {"вафельница", "Вафельниці"},
                {"телефон", "Мобільні телефони"},
                {"ноутбук", "Ноутбуки"},
                {"наушники", "Навушники"}
        };
    }

    @DataProvider(name = "searchItemData")
    public static Object[][] searchItemData() {
        return new Object[][]{
                {"вафельница", "Вафельницы"},
                {"телефон", "Мобильные телефоны"},
                {"ноутбук", "Ноутбуки"},
                {"наушники", "Наушники"}
        };
    }

    @DataProvider(name = "itemMenuTitles")
    public static Object[][] itemMenuTitles() {
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
    public static Object[][] itemMenuTitlesUA() {
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
}
