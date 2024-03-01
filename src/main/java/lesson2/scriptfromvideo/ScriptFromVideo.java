/*Task 4. Написать программу, которая повторит действия на видео "Сценарий для автоматизации Лекция 10.mp4".
Сценарий для автоматизации Лекция 10.mov Сценарий для автоматизации Лекция 10.mov
*/

package lesson2.scriptfromvideo;

import lesson2.SetUpDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScriptFromVideo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        driver.get("http://www.automationpractice.pl/index.php");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

        WebElement searchField = driver.findElement(By.xpath("//input[@name='search_query']"));
        searchField.sendKeys("Printed chiffon dress");

        WebElement buttonSearch = driver.findElement(By.xpath("//button[@name='submit_search']"));
        buttonSearch.click();

        WebElement sizeChoice = driver.findElement(By.cssSelector(".icon-th-list"));
        sizeChoice.click();

        WebElement addToCompare1 = driver.findElement(By.xpath("//a[@data-id-product='7']"));
        addToCompare1.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='total-compare-val'][text()='1']")));
        WebElement buttonWomen = driver.findElement(By.xpath("//a[@title='Women']"));
        buttonWomen.click();

        searchField = driver.findElement(By.xpath("//input[@name='search_query']"));
        searchField.sendKeys("Faded Short");

        buttonSearch = driver.findElement(By.xpath("//button[@name='submit_search']"));
        buttonSearch.click();

        WebElement addToCompare2 = driver.findElement(By.xpath("//a[@data-id-product='1']"));
        addToCompare2.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[@class='total-compare-val'][text()='2']")));
        WebElement compareButton = driver.findElement(By.xpath("//strong[@class='total-compare-val']"));
        compareButton.click();

        Thread.sleep(5000);
        driver.quit();
    }
}