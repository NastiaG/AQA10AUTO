package visual_andersenlab_pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AndersenlabStartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public AndersenlabStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[contains(@class, 'CookiesPolicy-module--button--259ad')]")
    private static WebElement cookiesButton;

    @FindBy(xpath = "//div[@tabindex='Company']")
    private static WebElement linkCompany;

    @FindBy(xpath = "//span[@class='SubMenu-module--wrapLink--a9a7d']/a[text()='Insights']")
    private static WebElement linkInsights;

    @FindBy(xpath = "//a[@href='/privacy-policy']/following-sibling::a")
    private static WebElement cookiesLink;


    public void openAndersenlabStartPage() {
        driver.get("https://andersenlab.com/");
    }

    public void openSectionInsights() {
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesButton));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        actions.moveToElement(linkCompany).perform();
        actions.click(linkInsights).perform();
    }

    public void scrollingAndOpenCookiesPolicyNewTab() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(cookiesButton));
            cookiesButton.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", cookiesLink);
        js.executeScript("arguments[0].click();", cookiesLink);

        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String newTab = windowHandles.get(1);
        driver.switchTo().window(newTab);
    }
}
