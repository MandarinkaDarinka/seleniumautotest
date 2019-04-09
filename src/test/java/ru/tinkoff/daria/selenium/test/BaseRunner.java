package ru.tinkoff.daria.selenium.test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.tinkoff.daria.selenium.test.pages.TariffPage;

import java.util.concurrent.TimeUnit;

public class BaseRunner {
    public WebDriver driver;
    public final String baseUrl = "https://www.tinkoff.ru/mobile-operator/tariffs/";
    public final String browserName = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");

    public TariffPage tariff;

    @Before
    public void setUp()  {
        driver = BrowserFactory.buildDriver(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        tariff = new TariffPage(driver);
    }

    @After
    public void tearDown()  {
        driver.quit();
    }
}
