package pages;

import abstractComponent.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductCatalogue extends AbstractComponents {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='card-body']/h5")
    List<WebElement> brand;

    @FindBy(css = ".ng-animating")
    WebElement spinner;

    @FindBy(css = "button[routerLink*='/dashboard/cart']")
    WebElement cartButton;

    public void selectItem(String item){
        WebElement prod =  brand.stream().filter(v->v.getText().equalsIgnoreCase(item)).findFirst().orElse(null);
        prod.findElement(By.xpath("following-sibling::button[2]")).click();
        invisibilityOf(spinner);
    }

    public void getCartButton(){
        cartButton.click();
    }



}
