package pages;

import abstractComponent.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends AbstractComponents {

    WebDriver driver;
    public ProfilePage(WebDriver driver) {
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy( xpath= "//div[@aria-label='Create a post'] //span[contains(text(),\"What's on your mind\")]")
    private WebElement postField;

    @FindBy( css= "div[aria-label*=\"What's on your mind\"] p")
    private WebElement popUpPostField;

    @FindBy( css= "div[aria-label*='Post']")
    private WebElement postButton;

    @FindBy( css= "div[class*='xdj266r x11i5rnm xat24cr x1mh8g0r x1vvkbs']")
    private WebElement recentPost;

    public String MakeAPost(String text){
        postField.click();
        popUpPostField.sendKeys(text);
        postButton.click();
        invisibilityOf(postButton);
        return recentPost.getText();

    }
}
