package com.Instagram.service;

import com.Instagram.model.Post;
import com.Instagram.repository.IPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class PostService {
    @Autowired
    IPostRepo postRepo;

    public String createInstaPost(Post post) {
        post.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        postRepo.save(post);
        return "Post uploaded!!!!";
    }
}
