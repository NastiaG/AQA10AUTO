/*Task 2. Доделать по три автотеста из каждого модуля, на которые писали тест - кейсы в лекции 10.*/

import auto_test_cases_pages.AndersenRegistrationPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import static auto_test_cases_pages.AndersenRegistrationPage.*;

public class AndersenRegistrationPageTest {
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
    @Description("Registering already existent user test")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering data of already existent user")
    @Story("I see an error message if I try to register already existent user")
    public void testRegistrationOfAnExistentUserWithCorrectData() {
        String firstName = "Bob";
        String lastName = "Bobson";
        String dateOfBirthMmDdYy = "01/01/2001";
        String email = "bbb@mail.bbb";
        String password = "111111111";
        String passwordConfirmation = "111111111";

        andersenRegistrationPage
                .openAndersenRegistrationPage()
                .clickRegistrationButton()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillDateOfBirth(dateOfBirthMmDdYy)
                .fillEmail(email)
                .fillPassword(password)
                .fillPasswordConfirmation(passwordConfirmation)
                .clickSubmit();

        Assert.assertTrue(andersenRegistrationPage.findErrorMessageUserExists(), "There is no error message in case of registering already existent user");
    }

    @Test //RC03
    @Description("Registering with an empty form test")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Sending an empty form testing")
    @Story("I can't click Submit if I didn't fill the registration form")
    public void testSendingAnEmptyForm() {
        String firstName = "";
        String lastName = "";
        String dateOfBirthMmDdYy = "";
        String email = "";
        String password = "";
        String passwordConfirmation = "";

        andersenRegistrationPage
                .openAndersenRegistrationPage()
                .clickRegistrationButton()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillDateOfBirth(dateOfBirthMmDdYy)
                .fillEmail(email)
                .fillPassword(password)
                .fillPasswordConfirmation(passwordConfirmation)
                .clickSubmit();

        Assert.assertFalse(buttonSubmit.isEnabled());
    }

    @Test //RC05
    @Description("Registering with overly long lines test")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Entering overly long lines testing")
    @Story("I see an error message if I entered overly long lines in the registration form")
    public void testRegistrationWithOverlyLongCharacterSetsInTheFields() {
        String firstName = "_nHqXHmkOWPCmfbfnyTfwxXvFjUxg1BE1FpeUfsgpcqKNv8nHwIRq_0xw2bY0dlOFGfYUNGqJRVbsHJupcUAN8PrV.PT1tUb1DeoaZkBxkTxTBSYUMqyfejEFQjkcEqeXzuFRZmrDln8ibI3arMUFsWVLIyejsjgfqVlLfQXTWhacNKNkLbHuaKfXFlTiyiOOfPVqzjBTqwOyI3bXrBVbhGREBw7p6So5fteEfQPkLUzjVkxJBh3AmNHq7ke1oAEj28GulHhhz39AOnoaTbE7Sur5Fl2Y6EES.CtLqlHaQpYeWsylBwvsuEyeNX2nV5y.pdYCd95cgDj.X05gbjTaxA_T5e_E5MDtXGFhOHFSIevaMbnMwlFymOFJokddWfYo7dSpFWmkGvOW6m1BDYzlMiGdHmnwKCHhQDsxpEVpgRZDwkLVB8YdpsDmaSWADfDsfmxT9PKnBhnv1yAJSjvynlFLl7vmwxkwYusNf8x7ufr7iAzJBDqFnngPFggsajIuywRomxMlgwUcz67bQXqffhvgZW-jSg2UJlDAx-AZBpZOHHbq1N4XnvXriej_Ia9GsNNak0MtZhImbtvrjvGQoouKngjlwFFtMWaB1MQFYIg6NfUno6VXZAaZFtFlJHFp0Ota_bt3iG7otAPxhUTsMjdXxSgE_D8inIqH3_E1bKXQo9tAOprX9AngIr6n-qDlqwcdcBVAKhRtp5sCw6nVvADWNkZcjAqVikt5pODWwF-Z-7ZA9w-dOmkA0x-QalBbHRRydC7jCI5lixqQUoPJmvXlubZkVQRgfP32zTI9wZjYXCmBe2TVD5Ttu7wKoMAqfxGLNZBEkiOrGoUf6RqWjUHQFnMZ5PvxtprBRZXACjFZowYeOvuOI_YaabeZkLIZZIPHIBjsAoDc9GaiMkFJlVV3gNEezvSSJ7fAgWUQdDrMoFuYSIu4eWNrQuSkPSB45u5XGwkRqnceQitJ6kUTEYULsfp3LgQvvZ00GG";
        String lastName = "_nHqXHmkOWPCmfbfnyTfwxXvFjUxg1BE1FpeUfsgpcqKNv8nHwIRq_0xw2bY0dlOFGfYUNGqJRVbsHJupcUAN8PrV.PT1tUb1DeoaZkBxkTxTBSYUMqyfejEFQjkcEqeXzuFRZmrDln8ibI3arMUFsWVLIyejsjgfqVlLfQXTWhacNKNkLbHuaKfXFlTiyiOOfPVqzjBTqwOyI3bXrBVbhGREBw7p6So5fteEfQPkLUzjVkxJBh3AmNHq7ke1oAEj28GulHhhz39AOnoaTbE7Sur5Fl2Y6EES.CtLqlHaQpYeWsylBwvsuEyeNX2nV5y.pdYCd95cgDj.X05gbjTaxA_T5e_E5MDtXGFhOHFSIevaMbnMwlFymOFJokddWfYo7dSpFWmkGvOW6m1BDYzlMiGdHmnwKCHhQDsxpEVpgRZDwkLVB8YdpsDmaSWADfDsfmxT9PKnBhnv1yAJSjvynlFLl7vmwxkwYusNf8x7ufr7iAzJBDqFnngPFggsajIuywRomxMlgwUcz67bQXqffhvgZW-jSg2UJlDAx-AZBpZOHHbq1N4XnvXriej_Ia9GsNNak0MtZhImbtvrjvGQoouKngjlwFFtMWaB1MQFYIg6NfUno6VXZAaZFtFlJHFp0Ota_bt3iG7otAPxhUTsMjdXxSgE_D8inIqH3_E1bKXQo9tAOprX9AngIr6n-qDlqwcdcBVAKhRtp5sCw6nVvADWNkZcjAqVikt5pODWwF-Z-7ZA9w-dOmkA0x-QalBbHRRydC7jCI5lixqQUoPJmvXlubZkVQRgfP32zTI9wZjYXCmBe2TVD5Ttu7wKoMAqfxGLNZBEkiOrGoUf6RqWjUHQFnMZ5PvxtprBRZXACjFZowYeOvuOI_YaabeZkLIZZIPHIBjsAoDc9GaiMkFJlVV3gNEezvSSJ7fAgWUQdDrMoFuYSIu4eWNrQuSkPSB45u5XGwkRqnceQitJ6kUTEYULsfp3LgQvvZ00GG";
        String dateOfBirthMmDdYy = "02/24/2024";
        String email = "fff@mail.fff";
        String password = "12345678";
        String passwordConfirmation = "12345678";

        andersenRegistrationPage
                .openAndersenRegistrationPage()
                .clickRegistrationButton()
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillDateOfBirth(dateOfBirthMmDdYy)
                .fillEmail(email)
                .fillPassword(password)
                .fillPasswordConfirmation(passwordConfirmation)
                .clickSubmit();

        Assert.assertTrue(andersenRegistrationPage.findErrorMessageOverlyLongLine(), "There is no error message in case of extra long line input");
    }
}
