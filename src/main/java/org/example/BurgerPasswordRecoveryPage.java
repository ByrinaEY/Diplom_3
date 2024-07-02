package org.example;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BurgerPasswordRecoveryPage {
    private final WebDriver webDriver;
    public final By entaranceButton = By.xpath(".//a[@href='/login'][text()='Войти']");

    public BurgerPasswordRecoveryPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    @Step("Ожидание загрузки страницы полностью")
    public void waitForInvisibilityLoadingAnimation() {

        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
    }
    @Step("Нажатие на кнопку Войти")
    public void clickEnterButton(){
        WebElement element = webDriver.findElement(entaranceButton);
        element.click();
    }
}
