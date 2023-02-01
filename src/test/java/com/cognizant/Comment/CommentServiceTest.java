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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

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
    void getComment_positiveTest() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        Page<Comment> page = new PageImpl<>(comments);
        Pageable pageable = PageRequest.of(1, 1, Sort.by("id").descending());
        when(commentRepository.findCommentByPostId(1, pageable))
                .thenReturn(page);

        PageOfItems<Comment> actual = commentService.getPageOfComments(1, 1, 1);
        PageOfItems<Comment> expected = new PageOfItems<>(page);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void getComment_noCommentsPositive() {
        PageOfItems<Comment> actual = commentService.getPageOfComments(-1, 0, 1);

        assert(actual.getTotalElements() == 0);
    }

    @Test
    void createComment() {

        Comment commentToSave = new Comment(
                0,
                comment.getPostId(),
                comment.getUsername(),
                comment.getBody(),
                comment.getCreatedOn()
        );

        when(commentRepository.save(commentToSave)).thenReturn(comment);

        Comment actual = commentService.createComment(comment.getPostId(), commentDTO);

        assertEquals(comment, actual);
    }

    @Test
    void deleteComment_positiveTest() {
        when(commentRepository.findById(0)).thenReturn(Optional.empty());
        Boolean actual = commentService.deleteComment(0);
        Assertions.assertTrue(actual);
    }

}
