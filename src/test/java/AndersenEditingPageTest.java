/*Task 2. Доделать по три автотеста из каждого модуля, на которые писали тест - кейсы в лекции 10.*/

import auto_test_cases_pages.AndersenEditingPage;
import auto_test_cases_pages.AndersenSigningPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class AndersenEditingPageTest {
    private static WebDriver driver;
    private static ChromeOptions options;

    private static AndersenSigningPage andersenSigningPage;
    private static AndersenEditingPage andersenEditingPage;

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
        andersenSigningPage = new AndersenSigningPage(driver);
        andersenEditingPage = new AndersenEditingPage(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test //EC01
    @Description("Editing name with incorrect characters test")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering incorrect characters in the fields First name and Last name")
    @Story("If I enter incorrect characters in name I want to see ar error message")
    public void testEditingNamesWithIncorrectCharacters() {
        String email = "aaaa@qqq.qqq";
        String password = "111111111";
        String firstName = "Fsdfsddsdfsdf $%^%$&@#%&@";
        String lastName = "8237523$$%^^%$^$  @#%$G f%$#";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        andersenEditingPage
                .openAndersenEditingPage()
                .fillFirstName(firstName)
                .fillLastName(lastName);

        Assert.assertTrue(andersenEditingPage.findErrorMessageIncorrectCharacters(), "There is no message about incorrect characters in names");
    }

    @Test //EC03
    @Description("Changing the password and trying to log in with an old password test")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering old password after changing it")
    @Story("I want to change password, not to log in with an old password, and to see an error message")
    public void testChangingPasswordAndLoggingWithOld() {
        String email = "mmm@mmm.mmm";
        String password = "111111111";
        String newPassword = "222222222";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        andersenEditingPage
                .openAndersenEditingPage()
                .fillPassword(newPassword)
                .fillPasswordConfirmation(newPassword)
                .clickSubmit()
                .clickConfirmationOfLoggingOut();

        andersenSigningPage
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        Assert.assertTrue(andersenEditingPage.findErrorMessageInvalidEmailOrPassword());
    }

    @Test //Changing password back after test EC03
    @Description("Changing password back after the test EC03")
    public void testChangingPasswordBack() {
        String email = "mmm@mmm.mmm";
        String password = "222222222";
        String newPassword = "111111111";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        andersenEditingPage
                .openAndersenEditingPage()
                .fillPassword(newPassword)
                .fillPasswordConfirmation(newPassword)
                .clickSubmit()
                .clickConfirmationOfLoggingOut();

        andersenSigningPage
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        Assert.assertTrue(andersenEditingPage.findErrorMessageInvalidEmailOrPassword());
    }

    @Test //EC04
    @Description("Editing password with incorrect characters test")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering incorrect characters in the field password")
    @Story("If I enter incorrect characters in the field password I want to see an error message")
    public void testEditingPasswordWithIncorrectCharacters() {
        String email = "mmm@mmm.mmm";
        String password = "111111111";
        String newPassword = "%%%   &&&";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        andersenEditingPage
                .openAndersenEditingPage()
                .fillPassword(newPassword);

        Assert.assertTrue(andersenEditingPage.findErrorMessageIncorrectCharactersInPassword(), "There is no message about incorrect characters in password");
    }
}
