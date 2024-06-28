import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import user.LogIn;
import user.User;

/*Вход
Проверь:
*вход по кнопке «Войти в аккаунт» на главной,
*вход через кнопку «Личный кабинет»,
*вход через кнопку в форме регистрации,
*вход через кнопку в форме восстановления пароля. */
public class EnteranceTest {
    private WebDriver webDriver;
    static String EMAIL = "ByrinaEY@mail.ru";
    static String PASSWORD = "123456";

    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }
    @Test
    @DisplayName("Вход по кнопке")
    @Description("Войти в аккаунт на главной")
    public void entaranceFromButtonEnterOnMainPage(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.entaranceUser(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();

    }

    @Test
    @DisplayName("Вход по кнопке")
    @Description("Вход через кнопку Личный кабинет")
    public void entaranceFromPersonalArea(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickEnterAccountButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.entaranceUser(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
    }
    @Test
    @DisplayName("Вход по кнопке")
    @Description("Вход через кнопку в форме регистрации")
    public void entaranceFromRegistration(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.clickRegistrationButton();
        BurgerRegistrationPage registrationPage = new BurgerRegistrationPage(webDriver);
        registrationPage.waitForLoadRegisterPage();
        registrationPage.clickEnterOnRegistrationPage();
        entarancePage.waitForLoadEntrance();
        entarancePage.entaranceUser(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();

    }

    @Test
    @DisplayName("Вход по кнопке")
    @Description("Вход через кнопку в форме восстановления пароля")
    public void entaranceFromPasswordRecovery(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.clickPasswordRecoveryButton();
        BurgerPasswordRecoveryPage burgerPasswordRecoveryPage = new BurgerPasswordRecoveryPage(webDriver);
        burgerPasswordRecoveryPage.waitForInvisibilityLoadingAnimation();
        burgerPasswordRecoveryPage.clickEnterButton();
        entarancePage.waitForLoadEntrance();
        entarancePage.entaranceUser(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();

    }
    @After
    public void tearDown() {
        // Закрытие браузера
        webDriver.quit();
    }
}
