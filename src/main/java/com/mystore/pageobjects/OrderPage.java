package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends BaseClass {

    @FindBy(xpath = "//td[@class=\"cart_unit\"]//span//span")
    WebElement unitPrice;

    @FindBy(xpath = "//span[@id=\"total_price\"]")
    WebElement totalPrice;

    @FindBy(xpath = "//span[text()='Proceed to checkout']")
    WebElement proceedToCheckOut;

    public OrderPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public double getUnitPrice(){
        String unitPrice1 = unitPrice.getText();
        String unitPriceRemovedSign = unitPrice1.replaceAll("[^a-zA-Z0-9]","");
        double finalUnitPrice = Double.parseDouble(unitPriceRemovedSign);
        return finalUnitPrice/100;
    }

    public double getTotalPrice(){
        String totalPrice1 = totalPrice.getText();
        String totalPriceRemovedSign = totalPrice1.replaceAll("[^a-zA-Z0-9]","");
        double finalTotalPrice = Double.parseDouble(totalPriceRemovedSign);
        return finalTotalPrice/100;
    }

    public LoginPage clickOnCheckOut(){
        Action.click(getDriver(), proceedToCheckOut);
        return new LoginPage();
    }
}
