/*Task 1. Напишите 2 тест кейса на проверку каких либо визуальных элементов сайта https://andersenlab.com/.
(Например отображение кнопок Skype, WatsApp или на переход на страницу проекта Verivox.
А лучше придумайте что-нибудь свое)).*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class TestVisualAndersenTestCases {
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


    @Test //Test of opening site section Company/Insights
    public void testOpenSectionInsights() {
        driver.get("https://andersenlab.com/");

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//button[contains(@class, 'CookiesPolicy-module--button--259ad')]"))).click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        actions
                .moveToElement(driver.findElement(By.xpath("//div[@tabindex='Company']")))
                .perform();

        driver.findElement(By.xpath("//span[@class='SubMenu-module--wrapLink--a9a7d']/a[text()='Insights']")).click();
        WebElement titleSiteSection = wait.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//h1[contains(@class, 'Title-module--title--e41b8 Content-module--title--27d83')]")));

        Assert.assertEquals(titleSiteSection.getText(), "Insights");
    }


    @Test // Test of scrolling to element, clicking it and checking if it was opened in a new tab
    public void testScrollAndNewTab() {
        driver.get("https://andersenlab.com/");

        try {
            WebElement cookiesLink = wait.until(ExpectedConditions.visibilityOfElementLocated
                    (By.xpath("//button[contains(@class, 'CookiesPolicy-module--button--259ad')]")));
            cookiesLink.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement cookiesElement = driver.findElement(By.xpath("//a[@href='/privacy-policy']/following-sibling::a"));
        js.executeScript("arguments[0].scrollIntoView(true);", cookiesElement);
        js.executeScript("arguments[0].click();", cookiesElement);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String newTab = windowHandles.get(1);
        driver.switchTo().window(newTab);

        Assert.assertEquals(driver.getTitle(), "Cookie Policy of Andersen's Websites");
    }
}