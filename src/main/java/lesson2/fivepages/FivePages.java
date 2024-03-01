/* Task 2. Написать программу, которая будет открывать пять различных страниц в новых окнах:
    http://www.automationpractice.pl/index.php
    https://zoo.waw.pl/
    https://www.w3schools.com/
    https://www.clickspeedtester.com/click-counter/
    https://andersenlab.com/
Прописать цикл, который будет переключаться поочередно через все страницы,
для каждой страницы выводить в консоль название и ссылку на эту страницу.
И будет закрывать ту страницу в названии которой есть слово "Zoo".
*/

package lesson2.fivepages;

import lesson2.SetUpDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.time.Duration;

public class FivePages {

    public static void main(String[] args) throws InterruptedException {
        fivePages();
    }

    public static void fivePages() throws InterruptedException {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("http://www.automationpractice.pl/index.php");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://zoo.waw.pl/");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.w3schools.com/");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.clickspeedtester.com/click-counter/");

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://andersenlab.com/");

        for (String windowHandle : driver.getWindowHandles()) {
            driver.switchTo().window(windowHandle);
            String s = driver.getTitle();
            System.out.println(s);
            System.out.println(driver.getCurrentUrl());
            System.out.println(windowHandle);
            System.out.println("..............");
            if (s.contains("Zoo")) {
                Thread.sleep(2000);
                driver.close();
            }
        }
        Thread.sleep(2000);
        driver.quit();
    }
}