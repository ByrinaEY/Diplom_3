package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerRegistrationPage {
    private final WebDriver webDriver;
//Надпись Регистрация
   public final By registerText = By.xpath(".//div/h2[text()='Регистрация']");
   // Поле ввода имени
   private final By nameField = By.xpath(".//div[./label[text()='Имя']]/input[@name='name']");
   //поле ввода E-mail
   private final By emailField = By.xpath(".//div[./label[text()='Email']]/input[@name='name']");
    //поле ввода пароля
    private final By passwordField = By.xpath(".//div[./label[text()='Пароль']]/input[@name='Пароль']");
   //Кнопка регистрации
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Некорректный пароль
    public final By incorrectPassword = By.xpath(".//p[@class='input__error text_type_main-default'][text()='Некорректный пароль']");
    //Кнопка Войти
    public final By enterButton = By.xpath(".//a[@class=\"Auth_link__1fOlj\"][text()='Войти']");
    public BurgerRegistrationPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
//Ожидание загрузки страницы регистрации
    @Step("Ожидание заргузки страницы")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(webDriver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }
    @Step("Ввод данных для регистрации пользователя")
    public void registration(String name, String email, String password){
        WebElement nameInput = webDriver.findElement(nameField);
        nameInput.sendKeys(name);

        WebElement emailInput = webDriver.findElement(emailField);
        emailInput.sendKeys(email);

        WebElement passwordInput = webDriver.findElement(passwordField);
        passwordInput.sendKeys(password);
    }
    @Step("Нажатие кнопки Регистрация")
    public void clickRegistrationButton()
    { WebElement element = webDriver.findElement(registrationButton);
        element.click();

    }
    @Step("Нажатие на Войти на странице регистрации")
    public void clickEnterOnRegistrationPage(){
        WebElement element = webDriver.findElement(enterButton);
        element.click();
    }
}
