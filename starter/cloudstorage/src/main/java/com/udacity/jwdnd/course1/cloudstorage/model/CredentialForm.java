package com.udacity.jwdnd.course1.cloudstorage.model;

public class CredentialForm {

    private String URL;
    private String UserName;
    private String CredentialID;

    private String password;



    public CredentialForm(String URL, String userName, String credentialID, String password) {
        this.URL = URL;
        this.UserName = userName;
        this.CredentialID = credentialID;
        this.password = password;

    }

    public CredentialForm(String URL, String userName, String credentialID) {
        this.URL = URL;
        this.UserName = userName;
        this.CredentialID = credentialID;
    }

    public CredentialForm() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCredentialID() {
        return CredentialID;
    }

    public void setCredentialID(String credentialID) {
        CredentialID = credentialID;
    }
}
