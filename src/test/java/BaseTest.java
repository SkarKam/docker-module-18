import org.griddynamics.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;

public class BaseTest {


    WebDriverFactory webDriverFactory;
    WebDriver webDriver;

    @BeforeMethod
    public void setUp() throws MalformedURLException, InterruptedException {
        webDriverFactory = new WebDriverFactory();
        webDriverFactory.initialize();
        webDriver=webDriverFactory.getDriver();


    }


    @AfterMethod
    public void tearDown() {
        webDriverFactory.quit();
    }
}
