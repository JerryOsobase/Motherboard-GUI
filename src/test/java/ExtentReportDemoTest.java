import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;

import java.io.IOException;

public class ExtentReportDemoTest extends Base{

    LandingPage landingPage;
    ExtentReports extentReport;

    @BeforeTest
    public void config(){
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setDocumentTitle("Selenium Framework Test Results");
        extentSparkReporter.config().setReportName("Automation report");

         extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Tester", "Jerry");


    }



    @Test
    public void initialDemo() throws IOException {
        extentReport.createTest("initialize");
       System.out.println(driver.getTitle());

        extentReport.flush();
    }
}
