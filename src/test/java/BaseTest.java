import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {


    WebDriver webDriver;

    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        WebDriverManager.chromiumdriver().clearDriverCache().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        //chromeOptions.addArguments("--headless=new"); // use "--headless" if "new" causes issues
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");


        //URL remoteURL = new URL("http://localhost:4444/");
        URL remoteURL = new URL("http://selenium-hub:4444/");
        //webDriver = new RemoteWebDriver(remoteURL,chromeOptions);
        webDriver = new ChromeDriver();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        //webDriver.manage().window().setSize(new Dimension(1920,1280));
        webDriver.manage().window().maximize();
        webDriver.get("https://www.komputronik.pl/");

    }


    @AfterMethod
    public void tearDown() {
        webDriver.quit();
    }
}
