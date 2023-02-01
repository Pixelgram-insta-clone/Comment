package com.cognizant.Comment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int postId;
    private String username;
    private String body;
    private LocalDate createdOn;

    public Comment(int postId, String username, String body, LocalDate createdOn) {
        this.postId = postId;
        this.username = username;
        this.body = body;
        this.createdOn = createdOn;
    }

}
