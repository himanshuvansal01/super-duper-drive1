package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.Security.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.model.*;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequestMapping("credential")
public class CredentialController {

    private final CredentialService credentialService;
    private final EncryptionService encryptionService;
    private final UserService userService;

    public CredentialController(CredentialService credentialService, EncryptionService encryptionService, UserService userService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
        this.userService = userService;
    }

    public CredentialService getCredentialService() {
        return credentialService;
    }

    public EncryptionService getEncryptionService() {
        return encryptionService;
    }

    public UserService getUserService() {
        return userService;
    }




    @PostMapping("addcredential")
    public String newCredential(Authentication authentication, @ModelAttribute("newFiles") FileForm newFiles,
                                @ModelAttribute("newCredentials") CredentialForm newCredentials, @ModelAttribute("newNotes")NoteForm newNotes, Model model){


        String NewUrl = newCredentials.getURL();
        String  CredentialId = newCredentials.getCredentialID();
        String password = newCredentials.getPassword();
        String UserName = authentication.getName();


        SecureRandom random = new SecureRandom();
        byte[] Key = new byte[16];
        random.nextBytes(Key);
        String encodeKey = Base64.getEncoder().encodeToString(Key);
        String encryptedPassword = encryptionService.encryptValue(password,encodeKey);

        if(CredentialId.isEmpty()){
            credentialService.AddCredential(NewUrl,UserName,newCredentials.getUserName(),encodeKey, encryptedPassword );


        }

        else{
            Credential existingCredential = credentialService.getCredential(Integer.parseInt(CredentialId));
            credentialService.updateCredential(existingCredential.getCredentialID(), newCredentials.getUserName(), NewUrl,encodeKey, encryptedPassword);
        }

        User user = userService.getUser(UserName);
        model.addAttribute("credentials", credentialService.getCredentialListings(user.getUserID()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("result", "success");

        return "result";



    }


    @GetMapping
    public String getHomePage(Authentication authentication, @ModelAttribute("newFiles") FileForm newFiles,
                              @ModelAttribute("newCredentials") CredentialForm newCredentials, @ModelAttribute("newNotes")NoteForm newNotes, Model model){

        String userName = authentication.getName();
        User user = userService.getUser(userName);
        model.addAttribute("credentials", this.credentialService.getCredentialListings(user.getUserID()));
        model.addAttribute("encryptionService", encryptionService);

        return "home";

    }



    @GetMapping(value = "/deletecredential/{id}")
    public String deleteCredential(Authentication authentication, @PathVariable Integer id,
                                   @ModelAttribute("newFiles") FileForm newFiles,
                                   @ModelAttribute("newCredentials") CredentialForm newCredentials,
                                   @ModelAttribute("newNotes")NoteForm newNotes, Model model) {

        credentialService.deleteCredential(id);
        String UserName = authentication.getName();
        User users = userService.getUser(UserName);
        model.addAttribute("credentials",credentialService.getCredentialListings(users.getUserID()));
        model.addAttribute("encryptionService", encryptionService);
        model.addAttribute("result", "success");

        return "result";


    }

    @GetMapping(value = "/getcredential/{id}")
    public Credential getCredential(@PathVariable Integer id){
        return credentialService.getCredential(id);

    }
}
