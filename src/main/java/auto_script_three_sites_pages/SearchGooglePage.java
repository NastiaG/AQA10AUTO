package auto_script_three_sites_pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class SearchGooglePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private static String idOfSecondWindow;
    private static String idOfThirdWindow;


    public SearchGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static String getIdOfSecondWindow() {
        return idOfSecondWindow;
    }

    public static String getIdOfThirdWindow() {
        return idOfThirdWindow;
    }

    @FindBy(xpath = "//button[@id='L2AGLb']")
    private static WebElement cookiesButtonGoogle;

    @FindBy(xpath = "//textarea")
    private static WebElement googleSearchLine;

    @FindBy(xpath = "//h3[contains(text(),'Create account')]")
    private static WebElement resultLinkGuiness;

    @FindBy(xpath = "//span[@jsname='itVqKe']")
    private static WebElement clearSign;

    @FindBy(xpath = "//h3[contains(text(), 'AlertsDemo')]")
    private static WebElement resultLinkHyrtutorials;

    public void openSearchGoogle() {
        driver.get("https://www.google.com/search");
    }

    public void searchAndOpenLinks() {
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Set<String> windowHandles1 = driver.getWindowHandles();

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesButtonGoogle));
            cookiesButtonGoogle.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        actions
                .sendKeys(googleSearchLine, "https://www.guinnessworldrecords.com/account/register")
                .keyDown(Keys.ENTER)
                .build().perform();

        actions
                .keyDown(Keys.CONTROL)
                .click(resultLinkGuiness)
                .keyUp(Keys.CONTROL)
                .build().perform();

        Set<String> windowHandles2 = driver.getWindowHandles();
        windowHandles2.removeAll(windowHandles1);
        idOfSecondWindow = windowHandles2.iterator().next();

        actions
                .click(clearSign)
                .sendKeys(googleSearchLine, "https://www.hyrtutorials.com/p/alertsdemo.html")
                .keyDown(Keys.ENTER)
                .build().perform();

        actions
                .keyDown(Keys.CONTROL)
                .click(resultLinkHyrtutorials)
                .keyUp(Keys.CONTROL)
                .build().perform();

        Set<String> windowHandles3 = driver.getWindowHandles();
        windowHandles3.removeAll(windowHandles2);
        windowHandles3.removeAll(windowHandles1);
        idOfThirdWindow = windowHandles3.iterator().next();
    }
}
