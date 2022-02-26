package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage extends BaseClass {
    @FindBy(id = "cgv")
    WebElement terms;

    @FindBy(xpath = "//button/span[contains(text(),'Proceed to checkout')]")
    WebElement proceedToCheckOutButton;

    public ShippingPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void checkTheTerms() {
        Action.click(getDriver(), terms);
    }

    public PaymentPage clickOnProceedToChceckOut() {
        Action.click(getDriver(), proceedToCheckOutButton);
        return new PaymentPage();
    }
}
