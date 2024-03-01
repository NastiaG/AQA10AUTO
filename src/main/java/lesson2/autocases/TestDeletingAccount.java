package lesson2.autocases;

import lesson2.SetUpDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;

import static lesson2.autocases.TestSigning.doSigning;

public class TestDeletingAccount {
    public static void testDeletingAccount(String email, String password) throws InterruptedException {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qa-course-01.andersenlab.com/");

        doSigning(driver, email, password);

        WebElement buttonEditAccount = driver.findElement(By.xpath("//a[@href='/editAccount']"));
        buttonEditAccount.click();
        Thread.sleep(1000);

        WebElement buttonDelete = driver.findElement(By.xpath("//p[text()='Delete account']"));
        buttonDelete.click();
        Thread.sleep(1000);

        WebElement buttonConfirmLogout = driver.findElement(By.xpath("//button[@label='Yes']"));
        Thread.sleep(1000);

        buttonConfirmLogout.click();
        Thread.sleep(1000);

        doSigning(driver, email, password);
        Thread.sleep(1000);

        driver.quit();
    }
}