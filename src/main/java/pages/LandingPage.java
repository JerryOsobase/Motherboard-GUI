package pages;

import abstractComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {
    WebDriver driver;
    public LandingPage(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "userEmail")
    WebElement emailField;

    @FindBy(id = "userPassword")
    WebElement passwordField;

    @FindBy(id = "login")
    WebElement login;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;


    public void setLogin(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        login.click();
    }

    public String getErrorMessage(){
        visibilityOf(errorMessage);
       return errorMessage.getText();
    }

    public void getUrl(){

        driver.get("https://rahulshettyacademy.com/client");
    }


}
