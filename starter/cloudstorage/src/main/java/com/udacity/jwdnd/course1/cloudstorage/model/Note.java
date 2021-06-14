package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {

    private Integer NoteID;
    private String NoteTitle;
    private String NoteDescription;
    private Integer UserID;

    public Note(Integer noteID, String noteTitle, String noteDescription, Integer userID) {
        this.NoteID = noteID;
        this.NoteTitle = noteTitle;
        this.NoteDescription = noteDescription;
        this.UserID = userID;
    }

    public Note(String noteTitle, String noteDescription) {
        this.NoteTitle = noteTitle;
        this.NoteDescription = noteDescription;
    }

    public Integer getNoteID() {
        return NoteID;
    }

    public void setNoteID(Integer noteID) {
        NoteID = noteID;
    }

    public String getNoteTitle() {
        return NoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        NoteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return NoteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        NoteDescription = noteDescription;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }
}
