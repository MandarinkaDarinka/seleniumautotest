package ru.tinkoff.daria.selenium.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TinkoffTariffPageTest extends BaseRunner {

    @Test
    public void testEmptyFieldsTestCase() {
        tariff.open();

        tariff.clickName();
        tariff.clickPhone();
        tariff.clickEmail();

        assertEquals("Укажите ваше ФИО", tariff.getNameError());
        assertEquals("Необходимо указать номер телефона", tariff.getPhoneError());
    }

    @Test
    public void testInvalidValuesTestCase() {
        driver.navigate().to(baseUrl);

        tariff.fillName("нкгнукункг");
        tariff.fillPhone("+7(345) 345-");
        tariff.fillEmail("шгншгншгннг");

        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", tariff.getNameError());
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", tariff.getPhoneError());
        assertEquals("Введите корректный адрес эл. почты", tariff.getEmailError());
    }

    @Test
    public void testSearchTinkoffTariffInGoogle() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys("мобайл тинькофф");
        driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div/div[2]/div[2]/ul/li[2]/div/div[1]/div/span/b")).click();
        driver.findElement(By.xpath("//*[text()[contains(.,'https://www.tinkoff.ru/mobile-operator/tariffs/')]]")).click();
        assertEquals("Тарифы Тинькофф Мобайла", driver.getTitle());
        assertEquals("https://www.tinkoff.ru/mobile-operator/tariffs/", driver.getCurrentUrl());
    }

    @Test
    public void testChangeRegion() {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Да'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[5]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()[contains(.,'Москва и Московская область')]]")));

        assertEquals("Москва и Московская область",
                driver.findElement(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")
                ).getText()
        );

        driver.navigate().refresh();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()[contains(.,'Москва и Московская область')]]")));

        assertEquals("Москва и Московская область",
                driver.findElement(
                        By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Точки продаж'])[1]/following::div[13]")
                ).getText()
        );

        int mskTariff = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[1]/div/h3")).getText().replaceAll("[^\\d.]", ""));

        driver.findElement(By.xpath("//*[text()[contains(.,'Интернет')]]")).click();
        driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[3]/div/div[1]/div/div/div/div[2]/div[6]")).click();

        driver.findElement(By.xpath("//*[text()[contains(.,'Звонки')]]")).click();
        driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div/div[2]/div[6]")).click();

        driver.findElement(By.xpath("//*[text()[contains(.,'Режим модема')]]")).click();
        driver.findElement(By.xpath("//*[text()[contains(.,'Безлимитные СМС')]]")).click();

        int mskTariffMax = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[1]/div/h3")).getText().replaceAll("[^\\d.]", ""));

        driver.findElement(By.xpath("//*[text()[contains(.,'Москва и Московская область')]]")).click();
        driver.findElement(By.xpath("//*[text()[contains(.,'Краснодарский кр.')]]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()[contains(.,'Краснодарский край')]]")));

        int krasnodarTariff = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[1]/div/h3")).getText().replaceAll("[^\\d.]", ""));

        driver.findElement(By.xpath("//*[text()[contains(.,'Интернет')]]")).click();
        driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[3]/div/div[1]/div/div/div/div[2]/div[6]")).click();

        driver.findElement(By.xpath("//*[text()[contains(.,'Звонки')]]")).click();
        driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[3]/div/div[2]/div/div/div/div[2]/div[6]")).click();

        driver.findElement(By.xpath("//*[text()[contains(.,'Режим модема')]]")).click();
        driver.findElement(By.xpath("//*[text()[contains(.,'Безлимитные СМС')]]")).click();

        int krasnodarTariffMax = Integer.parseInt(driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[1]/div/h3")).getText().replaceAll("[^\\d.]", ""));

        assertTrue(mskTariff != krasnodarTariff);
        assertEquals(krasnodarTariffMax, mskTariffMax);
    }

    @Test
    public void testInactiveDeliveryButton() {
        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.get("https://www.tinkoff.ru/mobile-operator/tariffs/");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Да'])[1]/following::span[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Город'])[1]/following::div[5]")).click();

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("Иванов Иван Оскарович");

        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).clear();
        driver.findElement(By.name("phone_mobile")).sendKeys("9993454545");

        driver.findElement(By.xpath("//*[@id=\"form-application\"]/div[2]/div/div/form/div[6]/div/div[2]/div/div/div/div/button/span/div[2]/div")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()[contains(.,'Заказать доставку')]]")));

        assertFalse(driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/div/div[2]/div/div/div[5]/div/button/span/span/span/span")).isDisplayed());
    }
}
