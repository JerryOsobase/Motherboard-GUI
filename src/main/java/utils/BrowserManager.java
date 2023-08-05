package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BrowserManager {

    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//global.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
        if(browserName.toLowerCase().contains("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("use-fake-ui-for-media-stream");
            options.addArguments("enable-automation");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            if(browserName.toLowerCase().contains("headless")){
                options.addArguments("headless");
            }
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver(options);
             driver.manage().window().setSize(new Dimension(1920, 1080));
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            //dd
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        else if (browserName.equalsIgnoreCase("edge")) {
            //dd
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeClass(alwaysRun = true)
    @Parameters({"url"})
    public LandingPage launchBrowser(String url) throws IOException {
        initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.getUrl(url);
        return landingPage;
    }

    @AfterClass(alwaysRun = true)
    public void terminate(){
        driver.close();
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+ "//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, destination);
        return System.getProperty("user.dir")+ "//reports//"+testCaseName+".png";
    }
}
