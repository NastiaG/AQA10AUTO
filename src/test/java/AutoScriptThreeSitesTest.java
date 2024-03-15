/*Task 1. Проведите рефакторинг в соответствии с PageObject.*/

import auto_script_three_sites_pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Set;

public class AutoScriptThreeSitesTest {
    private WebDriver driver;
    private ChromeOptions options;

    private static SearchGooglePage searchGooglePage;
    private static ResultSearchGooglePage resultSearchGooglePage;
    private static HyrtutorialsPage hyrtutorialsPage;
    private static GuinessPage guinessPage;
    private static WThreeSchoolsPage wThreeSchoolsPage;


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
        searchGooglePage = new SearchGooglePage(driver);
        resultSearchGooglePage = new ResultSearchGooglePage(driver);
        wThreeSchoolsPage = new WThreeSchoolsPage(driver);
        guinessPage = new GuinessPage(driver);
        hyrtutorialsPage = new HyrtutorialsPage(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    @Description("Auto script of opening pages, filling data and clicking alert buttons")
    public void testAutoScriptThreePages() {
        String searchLineFirst = "https://www.guinnessworldrecords.com/account/register";
        String searchLineSecond = "https://www.hyrtutorials.com/p/alertsdemo.html";
        String idOfSecondWindow;
        String idOfThirdWindow;
        String testLastName = "Homazava";
        String testFirstName = "Anastasia";
        String testDayOfBirth = "07";
        String testMonthOfBirth = "02";
        String testYearOfBirth = "1989";
        String testCountryName = "Poland";
        String testState = "Dolny";
        String testEmail = "mail@mail.fmail";
        String testConfirmEmail = "mail@mail.fmail";
        String testPassword = "111111111";
        String testConfirmPassword = "222222222";

        searchGooglePage
                .openSearchGoogle()
                .closeCookiesGoogle(searchGooglePage.getCookiesButtonGoogle())
                .enterSearchLine(searchLineFirst);
        Set<String> windowHandles1 = driver.getWindowHandles();

        resultSearchGooglePage
                .openResultLink(resultSearchGooglePage.getResultLinkGuiness());
        Set<String> windowHandles2 = driver.getWindowHandles();
        windowHandles2.removeAll(windowHandles1);
        idOfSecondWindow = windowHandles2.iterator().next();

        resultSearchGooglePage
                .searchAnotherLine(searchLineSecond)
                .openResultLink(resultSearchGooglePage.getrResultLinkHyrtutorials());

        Set<String> windowHandles3 = driver.getWindowHandles();
        windowHandles3.removeAll(windowHandles2);
        windowHandles3.removeAll(windowHandles1);
        idOfThirdWindow = windowHandles3.iterator().next();

        wThreeSchoolsPage.openWThreeSchools();
        wThreeSchoolsPage.fillFieldsWithDataAndGetTextWThree(testFirstName, testLastName);

        driver.switchTo().window(idOfSecondWindow);
        guinessPage.fillFieldsWithDataGuiness(testLastName, testFirstName,
                testDayOfBirth, testMonthOfBirth, testYearOfBirth, testCountryName,
                testState, testEmail, testConfirmEmail, testPassword, testConfirmPassword);

        driver.switchTo().window(idOfThirdWindow);
        hyrtutorialsPage.clickAlertButtons();
        Assert.assertTrue(true);
    }
}
