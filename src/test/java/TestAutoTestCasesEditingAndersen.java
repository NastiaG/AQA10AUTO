/*Task 2. Доделать по три автотеста из каждого модуля, на которые писали тест - кейсы в лекции 10.*/

import auto_test_cases_pages.AndersenEditingPage;
import auto_test_cases_pages.AndersenSigningPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestAutoTestCasesEditingAndersen {
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
        andersenSigningPage.openAndersenSignInPage();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test //EC01
    public void testEditingNamesWithIncorrectCharacters() {
        String testEmail = "aaaa@qqq.qqq";
        String testPassword = "111111111";
        String testFirstName = "Fsdfsddsdfsdf $%^%$&@#%&@";
        String testLastName = "8237523$$%^^%$^$  @#%$G f%$#";
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        andersenEditingPage.openAndersenEditingPage();
        andersenEditingPage.editingNamesWithIncorrectCharacters(testFirstName, testLastName);
        Assert.assertTrue(andersenEditingPage.findErrorMessageIncorrectCharacters(), "There is no message about incorrect characters in names");
    }

    @Test //EC03
    public void testChangingPasswordAndLoggingWithOld() {
        String testEmail = "mmm@mmm.mmm";
        String testPassword = "111111111";
        String newPassword = "222222222";
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        andersenEditingPage.openAndersenEditingPage();
        andersenEditingPage.editingPassword(newPassword);
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        Assert.assertTrue(andersenEditingPage.findErrorMessageInvalidEmailOrPassword());
    }

    @Test //Changing password back after test EC03
    public void testChangingPasswordBack() {
        String testEmail = "mmm@mmm.mmm";
        String testPassword = "222222222";
        String newPassword = "111111111";
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        andersenEditingPage.openAndersenEditingPage();
        andersenEditingPage.editingPassword(newPassword);
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        Assert.assertTrue(andersenEditingPage.findErrorMessageInvalidEmailOrPassword());
    }

    @Test //EC04
    public void testEditingPasswordWithIncorrectCharacters() {
        String testEmail = "mmm@mmm.mmm";
        String testPassword = "111111111";
        String newPassword = "%%%   &&&";
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        andersenEditingPage.openAndersenEditingPage();
        andersenEditingPage.editingPasswordWithIncorrectCharacters(newPassword);
        Assert.assertTrue(andersenEditingPage.findErrorMessageIncorrectCharactersInPassword(), "There is no message about incorrect characters in password");
    }
}
