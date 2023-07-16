package com.Instagram.repository;

import com.Instagram.model.Post;
import com.Instagram.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post,Integer> {
    Object findAllByUser(User postOwner);
}
