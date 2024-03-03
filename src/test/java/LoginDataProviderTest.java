import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class LoginDataProviderTest {

    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options;

    @BeforeClass
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
    }

    @BeforeMethod
    public void openPage() {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa-course-01.andersenlab.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    @Test(dataProvider = "loginParameters")
    public void loginTest(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']"))).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        By successLocator = By.xpath("//h1[contains(text(), 'My account')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(successLocator));
        String successText = driver.findElement(successLocator).getText();
        Assert.assertTrue(successText.contains("My account"));
    }

    @DataProvider(name = "loginParameters")
    public Object[][] createData() {
        return new Object[][]{
                {"aaa@aaa.aaa", "11111111"}, {"bbb@bbb.bbb", "22222222"}, {"ccc@ccc.ccc", "33333333"}
        };
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}