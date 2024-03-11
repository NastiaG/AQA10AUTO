package auto_test_cases_pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenRegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public AndersenRegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='/registration']")
    private static WebElement registrationButton;
    @FindBy(xpath = "//input[@name='firstName']")
    private static WebElement inputFirstName;
    @FindBy(xpath = "//input[@name='lastName']")
    private static WebElement inputLastName;
    @FindBy(xpath = "//input[@name='dateOfBirth']")
    private static WebElement dateOfBirth;
    @FindBy(xpath = "//input[@name='email']")
    private static WebElement inputEmail;
    @FindBy(xpath = "//input[@name='password']")
    private static WebElement inputPassword;
    @FindBy(xpath = "//input[@name='passwordConfirmation']")
    private static WebElement confirmPassword;
    @FindBy(xpath = "//button[@type='submit']")
    public static WebElement buttonSubmit;
    @FindBy(xpath = "//*[contains(text(), 'Such user already exists')]")
    public static WebElement errorMessageSuchUserExists;

    @FindBy(xpath = "//*[contains(text(), 'name must be between')]")
    public static WebElement errorMessageNameLength;

    public void openAndersenRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com");
    }

    public void registrationAndersen(String testFirstName, String testLastName,
                                     String testDateOfBirth, String testEmail,
                                     String testPassword, String testConfirmPassword) {

        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        registrationButton.click();
        inputFirstName.sendKeys(testFirstName);
        inputLastName.sendKeys(testLastName);
        dateOfBirth.sendKeys(testDateOfBirth);
        actions.keyDown(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.click();
        inputEmail.sendKeys(testEmail);
        inputPassword.sendKeys(testPassword);
        confirmPassword.sendKeys(testConfirmPassword);
        buttonSubmit.click();
    }

    public boolean findErrorMessageUserExists() {
        try {
            return errorMessageSuchUserExists.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean findErrorMessageOverlyLongLine() {
        try {
            return errorMessageNameLength.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
