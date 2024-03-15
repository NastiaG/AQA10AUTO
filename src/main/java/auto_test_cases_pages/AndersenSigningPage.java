package auto_test_cases_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registration_script_pages.RegistrationScriptPage;

import java.time.Duration;

public class AndersenSigningPage {
    static final Logger logger = LoggerFactory.getLogger(RegistrationScriptPage.class);

    private WebDriver driver;
    private WebDriverWait wait;

    public AndersenSigningPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//input[@name='email']")
    private static WebElement inputEmail;
    @FindBy(xpath = "//input[@name='password']")
    private static WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement buttonSubmit;
    @FindBy(xpath = "(//span[text()='Email or password is not valid'])[1]")
    private static WebElement errorMessageInvalidEmailOrPassword;
    @FindBy(xpath = "//*[contains(text(), 'Required')]")
    private static WebElement errorMessageRequired;

    @FindBy(xpath = "//*[contains(text(), 'Invalid email address')]")
    private static WebElement errorMessageInvalidEmail;
    @FindBy(xpath = "//*[contains(text(), 'Maximum 20 characters')]")
    private static WebElement errorMessageInvalidPassword;

    public AndersenSigningPage openAndersenSignInPage() {
        driver.get("https://qa-course-01.andersenlab.com");
        return this;
    }

    @Step("entering email")
    public AndersenSigningPage fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.click();
        inputEmail.sendKeys(email);
        logger.info("entering email " + email + " to element " + inputEmail.toString());
        return this;
    }
    @Step("entering password")
    public AndersenSigningPage fillPassword(String password) {
        inputPassword.sendKeys(password);
        logger.info("entering password " + password + " to element " + inputPassword.toString());
        return this;
    }

    @Step("clicking Submit")
    public void clickSubmit () {
        buttonSubmit.click();
        logger.info("clicking button Submit " + "element " + buttonSubmit.toString());
    }

    public boolean findErrorMessageInvalidEmailOrPassword() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageInvalidEmailOrPassword));
            return errorMessageInvalidEmailOrPassword.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(errorMessageInvalidEmailOrPassword.getText());
            return false;
        }
    }

    public boolean findErrorMessageRequired() {
        try {
            return errorMessageRequired.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean findErrorMessageIncorrectData() {
        try {
            return errorMessageInvalidEmail.isDisplayed() && errorMessageInvalidPassword.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
