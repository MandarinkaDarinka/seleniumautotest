package ru.tinkoff.daria.selenium.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TariffPage extends Page {
    public TariffPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[1]/div[1]/label/div[1]/input")
    WebElement name;

    @FindBy(xpath = "//*[@id=\"form-application\"]/div[2]/div/div/form/div[2]/div/div[1]/div/div[1]/label/div[1]/input[2]")
    WebElement phone;

    @FindBy(xpath = "//*[@id=\"form-application\"]/div[2]/div/div/form/div[2]/div/div[2]/div/div[1]/div[1]/label/div[1]/input")
    WebElement email;

    @FindBy(xpath = "//*[@id=\"form-application\"]/div[2]/div/div/form/div[1]/div/div[2]")
    WebElement nameError;

    @FindBy(xpath = "//*[@id=\"form-application\"]/div[2]/div/div/form/div[2]/div/div[1]/div/div[2]")
    WebElement phoneError;

    @FindBy(xpath = "//*[@id=\"form-application\"]/div[2]/div/div/form/div[2]/div/div[2]/div/div[2]")
    WebElement emailError;

    public void open() {
        driver.navigate().to("https://www.tinkoff.ru/mobile-operator/tariffs/");
        isLoadedByTitleContains("Тарифы");
    }


    public void clickName() {
        name.click();
    }

    public void clickPhone() {
        phone.click();
    }

    public void clickEmail() {
        email.click();
    }

    public void fillName(String text) {
        name.clear();
        name.sendKeys(text);
        name.submit();
    }

    public void fillPhone(String text) {
        phone.clear();
        phone.sendKeys(text);
        phone.submit();
    }

    public void fillEmail(String text) {
        email.clear();
        email.sendKeys(text);
        email.submit();
    }

    public String getNameError() {
        return nameError.getText();
    }

    public String getPhoneError() {
        return phoneError.getText();
    }

    public String getEmailError() {
        return emailError.getText();
    }


    public boolean isLoadedByTitleContains(String substring) {
        wait.until(d -> d.getTitle().contains(substring));
        return true;
    }
}
