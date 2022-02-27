package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class IndexPageTest extends BaseClass {

    IndexPage indexPage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(groups = "Smoke")
    public void verifyLogo(){
        indexPage = new IndexPage();
        boolean result = indexPage.validateLogo();
        Assert.assertTrue(result);
    }

    @Test(groups = "Smoke")
    public void verifyTitle(){
        indexPage = new IndexPage();
        String actTitle = indexPage.getMyStoreTitle();
        Assert.assertEquals(actTitle, "My1 Store");
    }

}
