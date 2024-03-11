/*Task 1. Проведите рефакторинг в соответствии с PageObject.*/

import registration_script_pages.RegistrationScriptPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import static registration_script_pages.RegistrationScriptPage.buttonSubmit;

public class TestRegistrationScript {
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
        registrationScriptPage.openRegistrationPage();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void testFillFieldsWithData() {
        String firstName = "Bob";
        String lastName = "Boob";
        String dateOfBirthMmDdYy = "03/10/2000";
        String email = "fff@fff.fff";
        String password = "111111111";
        String passwordConfirmation = "111111111";
        registrationScriptPage.fillFieldsWithData(firstName, lastName, dateOfBirthMmDdYy, email, password, passwordConfirmation);
        Assert.assertTrue(buttonSubmit.isEnabled());
    }
}
