package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    @FindBy(id = "btnLogout")
    private WebElement LogoutButton;

    @FindBy(id = "fileUpload")
    private WebElement FileUpload;

    @FindBy(id = "btnAddNewNote")
    private WebElement BtnAddNewNote;

    @FindBy(id = "btnAddNewCredential")
    private WebElement btnAddNewCredential;

    @FindBy(id = "note-title")
    private WebElement txtNoteTitle;

    @FindBy(id = "nav-notes-tab")
    private WebElement NavNotesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement NavCredentialTab;

    @FindBy(id = "note-description")
    private WebElement txtNoteDescription;

    @FindBy(id= "btnSaveChanges" )
    private WebElement btnSaveChanges;

    @FindBy(id = "tableNoteTitle")
    private WebElement tableNoteTitle;

    @FindBy(id = "tableNoteDescription")
    private WebElement tableNoteDescription;

    @FindBy(id = "btnEditNote")
    private WebElement btnEditNote;

    @FindBy(id = "btnEditCredential")
    private WebElement btnEditCredential;

    @FindBy(id = "txtModifyCredential")
    private WebElement txtModifyNoteDescription;

    @FindBy(id = "ancDeleteNote")
    private WebElement DeleteNote;

    @FindBy(id = "aDeleteCredential")
    private WebElement DeleteCredential;

    @FindBy(id = "credential-url")
    private WebElement CredentialUrl;

    @FindBy(id = "credential-username")
    private WebElement CredentialUsername;

    @FindBy(id = "credential-password")
    private WebElement CredentialPassword;

    @FindBy(id = "btnCredentialSaveChanges")
    private WebElement CredentialSaveChanges;

    @FindBy(id = "tblCredentialUrl")
    private WebElement tblCredentialUrl;

    @FindBy(id = "tblCredentialUsername")
    private WebElement tblCredentialUsername;

    @FindBy(id = "tblCredentialPassword")
    private WebElement tblCredentialPassword;


    private final JavascriptExecutor Js;

    private final WebDriverWait Wait;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        Js = (JavascriptExecutor) driver;
        Wait = new WebDriverWait(driver, 500);
    }

    public void DeleteCredentials() {

        Js.executeScript("arguments[0].click();", DeleteCredential);
    }

    public void logout() {
        Js.executeScript("arguments[0].click();",LogoutButton);
    }



    public void NavToCredentialsTab() {
        Js.executeScript("arguments[0].click();", NavCredentialTab);
    }

    public void AddNewCredential() {

        Js.executeScript("arguments[0].click();", btnAddNewCredential);
    }

    public void SaveCredentialChanges() {
        Js.executeScript("arguments[0].click();",CredentialSaveChanges);
    }

    public void setCredentialUrl(String url) {
        Js.executeScript("arguments[0].value='" + url + "';", CredentialUrl);
    }

    public void setCredentialUserName(String username) {
        Js.executeScript("arguments[0].value='" + username + "';", CredentialUsername);
    }

    public void setCredentialPassword(String Password) {
        Js.executeScript("arguments[0].value='" + Password + "';", CredentialPassword);
    }


    public void EditNote(){
        Js.executeScript("arguments[0].click();", btnEditNote);
    }

    public void EditCredential(){
        Js.executeScript("arguments[0].click();", btnEditCredential);
    }

    public void DeleteNote(){
        Js.executeScript("arguments[0].click();", DeleteNote);
    }

    public void UploadFile(){
        Js.executeScript("arguments[0].click();", FileUpload);
    }

    public void addNewNote(){
        Js.executeScript("arguments[0].click();", BtnAddNewNote);
    }

    public void setNoteTitle(String NoteTitle){
        Js.executeScript("arguments[0].value='" + NoteTitle + "';", txtNoteTitle);
    }

    public Note getFirstNote(){
        String Title = Wait.until(ExpectedConditions.elementToBeClickable(tableNoteTitle)).getText();
        String Description = tableNoteDescription.getText();

        return new Note(Title, Description);
    }

    public void addNewCredential(){
        Js.executeScript("arguments[0].click();", btnAddNewCredential);
    }

    public void ModifyNoteTitle(String NewNoteTitle){
        Wait.until(ExpectedConditions.elementToBeClickable(txtNoteTitle)).clear();
        Wait.until(ExpectedConditions.elementToBeClickable(txtNoteTitle)).sendKeys(NewNoteTitle);


    }

    public void ModifyNoteDescription(String NewNoteDescription){
        Wait.until(ExpectedConditions.elementToBeClickable(txtNoteDescription)).clear();
        Wait.until(ExpectedConditions.elementToBeClickable(txtNoteDescription)).sendKeys(NewNoteDescription);


    }

    public void NavToNotesTab(){
        Js.executeScript("arguments[0].click();", NavNotesTab);
    }

    public void setNoteDescription(String noteDescription){
        Js.executeScript("arguments[0].value='" + noteDescription + "';", txtNoteDescription);

    }

    public void saveNoteChanges(){
        Js.executeScript("arguments[0].click();", btnSaveChanges);
    }

    public boolean NoNotes(WebDriver driver) {

        return !IsElementPresent(By.id("tableNoteTitle"), driver) && !IsElementPresent(By.id("tableNoteDescription"), driver);

    }


    public boolean NoCredentials(WebDriver driver) {

        return !IsElementPresent(By.id("tblCredentialUrl"), driver) &&
                !IsElementPresent(By.id("tblCredentialUsername"), driver) &&
                !IsElementPresent(By.id("tblCredentialPassword"), driver);

    }

    public boolean IsElementPresent(By LocatorKey, WebDriver driver) {

        try{
            driver.findElement(LocatorKey);

            return true;
        } catch (NoSuchElementException e){
            return false;
        }

    }

    public Credential getFirstCredential(){
        String URl = Wait.until(ExpectedConditions.elementToBeClickable(tblCredentialUrl)).getText();
        String username = tblCredentialUsername.getText();
        String password = tblCredentialPassword.getText();

        return new Credential(URl, username, password);
    }
}
