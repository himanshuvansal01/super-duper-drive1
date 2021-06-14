package com.udacity.jwdnd.course1.cloudstorage.model;

public class User {

    private Integer UserID;
    private String UserName;
    private String Salt;
    private String Password;
    private String FirstName;
    private String LastName;

    public User(Integer userID, String userName, String salt, String password, String firstName, String lastName) {
        this.UserID = userID;
        this.UserName = userName;
        this.Salt = salt;
        this.Password = password;
        this.FirstName = firstName;
        this.LastName = lastName;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUsername() {
        return UserName;
    }

    public void setUsername(String userName) {
        UserName = userName;
    }

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }



}
