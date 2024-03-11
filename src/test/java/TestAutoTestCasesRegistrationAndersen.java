/*Task 2. Доделать по три автотеста из каждого модуля, на которые писали тест - кейсы в лекции 10.*/

import auto_test_cases_pages.AndersenRegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import static auto_test_cases_pages.AndersenRegistrationPage.*;

public class TestAutoTestCasesRegistrationAndersen {
    private static WebDriver driver;
    private static ChromeOptions options;
    private static AndersenRegistrationPage andersenRegistrationPage;

    @BeforeClass
    public void setupDriver() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addArguments("--disable-notifications");
    }

    @BeforeMethod
    public void openRegistrationPage() {
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        andersenRegistrationPage = new AndersenRegistrationPage(driver);
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }


    @Test //RC02
    public void testRegistrationOfAnExistentUserWithCorrectData() {
        String testFirstName = "Bob";
        String testLastName = "Bobson";
        String testDateOfBirthMmDdYy = "01/01/2001";
        String testEmail = "bbb@mail.bbb";
        String testPassword = "111111111";
        String testConfirmPassword = "111111111";
        andersenRegistrationPage.openAndersenRegistrationPage();
        andersenRegistrationPage.registrationAndersen(testFirstName, testLastName, testDateOfBirthMmDdYy,
                testEmail, testPassword, testConfirmPassword);
        Assert.assertTrue(andersenRegistrationPage.findErrorMessageUserExists(), "There is no error message in case of registering already existent user");
    }

    @Test //RC03
    public void testSendingAnEmptyForm() {
        String testFirstName = "";
        String testLastName = "";
        String testDateOfBirthMmDdYy = "";
        String testEmail = "";
        String testPassword = "";
        String testConfirmPassword = "";
        andersenRegistrationPage.openAndersenRegistrationPage();
        andersenRegistrationPage.registrationAndersen(testFirstName, testLastName, testDateOfBirthMmDdYy,
                testEmail, testPassword, testConfirmPassword);
        Assert.assertFalse(buttonSubmit.isEnabled());
    }

    @Test //RC05
    public void testRegistrationWithOverlyLongCharacterSetsInTheFields() {
        String testFirstName = "_nHqXHmkOWPCmfbfnyTfwxXvFjUxg1BE1FpeUfsgpcqKNv8nHwIRq_0xw2bY0dlOFGfYUNGqJRVbsHJupcUAN8PrV.PT1tUb1DeoaZkBxkTxTBSYUMqyfejEFQjkcEqeXzuFRZmrDln8ibI3arMUFsWVLIyejsjgfqVlLfQXTWhacNKNkLbHuaKfXFlTiyiOOfPVqzjBTqwOyI3bXrBVbhGREBw7p6So5fteEfQPkLUzjVkxJBh3AmNHq7ke1oAEj28GulHhhz39AOnoaTbE7Sur5Fl2Y6EES.CtLqlHaQpYeWsylBwvsuEyeNX2nV5y.pdYCd95cgDj.X05gbjTaxA_T5e_E5MDtXGFhOHFSIevaMbnMwlFymOFJokddWfYo7dSpFWmkGvOW6m1BDYzlMiGdHmnwKCHhQDsxpEVpgRZDwkLVB8YdpsDmaSWADfDsfmxT9PKnBhnv1yAJSjvynlFLl7vmwxkwYusNf8x7ufr7iAzJBDqFnngPFggsajIuywRomxMlgwUcz67bQXqffhvgZW-jSg2UJlDAx-AZBpZOHHbq1N4XnvXriej_Ia9GsNNak0MtZhImbtvrjvGQoouKngjlwFFtMWaB1MQFYIg6NfUno6VXZAaZFtFlJHFp0Ota_bt3iG7otAPxhUTsMjdXxSgE_D8inIqH3_E1bKXQo9tAOprX9AngIr6n-qDlqwcdcBVAKhRtp5sCw6nVvADWNkZcjAqVikt5pODWwF-Z-7ZA9w-dOmkA0x-QalBbHRRydC7jCI5lixqQUoPJmvXlubZkVQRgfP32zTI9wZjYXCmBe2TVD5Ttu7wKoMAqfxGLNZBEkiOrGoUf6RqWjUHQFnMZ5PvxtprBRZXACjFZowYeOvuOI_YaabeZkLIZZIPHIBjsAoDc9GaiMkFJlVV3gNEezvSSJ7fAgWUQdDrMoFuYSIu4eWNrQuSkPSB45u5XGwkRqnceQitJ6kUTEYULsfp3LgQvvZ00GG";
        String testLastName = "_nHqXHmkOWPCmfbfnyTfwxXvFjUxg1BE1FpeUfsgpcqKNv8nHwIRq_0xw2bY0dlOFGfYUNGqJRVbsHJupcUAN8PrV.PT1tUb1DeoaZkBxkTxTBSYUMqyfejEFQjkcEqeXzuFRZmrDln8ibI3arMUFsWVLIyejsjgfqVlLfQXTWhacNKNkLbHuaKfXFlTiyiOOfPVqzjBTqwOyI3bXrBVbhGREBw7p6So5fteEfQPkLUzjVkxJBh3AmNHq7ke1oAEj28GulHhhz39AOnoaTbE7Sur5Fl2Y6EES.CtLqlHaQpYeWsylBwvsuEyeNX2nV5y.pdYCd95cgDj.X05gbjTaxA_T5e_E5MDtXGFhOHFSIevaMbnMwlFymOFJokddWfYo7dSpFWmkGvOW6m1BDYzlMiGdHmnwKCHhQDsxpEVpgRZDwkLVB8YdpsDmaSWADfDsfmxT9PKnBhnv1yAJSjvynlFLl7vmwxkwYusNf8x7ufr7iAzJBDqFnngPFggsajIuywRomxMlgwUcz67bQXqffhvgZW-jSg2UJlDAx-AZBpZOHHbq1N4XnvXriej_Ia9GsNNak0MtZhImbtvrjvGQoouKngjlwFFtMWaB1MQFYIg6NfUno6VXZAaZFtFlJHFp0Ota_bt3iG7otAPxhUTsMjdXxSgE_D8inIqH3_E1bKXQo9tAOprX9AngIr6n-qDlqwcdcBVAKhRtp5sCw6nVvADWNkZcjAqVikt5pODWwF-Z-7ZA9w-dOmkA0x-QalBbHRRydC7jCI5lixqQUoPJmvXlubZkVQRgfP32zTI9wZjYXCmBe2TVD5Ttu7wKoMAqfxGLNZBEkiOrGoUf6RqWjUHQFnMZ5PvxtprBRZXACjFZowYeOvuOI_YaabeZkLIZZIPHIBjsAoDc9GaiMkFJlVV3gNEezvSSJ7fAgWUQdDrMoFuYSIu4eWNrQuSkPSB45u5XGwkRqnceQitJ6kUTEYULsfp3LgQvvZ00GG";
        String testDateOfBirthMmDdYy = "02/24/2024";
        String testEmail = "fff@mail.fff";
        String testPassword = "12345678";
        String testConfirmPassword = "12345678";
        andersenRegistrationPage.openAndersenRegistrationPage();
        andersenRegistrationPage.registrationAndersen(testFirstName, testLastName, testDateOfBirthMmDdYy,
                testEmail, testPassword, testConfirmPassword);
        Assert.assertTrue(andersenRegistrationPage.findErrorMessageOverlyLongLine(), "There is no error message in case of extra long line input");
    }
}
