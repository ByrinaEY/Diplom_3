import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import user.LogIn;
import user.User;

/*
- Переход в личный кабинет по клику на «Личный кабинет».
- Переход из личного кабинета в конструктор по клику на «Конструктор»
- Переход из личного кабинета в конструктор Stellar Burgers.
- Выход из аккаунта по кнопке «Выйти» в личном кабинете.
 */

public class TransitionsTest {
    private WebDriver webDriver;
    private static String accessToken;
    public static final String URL = "https://stellarburgers.nomoreparties.site/";
    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    @DisplayName("Переход в личный кабинет по клику на Личный кабинет")
    @Description("Переход по клику на Личный кабинет")
    public void transitionPersonalArea(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.waitForLoadEntrance();
        Assert.assertTrue("Не отобразилась страница входа", webDriver.findElement(entarancePage.entrance).isDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета")
    @Description("В конструктор по клику на Stellar Burgers")
    public void transitionInConstructorFromStellarBurgers(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.waitForLoadEntrance();
        entarancePage.clickStellaBurgerButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Конструктор при клике на логотип не загрузился", webDriver.findElement(mainPage.main).isDisplayed());
    }

    @Test
    @DisplayName("Переход из личного кабинета")
    @Description("В конструктор по клику на Конструктор")
    public void transitionInConstructor(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.waitForLoadEntrance();
        entarancePage.clickConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Конструктор при клике на логотип не загрузился", webDriver.findElement(mainPage.main).isDisplayed());
    }
    @Test
    @DisplayName("Выход из аккаунта")
    @Description("По кнопке «Выйти» в личном кабинете")
    public void exitFromAccountFromPersonalArea(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickPersonAreaButton();
        BurgerEntarancePage entarancePage = new BurgerEntarancePage(webDriver);
        entarancePage.clickRegistrationButton();
        BurgerRegistrationPage registrationPage = new BurgerRegistrationPage(webDriver);
        registrationPage.waitForLoadRegisterPage();
        registrationPage.registration(RegistrationTest.NAME, RegistrationTest.EMAIL, RegistrationTest.PASSWORD);
        registrationPage.clickRegistrationButton();
        entarancePage.waitForLoadEntrance();
        entarancePage.entaranceUser(RegistrationTest.EMAIL, RegistrationTest.PASSWORD);
        mainPage.waitForLoadMainPage();
        mainPage.clickPersonAreaButton();
        BurgerPersonalAreaPage personalAreaPage = new BurgerPersonalAreaPage(webDriver);
        personalAreaPage.waitForLoadProfilePage();
        personalAreaPage.clickExitButton();
        entarancePage.waitForLoadEntrance();
        Assert.assertTrue("Не удалось выйти из аккаунта", webDriver.findElement(entarancePage.entrance).isDisplayed());
        //удаление созданного акк
        RestAssured.baseURI = URL;
        accessToken = LogIn.checkRequestAuthLogin(new User(RegistrationTest.EMAIL, RegistrationTest.PASSWORD, RegistrationTest.NAME)).then().extract().path("accessToken");
        if (accessToken != null){
            LogIn.deleteUser(accessToken);}
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        webDriver.quit();
    }

}
