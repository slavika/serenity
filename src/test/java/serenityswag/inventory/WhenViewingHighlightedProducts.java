package serenityswag.inventory;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.User;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.inventory.actions.ViewProductDetailsActions;
import serenityswag.inventory.pageObjects.ProductDetails;
import serenityswag.inventory.pageObjects.ProductList;

import java.util.List;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenViewingHighlightedProducts {

    @Managed
    WebDriver driver;

    @Steps
    LoginActions login;

    @Steps
    ViewProductDetailsActions viewProductDetails;

    ProductList productList;
    ProductDetails productDetails;

    @BeforeEach
    public void login() {
        login.loginUsingCredentials(User.STANDARD_USER);
    }

    @Test
    public void shouldDisplayHighlightedProductsOnTheWelcomePage() {
        List<String> productNames = productList.getTitles();

        Serenity.reportThat("Number of elements is 6", () -> Assertions.assertEquals(productNames.size(), 6));
    }

    @Test
    public void shouldDisplayCorrectProductDetailsPage() {
        String firstElementName = productList.getTitles().get(0);

        viewProductDetails.forProductWithName(firstElementName);

        Serenity.reportThat("Product name should be correctly displayed", () -> Assertions.assertEquals(productDetails.getProductName(), firstElementName));
        Serenity.reportThat("Product image should have correct alt text",() ->  productDetails.productImageWithAltValueOf(firstElementName).shouldBeVisible());
    }


}
