package auto_script_three_sites_pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GuinessPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public GuinessPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @FindBy(xpath = "//button[@id='ez-accept-all']")
    private static WebElement cookiesButtonGuiness;
    @FindBy(id = "LastName")
    private static WebElement lastName;
    @FindBy(id = "FirstName")
    private static WebElement firstName;
    @FindBy(css = ".input-mini.dob-day")
    private static WebElement dayOfBirth;
    @FindBy(css = ".input-mini.dob-month")
    private static WebElement monthOfBirth;
    @FindBy(css = ".input-mini.dob-year")
    private static WebElement yearOfBirth;
    @FindBy(xpath = "//select[@id='Country']")
    private static WebElement selectCountry;
    @FindBy(xpath = "//input[@name='State']")
    private static WebElement inputState;
    @FindBy(xpath = "//input[@id='EmailAddress']")
    private static WebElement inputEmail;
    @FindBy(xpath = "//input[@id='ConfirmEmailAddress']")
    private static WebElement confirmEmail;
    @FindBy(xpath = "//input[@id='Password']")
    private static WebElement inputPassword;
    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    private static WebElement confirmPassword;
    @FindBy(xpath = "//label[@for='ConfirmPassword']")
    private static WebElement labelConfirmPassword;
    @FindBy(css = ".field-validation-error")
    private static WebElement errorPasswordConfirmation;

    public void fillFieldsWithDataGuiness(String testLastName, String testFirstName,
                                          String testDayOfBirth, String testMonthOfBirth,
                                          String testYearOfBirth, String testCountryName,
                                          String testState, String testEmail, String testConfirmEmail,
                                          String testPassword, String testConfirmPassword) {

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesButtonGuiness));
            cookiesButtonGuiness.click();
        } catch (NoSuchElementException | TimeoutException e) {
            System.out.println("No cookies here");
        }

        actions
                .sendKeys(lastName, testLastName)
                .sendKeys(firstName, testFirstName)
                .sendKeys(dayOfBirth, testDayOfBirth)
                .sendKeys(monthOfBirth, testMonthOfBirth)
                .sendKeys(yearOfBirth, testYearOfBirth)
                .build().perform();

        Select countries = new Select(selectCountry);
        countries.selectByVisibleText(testCountryName);

        wait.until(ExpectedConditions.elementToBeClickable(inputState));
        inputState.click();

        actions
                .sendKeys(inputState, testState)
                .sendKeys(inputEmail, testEmail)
                .sendKeys(confirmEmail, testConfirmEmail)
                .sendKeys(inputPassword, testPassword)
                .sendKeys(confirmPassword, testConfirmPassword)
                .click(labelConfirmPassword)
                .build().perform();

        wait.until(ExpectedConditions.visibilityOf(errorPasswordConfirmation));
    }
}
