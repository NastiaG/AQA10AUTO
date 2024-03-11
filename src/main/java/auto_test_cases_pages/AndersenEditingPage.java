package auto_test_cases_pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenEditingPage {
    private WebDriver driver;
    private WebDriverWait wait;


    public AndersenEditingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void openAndersenEditingPage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(editLink));
        editLink.click();
    }

    public void editingNamesWithIncorrectCharacters(String testFirstName, String testLastName) {
        inputFirstName.sendKeys(testFirstName);
        inputLastName.sendKeys(testLastName);
    }

    public boolean findErrorMessageIncorrectCharacters() {
        try {
            return errorMessageIncorrectCharacters.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void editingPassword(String newPassword) {
        inputPassword.sendKeys(newPassword);
        inputPasswordConfirmation.sendKeys(newPassword);
        buttonSubmit.click();
        wait.until(ExpectedConditions.visibilityOf(buttonLogout));
        buttonLogout.click();
        confirmButtonYes.click();
    }

    public boolean findErrorMessageInvalidEmailOrPassword() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessageInvalidEmailOrPassword));
            return errorMessageInvalidEmailOrPassword.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void editingPasswordWithIncorrectCharacters(String newPassword) {
        inputPassword.sendKeys(newPassword);
        inputPasswordConfirmation.sendKeys(newPassword);
    }

    public boolean findErrorMessageIncorrectCharactersInPassword() {
        try {
            return errorMessageIncorrectCharactersInPassword.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
