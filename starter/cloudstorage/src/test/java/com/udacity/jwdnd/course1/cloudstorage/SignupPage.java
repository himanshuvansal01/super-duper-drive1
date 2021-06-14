package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputLastName")
    private WebElement InputLastName;

    @FindBy(id = "inputFirstName")
    private WebElement InputFirstName;

    @FindBy(id = "inputUsername")
    private WebElement InputUserName;

    @FindBy(id = "inputPassword")
    private WebElement InputPassword;

    @FindBy(id = "submit-button")
    private WebElement SubmitButton;

    private final JavascriptExecutor Js;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        Js = (JavascriptExecutor) webDriver;
    }

    public void SignUp() {

        Js.executeScript("arguments[0].click();", SubmitButton );
    }

    public void setFirstName(String FirstName) {
        Js.executeScript("arguments[0].value='" + FirstName + "';", InputFirstName);
    }

    public void setLastName(String LastName) {
        Js.executeScript("arguments[0].value='" + LastName + "';", InputLastName);
    }

    public void setPassword(String Password) {
        Js.executeScript("arguments[0].value='" + Password + "';", InputPassword);
    }

    public void setUserName(String UserName) {

        Js.executeScript("arguments[0].value='" + UserName + "';", InputUserName);
    }
}
