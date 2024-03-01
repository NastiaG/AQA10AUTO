package lesson2.autocases;

import lesson2.SetUpDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;


public class TestRegistration {

    public static void testRegistration(String firstName, String lastName, String dateOfBirth, String email, String password, String confirmPassword) throws InterruptedException {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://qa-course-01.andersenlab.com/");

        WebElement buttonRegistration = driver.findElement(By.xpath("//a[@href='/registration']"));
        buttonRegistration.click();

        WebElement firstNameInput = driver.findElement(By.xpath("//input[@name='firstName']"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.xpath("//input[@name='lastName']"));
        lastNameInput.sendKeys(lastName);

        WebElement dateOfBirthInput = driver.findElement(By.xpath("//input[@name='dateOfBirth']"));
        dateOfBirthInput.sendKeys(dateOfBirth);

        WebElement emailInput = driver.findElement(By.xpath("//input[@name='email']"));
        emailInput.sendKeys(email);
        emailInput.click(); //необходимо для закрытия элемента date-picker

        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        passwordInput.sendKeys(password);

        WebElement confirmPasswordInput = driver.findElement(By.xpath("//input[@name='passwordConfirmation']"));
        confirmPasswordInput.sendKeys(confirmPassword);

        WebElement buttonSubmit = driver.findElement(By.xpath("//button[@type='submit']"));
        buttonSubmit.click();

        Thread.sleep(2000);
        driver.quit();
    }
}