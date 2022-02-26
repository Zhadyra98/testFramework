package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class EndToEndTest extends BaseClass {
    IndexPage indexPage;
    AddToCartPage addToCartPage;
    SearchResultPage searchResultPage;
    OrderPage orderPage;
    LoginPage loginPage;
    AddressPage addressPage;
    ShippingPage shippingPage;
    PaymentPage paymentPage;
    OrderSummaryPage orderSummaryPage;
    OrderConfirmationPage orderConfirmationPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke","Sanity","Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    @Test(groups = "Regression")
    public void endToEndTest() {
        indexPage = new IndexPage();
        searchResultPage = indexPage.searchProduct("t-shirt");
        addToCartPage = searchResultPage.clickOnProduct();
        addToCartPage.enterQuantity("2");
        addToCartPage.clickOnAddToCart();
        orderPage = addToCartPage.clickOnCheckOut();
        loginPage = orderPage.clickOnCheckOut();
        addressPage = loginPage.login1(prop.getProperty("username"),prop.getProperty("password"));
        shippingPage = addressPage.clickOnCheckOut();
        shippingPage.checkTheTerms();
        paymentPage = shippingPage.clickOnProceedToChceckOut();
        orderSummaryPage = paymentPage.clickOnPaymentMethod();
        orderConfirmationPage = orderSummaryPage.clickOnConfirmOrderBtn();
        String result = orderConfirmationPage.validateConfirmMessage();
        Assert.assertEquals(result, "Your order on My Store is complete.");
    }
}
