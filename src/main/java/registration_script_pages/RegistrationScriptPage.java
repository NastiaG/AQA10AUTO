package registration_script_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class RegistrationScriptPage {
    static final Logger logger = LoggerFactory.getLogger(RegistrationScriptPage.class);
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public RegistrationScriptPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public RegistrationScriptPage openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        return this;
    }

    @FindBy(xpath = "//input[@name='firstName']")
    private static WebElement inputFirstName;
    @FindBy(xpath = "//input[@name='lastName']")
    private static WebElement inputLastName;
    @FindBy(xpath = "//input[@name='dateOfBirth']")
    private static WebElement inputDateOfBirth;
    @FindBy(xpath = "//input[@name='email']")
    private static WebElement inputEmail;
    @FindBy(xpath = "//input[@name='password']")
    private static WebElement inputPassword;
    @FindBy(xpath = "//input[@name='passwordConfirmation']")
    private static WebElement inputPasswordConfirmation;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement buttonSubmit;

    @Step("filling first name")
    public RegistrationScriptPage fillFirstName(String firstName) {
        inputFirstName.sendKeys(firstName);
        logger.info("entering first name " + firstName + " to element " + inputFirstName.toString());
        return this;
    }
    @Step("filling last name")
    public RegistrationScriptPage fillLastName(String lastName) {
        inputLastName.sendKeys(lastName);
        logger.info("entering last name " + lastName + " to element " + inputLastName.toString());
        return this;
    }
    @Step("filling date of birth")
    public RegistrationScriptPage fillDateOfBirth(String dateOfBirthMmDdYe) {
        inputDateOfBirth.sendKeys(dateOfBirthMmDdYe);
        actions.keyDown(Keys.TAB);
        logger.info("entering date of birth " + dateOfBirthMmDdYe + " to element " + inputDateOfBirth.toString());
        return this;
    }
    @Step("filling email")
    public RegistrationScriptPage fillEmail(String email) {
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.click();
        inputEmail.sendKeys(email);
        logger.info("entering email " + email + " to element " + inputEmail.toString());
        return this;
    }
    @Step("filling password")
    public RegistrationScriptPage fillPassword(String password) {
        inputPassword.sendKeys(password);
        logger.info("entering password " + password + " to element " + inputPassword.toString());
        return this;
    }

    @Step("filling password confirmation")
    public RegistrationScriptPage fillPasswordConfirmation(String passwordConfirmation) {
        inputPasswordConfirmation.sendKeys(passwordConfirmation);
        logger.info("entering password confirmation " + passwordConfirmation + " to element " + inputPasswordConfirmation.toString());
        return this;
    }
}
