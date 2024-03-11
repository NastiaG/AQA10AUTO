package auto_script_three_sites_pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HyrtutorialsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private String stringOutputText;

    public HyrtutorialsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@aria-label='Consent']")
    private static WebElement cookiesButtonHyrtutorials;
    @FindBy(id = "cookieChoiceDismiss")
    private static WebElement cookiesInfoHyrtutorials;
    @FindBy(xpath = "//button[@id='alertBox']")
    private static WebElement buttonAlertBox;
    @FindBy(xpath = "//div[@id='output']")
    private static WebElement outputText;
    @FindBy(xpath = "//button[@id='confirmBox']")
    private static WebElement buttonConfirmBox;
    @FindBy(xpath = "//button[@id='promptBox']")
    private static WebElement buttonPromptBox;

    public void clickAlertButtons() {
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesButtonHyrtutorials));
            cookiesButtonHyrtutorials.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesInfoHyrtutorials));
            cookiesInfoHyrtutorials.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        buttonAlertBox.click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        stringOutputText = outputText.getText();
        System.out.println(stringOutputText);

        buttonConfirmBox.click();
        alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();

        stringOutputText = outputText.getText();
        System.out.println(stringOutputText);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", buttonPromptBox);
        js.executeScript("arguments[0].click();", buttonPromptBox);

        alert = wait.until(ExpectedConditions.alertIsPresent());
        String text = "Final step of this task";
        alert.sendKeys(text);
        alert.accept();

        stringOutputText = outputText.getText();
        System.out.println(stringOutputText);
    }
}
