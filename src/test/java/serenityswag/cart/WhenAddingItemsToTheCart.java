package serenityswag.cart;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.authentication.User;
import serenityswag.cart.actions.CartActions;
import serenityswag.cart.pageObjects.CartPageObject;
import serenityswag.inventory.pageObjects.ProductList;
import serenityswag.cart.pageObjects.ShoppingCartIcon;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenAddingItemsToTheCart {

    @Managed
    WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    CartActions cart;

    ShoppingCartIcon shoppingCartBadge;
    ProductList productList;

    @BeforeEach
    public void login() {
        login.loginUsingCredentials(User.STANDARD_USER);
    }

    @Test
    public void theCorrectItemCountShouldBeShown() {
        Serenity.reportThat("Shopping cart badge should be empty", () -> Assertions.assertTrue(shoppingCartBadge.badgeCount().isEmpty()));

        cart.addItem("Sauce Labs Backpack");

        Serenity.reportThat("Shopping cart should contain 1 product", () -> Assertions.assertEquals(shoppingCartBadge.badgeCount(), "1"));
    }

    @Test
    public void allTheItemShouldAppearInTheCart() {
        List<String> selectedTitles = productList.getTitles().subList(0, 3);
        cart.addItems(selectedTitles);
        cart.openCart();

        Serenity.reportThat("Should see all of items listed", () -> Assertions.assertEquals(cart.displayItems(), selectedTitles));
    }

    CartPageObject cartPage;

    @Test
    public void pricesForAllItemsShouldBeShownInTheCart() {
        List<String> selectedTitles = productList.getTitles().subList(0, 3);
        cart.addItems(selectedTitles);

        Serenity.reportThat("Go to the cart page",() ->  cartPage.open());

        List<CartItem> cartItems = cartPage.items();

        Serenity.reportThat("Number of items should be 3 and each has a price over 0.0",() ->  assertThat(cartItems).hasSize(3)
                .allMatch(cartItem -> cartItem.price() > 0));
    }
}
