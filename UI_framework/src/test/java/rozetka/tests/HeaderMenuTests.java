package rozetka.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static rozetka.logic.pages.HeaderMenu.*;
import static rozetka.logic.pages.ItemPage.*;
import static rozetka.logic.pages.MainPage.*;

public class HeaderMenuTests extends BaseTest {

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
    public void checkAuthRegisterForm() {
        openRegisterForm();
    }
}
