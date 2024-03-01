package lesson2.autocases;

import lesson2.SetUpDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

public class TestSigning {
    public static void testSigning(String email, String password) throws InterruptedException {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qa-course-01.andersenlab.com/");
        doSigning(driver, email, password);
        Thread.sleep(2000);
        driver.quit();
    }

    public static void doSigning(WebDriver driver, String email, String password) {
        WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
        emailInput.sendKeys(email);

        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.sendKeys(password);

        WebElement buttonSigning = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonSigning.click();
    }
}