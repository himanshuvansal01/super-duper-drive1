package com.udacity.jwdnd.course1.cloudstorage.controller;


import com.udacity.jwdnd.course1.cloudstorage.Security.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("note")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    private Integer GetUserId(Authentication authentication){
        String userName = authentication.getName();
        User user = userService.getUser(userName);
        return user.getUserID();
    }


    @GetMapping
    public String getHomePage(
            Authentication authentication, @ModelAttribute("NewFiles") FileForm newFiles,
            @ModelAttribute("NewCredentials") CredentialForm NewCredentials,
            @ModelAttribute("NewNotes") NoteForm NewNotes, Model model){

        Integer userId = GetUserId(authentication);
        model.addAttribute("notes", this.noteService.getNoteListings(userId));

        return "home";

    }



    @PostMapping("addnote")
    public String newNote(Authentication authentication, @ModelAttribute("NewFiles") FileForm NewFiles,
                          @ModelAttribute("NewCredentials") CredentialForm NewCredentials,
                          @ModelAttribute("NewNotes") NoteForm NewNotes, Model model){


        String NewTitle = NewNotes.getTitle();

        String UserName = authentication.getName();
        String NewDescription = NewNotes.getDescription();
        String NoteId = NewNotes.getNoteID();
        if(NoteId.isEmpty()){
            noteService.AddNote(NewTitle, NewDescription, UserName);


        } else{
            Note existingNote = noteService.getNote(Integer.parseInt(NoteId));
            noteService.updateNote(existingNote.getNoteID(), NewTitle,NewDescription);
        }

        Integer userId = GetUserId(authentication);
        model.addAttribute("notes", noteService.getNoteListings(userId));
        model.addAttribute("result", "success");

        return "result";

    }



    @GetMapping(value = "/getnote/{id}")
    public Note getNote(@PathVariable Integer id){

        return noteService.getNote(id);

    }


    @GetMapping(value = "/deletenote/{id}")
    public String deleteNote(Authentication authentication, @PathVariable Integer id, @ModelAttribute("NewFiles") FileForm NewFiles,
                             @ModelAttribute("NewCredentials") CredentialForm NewCredentials,
                             @ModelAttribute("NewNotes") NoteForm NewNotes, Model model){
        noteService.deleteNote(id);
        Integer userId = GetUserId(authentication);
        model.addAttribute("notes", noteService.getNoteListings(userId));
        model.addAttribute("result", "success");

        return "result";

    }


    }
