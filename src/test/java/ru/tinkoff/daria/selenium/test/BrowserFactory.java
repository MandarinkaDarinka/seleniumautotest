package ru.tinkoff.daria.selenium.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

    public static WebDriver buildDriver(String browserName) {
        switch (browserName) {

            case "chrome":
                ChromeOptions chromeInvisibleOpt = new ChromeOptions();
                chromeInvisibleOpt.addArguments("--disable-notifications");
                return new ChromeDriver(chromeInvisibleOpt);

            case "firefox":
                System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");

                FirefoxOptions ffOpt = new FirefoxOptions();
                ffOpt.addPreference("dom.webnotifications.enabled", false);
                return new FirefoxDriver(ffOpt);

            default:
                throw new RuntimeException("Unknown driver.");
        }
    }

}
