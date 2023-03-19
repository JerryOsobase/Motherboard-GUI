import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public LandingPage landingPage;


    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//global.properties");
        prop.load(fis);
        String browserName = System.getProperty("browser") !=null ? System.getProperty("browser") : prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("chrome")){
            ChromeOptions options = new ChromeOptions();
            options.addArguments("use-fake-ui-for-media-stream");
            options.addArguments("enable-automation");
            options.addArguments("--disable-infobars");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--remote-allow-origins=*");
            WebDriverManager.chromedriver().setup();
             driver = new ChromeDriver(options);
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

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchBrowser() throws IOException {
        initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.getUrl();
        return landingPage;
    }

    @AfterMethod(alwaysRun = true)
    public void terminate(){
        driver.close();
    }

    public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException {

        //read json to string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + filePath), StandardCharsets.UTF_8);

        //convert string to hashmap
        ObjectMapper objectMapper =  new ObjectMapper();
        List<HashMap<String, String>> data = objectMapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;
    }

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File destination = new File(System.getProperty("user.dir")+ "//reports//"+testCaseName+".png");
        FileUtils.copyFile(source, destination);
        return System.getProperty("user.dir")+ "//reports//"+testCaseName+".png";
    }
}
