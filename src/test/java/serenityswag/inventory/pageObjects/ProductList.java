package serenityswag.inventory.pageObjects;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

import java.util.List;

public class ProductList extends PageObject {

    public static By productDetailsLinkFor(String itemName) {
        return By.linkText(itemName);
    }

    public static By addToCartButtonFor(String item) {
        return By.xpath("//div[@class='inventory_item_description'][contains(., '" + item + "')]//button");
    }

    public List<String> getTitles() {
        return findAll(net.serenitybdd.core.annotations.findby.By.className("inventory_item_name")).textContents();
    }

}
