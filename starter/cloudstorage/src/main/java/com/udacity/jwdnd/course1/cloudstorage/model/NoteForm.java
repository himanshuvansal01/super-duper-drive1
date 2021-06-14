package com.udacity.jwdnd.course1.cloudstorage.model;

public class NoteForm {

    private String Title;
    private String Description;
    private String NoteID;

    public String getNoteID() {
        return NoteID;
    }

    public void setNoteID(String noteID) {
        NoteID = noteID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
