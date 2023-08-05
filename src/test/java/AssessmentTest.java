import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.ProfilePage;
import pages.SearchPage;
import utils.BrowserManager;

import java.io.IOException;

public class AssessmentTest extends BrowserManager {

    ProfilePage profilePage;

    SearchPage searchPage;


    @Test(priority = 1)
    @Parameters({"email", "password"})
    public void Facebook(String email, String password){
        profilePage = landingPage.setLogin(email, password);
        String postMessage = "I Kill Bugs";
        String confirmPost = profilePage.MakeAPost(postMessage);
        Assert.assertEquals(confirmPost, postMessage);
    }

    @Test(priority = 2)
    public void Google() throws InterruptedException {
        driver.get("https://www.google.com/");
        searchPage = new SearchPage(driver);
        searchPage.makeSearch("best QA Engineering practices");

    }

}
