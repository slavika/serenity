package serenityswag.inventory.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.inventory.pageObjects.ProductList;

public class ViewProductDetailsActions extends UIInteractionSteps {

    @Step("View product details for product '{0}'")
    public void forProductWithName(String itemName) {
        $(ProductList.productDetailsLinkFor(itemName)).click();
    }
}
