package ru.tinkoff.daria.selenium.test;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class TinkoffTariffPageTest extends BaseRunner {

    @Test
    public void testEmptyFieldsTestCase() {
        driver.navigate().to(baseUrl);

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("email")).click();

        assertEquals("Укажите ваше ФИО", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Необходимо указать номер телефона", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]")).getText());
    }

    @Test
    public void testInvalidValuesTestCase() throws InterruptedException {
        driver.navigate().to(baseUrl);

        driver.findElement(By.name("fio")).click();
        driver.findElement(By.name("fio")).clear();
        driver.findElement(By.name("fio")).sendKeys("нкгнукункг");

        driver.findElement(By.name("phone_mobile")).click();
        driver.findElement(By.name("phone_mobile")).clear();
        driver.findElement(By.name("phone_mobile")).sendKeys("+7(345) 345-");

        driver.findElement(By.name("email")).click();
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("шгншгншгннг");
        driver.findElement(By.name("email")).submit();

        assertEquals("Недостаточно информации. Введите фамилию, имя и отчество через пробел (Например: Иванов Иван Алексеевич)", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Фамилия, имя и отчество'])[1]/following::div[3]")).getText());
        assertEquals("Номер телефона должен состоять из 10 цифр, начиная с кода оператора", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Контактный телефон'])[1]/following::div[2]")).getText());
        assertEquals("Введите корректный адрес эл. почты", driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Электронная почта'])[1]/following::div[3]")).getText());
    }


}
