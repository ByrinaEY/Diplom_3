package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerMainPage {
    private final WebDriver webDriver;


    public BurgerMainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    //Кнопка Личный кабинет
    private final By buttonPersonalArea = By.xpath(".//a[@href='/account']");
    // Кнопка Булки
    private final By bansButton = By.xpath(".//div/span[text()='Булки']");
    // Кнопка Соусы
    private final By sauceButton = By.xpath(".//div/span[text()='Соусы']");
    // Кнопка Начинки
    private final By fillingsButton = By.xpath(".//div/span[text()='Начинки']");
    // Кнопка Войти в аккаунт
    private final By enaterInAccount = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg'][text()='Войти в аккаунт']");
    //Надпись булки для проверки перехода в разделе
    public final By main = By.xpath(".//h1[@class='text text_type_main-large mb-5 mt-10'][text()='Соберите бургер']");
//Активная вкладка конструктора бургера
    private final By activeCounstructorTab = By.xpath(".//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']/span");

    @Step ("Клик по кнопке Личный кабинет")
    public void clickPersonAreaButton(){
        WebElement element = webDriver.findElement(buttonPersonalArea);
        element.click();
    }
    @Step ("Клик по кнопке Булки")
    public void clickBansButton(){
        WebElement element = webDriver.findElement(bansButton);
        element.click();
    }
    @Step ("Клик по кнопке Соусы")
    public void clickSauceButton(){
        WebElement element = webDriver.findElement(sauceButton);
        element.click();
    }
    @Step ("Клик по кнопке Начинки")
    public void clickFillingsButton(){
        WebElement element = webDriver.findElement(fillingsButton);
        element.click();
    }

    @Step ("Получение значения активной вкладки")
    public String getActiveConstructorTab() {
        WebElement element = webDriver.findElement(activeCounstructorTab);
        return element.getText();

    }
    @Step ("Ожидание загрузки главной страницы")
    public void waitForLoadMainPage() {
        new WebDriverWait(webDriver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(main));
    }
    @Step ("Нажатие кнопки Войти в аккаунт")
    public void clickEnterAccountButton() {
        WebElement element = webDriver.findElement(enaterInAccount);
        element.click();
    }

}
