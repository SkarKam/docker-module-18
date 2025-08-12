package org.griddynamics.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;
import java.util.NoSuchElementException;

public class StoreLocationPage extends BasePage{

    private final By storeListScroll = By.xpath("//div[@data-name='parcelList']");
    private final By storeListWrapper = By.xpath("//div[@class='vue-recycle-scroller__item-wrapper']");
    private final By storeList = By.xpath("//div[@class='vue-recycle-scroller__item-view']");
    private final By storeName = By.xpath(".//p[@class='line-clamp-3 pt-0.5 font-semibold max-w-[78%]']");
    private final By storeSelectIndicator = By.xpath(".//div[@data-name='tileSelectIndicator']");
    private final By showStore = By.xpath("//button[normalize-space()='Zobacz sklep']");

    public StoreLocationPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean showStore(String storeCityLocation) {
        try {
            WebElement wantedStore = findStore(storeCityLocation);
            wantedStore.findElement(storeSelectIndicator).click();
            String[] storeFullName = wantedStore.findElement(storeName).getText().split("\\s-\\s");
            waiter.until(ExpectedConditions.visibilityOfElementLocated(showStore));
            driver.findElement(showStore).click();
            List<String> tabs = driver.getWindowHandles().stream().toList();
            driver.switchTo().window(tabs.get(1));
            return driver.getTitle().equals("Komputronik "+storeFullName[0]+isCorporate(storeFullName[1]));
        } catch (NoSuchElementException noSuchElementException) {
            return false;
        }
    }

    private WebElement findStore(String storeCityLocation) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(storeListWrapper));
        WebElement scroll = driver.findElement(storeListScroll);
        Map<String,WebElement> stores = new HashMap<>();
        while (true) {
            List<WebElement> visibleStores = driver.findElements(storeList);
            for(WebElement store : visibleStores) {
                String name = store.findElement(storeName).getText();
                if(!stores.containsKey(name)) {
                    stores.put(name,store);
                    if(store.findElement(storeName).getText().split("\\s-\\s")[0].equals(storeCityLocation)) {
                        return store;
                    }
                }
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollTop += arguments[0].offsetHeight;",scroll);
            try {
                waiter.until(driver -> !driver.findElements(storeList).equals(visibleStores));
            } catch (TimeoutException timeout) {
                throw new NoSuchElementException("Store doesn't exists!");
            }
        }
    }

    private String isCorporate(String secondPartOfName) {
        return secondPartOfName.equals("salon firmowy") ? "" : " - punkt partnerski";
    }
}
