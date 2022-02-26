package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage extends BaseClass {

    @FindBy(xpath = "//*[@id=\"center_column\"]/ul/li[1]/div/div[1]/div/a[1]/img")
    WebElement productResult;

    public SearchResultPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public boolean isProductAvailable() {
        return Action.isDisplayed(getDriver(), productResult);
    }

    public AddToCartPage clickOnProduct() {
        Action.click(getDriver(), productResult);
        return new AddToCartPage();
    }
}
