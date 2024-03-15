package visual_andersenlab_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registration_script_pages.RegistrationScriptPage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AndersenlabStartPage {
    static final Logger logger = LoggerFactory.getLogger(RegistrationScriptPage.class);

    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    public AndersenlabStartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[contains(@class, 'CookiesPolicy-module--button--259ad')]")
    private static WebElement cookiesButton;

    @FindBy(xpath = "//div[@tabindex='Company']")
    private static WebElement linkCompany;

    @FindBy(xpath = "//span[@class='SubMenu-module--wrapLink--a9a7d']/a[text()='Insights']")
    private static WebElement linkInsights;

    @FindBy(xpath = "//a[@href='/privacy-policy']/following-sibling::a")
    private static WebElement cookiesLink;

    public WebElement getElementToMove() {
        return linkCompany;
    }

    public WebElement getCookiesButton() {
        return cookiesButton;
    }

    public WebElement getInnerElement() {
        return linkInsights;
    }
    public WebElement getScrollingElement() {
        return cookiesLink;
    }

    @Step("Opening the start page")
    public AndersenlabStartPage openAndersenlabStartPage() {
        driver.get("https://andersenlab.com/");
        return this;
    }

    @Step("Closing cookies")
    public AndersenlabStartPage closeCookiesAndersenStartPage(WebElement elementCookiesButton) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elementCookiesButton));
            elementCookiesButton.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    @Step("Moving to element")
    public AndersenlabStartPage moveToElement(WebElement element) {
        actions.moveToElement(element).perform();
        logger.info("Moving to element");
        return this;
    }

    @Step("Clicking on inner element")
    public AndersenlabStartPage clickOnInnerElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        actions.click(element).perform();
        logger.info("Clicking on element");
        return this;
    }

    @Step("Scrolling to element")
    public AndersenlabStartPage scrollingToElementAndClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        js.executeScript("arguments[0].click();", element);
        logger.info("Scrolling and clicking on element");
        return this;
    }


    @Step("Switching to new tab")
    public AndersenlabStartPage switchToNewTab() {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        String newTab = windowHandles.get(1);
        driver.switchTo().window(newTab);
        return this;
    }
}
