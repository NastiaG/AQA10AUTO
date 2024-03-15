package visual_andersenlab_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registration_script_pages.RegistrationScriptPage;

import java.time.Duration;

public class AndersenlabInsightsPage {
    static final Logger logger = LoggerFactory.getLogger(RegistrationScriptPage.class);

    private static WebDriver driver;
    private static WebDriverWait wait;

    public AndersenlabInsightsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//h1[contains(@class, 'Title-module--title--e41b8 Content-module--title--27d83')]")
    private static WebElement titleOfTheSection;

    @Step("Reading of title of the section")
    public String checkOpenedSectionInsights() {
        wait.until(ExpectedConditions.visibilityOf(titleOfTheSection));
        logger.info("Reading of title of the section");
        return titleOfTheSection.getText();
    }
}
