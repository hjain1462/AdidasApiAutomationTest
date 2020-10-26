package com.adidas.bdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.adidas.bdd.ui.Locators.DELL_I7_8gb_LAPTOP;
import static com.adidas.bdd.ui.Locators.HOME_BUTTON;
import static com.adidas.bdd.ui.Locators.LAPTOPS_BUTTON;
import static com.adidas.bdd.ui.Locators.LAPTOP_AMOUNT;
import static com.adidas.bdd.ui.Locators.SONY_VIO_I5_LAPTOP;

public class HomePage {

    WebDriver driver;
    @FindBy(xpath = LAPTOPS_BUTTON)
    WebElement laptopsButton;
    @FindBy(xpath = SONY_VIO_I5_LAPTOP)
    WebElement sonyVioLaptop;
    @FindBy(xpath = LAPTOP_AMOUNT)
    WebElement laptopAmount;
    @FindBy(xpath = DELL_I7_8gb_LAPTOP)
    WebElement dellI7Laptop;
    @FindBy(xpath = HOME_BUTTON)
    WebElement homeButton;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickLaptopButton(){
        laptopsButton.click();
    }

    public void clickSonyLaptop(){
        sonyVioLaptop.click();
    }

    public void clickDellLaptop(){
        dellI7Laptop.click();
    }

    public void clickHomeButton(){
        homeButton.click();
    }

    public String getLaptopPrice(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LAPTOP_AMOUNT)));
        return laptopAmount.getText();
    }

}
