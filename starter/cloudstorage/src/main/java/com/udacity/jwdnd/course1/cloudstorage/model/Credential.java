package com.udacity.jwdnd.course1.cloudstorage.model;

public class Credential {

    private Integer CredentialID;
    private String URL;
    private String UserName;
    private String  Key;
    private String Password;
    private Integer UserId;

    public Credential(Integer CredentialID, String URL, String UserName, String Key, String Password, Integer UserId) {
        this.CredentialID = CredentialID;
        this.URL = URL;
        this.UserName = UserName;
        this.Key = Key;
        this.Password = Password;
        this.UserId = UserId;
    }

    public Credential() {
    }

    public Credential(String URL, String userName, String password) {
        this.URL = URL;
        this.UserName = userName;
        this.Password = password;
    }

    public Integer getCredentialID() {
        return CredentialID;
    }

    public void setCredentialID(Integer credentialID) {
        CredentialID = credentialID;
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

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }


}
