package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerEntarancePage {
    private final WebDriver webDriver;

    //Кнопка Регистрации
    private final By buttonRegistration = By.xpath(".//a[@href='/register']");
    //Вход
    public final By entrance = By.xpath(".//div/h2[text()='Вход']");
    //кнопка Конструктор
    public final By constructor = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2'][text()='Конструктор']");
    //Кнопка Стеллар Бургер
    public final By stellarBurger = By.xpath(".//div/a[@href='/']");
    //Поле ввода Email
    public final By emailField = By.xpath(".//div[label[@class='input__placeholder text noselect text_type_main-default'][text()='Email']]/input");
    //Поле ввода пароля
    public final By passwordField = By.xpath(".//div[label[@class='input__placeholder text noselect text_type_main-default'][text()='Пароль']]/input");
    //Кнопка Войти
    public final By entaranceButton = By.xpath(".//button[text()='Войти']");
    //Кнопка Восстановить пароль
    public final By passwordRecoveryButton = By.xpath(".//a[@href='/forgot-password'][text()='Восстановить пароль']");
    public BurgerEntarancePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Нажатие на кнопку Регистрация")
    public void clickRegistrationButton(){
        WebElement element = webDriver.findElement(buttonRegistration);
        element.click();
    }
    @Step("Ожидание загрузки страницы Вход")
    public void waitForLoadEntrance() {
        new WebDriverWait(webDriver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }
    @Step("Нажатие на Конструктор")
    public void clickConstructorButton(){
        WebElement element = webDriver.findElement(constructor);
        element.click();
    }
    @Step("Нажатие на Стеллар Бургер")
    public void clickStellaBurgerButton(){
        WebElement element = webDriver.findElement(stellarBurger);
        element.click();
    }
    @Step("Вход под зарегистрированным пользователем")
    public void entaranceUser(String email, String password){
        WebElement emailInput = webDriver.findElement(emailField);
        emailInput.sendKeys(email);

        WebElement passwordInput = webDriver.findElement(passwordField);
        passwordInput.sendKeys(password);

        WebElement element = webDriver.findElement(entaranceButton);
        element.click();
    }
    @Step("Нажатие кнопки Восстановить пароль")
    public void clickPasswordRecoveryButton(){
        WebElement element = webDriver.findElement(passwordRecoveryButton);
        element.click();
    }

}
