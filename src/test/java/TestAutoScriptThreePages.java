/*Task 2. Необходимо автоматизировать сценарий, который показан на видео “Сценарий для автоматизации Лекция 12.mp4”.*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Set;


public class TestAutoScriptThreePages {
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
    public void testAutoScript() {
        driver.get("https://www.google.com/search");

        Set<String> windowHandles1 = driver.getWindowHandles();

        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='L2AGLb']")));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        actions
                .sendKeys(driver.findElement(By.xpath("//textarea")), "https://www.guinnessworldrecords.com/account/register")
                .keyDown(Keys.ENTER)
                .build().perform();

        actions
                .keyDown(Keys.CONTROL)
                .click(driver.findElement(By.xpath("//h3[contains(text(),'Create account')]")))
                .keyUp(Keys.CONTROL)
                .build().perform();

        Set<String> windowHandles2 = driver.getWindowHandles();
        windowHandles2.removeAll(windowHandles1);
        String idOfSecondWindow = windowHandles2.iterator().next();

        actions
                .click(driver.findElement(By.xpath("//span[@jsname='itVqKe']")))
                .sendKeys(driver.findElement(By.xpath("//textarea")), "https://www.hyrtutorials.com/p/alertsdemo.html")
                .keyDown(Keys.ENTER)
                .build().perform();

        actions
                .keyDown(Keys.CONTROL)
                .click(driver.findElement(By.xpath("//h3[contains(text(), 'AlertsDemo')]")))
                .keyUp(Keys.CONTROL)
                .build().perform();

        Set<String> windowHandles3 = driver.getWindowHandles();
        windowHandles3.removeAll(windowHandles2);
        windowHandles3.removeAll(windowHandles1);
        String idOfThirdWindow = windowHandles3.iterator().next();

        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");

        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accept-choices")));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        driver.switchTo().frame("iframeResult");

        WebElement firstName = driver.findElement(By.xpath("//input[@id='fname']"));
        firstName.clear();
        firstName.sendKeys("Anastasia");

        WebElement lastName = driver.findElement(By.xpath("//input[@id='lname']"));
        lastName.clear();
        lastName.sendKeys("Homazava");

        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
        submitButton.click();

        WebElement noteText = driver.findElement(By.cssSelector(".w3-panel.w3-pale-yellow.w3-leftbar.w3-border-yellow"));
        System.out.println(noteText.getText());

        driver.switchTo().defaultContent();

        driver.switchTo().window(idOfSecondWindow);

        try {
            WebElement cookiesButton = driver.findElement(By.xpath("//button[@id='ez-accept-all']"));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        actions
                .sendKeys(driver.findElement(By.id("LastName")), "Homazava")
                .sendKeys(driver.findElement(By.id("FirstName")), "Anastasia")
                .sendKeys(driver.findElement(By.cssSelector(".input-mini.dob-day")), "07")
                .sendKeys(driver.findElement(By.cssSelector(".input-mini.dob-month")), "02")
                .sendKeys(driver.findElement(By.cssSelector(".input-mini.dob-year")), "1989")
                .build().perform();

        Select countries = new Select(driver.findElement(By.xpath("//select[@id='Country']")));
        countries.selectByValue("PL");

        WebElement inputState = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='State']")));
        inputState.click();

        actions
                .sendKeys(inputState, "Dolny")
                .sendKeys(driver.findElement(By.xpath("//input[@id='EmailAddress']")), "mail@mail.fmail")
                .sendKeys(driver.findElement(By.xpath("//input[@id='ConfirmEmailAddress']")), "mail@mail.fmail")
                .sendKeys(driver.findElement(By.xpath("//input[@id='Password']")), "111111111")
                .sendKeys(driver.findElement(By.xpath("//input[@id='Password']")), "111111111")
                .sendKeys(driver.findElement(By.xpath("//input[@id='ConfirmPassword']")), "222222222")
                .click(driver.findElement(By.xpath("//label[@for='ConfirmPassword']")))
                .build().perform();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".field-validation-error")));

        driver.switchTo().window(idOfThirdWindow);
        try {
            WebElement cookiesButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@aria-label='Consent']")));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }

        try {
            WebElement cookiesInfo = driver.findElement(By.id("cookieChoiceDismiss"));
            cookiesInfo.click();
        } catch (NoSuchElementException e) {
            e.getMessage();
        }


        WebElement buttonAlertBox = driver.findElement(By.xpath("//button[@id='alertBox']"));
        buttonAlertBox.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        String outputText = driver.findElement(By.xpath("//div[@id='output']")).getText();
        System.out.println(outputText);

        WebElement buttonConfirmBox = driver.findElement(By.xpath("//button[@id='confirmBox']"));
        buttonConfirmBox.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        outputText = driver.findElement(By.xpath("//div[@id='output']")).getText();
        System.out.println(outputText);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement buttonPromptBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='promptBox']")));
        js.executeScript("arguments[0].scrollIntoView(true);", buttonPromptBox);
        js.executeScript("arguments[0].click();", buttonPromptBox);

        alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = "Final step of this task";
        alert.sendKeys(text);
        alert.accept();

        outputText = driver.findElement(By.xpath("//div[@id='output']")).getText();
        System.out.println(outputText);
    }
}