package com.adidas.bdd.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static com.adidas.bdd.ui.Locators.CART_MENU_BUTTON;
import static com.adidas.bdd.ui.Locators.CITY;
import static com.adidas.bdd.ui.Locators.COUNTRY;
import static com.adidas.bdd.ui.Locators.CREDIT_CARD;
import static com.adidas.bdd.ui.Locators.DELETE_DELL_LAPTOP_FROM_CART_BUTTON;
import static com.adidas.bdd.ui.Locators.MONTH;
import static com.adidas.bdd.ui.Locators.NAME;
import static com.adidas.bdd.ui.Locators.PLACE_ORDER_BUTTON;
import static com.adidas.bdd.ui.Locators.PURCHASE_BUTTON;
import static com.adidas.bdd.ui.Locators.YEAR;

public class CartPage {

    WebDriver driver;
    @FindBy(xpath = CART_MENU_BUTTON)
    WebElement cartMenuButton;
    @FindBy(xpath = PLACE_ORDER_BUTTON)
    WebElement placeOrderButton;
    @FindBy(xpath = PURCHASE_BUTTON)
    WebElement purchaseButton;
    @FindBy(xpath = DELETE_DELL_LAPTOP_FROM_CART_BUTTON)
    WebElement deleteDellLaptopButton;
    @FindBy(id = NAME)
    WebElement name;
    @FindBy(id = COUNTRY)
    WebElement country;
    @FindBy(id = CITY)
    WebElement city;
    @FindBy(id = CREDIT_CARD)
    WebElement card;
    @FindBy(id = MONTH)
    WebElement month;
    @FindBy(id = YEAR)
    WebElement year;

    public CartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickCartMenuButton(){
        cartMenuButton.click();
    }

    public void clickDeleteButton(){
        deleteDellLaptopButton.click();
    }

    public void clickPlaceOrderButton(){
        placeOrderButton.click();
    }

    public void clickPurchaseButton(){
        purchaseButton.click();
    }

    public void purchaseSelectedLaptop(String userName, String countryName, String cityName, String cardDetails, String monthNo, String yearNo){
        name.sendKeys(userName);
        country.sendKeys(countryName);
        city.sendKeys(cityName);
        card.sendKeys(cardDetails);
        month.sendKeys(monthNo);
        year.sendKeys(yearNo);
        clickPurchaseButton();
    }

}
