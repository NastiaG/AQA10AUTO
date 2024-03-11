package visual_andersenlab_pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class AndersenlabCookiesPage {
    private static WebDriver driver;
    public AndersenlabCookiesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String returnTitle() {
        return driver.getTitle();
    }
}
