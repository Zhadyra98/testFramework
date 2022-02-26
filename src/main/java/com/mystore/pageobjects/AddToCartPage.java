package com.mystore.pageobjects;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPage extends BaseClass {

    @FindBy(id = "quantity_wanted")
    WebElement quantity;

    @FindBy(name = "group_1")
    String size;

    @FindBy(xpath = "//span[text()='Add to cart']")
    WebElement addToCartBtn;

    @FindBy(xpath = "//*[@id=\"layer_cart\"]//h2/i")
    WebElement addToCartMessage;

    @FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
    WebElement proceedToCheckOutBtn;

    public AddToCartPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void enterQuantity(String quantityEnter) {
        Action.type(quantity, quantityEnter);
    }

    public void selectSize(WebElement sizeEnter) {
        Action.selectByVisibleText(size, sizeEnter);
    }

    public void clickOnAddToCart() {
        Action.click(getDriver(), addToCartBtn);
    }

    public boolean validateAddtoCart() {
        Action.fluentWait(getDriver(), addToCartMessage, 10);
        return Action.isDisplayed(getDriver(), addToCartMessage);
    }

    public OrderPage clickOnCheckOut() {
        Action.fluentWait(getDriver(), proceedToCheckOutBtn,10);
        Action.JSClick(getDriver(), proceedToCheckOutBtn);
        return new OrderPage();
    }
}
