package com.cognizant.Comment;

import com.cognizant.Comment.model.Comment;
import com.cognizant.Comment.model.CommentDTO;
import com.cognizant.Comment.model.PageOfItems;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/comments")
    public PageOfItems<Comment> getPageOfComments(
            @RequestParam int postId,
            @RequestParam int pageNumber,
            @RequestParam int pageSize) {
        return commentService.getPageOfComments(postId, pageNumber, pageSize);
    }

    @PostMapping("/posts/{postId}/comments")
    public Comment addComment(
            @PathVariable int postId,
            @RequestBody CommentDTO commentDTO) {
        return commentService.createComment(postId, commentDTO);
    }

    @DeleteMapping("/comments")
    public Boolean deleteComment(
            @RequestParam int commentId
    ) {
        return commentService.deleteComment(commentId);
    }

}
