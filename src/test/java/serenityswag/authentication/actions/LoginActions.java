package serenityswag.authentication.actions;

import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;
import serenityswag.authentication.pageObjects.LoginForm;
import serenityswag.authentication.User;

public class LoginActions extends UIInteractionSteps {

    @Step("Login as {0}")
    public void loginUsingCredentials(User user) {
        openUrl("https://www.saucedemo.com/");

        find(LoginForm.USER_NAME).sendKeys(user.getUsername());
        find(LoginForm.PASSWORD).sendKeys(user.getPassword());
        find(LoginForm.LOGIN_BUTTON).click();
    }
}
