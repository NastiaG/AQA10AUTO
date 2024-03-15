package auto_script_three_sites_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResultSearchGooglePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    public ResultSearchGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @FindBy(xpath = "//textarea")
    private static WebElement googleSearchLine;

    @FindBy(xpath = "//h3[contains(text(),'Create account')]")
    private static WebElement resultLinkGuiness;

    @FindBy(xpath = "//span[@jsname='itVqKe']")
    private static WebElement clearSign;

    @FindBy(xpath = "//h3[contains(text(), 'AlertsDemo')]")
    private static WebElement resultLinkHyrtutorials;

    public WebElement getResultLinkGuiness() {
        return resultLinkGuiness;
    }
    public WebElement getrResultLinkHyrtutorials() {
        return resultLinkHyrtutorials;
    }


    public void openResultLink(WebElement resultLink) {
        actions
                .keyDown(Keys.CONTROL)
                .click(resultLink)
                .keyUp(Keys.CONTROL)
                .build()
                .perform();
    }

    public ResultSearchGooglePage searchAnotherLine(String anotherLine) {
        actions
                .click(clearSign)
                .sendKeys(googleSearchLine, anotherLine)
                .keyDown(Keys.ENTER)
                .build()
                .perform();
        return this;
    }
}

