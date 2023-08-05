package pages;

import abstractComponent.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchPage extends AbstractComponents {
    WebDriver driver;
    public SearchPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "q")
    private WebElement searchBar;

    public void makeSearch(String text) throws InterruptedException {
        searchBar.sendKeys(text);
        searchBar.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
    }
}
