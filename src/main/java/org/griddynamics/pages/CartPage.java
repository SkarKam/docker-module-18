package org.griddynamics.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage{

    private final By finalPrice = By.xpath("//p[@class='font-semibold leading-7 text-lg']");
    private final By removeAllProductsButton = By.xpath("//button[normalize-space()='Wyczyść koszyk']");
    private final By removeAllProductsSubmitButton = By.xpath("//button[normalize-space()='Wyczyść']");
    private final By emptyCartMessage = By.xpath("//div/span[text()='Koszyk jest pusty']");

    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String getTotalPrice() {
        return waiter.until(ExpectedConditions.visibilityOfElementLocated(finalPrice)).getText();
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
        WebElement removeButton = waiter.until(ExpectedConditions.elementToBeClickable(removeAllProductsButton));
        removeButton.click();
        return new CartPage(driver);
    }

    public CartPage submitCleaning() {
        waiter.until(ExpectedConditions.elementToBeClickable(removeAllProductsSubmitButton)).click();
        return this;
    }

    public String getNumberOfProductsInCart() {
        return waiter.until(ExpectedConditions.visibilityOfElementLocated(emptyCartMessage)).getText();
    }
}
