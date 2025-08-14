package org.griddynamics.components;

import org.griddynamics.exceptions.EmptyFieldException;
import org.griddynamics.exceptions.InvalidCredentialsException;
import org.griddynamics.pages.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.InvocationTargetException;
import java.time.Duration;

public class LogInModal <T extends BasePage>{

    private WebDriver driver;
    private WebDriverWait waiter;
    private Class<T> pageClass;

    private final By loginField = By.id("loginEmail");
    private final By passwordField = By.id("loginPassword");
    private final By logInButton = By.xpath("//button[normalize-space()='Zaloguj się']");
    private final By emptyField = By.xpath("//span[text()='Pole jest wymagane']");
    private final By invalidCredentials = By.xpath("//p[text()='Nieprawidłowe logowanie.']");

    public LogInModal(WebDriver webDriver, Class<T> pageClass) {
        this.driver = webDriver;
        this.waiter = new WebDriverWait(this.driver, Duration.ofSeconds(2));
        this.pageClass = pageClass;
    }

    /*
    * Standard procedures to successful log in.
    * */
    public LogInModal enterLogin(String login) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(loginField));
        WebElement loginInputField = driver.findElement(loginField);
        loginInputField.sendKeys(login);
        return this;
    }

    public LogInModal enterPassword(String password) {
        waiter.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        WebElement passwordInputField = driver.findElement(passwordField);
        passwordInputField.sendKeys(password);
        return this;
    }

    public T logIn() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        WebElement logInBut = driver.findElement(logInButton);
        logInBut.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        if(!driver.findElements(emptyField).isEmpty()) {
            throw new EmptyFieldException("One or more fields are empty!");
        }

        if(!driver.findElements(invalidCredentials).isEmpty()) {
            throw new InvalidCredentialsException("Invalid credentials!");
        }

        return pageClass.getDeclaredConstructor(WebDriver.class).newInstance(this.driver);
    }

}
