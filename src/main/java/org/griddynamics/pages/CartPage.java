package org.griddynamics.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{

    private final By finalPrice = By.xpath("//p[@class='font-semibold leading-7 text-lg']");
    private final By productsInCart = By.xpath("//article[@data-name='cartProductGroup']");
    private final By moreOptions = By.xpath("//aside[@data-name='handyMenu']");
    private final By removeProduct = By.xpath("//div[@data-name='deleteProduct']");
    private final By removeAllProductsButton = By.xpath("//button[normalize-space()='Wyczyść koszyk']");
    private final By removeAllProductsSubmitButton = By.xpath("//button[normalize-space()='Wyczyść']");
    private final By emptyCartMessage = By.xpath("//div/span[text()='Koszyk jest pusty']");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTotalPrice() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(finalPrice));
        return driver.findElement(finalPrice).getText();
    }

    public CartPage removeAllItems() {
        Actions rollDown = new Actions(driver);
        try {
            WebElement removeButton = driver.findElement(removeAllProductsButton);
        } catch (NoSuchElementException notVisibleInDOM) {
            rollDown
                    .scrollByAmount(0, driver.manage().window().getSize().getHeight()-1)
                    .perform();
            }

        waiter.until(ExpectedConditions.visibilityOfElementLocated(removeAllProductsButton));
        waiter.until(ExpectedConditions.elementToBeClickable(removeAllProductsButton));
        WebElement removeButton = driver.findElement(removeAllProductsButton);
        removeButton.click();
        return new CartPage(driver);
    }

    public CartPage submitCleaning() {
        waiter.until(ExpectedConditions.elementToBeClickable(removeAllProductsSubmitButton));
        driver.findElement(removeAllProductsSubmitButton).click();
        return this;
    }

    public String getNumberOfProductsInCart() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage));
        return driver.findElement(emptyCartMessage).getText();
    }
}
