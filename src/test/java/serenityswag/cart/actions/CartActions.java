package serenityswag.cart.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.inventory.pageObjects.ProductList;
import serenityswag.cart.pageObjects.ShoppingCartIcon;

import java.util.List;

public class CartActions extends UIInteractionSteps {

    @Step("Add to cart item '{0}'")
    public void addItem(String item) {
        $(ProductList.addToCartButtonFor(item)).click();
    }

    @Step("Add {0} to the cart")
    public void addItems(List<String> items) {
        items.forEach(this::addItem);
    }

    @Step("Open the shopping cart page")
    public void openCart() {
        $(ShoppingCartIcon.link()).click();
    }

    public List<String> displayItems() {
        return findAll(".inventory_item_name").texts();
    }
}
