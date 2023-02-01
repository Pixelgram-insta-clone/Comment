package com.cognizant.Comment;

import com.cognizant.Comment.model.Comment;
import com.cognizant.Comment.model.CommentDTO;
import com.cognizant.Comment.model.PageOfItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public PageOfItems<Comment> getPageOfComments(int postId, int pageNumber, int pageSize) {

        if (!(postId < 0 || pageNumber < 0 || pageSize <= 0)) {
            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
            return new PageOfItems<>(
                    commentRepository.findCommentByPostId(postId, pageable)
            );
        } else {
            return new PageOfItems<>();
        }
    }

    public Comment createComment(int postId, CommentDTO commentDTO) {
        Comment comment = new Comment(
                postId,
                commentDTO.getUsername(),
                commentDTO.getBody(),
                LocalDate.now()
        );

        return commentRepository.save(comment);
    }

    public Boolean deleteComment(int commentId) {
        commentRepository.deleteById(commentId);

        return !commentRepository.findById(commentId).isPresent();
    }
}
