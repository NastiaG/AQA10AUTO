/*Task 1. Проведите рефакторинг в соответствии с PageObject.*/

import io.qameta.allure.*;
import registration_script_pages.RegistrationScriptPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import static registration_script_pages.RegistrationScriptPage.buttonSubmit;

public class RegistrationScriptTest {
    private WebDriver driver;
    private ChromeOptions options;
    private RegistrationScriptPage registrationScriptPage;


    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
    }

    @BeforeMethod
    public void openRegistrationPage() {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        registrationScriptPage = new RegistrationScriptPage(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    @Description("Availability of registration with valid data test")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Filling registration form testing")
    @Story("I want to enter valid data, to click Submit and to register successfully")
    public void testFillFieldsWithData() {
        String firstName = "Bob";
        String lastName = "Boob";
        String dateOfBirthMmDdYy = "03/10/2000";
        String email = "fff@fff.fff";
        String password = "111111111";
        String passwordConfirmation = "111111111";

        registrationScriptPage
                .openRegistrationPage()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillDateOfBirth(dateOfBirthMmDdYy)
                .fillEmail(email)
                .fillPassword(password)
                .fillPasswordConfirmation(passwordConfirmation);

        Assert.assertTrue(buttonSubmit.isEnabled());
    }
}
