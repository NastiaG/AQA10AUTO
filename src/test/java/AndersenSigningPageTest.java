/*Task 2. Доделать по три автотеста из каждого модуля, на которые писали тест - кейсы в лекции 10.*/

import auto_test_cases_pages.AndersenSigningPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AndersenSigningPageTest {
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
    @Description("Logging in with invalid password with valid email")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering invalid password and valid email")
    @Story("I can't log in entering invalid password and I see an error message")
    public void loggingInWithInvalidPasswordWithValidEmail() {
        String email = "nastia.gomozova@gmail.com";
        String password = "Test2024";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        Assert.assertTrue(andersenSigningPage.findErrorMessageInvalidEmailOrPassword());
    }

    @Test //LC04
    @Description("Logging in with empty form")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering nothing and clicking Submit")
    @Story("I can't log in entering nothing and I see an error message")
    public void loggingInWithEmptyForm() {
        String email = "";
        String password = "";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        Assert.assertTrue(andersenSigningPage.findErrorMessageRequired());
    }

    @Test //LC05
    @Description("Logging in with incorrect data")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering incorrect characters")
    @Story("I can't log in entering incorrect data and I see an error message")
    public void loggingInWithIncorrectData() {
        String email = "Dsjhdsfadsk %^& #jsdfajklsdflkjsdfjlkdfsjlkdfs";
        String password = "Dsjhdsfads   kjsdfaj$%^^%klsdflkjsdfjlkdfsjlkdfs";

        andersenSigningPage
                .openAndersenSignInPage()
                .fillEmail(email)
                .fillPassword(password)
                .clickSubmit();

        Assert.assertTrue(andersenSigningPage.findErrorMessageIncorrectData());
    }
}
