/*Task 1. Проведите рефакторинг в соответствии с PageObject.*/

import auto_script_three_sites_pages.GuinessPage;
import auto_script_three_sites_pages.HyrtutorialsPage;
import auto_script_three_sites_pages.SearchGooglePage;
import auto_script_three_sites_pages.WThreeSchoolsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static auto_script_three_sites_pages.SearchGooglePage.getIdOfSecondWindow;
import static auto_script_three_sites_pages.SearchGooglePage.getIdOfThirdWindow;

public class TestAutoScriptThreeSites {
    private WebDriver driver;
    private ChromeOptions options;

    private static SearchGooglePage searchGooglePage;
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
        wThreeSchoolsPage = new WThreeSchoolsPage(driver);
        guinessPage = new GuinessPage(driver);
        hyrtutorialsPage = new HyrtutorialsPage(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    @Test
    public void testAutoScriptThreePages() {
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

        searchGooglePage.openSearchGoogle();
        searchGooglePage.searchAndOpenLinks();
        wThreeSchoolsPage.openWThreeSchools();
        wThreeSchoolsPage.fillFieldsWithDataAndGetTextWThree(testFirstName, testLastName);
        driver.switchTo().window(getIdOfSecondWindow());
        guinessPage.fillFieldsWithDataGuiness(testLastName, testFirstName,
                testDayOfBirth, testMonthOfBirth, testYearOfBirth, testCountryName,
                testState, testEmail, testConfirmEmail, testPassword, testConfirmPassword);
        driver.switchTo().window(getIdOfThirdWindow());
        hyrtutorialsPage.clickAlertButtons();
        Assert.assertTrue(true);
    }
}
