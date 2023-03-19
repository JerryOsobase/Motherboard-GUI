import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNg {

    public static ExtentReports config(){
        String path = System.getProperty("user.dir")+"//reports//index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setDocumentTitle("Selenium Framework Test Results");
        extentSparkReporter.config().setReportName("Automation report");

        ExtentReports  extentReport = new ExtentReports();
        extentReport.attachReporter(extentSparkReporter);
        extentReport.setSystemInfo("Tester", "Jerry");
        return extentReport;

    }

}
