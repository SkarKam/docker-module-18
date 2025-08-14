import io.github.cdimascio.dotenv.Dotenv;
import org.griddynamics.exceptions.EmptyFieldException;
import org.griddynamics.exceptions.InvalidCredentialsException;
import org.griddynamics.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;

public class LogInTest extends BaseTest {

    @Test
    public void logInSuccessful() throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
       String email = Dotenv.load().get("EMAIL");
       String pass = Dotenv.load().get("PASS");

        String popUpInfo = new HomePage(webDriver)
                .acceptCookies()
                .getActionNavBar()
                .goToLogInForm(HomePage.class)
                .enterLogin(email)
                .enterPassword(pass)
                .logIn()
                .popUpAfterAccountLog();

        Assert.assertEquals(popUpInfo,"Pomyślnie zalogowano użytkownika","Pop-up have different message! \n Message: "+popUpInfo);
    }

    @Test
    public void logInWithoutLogin() {
        Assert.assertThrows(EmptyFieldException.class,
                () -> new HomePage(webDriver)
                        .acceptCookies()
                        .getActionNavBar()
                        .goToLogInForm(HomePage.class)
                        .enterLogin("")
                        .enterPassword("Adlas!2")
                        .logIn());
    }

    @Test
    public void logInWithoutPassword() {
        Assert.assertThrows(EmptyFieldException.class,
                () -> new HomePage(webDriver)
                        .acceptCookies()
                        .getActionNavBar()
                        .goToLogInForm(HomePage.class)
                        .enterLogin("adsda@fae.as")
                        .enterPassword("")
                        .logIn());
    }

    @Test
    public void logInWithInvalidCredentials() {
        Assert.assertThrows(InvalidCredentialsException.class,
                () -> new HomePage(webDriver)
                        .acceptCookies()
                        .getActionNavBar()
                        .goToLogInForm(HomePage.class)
                        .enterLogin("adsda@fae.as")
                        .enterPassword("Adlas!2")
                        .logIn());
    }

    @Test
    public void logOut() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String email = Dotenv.load().get("EMAIL"), password = Dotenv.load().get("PASS");

        String popUpInformation = new HomePage(webDriver)
                .acceptCookies()
                .getActionNavBar()
                .goToLogInForm(HomePage.class)
                .enterLogin(email)
                .enterPassword(password)
                .logIn()
                .waitForPopUpClose()
                .getActionNavBar()
                .goToAccountPage()
                .logOut()
                .popUpAfterAccountLog();

        Assert.assertEquals(popUpInformation,"Pomyślnie wylogowano", "Pop-up have different message! \n Message: "+popUpInformation);
    }
}
