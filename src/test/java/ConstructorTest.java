import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.example.BurgerEntarancePage;
import org.example.BurgerMainPage;
import org.example.BurgerRegistrationPage;
import org.example.WebDriverFactory;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.core.Is.is;

/* Раздел «Конструктор»
Проверь, что работают переходы к разделам:
* «Булки»,
* «Соусы»,
* «Начинки».*/

public class ConstructorTest {
    private WebDriver webDriver;


    @Before
    public void setup(){
        webDriver = WebDriverFactory.getWebDriver(System.getProperty("browser", "chrome"));
        webDriver.get("https://stellarburgers.nomoreparties.site/");
    }



    @Test
    @DisplayName("Проверка перехода в раздел")
    @Description("Булки")
    public void goToTheBunSection(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickFillingsButton();
        mainPage.clickBansButton();
        System.out.println(mainPage.getActiveConstructorTab());
        MatcherAssert.assertThat(mainPage.getActiveConstructorTab(),is("Булки"));
    }

    @Test
    @DisplayName("Проверка перехода в раздел")
    @Description("Соусы")
    public void goToTheSauceSection(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickSauceButton();
        System.out.println(mainPage.getActiveConstructorTab());
        MatcherAssert.assertThat(mainPage.getActiveConstructorTab(),is("Соусы"));
    }

    @Test
    @DisplayName("Проверка перехода в раздел")
    @Description("Начинки")
    public void goToTheFillingsSection(){
        BurgerMainPage mainPage = new BurgerMainPage(webDriver);
        mainPage.clickFillingsButton();
        System.out.println(mainPage.getActiveConstructorTab());
        MatcherAssert.assertThat(mainPage.getActiveConstructorTab(),is("Начинки"));
    }
    @After
    public void tearDown() {
        // Закрытие браузера
        webDriver.quit();
    }
}
