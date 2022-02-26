package com.mystore.testcases;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseClass {

    IndexPage indexPage;
    LoginPage loginPage;
    HomePage homePage;

    @Parameters("browser")
    @BeforeMethod(groups = {"Smoke","Sanity","Regression"})
    public void setup(String browser) {
        launchApp(browser);
    }

    @AfterMethod(groups = {"Smoke","Sanity","Regression"})
    public void tearDown(){
        getDriver().quit();
    }

    @Test(dataProvider = "credentials", dataProviderClass = DataProviders.class, groups = {"Smoke","Sanity"})
    public void loginTest(String username, String password){
        Log.startTestCase("loginTest");
        indexPage = new IndexPage();
        Log.info("user is going to click on sign in");
        loginPage = indexPage.clickOnSignIn();
        Log.info("user will enter username and password");
        //homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
        homePage = loginPage.login(username,password);
        String actualURL = homePage.getCurrentURL();
        String expectedURL = "http://automationpractice.com/index.php?controller=my-account";
        Log.info("verify if user is able to log in");
        Assert.assertEquals(actualURL, expectedURL);
        Log.info("user is logged in");

    }
}
