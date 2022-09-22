package serenityswag.authentication;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Managed;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import serenityswag.authentication.actions.LoginActions;
import serenityswag.inventory.pageObjects.InventoryPage;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenLoggingOn extends UIInteractionSteps {

    @Managed
    WebDriver driver;

    LoginActions login;

    InventoryPage inventoryPage;

    @Test
    public void usersCanLoginViaHomePage(){
        login.loginUsingCredentials(User.STANDARD_USER);

        Serenity.reportThat("The inventory page should be displayed with correct title",
                () -> Assertions.assertEquals(inventoryPage.getHeading(), "PRODUCTS"));

    }



}
