package com.Instagram.controller;

import com.Instagram.model.Post;
import com.Instagram.service.AuthenticationService;
import com.Instagram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/instagram-post-app")
public class PostController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/post")
    public String createInstaPost(@RequestBody Post post, @RequestParam String email, @RequestParam String token)
    {
        if(authenticationService.authenticate(email,token)) {
            return userService.createInstaPost(post,email);
        }
        else {
            return "Not an Authenticated user activity!!!";
        }
    }
    @GetMapping("/get-post")
    public ResponseEntity getPost(@RequestParam String email, @RequestParam String token){
        if(authenticationService.authenticate(email,token)) {
            return ResponseEntity.ok(userService.getPost(email));
        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found") ; //"Not an Authenticated user activity!!!";
        }
    }
}
