package auto_script_three_sites_pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WThreeSchoolsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public WThreeSchoolsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "accept-choices")
    private static WebElement cookiesButtonWThree;
    @FindBy(xpath = "//input[@id='fname']")
    private static WebElement firstName;
    @FindBy(xpath = "//input[@id='lname']")
    private static WebElement lastName;
    @FindBy(xpath = "//input[@type='submit']")
    private static WebElement submitButton;
    @FindBy(css = ".w3-panel.w3-pale-yellow.w3-leftbar.w3-border-yellow")
    private static WebElement noteText;

    public void openWThreeSchools() {
        driver.get("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_form_submit");
    }

    public void fillFieldsWithDataAndGetTextWThree(String testFirstName, String testLastName) {
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesButtonWThree));
            cookiesButtonWThree.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        driver.switchTo().frame("iframeResult");

        firstName.clear();
        firstName.sendKeys(testFirstName);

        lastName.clear();
        lastName.sendKeys(testLastName);

        submitButton.click();

        System.out.println(noteText.getText());

        driver.switchTo().defaultContent();
    }
}
