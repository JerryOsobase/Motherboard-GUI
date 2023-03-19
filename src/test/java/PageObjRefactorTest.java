import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.OrderItem;
import pages.ProductCatalogue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageObjRefactorTest extends Base{

    @Test(dataProvider = "getJsonData")
    public void intro(HashMap<String, String> data) throws IOException {
        OrderItem orderItem = new OrderItem(driver);
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        //String item = "adidas original";
        landingPage.setLogin(data.get("email"), data.get("password"));
        productCatalogue.selectItem(data.get("productName"));
        productCatalogue.getCartButton();
        Boolean match = orderItem.getCartItems(data.get("productName"));
        Assert.assertTrue(match);
        orderItem.getSubtotalButton();

        executor.executeScript("arguments[0].click();", orderItem.selectAddress());

        String confirmationMessage = orderItem.completeOrder();

        Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");

    }

    @DataProvider
    public Object[][] getData(){
        Map<String, String> data = Map.of("email", "jjdj@dkk.com", "password", "Testing1@", "productName", "ZARA COAT 3");
        HashMap<String, String> hashMap = new HashMap<>(data);

        Map<String, String> data1 = Map.of("email", "jerry@base.com", "password", "Base4jerry@", "productName", "adidas original");
        HashMap<String, String> hashMap1 = new HashMap<>(data1);

        return new Object[][] {{hashMap}, {hashMap1}};

    }

    @DataProvider
    public Object[][] getJsonData() throws IOException {
        List<HashMap<String, String>> data = getJsonToMap("//src//test//java//jsonData//data//dataParameters.json");

        return new Object[][] {{data.get(0)}, {data.get(1)}};

    }



}
