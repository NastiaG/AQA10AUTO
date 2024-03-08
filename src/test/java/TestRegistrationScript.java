/*Task 3. Необходимо автоматизировать сценарий, который показан на видео
“Сценарий для автоматизации Лекция 12ч2.mp4. https://qa-course-01.andersenlab.com/registration*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class TestRegistrationScript {
    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions options;
    Actions actions;


    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
    }


    @BeforeMethod
    public void createDriver() {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }


    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }


    @Test
    public void testRegistration() {
        driver.get("https://qa-course-01.andersenlab.com/registration");
        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='firstName']")));
        firstName.sendKeys("Sam");

        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys("Meller");

        driver.findElement(By.xpath("//input[@name='dateOfBirth']")).click();

        Select years = new Select(driver.findElement(By.xpath("//select[1]")));
        years.selectByValue("1990");

        Select months = new Select(driver.findElement(By.xpath("//select[2]")));
        months.selectByValue("August");

        driver.findElement(By.xpath("//div[@class='react-datepicker__day react-datepicker__day--010']")).click();
        actions.keyDown(Keys.TAB).perform();

        WebElement inputEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='email']")));
        inputEmail.sendKeys("sam@sam.sam");

        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("111111111");
        driver.findElement(By.xpath("//input[@name='passwordConfirmation']")).sendKeys("111111111");

        Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit']")).isEnabled());
    }
}
