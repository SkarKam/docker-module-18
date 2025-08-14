package org.griddynamics.components;

import org.griddynamics.pages.AccountPage;
import org.griddynamics.pages.CartPage;
import org.griddynamics.pages.StoreLocationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ActionNavBar {

    private WebDriver driver;

    private final By logInIcon = By.xpath("//div[contains(@class, 'cursor-pointer') and .//span[text()='Zaloguj']]");
    private final By yourAccountIcon = By.xpath("//div/a[@href='/customer/account/']");
    private final By contactAndHelpIcon = By.xpath("//a[@aria-label='Kontakt i pomoc']");
    private final By findStore = By.xpath("//a[@aria-label='Znajdź salon']");
    private final By cartIcon = By.xpath("//a[contains(@href,'/cart') and .//span[text()='Koszyk']]");


    public ActionNavBar(WebDriver driver) {
        this.driver=driver;
    }

    public LogInModal goToLogInForm(Class pageClass) {
        WebElement logInButton = driver.findElement(logInIcon);
        logInButton.click();
        return new LogInModal(this.driver, pageClass);
    }

    public AccountPage goToAccountPage() {
        WebElement yourAccountButton = driver.findElement(yourAccountIcon);
        yourAccountButton.click();
        return new AccountPage(this.driver);
    }

    public CartPage goToCartPage () {
        WebElement cartIconButton = driver.findElement(cartIcon);
        cartIconButton.click();
        return new CartPage(driver);
    }

    public StoreLocationPage goToFindStore () {
        WebElement findStoreButton = driver.findElement(findStore);
        findStoreButton.click();
        return new StoreLocationPage(driver);
    }
}
