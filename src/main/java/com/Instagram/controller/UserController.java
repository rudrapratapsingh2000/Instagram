package com.Instagram.controller;

import com.Instagram.model.User;
import com.Instagram.model.dto.SignInInput;
import com.Instagram.model.dto.SignUpOutput;
import com.Instagram.service.AuthenticationService;
import com.Instagram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/instagram-app")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

     @PostMapping("/user/signIn")
     public String sigInInstaUser(@RequestBody @Valid SignInInput signInInput)
    {
        return userService.signInUser(signInInput);
    }

    @PostMapping("/user/signup")
    public SignUpOutput signUpInstaUser(@RequestBody User user)
    {

        return userService.signUpUser(user);
    }

    @PutMapping("/user/update/{userId}")
    public String updateUserDetails(@RequestBody User user,@RequestParam String email, @RequestParam String token) {
        if(authenticationService.authenticate(email,token)) {
            return userService.updateUser(user,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }

}
