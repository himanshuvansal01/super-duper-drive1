package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class SignupControllerClass {

    private final UserService userService;

    public SignupControllerClass(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public String signupView() {
            return "signup";

        }

        @PostMapping
        public String signupUser(@ModelAttribute User user, Model model, RedirectAttributes redirectAttributes){

        String SignUpError = null;

        if(!userService.isUserNameAvailable(user.getUsername())){
            SignUpError = "The UserName already exists.";
        }

        if(SignUpError == null){
            int RowsAdded = userService.createUser(user);
            if(RowsAdded < 0 ){
                SignUpError = "ERROR: There was error signing up. Please try again.";

            }
        }

        if(SignUpError == null){
            redirectAttributes.addFlashAttribute("SuccessMessage", "You have signed up successfully");
            return "login";

        }

        else{
            redirectAttributes.addFlashAttribute("signupError", SignUpError);
        }

        return "signup";

    }
}
