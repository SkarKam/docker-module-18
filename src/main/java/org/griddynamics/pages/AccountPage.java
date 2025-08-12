package org.griddynamics.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccountPage extends BasePage{

    private final By logOutLink = By.xpath("//div[text()='(wyloguj)']");

    public AccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    public HomePage logOut() {
        waiter.until(ExpectedConditions.elementToBeClickable(logOutLink));
        WebElement logOutButton = driver.findElement(logOutLink);
        logOutButton.click();
        return new HomePage(this.driver);
    }
}
