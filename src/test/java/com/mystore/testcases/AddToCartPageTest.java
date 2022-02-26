package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class AddToCartPageTest extends BaseClass {

    IndexPage indexPage;
    AddToCartPage addToCartPage;
    SearchResultPage searchResultPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke","Sanity","Regression"})
    public void tearDown() {
        getDriver().quit();
    }

    @Test(groups = {"Sanity","Regression"})
    public void addToCartTest() {
        indexPage= new IndexPage();
        searchResultPage = indexPage.searchProduct("t-shirt");
        addToCartPage = searchResultPage.clickOnProduct();
        addToCartPage.enterQuantity("2");
        addToCartPage.clickOnAddToCart();
        boolean result = addToCartPage.validateAddtoCart();
        Assert.assertTrue(result);
    }
}
