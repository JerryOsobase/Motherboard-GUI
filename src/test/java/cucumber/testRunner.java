package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumber/order.feature", glue = "cucumber/stepDefinitions", monochrome = true, tags = "@E2E", plugin = {"html: reports/cucumber.html"})
public class testRunner extends AbstractTestNGCucumberTests {
}
