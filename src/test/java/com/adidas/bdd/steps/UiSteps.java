package com.adidas.bdd.steps;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.adidas.bdd.ui.pages.AddToCartLaptopPage;
import com.adidas.bdd.ui.pages.CartPage;
import com.adidas.bdd.ui.pages.HomePage;
import com.adidas.bdd.ui.pages.OrderSummaryPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UiSteps {

    private WebDriver driver;
    private HomePage homePage;
    private CartPage cartPage;
    private AddToCartLaptopPage addToCartLaptopPage;
    private OrderSummaryPage orderSummaryPage;
    private String purchasedAmount;
    private Logger log = Logger.getLogger(String.valueOf(UiSteps.class));

    private String laptopPrice;
    private String baseUrl = "https://www.demoblaze.com/index.html";

    @Given("user launches the browser")
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/geckoDriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Given("user enters the url")
    public void enterUrl() {
        driver.get(baseUrl);
    }

    @And("navigate to Laptops")
    public void navigateToLaptops() {
        homePage = new HomePage(driver);
        homePage.clickLaptopButton();
    }

    @When("select sony laptop")
    public void selectSonyLaptop() {
        homePage = new HomePage(driver);
        homePage.clickSonyLaptop();
        laptopPrice = homePage.getLaptopPrice();
    }

    @When("select dell laptop")
    public void selectDellLaptop() {
        homePage = new HomePage(driver);
        homePage.clickDellLaptop();
    }

    @And("navigate on home page")
    public void navigateToHomePage() {
        homePage = new HomePage(driver);
        homePage.clickHomeButton();
    }

    @When("navigate on cart page")
    public void navigateToCartPage() {
        cartPage = new CartPage(driver);
        cartPage.clickCartMenuButton();
    }

    @Then("remove dell laptop from cart")
    public void removeDellLaptopFromCart() throws InterruptedException {
        cartPage = new CartPage(driver);
        Thread.sleep(5000);
        cartPage.clickDeleteButton();
        Thread.sleep(5000);
    }

    @When("place order for sony laptop")
    public void placeOrderButton() {
        cartPage = new CartPage(driver);
        cartPage.clickPlaceOrderButton();
    }

    @When("add laptop into cart")
    public void addToSonyLaptopToCart() {
        addToCartLaptopPage = new AddToCartLaptopPage(driver);
        addToCartLaptopPage.clickAddToCartButton();
    }

    @Then("accept the alert")
    public void handleAlert() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    @Then("purchase sony laptop")
    public void clickPurchaseButton() {
        cartPage = new CartPage(driver);
        cartPage.purchaseSelectedLaptop("user1", "India", "Delhi", "1234 5678 9876", "10", "2020");
    }

    @And("get oder id and amount")
    public void getConfirmationDetails() {
        orderSummaryPage = new OrderSummaryPage(driver);
        String orderSummary = orderSummaryPage.getOrderSummary();
        String[] details = orderSummary.split(":");
        log.info("Purchase Id is");
        log.info(details[1]);
        log.info("Amount is");
        purchasedAmount = details[2].split(" ")[1];
        log.info(purchasedAmount);
    }

    @And("validate both the amount should be same")
    public void validateAmount() {
        orderSummaryPage = new OrderSummaryPage(driver);
        Assert.assertEquals(laptopPrice.substring(1,4), purchasedAmount);
        log.info("Price is same");
    }

    @And("click ok button")
    public void clickOkButton() {
        orderSummaryPage = new OrderSummaryPage(driver);
        orderSummaryPage.clickOkButton();
    }

    @And("close the browser")
    public void tearDown(){
        driver.quit();
    }

}