package cucumber.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import pages.OrderItem;
import pages.ProductCatalogue;
import utils.BrowserManager;

import java.io.IOException;

public class mySteps extends BrowserManager {

    ProductCatalogue productCatalogue;
    OrderItem orderItem;
    @Given("initialize browser and navigate to url")
    public void initialize_browser_and_navigate_to_url() throws IOException {
        launchBrowser();
    }

    @Given("^user login with email (.+) and password (.+)$")
    public void user_login_with_email_and_password(String email, String password){
        productCatalogue  = landingPage.setLogin(email, password);
    }

    @When("^user add product (.+) to cart$")
    public void user_add_product_to_cart(String productName){
        orderItem  = new OrderItem(driver);

        productCatalogue.selectItem(productName);
        productCatalogue.getCartButton();
        Boolean match = orderItem.getCartItems(productName);
        Assert.assertTrue(match);
        orderItem.getSubtotalButton();

    }

    @And("Select country and make payment")
    public void And_Select_country_and_make_payment(){
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", orderItem.selectAddress());


    }

    @Then("user can view success message {string}")
    public void user_can_view_success_message(String message){
        String confirmationMessage = orderItem.completeOrder();
        Assert.assertEquals(confirmationMessage, message);
        driver.close();
    }

}
