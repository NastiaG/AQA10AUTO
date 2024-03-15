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

public class AndersenEditingPage {
    static final Logger logger = LoggerFactory.getLogger(RegistrationScriptPage.class);

    private WebDriver driver;
    private WebDriverWait wait;


    public AndersenEditingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @FindBy(xpath = "//a[@href='/editAccount']")
    private static WebElement editLink;
    @FindBy(xpath = "//input[@name='firstName']")
    private static WebElement inputFirstName;
    @FindBy(xpath = "//input[@name='lastName']")
    private static WebElement inputLastName;
    @FindBy(xpath = "//input[@name='password']")
    private static WebElement inputPassword;
    @FindBy(xpath = "//input[@name='passwordConfirmation']")
    private static WebElement inputPasswordConfirmation;
    @FindBy(xpath = "//button[@type='submit']")
    private static WebElement buttonSubmit;
    @FindBy(xpath = "//*[contains(text(), 'Incorrect characters')]")
    private static WebElement errorMessageIncorrectCharacters;
    @FindBy(xpath = "//p[text() = 'Logout']")
    private static WebElement buttonLogout;
    @FindBy(xpath = "//button[@label='Yes']")
    private static WebElement confirmButtonYes;
    @FindBy(xpath = "//span[text()='Email or password is not valid']")
    private static WebElement errorMessageInvalidEmailOrPassword;
    @FindBy(xpath = "//*[contains(text(), 'Incorrect characters in password')]")
    private static WebElement errorMessageIncorrectCharactersInPassword;

    public AndersenEditingPage openAndersenEditingPage() {
        wait.until(ExpectedConditions.visibilityOf(editLink));
        editLink.click();
        return this;
    }

    @Step("entering first name")
    public AndersenEditingPage fillFirstName(String firstName) {
        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
        inputFirstName.sendKeys(firstName);
        logger.info("entering first name " + firstName + " to element " + inputFirstName.toString());
        return this;
    }

    @Step("entering last name")
    public AndersenEditingPage fillLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        logger.info("entering last name " + lastName + " to element " + inputLastName.toString());
        return this;
    }

    @Step("entering password")
    public AndersenEditingPage fillPassword(String password) {
        inputPassword.sendKeys(password);
        logger.info("entering password " + password + " to element " + inputPassword.toString());
        return this;
    }

    @Step("entering password confirmation")
    public AndersenEditingPage fillPasswordConfirmation(String passwordConfirmation) {
        inputPasswordConfirmation.sendKeys(passwordConfirmation);
        logger.info("entering password confirmation " + passwordConfirmation + " to element " + inputPasswordConfirmation.toString());
        return this;
    }

    @Step("clicking Submit")
    public AndersenEditingPage clickSubmit() {
        buttonSubmit.click();
        logger.info("clicking button Submit " + "element " + buttonSubmit.toString());
        return this;
    }

    @Step("clicking Logout")
    public AndersenEditingPage clickConfirmationOfLoggingOut() {
        wait.until(ExpectedConditions.visibilityOf(buttonLogout));
        buttonLogout.click();
        confirmButtonYes.click();
        logger.info("clicking button Logout " + "element " + buttonLogout.toString());
        return this;
    }

    public boolean findErrorMessageIncorrectCharacters() {
        logger.info("Trying to find message about incorrect characters in name");
        try {
            return errorMessageIncorrectCharacters.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean findErrorMessageInvalidEmailOrPassword() {
        logger.info("Trying to find message about invalid email or password");
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageInvalidEmailOrPassword));
            return errorMessageInvalidEmailOrPassword.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean findErrorMessageIncorrectCharactersInPassword() {
        logger.info("Trying to find message about incorrect characters in password");
        try {
            return errorMessageIncorrectCharactersInPassword.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
