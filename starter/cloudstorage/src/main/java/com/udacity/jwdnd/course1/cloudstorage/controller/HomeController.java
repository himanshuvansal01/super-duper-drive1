package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Security.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final UserService userService;
    private final NoteService noteService;
    private final CredentialService credentialService;;
    private final EncryptionService encryptionService;

    public HomeController(FileService fileService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService,UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    private Integer getUserId(Authentication authentication) {

        String UserName = authentication.getName();
        User user = userService.getUser(UserName);
        return user.getUserID();
    }




    @PostMapping
    public String NewFile(Authentication authentication, @ModelAttribute("NewFile")FileForm NewFile,
                          @ModelAttribute("NewNote")NoteForm NewNote, @ModelAttribute("NewCredential")CredentialForm NewCredential, Model model) throws IOException {

        String UserName = authentication.getName();
        User user = userService.getUser(UserName);
        Integer UserID = user.getUserID();
        String[] FileListings = fileService.getFileListings(UserID);

        boolean FileIsDuplicate = false;
        MultipartFile multipartFile = null;

        if(!NewFile.getFile().getOriginalFilename().isEmpty()){
            multipartFile = NewFile.getFile();
            String fileName = multipartFile.getOriginalFilename();

            for(String FileListing: FileListings){
                if(FileListing.equals(fileName)){
                    FileIsDuplicate = true;

                    break;

                }

            }

        }



        if(NewFile.getFile().getOriginalFilename().isEmpty()){
            model.addAttribute("result", "error");
            model.addAttribute("message"," This is a duplicate file.");
        }

        if(!FileIsDuplicate){
            fileService.addFile(multipartFile,UserName);
            model.addAttribute("result", "success");
        }else{

            model.addAttribute("result", "error");
            model.addAttribute("message", "It is a duplicate file");

        }

        model.addAttribute("files", fileService.getFileListings(UserID));

        return "result";

    }

    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("NewFile")FileForm NewFile,
                              @ModelAttribute("NewNote")NoteForm NewNote, @ModelAttribute("NewCredential")CredentialForm NewCredential, Model model){

        Integer userID = getUserId(authentication);
        model.addAttribute("files", this.fileService.getFileListings(userID));
        model.addAttribute("notes", noteService.getNoteListings(userID));
        model.addAttribute("credentials", credentialService.getCredentialListings(userID));
        model.addAttribute("encryptionService", encryptionService);

        return "home";

    }






    @GetMapping(
            value = "/get-file/{FileName}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] getFile(@PathVariable String FileName){
        return fileService.getFile(FileName).getFileData();
    }

    @GetMapping(value = "/delete-file/{FileName}")
    public String deleteFile(Authentication authentication, @PathVariable String FileName, @ModelAttribute("NewFile")FileForm NewFile,
                             @ModelAttribute("NewNote")NoteForm NewNote, @ModelAttribute("NewCredential")CredentialForm NewCredential, Model model ){

        fileService.deleteFile(FileName);
        Integer UserID = getUserId(authentication);
        model.addAttribute("files", fileService.getFileListings(UserID));
        model.addAttribute("result", "success");

        return "result";


    }




}
