package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static WebDriver getWebDriver(String browserType) {
        if
         (browserType.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else {
            {
                System.setProperty("webdriver.chrome.driver", "C:\\Diplom\\Diplom_3\\src\\main\\resources\\yandexdriver.exe");
                return new ChromeDriver();
            }
        }
    }
}

