import org.griddynamics.models.Product;
import org.griddynamics.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest{

    @Test
    public void getNumberOfProduccts() {
        HomePage homePage = new HomePage(webDriver);
        int count = homePage.acceptCookies()
                .clickSearchBar()
                .searchForProduct("HyperX")
                .findNumberOfProducts();

        Assert.assertEquals(count,105,"Product numbers are different!");
    }

    @Test
    public void findProduct() {
        HomePage homePage = new HomePage(webDriver);
        Product product = homePage.acceptCookies()
                .clickSearchBar()
                .searchForProduct("HyperX")
                .goToProductPage("HyperX CloudX Stinger Core Wireless")
                .getProduct();

        Assert.assertEquals(product.getName(),"HyperX CloudX Stinger Core Wireless","Products names are different!");
        Assert.assertEquals(product.getPrice(), "336 zł","Prices are different!");
    }
}
