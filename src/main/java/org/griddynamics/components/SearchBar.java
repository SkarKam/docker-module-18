package org.griddynamics.components;

import org.griddynamics.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchBar {

    private WebDriver driver;

    private final By inputField = By.xpath("//input[@placeholder='Wpisz czego szukasz']");
    private final By searchButton = By.xpath("//button[@title='search-button']") ;

    public SearchBar(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public SearchPage searchForProduct(String product) {
        WebDriverWait waiter = new WebDriverWait(this.driver,Duration.ofSeconds(10));
        WebElement field = waiter.until(ExpectedConditions.visibilityOfElementLocated(inputField));
        WebElement searchBut = waiter.until(ExpectedConditions.elementToBeClickable(searchButton));
        if(product!=null) {
            field.sendKeys(product);
            searchBut.click();
        }
        return new SearchPage(driver);
    }
}
