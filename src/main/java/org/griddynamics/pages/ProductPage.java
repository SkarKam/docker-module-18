package org.griddynamics.pages;

import org.griddynamics.models.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends BasePage{

    private final By productName = By.xpath("//h1");
    private final By productPrice = By.xpath(".//div[@data-price-type]");
    private final By addToCartButton = By.xpath(".//button[@data-name='addToCartButton']");
    private final By goFutherButton = By.xpath("//button[@class='h-12 rounded px-8 text-sm font-semibold tracking-wide text-blue-smalt bg-white border border-blue-smalt hover:bg-main-gradient hover:text-white dark:border-blue-malibu dark:text-black-midnight mt-4 w-80 !px-2']");
    private final By goToCartPageButton = By.xpath("//div[@class='grid gap-2 sm:grid-cols-2']/button[normalize-space() = 'Przejdź do koszyka']");
    private final By backToShoppingButton = By.xpath("//div[@class='grid gap-2 sm:grid-cols-2']/button[normalize-space() = 'Wróć do zakupów']");
    private final By cartMobile = By.xpath("//div[@data-name='addToCartMobile']");
    private final By cartDesktop = By.xpath("//div[@data-name='addToCartDesktop']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public Product getProduct() {
        Product result = new Product();
        waiter.until(ExpectedConditions.visibilityOfElementLocated(productName));
        result.setName(driver.findElement(productName).getText());
        result.setPrice(getProductPriceByCartType(productPrice).getText());
        return result;
    }

    public ProductPage addToCart() {
        WebElement button = getAddToCartButtonFromCartType(addToCartButton);
        waiter.until(ExpectedConditions.elementToBeClickable(button));
        button.click();
        return this;
    }

    private WebElement getProductPriceByCartType(By by) {
        WebElement cartType = driver.findElement(cartDesktop);
        if(cartType.findElement(by).getText().isEmpty()) {
            cartType = driver.findElement(cartMobile);
        }
        return cartType.findElement(by);
    }

    private WebElement getAddToCartButtonFromCartType(By by) {
        waiter.until(ExpectedConditions
                .and(ExpectedConditions
                        .or(ExpectedConditions.visibilityOfElementLocated(cartMobile),
                            ExpectedConditions.visibilityOfElementLocated(cartDesktop)),
                        ExpectedConditions.presenceOfAllElementsLocatedBy(addToCartButton)));

        WebElement cartType = driver.findElement(cartDesktop);
        
        if (!cartType.findElement(by).isDisplayed()) {
            cartType = driver.findElement(cartMobile);
        }
        return cartType.findElement(by);
    }

    public ProductPage goFurther() {
        try {
            waiter.until(ExpectedConditions.elementToBeClickable(goFutherButton));
            driver.findElement(goFutherButton).click();
        } catch (TimeoutException ex) {
            System.out.println("One of pop-ups didn't load");
        } finally {
            return this;
        }
    }

    public CartPage goToCartPage() {
        waiter.until(ExpectedConditions.elementToBeClickable(goToCartPageButton));
        driver.findElement(goToCartPageButton).click();
        return new CartPage(driver);

    }

    public ProductPage backToShopping() {
        waiter.until(ExpectedConditions.elementToBeClickable(backToShoppingButton));
        driver.findElement(backToShoppingButton).click();
        return new ProductPage(driver);
    }
}
