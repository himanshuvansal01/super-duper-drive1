package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    private final JavascriptExecutor Js;

    @FindBy(id = "aResultSuccess")
    private WebElement ResultSuccess;

    public ResultPage(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
        Js = (JavascriptExecutor) webDriver;

    }


    public  void clickOk() {
        Js.executeScript("arguments[0].click();", ResultSuccess);
    }
}
