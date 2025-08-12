package org.griddynamics.components;

import org.griddynamics.pages.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBar {

    private WebDriver driver;
    private final By inputField = By.xpath("//input[@placeholder='Wpisz czego szukasz']");
    private final By searchButton = By.xpath("//button[@title='search-button']") ;

    public SearchBar(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public SearchPage searchForProduct(String product) {
        WebElement field = driver.findElement(inputField);
        WebElement searchBut = driver.findElement(searchButton);
        if(product!=null) {
            field.sendKeys(product);
            searchBut.click();
        }
        return new SearchPage(driver);
    }
}
