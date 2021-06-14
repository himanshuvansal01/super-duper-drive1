package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id= "inputUsername")
    private WebElement InputUserName;

    @FindBy(id= "submit-button")
    private WebElement SubmitButton;

    @FindBy(id= "inputPassword")
    private WebElement InputPassword;

    private final JavascriptExecutor Js;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver,this);
        Js = (JavascriptExecutor) webDriver;
    }

    public void Login(){
        Js.executeScript("arguments[0].click();", SubmitButton);
    }

    public void setUserName(String userName){
        Js.executeScript("arguments[0].value='" + userName + "';", InputUserName);
    }

    public void  setPassword(String password){
        Js.executeScript("arguments[0].value='" + password + "';" , InputPassword);
    }
}
