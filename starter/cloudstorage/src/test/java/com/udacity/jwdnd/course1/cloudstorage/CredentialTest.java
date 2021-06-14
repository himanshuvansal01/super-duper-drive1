package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 class CredentialTest extends CloudStorageApplicationTests {

    public static final String FACEBOOK_URL = "https://www.facebook.com/";
    public static final String FACEBOOK_USERNAME = "hiamanshu";
    public static final String FACEBOOK_PASSWORD = "vansal123";
    public static final String GMAIL_URL = "https://www.gmail.com/";
    public static final String GMAIL_USERNAME = "himanshuvansal";
    public static final String GMAIL_PASSWORD = "vansal1332";

    @Test
    public void testCredentialCreation(){

        HomePage homePage = SignUpAndLogin();
        CreateAndVerifyCredential(FACEBOOK_URL,FACEBOOK_USERNAME,FACEBOOK_PASSWORD,homePage);
        homePage.DeleteCredentials();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
        homePage.logout();



    }

    @Test
    private void CreateAndVerifyCredential(String URL, String UserName, String Password, HomePage homePage) {

        CreateCredential(URL,UserName,Password,homePage);
        homePage.NavToCredentialsTab();
        Credential credential = homePage.getFirstCredential();
        Assertions.assertEquals(URL,credential.getURL());
        Assertions.assertEquals(UserName,credential.getUserName());
        Assertions.assertNotEquals(Password,credential.getPassword());

    }

    @Test
    private void CreateCredential(String URL, String UserName, String Password, HomePage homePage) {

        homePage.NavToCredentialsTab();
        homePage.AddNewCredential();
        setCredentialFields(URL,UserName,Password,homePage);
        homePage.SaveCredentialChanges();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
        homePage.NavToCredentialsTab();
    }

    @Test
    private void setCredentialFields(String URL, String UserName, String Password, HomePage homePage) {
        homePage.setCredentialUrl(URL);
        homePage.setCredentialUserName(UserName);
        homePage.setCredentialPassword(Password);

    }

    @Test
    public void TestCredentialModification() {
        HomePage homePage =  SignUpAndLogin();
        CreateAndVerifyCredential(FACEBOOK_URL,FACEBOOK_USERNAME,FACEBOOK_PASSWORD,homePage);
        Credential OriginalCredential = homePage.getFirstCredential();
        String FirstEncryptPassword = OriginalCredential.getPassword();
        homePage.EditCredential();
        String NewUrl = GMAIL_URL;
        String NewUserName = GMAIL_USERNAME;
        String NewPassword = GMAIL_PASSWORD;
        setCredentialFields(NewUrl,NewUserName,NewPassword,homePage);
        homePage.SaveCredentialChanges();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
        homePage.NavToCredentialsTab();
        Credential ModifiedCredential = homePage.getFirstCredential();
        Assertions.assertEquals(NewUrl, ModifiedCredential.getURL());
        Assertions.assertEquals(NewUserName, ModifiedCredential.getUserName());
        String ModifiedCredentialPassword = ModifiedCredential.getPassword();
        Assertions.assertNotEquals(NewPassword, ModifiedCredentialPassword);
        Assertions.assertNotEquals(FirstEncryptPassword, ModifiedCredentialPassword);
        homePage.DeleteCredentials();
        resultPage.clickOk();
        homePage.logout();
    }

    @Test
    public void TestDeletion(){
        HomePage homePage = SignUpAndLogin();
        CreateCredential(FACEBOOK_URL,FACEBOOK_USERNAME,FACEBOOK_PASSWORD,homePage);
        CreateCredential(GMAIL_URL,GMAIL_USERNAME,GMAIL_PASSWORD,homePage);
        CreateCredential("https://www.instagram.com", "vansal", "vansal12345", homePage);
        Assertions.assertFalse(homePage.NoCredentials(webDriver));
        homePage.DeleteCredentials();
        ResultPage resultPage = new ResultPage(webDriver);
        resultPage.clickOk();
        homePage.NavToCredentialsTab();
        homePage.DeleteCredentials();
        resultPage.clickOk();

        homePage.DeleteCredentials();
        resultPage.clickOk();
        homePage.NavToCredentialsTab();

        Assertions.assertTrue(homePage.NoCredentials(webDriver));
        homePage.logout();


    }
}
