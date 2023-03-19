package pages;

import abstractComponent.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderItem extends AbstractComponents {
    WebDriver driver;
    public OrderItem(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cartSection h3")
    List<WebElement> cartItems;

    @FindBy(css = ".ta-results button span")
    List<WebElement> countries;

    @FindBy(css = "div[class*='subtotal'] button")
    WebElement subtotalButton;

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement countryField;

    @FindBy(css = "a.btnn")
    WebElement okButton;

    @FindBy(css = ".hero-primary")
    WebElement confirmationMessage;

    public String completeOrder(){
        okButton.click();
        return confirmationMessage.getText();
    }

    public Boolean getCartItems(String item){
        Boolean match = cartItems.stream().anyMatch(v->v.getText().equalsIgnoreCase(item));
        return match;
    }

    public WebElement selectAddress(){
        countryField.sendKeys("ni");
        visibilityOfAllElements(countries);
       WebElement address = countries.stream().filter(v->v.getText().contains("Nigeria")).findFirst().get();
       return address;
    }
    public void getSubtotalButton(){
        subtotalButton.click();
    }



}
