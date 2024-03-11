package visual_andersenlab_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AndersenlabInsightsPage {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public AndersenlabInsightsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//h1[contains(@class, 'Title-module--title--e41b8 Content-module--title--27d83')]")
    private static WebElement titleInsights;

    public String checkOpenedSectionInsights() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        System.out.println(driver.getCurrentUrl());
        wait.until(ExpectedConditions.visibilityOf(titleInsights));

        return titleInsights.getText();
    }
}
