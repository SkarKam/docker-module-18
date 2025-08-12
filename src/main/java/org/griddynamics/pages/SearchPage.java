package org.griddynamics.pages;

import org.griddynamics.models.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.NoSuchElementException;

public class SearchPage extends BasePage {

    private final By numberOfProducts = By.xpath("//div[@data-name='listingHeader']");
    private final By products = By.xpath("//div[@class='relative my-6']");
    private final By productsName = By.xpath(".//a/h2");
    private final By productPrice = By.xpath(".//div[@data-price-type]");
    private final By productPageGoTo = By.xpath(".//a[@href]/button[normalize-space()='Zobacz więcej']");
    private final By maxPageNumber = By.xpath("//li[@class='inline-flex items-center space-x-2']/p");
    private final By nextPage = By.xpath("//a[@aria-label='nawiguj do następnej strony']");
    private final By closeQuestionnaire = By.cssSelector("#yourcx_layer>.yourcx_close");

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    public int findNumberOfProducts() {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(numberOfProducts));
        WebElement productCount = driver.findElement(numberOfProducts);
        System.out.println(productCount.isDisplayed());
        if (!productCount.isDisplayed()) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productCount);
        }
        return Integer.parseInt(productCount.getAttribute("data-products-count"));
    }

    /**
     * Function to find a wanted product
     *
     * @param productFullName - full name of the searching product
     * @return - product with price.
     */
   public ProductPage goToProductPage(String productFullName) {
        while(true) {
            waiter.until(ExpectedConditions.visibilityOfElementLocated(products));
            List<WebElement> listOfProducts = driver.findElements(products);
            for (WebElement product : listOfProducts) {
                try{
                waiter.until(ExpectedConditions.presenceOfElementLocated(productsName));
                Thread.sleep(100);
                String productName = product.findElement(productsName).getText();
                if(productName.equals(productFullName)) {
                    try {
                        WebElement productPageButton = product.findElement(productPageGoTo);
                        waiter.until(ExpectedConditions.elementToBeClickable(productPageButton));
                        productPageButton.click();
                        waiter.until(ExpectedConditions.stalenessOf(productPageButton));
                        return new ProductPage(driver);
                    } catch (ElementClickInterceptedException questionnarePopUp) {

                    }
                    }
                } catch (StaleElementReferenceException stale) {
                    break;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                    waiter.until(ExpectedConditions.visibilityOfElementLocated(nextPage));
                    clickNextPageButton();
            } catch (TimeoutException exception) {
                throw new NoSuchElementException("Product not found!");
            }
        }

    }

    private void clickNextPageButton() {
        for(int i = 0; i<2; i++) {
            try {
                WebElement nextPageButton = driver.findElement(nextPage);
                nextPageButton.click();
                waiter.until(ExpectedConditions.stalenessOf(nextPageButton));
                break;
            } catch (StaleElementReferenceException ignored) {

            } catch (ElementClickInterceptedException questionnairePopUp) {
                questionnaireHandler();
            }
        }
    }

    private void questionnaireHandler () {
       waiter.until(ExpectedConditions.elementToBeClickable(closeQuestionnaire));
       WebElement quest = driver.findElement(closeQuestionnaire);
       quest.click();
    }
}
