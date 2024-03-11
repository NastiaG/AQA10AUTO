/*Task 2. Доделать по три автотеста из каждого модуля, на которые писали тест - кейсы в лекции 10.*/

import auto_test_cases_pages.AndersenSigningPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestAutoTestCasesSignInAndersen {
    private static WebDriver driver;
    private static ChromeOptions options;
    private static AndersenSigningPage andersenSigningPage;

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
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test //LC03
    public void loggingInWithInvalidPasswordWithValidEmail() {
        String testEmail = "nastia.gomozova@gmail.com";
        String testPassword = "Test2024";
        andersenSigningPage.openAndersenSignInPage();
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        Assert.assertTrue(andersenSigningPage.findErrorMessageInvalidEmailOrPassword());
    }

    @Test //LC04
    public void loggingInWithEmptyForm() {
        String testEmail = "";
        String testPassword = "";
        andersenSigningPage.openAndersenSignInPage();
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        Assert.assertTrue(andersenSigningPage.findErrorMessageRequired());
    }

    @Test //LC05
    public void loggingInWithIncorrectData() {
        String testEmail = "Dsjhdsfadsk %^& #jsdfajklsdflkjsdfjlkdfsjlkdfs";
        String testPassword = "Dsjhdsfads   kjsdfaj$%^^%klsdflkjsdfjlkdfsjlkdfs";
        andersenSigningPage.openAndersenSignInPage();
        andersenSigningPage.signInAndersen(testEmail, testPassword);
        Assert.assertTrue(andersenSigningPage.findErrorMessageIncorrectData());
    }
}
