package com.Instagram.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer PostId;
    private Timestamp createdDate;
    private Timestamp updatedDate;
    private String postData;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
