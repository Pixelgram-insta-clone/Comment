package com.cognizant.Comment;

import com.cognizant.Comment.model.Comment;
import com.cognizant.Comment.model.CommentDTO;
import com.cognizant.Comment.model.PageOfItems;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private static Comment comment;
    private static CommentDTO commentDTO;
    private static LocalDate date;

    @BeforeAll
    public static void init() {
        date = LocalDate.now();
        comment = new Comment(
                1,
                1,
                "paul",
                "The Bene Gesserit is an ancient order",
                date
        );

        commentDTO = new CommentDTO(
                comment.getUsername(),
                comment.getBody()
        );
    }

    @Test
    public void getPageOfComments_positiveTest()  {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        PageOfItems<Comment> expected = new PageOfItems<>(new PageImpl<>(comments));
        when(commentService.getPageOfComments(1, 1, 1))
                .thenReturn(expected);

        PageOfItems<Comment> actual = commentController.getPageOfComments(1, 1, 1);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void addComment() {
        when(commentService.createComment(comment.getPostId(), commentDTO)).thenReturn(comment);

        Comment actual = commentController.addComment(comment.getPostId(), commentDTO);

        assertEquals(comment, actual);
    }

    @Test
    void deleteComment_positiveTest() {
        when(commentService.deleteComment(comment.getId()))
                .thenReturn(true);
        Boolean actual = commentController.deleteComment(comment.getId());
        Assertions.assertTrue(actual);
    }
}
