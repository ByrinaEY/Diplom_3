package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerPersonalAreaPage {
    private final WebDriver webDriver;
   //Кнопка Выход
    public final By exitButton = By.xpath(".//li/button[text()='Выход']") ;
    // Надпись для проверки загрузки страницы Личного кабинета
    public final By textOnPersonalPage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");

    public BurgerPersonalAreaPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    @Step("Выход из личного кабинета")
    public void clickExitButton(){
        WebElement element = webDriver.findElement(exitButton);
        element.click();
        waitForInvisibilityLoadingAnimation();
    }
    @Step("Ожидание загрузки страницы полностью, пока не исчезнет анимация")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
    @Step("Ожидание загрузки страницы личного кабинета")
    public void waitForLoadProfilePage() {
        // подожди 3 секунды, чтобы элемент с нужным текстом стал видимым
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(textOnPersonalPage));
    }
}
