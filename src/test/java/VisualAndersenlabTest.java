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


public class VisualAndersenlabTest {
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
        String titleToCheck = "Insights";
        andersenlabInsightsPage = new AndersenlabInsightsPage(driver);
        andersenlabStartPage
                .openAndersenlabStartPage()
                .closeCookiesAndersenStartPage(andersenlabStartPage.getCookiesButton())
                .moveToElement(andersenlabStartPage.getElementToMove())
                .clickOnInnerElement(andersenlabStartPage.getInnerElement());

        Assert.assertEquals(andersenlabInsightsPage.checkOpenedSectionInsights(), titleToCheck);
    }

    @Test
    public void testScrollingAndOpenCookiesPolicyNewTab() {
        String expectedTitle = "Cookie Policy of Andersen's Websites";
        andersenlabCookiesPage = new AndersenlabCookiesPage(driver);
        andersenlabStartPage
                .scrollingToElementAndClick(andersenlabStartPage.getScrollingElement())
                        .switchToNewTab();
        Assert.assertEquals(andersenlabCookiesPage.returnTitle(), expectedTitle);
    }
}
