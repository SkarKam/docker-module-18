package org.griddynamics.pages;

import lombok.Getter;
import org.griddynamics.components.ActionNavBar;
//import org.griddynamics.components.ManagementNavBar;
import org.griddynamics.components.SearchBar;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    WebDriver driver;
    Wait<WebDriver> waiter;
    @Getter
    private SearchBar searchBar;
    @Getter
    private ActionNavBar actionNavBar;
    //@Getter
    //private ManagementNavBar managementNavBar;

    @FindBy
    private final By popUpInformation = By.xpath("//ul[@class='relative']/li[@data-name='toast']/div");

    public BasePage(WebDriver webDriver) {
        this.driver = webDriver;
        waiter = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        this.searchBar = new SearchBar(driver);
        this.actionNavBar = new ActionNavBar(driver);
    }

    public String popUpAfterAccountLog() {
        try {
            waiter.until(ExpectedConditions.visibilityOfElementLocated(popUpInformation));
            return driver.findElement(popUpInformation).getText();
        } catch (TimeoutException timeout) {
            throw new TimeoutException("Pop up is gone");
        } catch (StaleElementReferenceException staleElementReferenceException) {
            throw new StaleElementReferenceException("Element is no longer in DOM");
        }
    }

    public HomePage waitForPopUpClose()  {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(popUpInformation));
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(popUpInformation));
        return new HomePage(driver);
    }
}
