package com.adidas.bdd.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.adidas.bdd.ui.Locators.ADD_TO_CART_BUTTON;

public class AddToCartLaptopPage {

    WebDriver driver;
    @FindBy(xpath = ADD_TO_CART_BUTTON)
    WebElement addToCartButton;

    public AddToCartLaptopPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickAddToCartButton(){
        addToCartButton.click();
    }

}
