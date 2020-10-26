package com.adidas.bdd.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.adidas.bdd.ui.Locators.AMOUNT;
import static com.adidas.bdd.ui.Locators.OK_BUTTON;
import static com.adidas.bdd.ui.Locators.ORDER_SUMMARY;

public class OrderSummaryPage {

    WebDriver driver;
    @FindBy(xpath = OK_BUTTON)
    WebElement okButton;
    @FindBy(xpath = ORDER_SUMMARY)
    WebElement orderSummary;
    @FindBy(xpath = AMOUNT)
    WebElement amount;

    public OrderSummaryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickOkButton(){
        okButton.click();
    }

    public String getOrderSummary(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ORDER_SUMMARY)));
        return orderSummary.getText();
    }

    public String getPurchasedAmount(){
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(AMOUNT)));
        return amount.getText();
    }

}
