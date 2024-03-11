package registration_script_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationScriptPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public RegistrationScriptPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    public void openRegistrationPage() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
    }

    public void fillFieldsWithData
            (String firstName, String lastName, String dateOfBirthMmDdYe, String email,
             String password, String passwordConfirmation) {
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        inputFirstName.sendKeys(firstName);
        inputLastName.sendKeys(lastName);
        inputDateOfBirth.sendKeys(dateOfBirthMmDdYe);
        actions.keyDown(Keys.TAB);
        wait.until(ExpectedConditions.visibilityOf(inputEmail));
        inputEmail.click();
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        inputPasswordConfirmation.sendKeys(passwordConfirmation);
    }
}
