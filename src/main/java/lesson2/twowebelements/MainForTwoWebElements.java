/* Task 3. Написать метод в параметры которого принимаются два ВебЭлемента.
метод выводит в консоль информацию какой из двух элементов располагается  выше на странице,
какой из элементов располагается левее на странице,
а также какой из элементов занимает большую площадь.
Параметры метода могут также включать в себя другие аргументы, если это необходимо.
*/

package lesson2.twowebelements;

import lesson2.SetUpDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static lesson2.twowebelements.TwoWebElements.twoWebElements;

public class MainForTwoWebElements {
    public static void main(String[] args) {
        WebDriver driver = SetUpDriver.setUpDriver();
        driver.get("http://www.automationpractice.pl/index.php");

        try {
            WebElement firstElement = driver.findElement(By.xpath("//a[@title='View my shopping cart']"));
            WebElement secondElement = driver.findElement(By.xpath("//a[@title='Women']"));
            twoWebElements(firstElement, secondElement);
            System.out.println();
        } catch (NoSuchElementException e) {
            System.out.println("No such element was found");
        }

        try {
            WebElement thirdElement = driver.findElement(By.cssSelector(".login"));
            WebElement fourthElement = driver.findElement(By.cssSelector("#contact-link"));
            twoWebElements(thirdElement, fourthElement);
            System.out.println();
        } catch (NoSuchElementException e) {
            System.out.println("No such element was found");
        }

        try {
            WebElement fifthElement = driver.findElement(By.xpath("//*[@id='icon-truck']"));
            WebElement sixthElement = driver.findElement(By.xpath("//*[@id='icon-phone']"));
            twoWebElements(fifthElement, sixthElement);
            System.out.println();
        } catch (NoSuchElementException e) {
            System.out.println("No such element was found");
        }
        driver.quit();
    }
}