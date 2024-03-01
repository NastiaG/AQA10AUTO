package lesson2.autocases;

import lesson2.SetUpDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static lesson2.autocases.TestSigning.doSigning;

public class TestEditing {
    public static void testEditing(String email, String password, String newPassword, String confirmNewPassword) throws InterruptedException {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qa-course-01.andersenlab.com/");

        doSigning(driver, email, password); //Pre-condition
        testEditPassword(driver, email, newPassword, confirmNewPassword);
        driver.quit();
    }

    public static void testEditPassword(WebDriver driver, String email, String newPassword, String confirmNewPassword) throws InterruptedException {
        WebElement buttonEditAccount = driver.findElement(By.xpath("//a[@href='/editAccount']"));
        buttonEditAccount.click();
        Thread.sleep(1000);

        WebElement newPasswordInput = driver.findElement(By.xpath("//input[@name='password']"));
        newPasswordInput.sendKeys(newPassword);

        WebElement confirmNewPasswordInput = driver.findElement(By.xpath("//input[@name='passwordConfirmation']"));
        confirmNewPasswordInput.sendKeys(confirmNewPassword);
        Thread.sleep(1000);

        WebElement buttonSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonSubmit.click();

        WebElement buttonLogout = driver.findElement(By.xpath("//p[text()='Logout']"));
        buttonLogout.click();
        Thread.sleep(1000);

        WebElement buttonConfirmLogout = driver.findElement(By.xpath("//button[@label='Yes']"));
        Thread.sleep(1000);
        buttonConfirmLogout.click();

        doSigning(driver, email, newPassword);

        Thread.sleep(2000);
    }
}