package org.griddynamics.pages;

import org.griddynamics.components.SearchBar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{

    private final By cookieAcceptButton = By.cssSelector("button#onetrust-accept-btn-handler");
    private final By userLogInIcon = By.xpath("//div[contains(@class, 'cursor-pointer') and .//span[text()='Zaloguj']]");
    private final By logInForm = By.xpath("//h2[contains(text(),'Logowanie')]");
    private final By searchBar = By.xpath("//div/input[@class='w-full px-4 outline-none placeholder:text-gray-storm']");

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage acceptCookies() {
        waiter.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
        WebElement cookieAccButton = driver.findElement(cookieAcceptButton);
        cookieAccButton.click();
        return this;
    }

    public HomePage clickAccountIcon() {
        WebElement accountIcon = driver.findElement(userLogInIcon);
        accountIcon.click();
        return this;
    }

    public boolean isLogInForm() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(logInForm));
        WebElement logForm = driver.findElement(logInForm);
        return logForm.isDisplayed();
    }

    public SearchBar clickSearchBar() {
        WebElement search = driver.findElement(searchBar);
        search.click();
        return new SearchBar(driver);
    }
    // Old allegro stuff
   /* public LogInPage clickUserLogInButton() {
        WebElement loginButton = driver.findElement(userLogInButton);
        if(!loginButton.isDisplayed()) {
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);");
        }
        loginButton.click();
        return new LogInPage(driver);
    }*/
}
