import org.griddynamics.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest{

    @Test
    public void addToCart() {
        String finalPrice = new HomePage(webDriver)
                .acceptCookies()
                .clickSearchBar()
                .searchForProduct("HyperX")
                .goToProductPage("HyperX CloudX Stinger Core Wireless")
                .addToCart()
                .goFurther()
                .goToCartPage()
                .getTotalPrice();


        Assert.assertEquals(finalPrice,"336 zł","Prices are different!");
    }

    @Test
    public void addToCartMultipleProducts() {
        String finalPrice = new HomePage(webDriver)
                .acceptCookies()
                .clickSearchBar()
                .searchForProduct("HyperX")
                .goToProductPage("HyperX CloudX Stinger Core Wireless")
                .addToCart()
                .goFurther()
                .backToShopping()
                .getSearchBar()
                .searchForProduct("Kingston")
                .goToProductPage("Kingston NV3 M.2 2230 Pcie 4.0 NVMe 2TB")
                .addToCart()
                .goFurther()
                .goToCartPage()
                .getTotalPrice();

        Assert.assertEquals(finalPrice,"865 zł", "Prices are different!");
    }

    @Test
    public void cleanTheCart() {
       String productsInCart = new HomePage(webDriver)
               .acceptCookies()
                .clickSearchBar()
                .searchForProduct("HyperX")
                .goToProductPage("HyperX CloudX Stinger Core Wireless")
                .addToCart()
                .goFurther()
                .backToShopping()
                .getActionNavBar()
                .goToCartPage()
                .removeAllItems()
                .submitCleaning()
                .getNumberOfProductsInCart();

       Assert.assertEquals(productsInCart, "Koszyk jest pusty", "Cart is not EMPTY!");
    }

}
