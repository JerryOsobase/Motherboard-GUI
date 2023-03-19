import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class StandaloneTest {

    @Test
    public void intro(){
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        String item = "adidas original";
        driver.findElement(By.id("userEmail")).sendKeys("jerry@base.com");
        driver.findElement(By.id("userPassword")).sendKeys("Base4jerry@");
        driver.findElement(By.id("login")).click();
       List<WebElement> brand = driver.findElements(By.xpath("//div[@class='card-body']/h5"));

      WebElement prod =  brand.stream().filter(v->v.getText().equalsIgnoreCase(item)).findFirst().orElse(null);
       prod.findElement(By.xpath("following-sibling::button[2]")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
        driver.findElement(By.cssSelector("button[routerLink*='/dashboard/cart']")).click();
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartItems.stream().anyMatch(v->v.getText().equalsIgnoreCase(item));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector("div[class*='subtotal'] button")).click();

        driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ni");

        List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button span"));

        wait.until(ExpectedConditions.visibilityOfAllElements(countries));

        executor.executeScript("arguments[0].click();",countries.stream().filter(v->v.getText().contains("Nigeria")).findFirst().get());

        driver.findElement(By.cssSelector("a.btnn")).click();

        String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

        Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");

        driver.close();

    }
}
