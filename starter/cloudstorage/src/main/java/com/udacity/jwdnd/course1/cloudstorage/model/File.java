package com.udacity.jwdnd.course1.cloudstorage.model;

public class File {

    private int FileID;
    private String FileName;
    private String ContentType;
    private String FileSize;
    private Integer UserID;
    private byte[] FileData;

    public File(int fileID, String fileName, String contentType, String fileSize, Integer userID, byte[] fileData) {
        this.FileID = fileID;
        this.FileName = fileName;
        this.ContentType = contentType;
        this.FileSize = fileSize;
        this.UserID = userID;
        this.FileData = fileData;
    }

    public File() {
    }

    public int getFileID() {
        return FileID;
    }

    public void setFileID(int fileID) {
        FileID = fileID;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getContentType() {
        return ContentType;
    }

    public void setContentType(String contentType) {
        ContentType = contentType;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String fileSize) {
        FileSize = fileSize;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public byte[] getFileData() {
        return FileData;
    }

    public void setFileData(byte[] fileData) {
        FileData = fileData;
    }
}
