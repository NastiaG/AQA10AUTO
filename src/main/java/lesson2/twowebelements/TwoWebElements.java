package lesson2.twowebelements;

import org.openqa.selenium.WebElement;

public class TwoWebElements {
    public static void twoWebElements(WebElement firstElement, WebElement secondElement) {
        int xForFirstElement = firstElement.getLocation().x;
        int yForFirstElement = firstElement.getLocation().y;
        int xForSecondElement = secondElement.getLocation().x;
        int yForSecondElement = secondElement.getLocation().y;


        if (xForFirstElement < xForSecondElement) {
            System.out.println("First element is more left on the page");
        } else if (xForFirstElement > xForSecondElement) {
            System.out.println("Second element is more left on the page");
        } else if (xForFirstElement == xForSecondElement) {
            System.out.println("The left borders of two elements are on the same vertical line");
        }

        if (yForFirstElement < yForSecondElement) {
            System.out.println("First element is higher on the page");
        } else if (yForFirstElement > yForSecondElement) {
            System.out.println("Second element is higher on the page");
        } else if (yForFirstElement == yForSecondElement) {
            System.out.println("The upper borders of the two elements are on the same horizontal line");
        }


        int squareOfFirstElement = firstElement.getSize().width * firstElement.getSize().height;
        int squareOfSecondElement = secondElement.getSize().width * secondElement.getSize().height;

        if (squareOfFirstElement > squareOfSecondElement) {
            System.out.println("The square of the first element is more");
        } else if (squareOfFirstElement < squareOfSecondElement) {
            System.out.println("The square of the second element is more");
        } else if (squareOfFirstElement == squareOfSecondElement) {
            System.out.println("The squares of the elements are equal");
        }
    }
}