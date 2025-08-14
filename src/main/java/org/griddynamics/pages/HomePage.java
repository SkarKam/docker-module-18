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
        WebElement cookieAccButton = waiter.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
        cookieAccButton.click();
        return this;
    }

    public HomePage clickAccountIcon() {
        WebElement accountIcon = waiter.until(ExpectedConditions.elementToBeClickable(userLogInIcon));
        accountIcon.click();
        return this;
    }

    public boolean isLogInForm() {
        WebElement logForm = waiter.until(ExpectedConditions.elementToBeClickable(logInForm));
        return logForm.isDisplayed();
    }

    public SearchBar clickSearchBar() {
        WebElement search = waiter.until(ExpectedConditions.elementToBeClickable(searchBar));
        search.click();
        return new SearchBar(driver);
    }
}
