package com.cognizant.Comment;

import com.cognizant.Comment.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Page<Comment> findCommentByPostId(int postId, Pageable pageable);
}
