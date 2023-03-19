import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationTest extends Base{

    @Test(groups = "errorHandling", retryAnalyzer = Retry.class)
    public void errorLogin() throws IOException {
        landingPage.setLogin("jerry@base.com", "Base4jery@");

        Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password");

    }
}
