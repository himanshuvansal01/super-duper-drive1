package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

    private final UserMapper userMapper;
    private final NoteMapper noteMapper;

    public NoteService(UserMapper userMapper, NoteMapper noteMapper) {
        this.userMapper = userMapper;
        this.noteMapper = noteMapper;
    }

    public void AddNote(String title, String description,String userName){

        Integer userId = userMapper.getUser(userName).getUserID();
        Note note = new Note(0,title,description,userId);
        noteMapper.insert(note);

    }
    public Note[] getNoteListings(Integer UserID){
        return noteMapper.getNotesForUser(UserID);
    }

    public Note getNote(Integer noteId){
        return noteMapper.getNote(noteId);
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }

    public void updateNote(Integer noteId,String Title, String Description){
        noteMapper.updateNote(noteId,Title,Description);
    }




}
