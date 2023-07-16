package com.Instagram.service;

import com.Instagram.model.AuthenticationToken;
import com.Instagram.model.Post;
import com.Instagram.model.User;
import com.Instagram.model.dto.SignInInput;
import com.Instagram.model.dto.SignUpOutput;
import com.Instagram.repository.IUserRepo;
import com.Instagram.service.emailUtility.EmailHandler;
import com.Instagram.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    PostService postService;

    public String signInUser(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if (signInEmail == null) {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }

        User existingUser = userRepo.findFirstByUserEmail(signInEmail);

        if (existingUser == null) {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }
        try{
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getUserPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(authToken);

                EmailHandler.sendEmail("rc195131@gmail.com","email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e) {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public SignUpOutput signUpUser(User user) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getUserEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this user email already exists ??
        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getUserPassword());

            //saveAppointment the user with the new encrypted password

            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String createInstaPost(Post post, String email) {
        User postOwner = userRepo.findFirstByUserEmail(email);
        post.setUser(postOwner);
        return postService.createInstaPost(post);
    }

    public String updateUser(User user, String email) {
        User existuser=userRepo.findFirstByUserEmail(email);
       existuser.setUserPassword(user.getUserPassword());
        existuser.setAge((user.getAge()));
        existuser.setFirstName(user.getFirstName());
        existuser.setLastName(user.getLastName());
        existuser.setPhoneNumber(user.getPhoneNumber());
        userRepo.save(existuser);
        return "Updated";
    }

    public Object getPost(String email) {
        User postOwner = userRepo.findFirstByUserEmail(email);
        Post post=new Post();
        post.setUser(postOwner);
        return postService.postRepo.findAllByUser(postOwner);
    }
}
