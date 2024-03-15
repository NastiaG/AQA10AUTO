package visual_andersenlab_pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import registration_script_pages.RegistrationScriptPage;

public class AndersenlabCookiesPage {
    static final Logger logger = LoggerFactory.getLogger(RegistrationScriptPage.class);

    private static WebDriver driver;
    public AndersenlabCookiesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Getting title of the new window")
    public String returnTitle() {
        logger.info("Getting title of the new window");
        return driver.getTitle();
    }
}
