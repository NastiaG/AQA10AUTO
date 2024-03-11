/*Task 1. Проведите рефакторинг в соответствии с PageObject.*/

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;
import visual_andersenlab_pages.AndersenlabCookiesPage;
import visual_andersenlab_pages.AndersenlabInsightsPage;
import visual_andersenlab_pages.AndersenlabStartPage;


public class TestVisualAndersenlab {
    private WebDriver driver;
    private ChromeOptions options;
    private AndersenlabStartPage andersenlabStartPage;
    private AndersenlabInsightsPage andersenlabInsightsPage;
    private AndersenlabCookiesPage andersenlabCookiesPage;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
    }

    @BeforeMethod
    public void openPage() {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        andersenlabStartPage = new AndersenlabStartPage(driver);
        andersenlabStartPage.openAndersenlabStartPage();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void testOpenSectionInsights() {
        andersenlabInsightsPage = new AndersenlabInsightsPage(driver);
        andersenlabStartPage.openSectionInsights();
        Assert.assertEquals(andersenlabInsightsPage.checkOpenedSectionInsights(), "Insights");
    }

    @Test
    public void testScrollingAndOpenCookiesPolicyNewTab() {
        andersenlabCookiesPage = new AndersenlabCookiesPage(driver);
        andersenlabStartPage.scrollingAndOpenCookiesPolicyNewTab();
        Assert.assertEquals(andersenlabCookiesPage.returnTitle(), "Cookie Policy of Andersen's Websites");
    }
}
