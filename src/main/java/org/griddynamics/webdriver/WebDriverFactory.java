package org.griddynamics.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.griddynamics.config.Configuration;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {

    private static WebDriver driver;

    public void initialize() {
        String browser = "";
        Dimension dimension = new Dimension(0,0);
        if(driver==null) {
            if(isAnyPropertyNull()) {
            dimension = new Dimension(Integer
                    .parseInt(Configuration
                            .getRun()
                            .width()),
                    Integer
                            .parseInt(Configuration
                                    .getRun()
                                    .height()));

                browser = Configuration.getRun().browser();
            } else {
                dimension = new Dimension(Integer
                        .parseInt(System
                                .getProperty("browser.width")),
                        Integer
                                .parseInt(System
                                        .getProperty("browser.height")));

                browser = System.getProperty("browser.name").toLowerCase();
            }
            if(Configuration.getRun().isRemote()) {
                switch (browser) {
                    case "chrome":
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--no-sandbox");
                        chromeOptions.addArguments("--disable-dev-shm-usage");
                        chromeOptions.addArguments("--disable-gpu");
                        driver = createRemoteDriver(chromeOptions);
                        break;
                    case "firefox":
                        FirefoxOptions firefoxOptions = new FirefoxOptions();
                        driver = createRemoteDriver(firefoxOptions);
                        break;
                    default:
                    throw new IllegalArgumentException("Browser is not supported!");
                }
            } else {
                switch (browser) {
                    case "chrome":
                        driver = createChromeDriver();
                        break;
                    case "firefox":
                        driver = createFirefoxDriver();
                        break;
                    default:
                        throw new IllegalArgumentException("Browser is not supported!");
                }
            }
        }
        driver.manage().window().setSize(dimension);
        driver.get(Configuration.getEnv().siteUrl());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void quit() {
        driver.quit();
        driver=null;
    }

    private WebDriver createRemoteDriver(MutableCapabilities capabilities) {
        try {
            URL url = new URL(Configuration.getEnv().remoteUrl());
            return new RemoteWebDriver(url, capabilities);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException("Wrong URL");
        }
    }


    private WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().clearDriverCache().setup();
        return new ChromeDriver();
    }

    private WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private boolean isAnyPropertyNull() {
        return System.getProperty("browser.name")==null ||
                System.getProperty("browser.width")==null ||
                System.getProperty("browser.height")==null;
    }
}
