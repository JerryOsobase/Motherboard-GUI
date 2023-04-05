import org.testng.Assert;
import org.testng.annotations.Test;
import utils.BrowserManager;
import utils.Retry;

import java.io.IOException;

public class ErrorValidationTest extends BrowserManager {

    @Test(groups = "errorHandling", retryAnalyzer = Retry.class)
    public void errorLogin() throws IOException {
        landingPage.setLogin("jerry@base.com", "Base4jery@");

        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");

    }
}
