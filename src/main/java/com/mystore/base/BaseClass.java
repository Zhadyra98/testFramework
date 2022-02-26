package com.mystore.base;

import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
    public static Properties prop;
    //public static WebDriver driver;

    public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

    @BeforeSuite(groups = {"Smoke","Sanity","Regression"})
    public void beforeSuite(){
        DOMConfigurator.configure("log4j.xml");
        ExtentManager.setExtent();
    }
    public static WebDriver getDriver(){
        return driver.get();
    }

    @AfterSuite(groups = {"Smoke","Sanity","Regression"})
    public void afterSuite(){
        ExtentManager.endReport();
    }

    @BeforeTest(groups = {"Smoke","Sanity","Regression"})
    public void loadConfig() {

        try {
            prop = new Properties();
            System.out.println("super constructor invoked");
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "\\Configuration\\config.properties");
            prop.load(ip);
            System.out.println("driver: " + driver);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void launchApp(String browserName) {
        //String browserName = prop.getProperty("browser");
        if(browserName.equalsIgnoreCase("Chrome")){
            WebDriverManager.chromedriver().setup();
            driver.set(new ChromeDriver());

        } else if (browserName.equalsIgnoreCase("FireFox")) {
            WebDriverManager.firefoxdriver().setup();
            driver.set(new FirefoxDriver());

        } else if (browserName.equalsIgnoreCase("Edge")) {
            //driver = new InternetExplorerDriver();
            WebDriverManager.edgedriver().setup();
            driver.set(new EdgeDriver());
        }

        getDriver().manage().window().maximize();
        Action.implicitWait(getDriver(), 10);
        Action.pageLoadTimeOut(getDriver(), 30);
        getDriver().get(prop.getProperty("url"));
    }
}
