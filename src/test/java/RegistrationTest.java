import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.BurgerEntarancePage;
import org.example.BurgerMainPage;
import org.example.BurgerRegistrationPage;
import org.example.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import user.LogIn;
import user.User;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.core.Is.is;
import static user.LogIn.checkRequestAuthLogin;

/* Регистрация
* Проверь:
* Успешную регистрацию.
Ошибку для некорректного пароля. Минимальный пароль — шесть символов. */
public class RegistrationTest {
    private WebDriver webDriver;

    static String NAME = randomAlphanumeric(4, 8);
    static String EMAIL = randomAlphanumeric(6, 10) + "@mail.ru";
    static String PASSWORD = randomAlphanumeric(10, 20);
    static String PASSWORD_WRONG = randomAlphanumeric(0, 5);
    public static String accessToken;
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }



    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Успешная регистрация")
    public void successRegistration(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.clickRegistrationButton();
        BurgerRegistrationPage registrationPage = new BurgerRegistrationPage(webDriver);
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registration(NAME, EMAIL, PASSWORD);
        registrationPage.clickRegistrationButton();
        entarancePage.waitForLoadEntrance();
        RestAssured.baseURI = URL;
        accessToken = LogIn.checkRequestAuthLogin(new User(EMAIL, PASSWORD, NAME)).then().extract().path("accessToken");
        if (accessToken != null){
            LogIn.deleteUser(accessToken);}

 }

    @Test
    @DisplayName("Регистрация пользователя")
    @Description("Ошибка для некорректного ввода пароля")
    public void inputIncorrectPassword(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.clickRegistrationButton();
        BurgerRegistrationPage registrationPage = new BurgerRegistrationPage(webDriver);
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registration(NAME, EMAIL, PASSWORD_WRONG);
        registrationPage.clickRegistrationButton();
        Assert.assertTrue("Текст об ошибке отсутствует", webDriver.findElement(registrationPage.incorrectPassword).isDisplayed());
    }


    @After
    public void tearDown() {

        // Закрытие браузера
        webDriver.quit();

    }

}
