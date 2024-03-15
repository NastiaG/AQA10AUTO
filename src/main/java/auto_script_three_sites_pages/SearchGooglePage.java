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

public class SearchGooglePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;


    public SearchGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
    }

    @FindBy(xpath = "//button[@id='L2AGLb']")
    private static WebElement cookiesButtonGoogle;

    @FindBy(xpath = "//textarea")
    private static WebElement googleSearchLine;

    public WebElement getCookiesButtonGoogle() {
        return cookiesButtonGoogle;
    }

    public SearchGooglePage openSearchGoogle() {
        driver.get("https://www.google.com/search");
        return this;
    }

    public SearchGooglePage closeCookiesGoogle(WebElement elementCookiesButton) {
        try {
            wait.until(ExpectedConditions.visibilityOf(elementCookiesButton));
            elementCookiesButton.click();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return this;
    }

    public void enterSearchLine(String line) {
        actions
                .sendKeys(googleSearchLine, line)
                .keyDown(Keys.ENTER)
                .build()
                .perform();
    }
}
