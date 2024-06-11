package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.DTO.CommentDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateComment_Success() {
        // Given
        CommentDTO commentDTO = new CommentDTO(/* initialize comment DTO */);
        Comment comment = new Comment(/* initialize comment */);
        when(commentService.createComment(commentDTO)).thenReturn(comment);

        // When
        ResponseEntity<Comment> responseEntity = commentController.createComment(commentDTO);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comment, responseEntity.getBody());
    }

    @Test
    void testCreateComment_PostNotFoundException() {
        // Given
        CommentDTO commentDTO = new CommentDTO(/* initialize comment DTO */);
        when(commentService.createComment(commentDTO)).thenThrow(new PostException("Post not found"));

        // When
        ResponseEntity<Comment> responseEntity = commentController.createComment(commentDTO);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testCreateComment_InternalServerError() {
        // Given
        CommentDTO commentDTO = new CommentDTO(/* initialize comment DTO */);
        when(commentService.createComment(commentDTO)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Comment> responseEntity = commentController.createComment(commentDTO);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllComments_Success() {
        // Given
        List<Comment> comments = new ArrayList<>();
        when(commentService.getAllComments()).thenReturn(comments);

        // When
        ResponseEntity<List<Comment>> responseEntity = commentController.getAllComments();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comments, responseEntity.getBody());
    }

    @Test
    void testGetAllComments_InternalServerError() {
        // Given
        when(commentService.getAllComments()).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Comment>> responseEntity = commentController.getAllComments();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetCommentById_Success() {
        // Given
        Comment comment = new Comment(/* initialize comment */);
        when(commentService.getCommentById(1)).thenReturn(Optional.of(comment));

        // When
        ResponseEntity<Comment> responseEntity = commentController.getCommentById(1);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comment, responseEntity.getBody());
    }

    @Test
    void testGetCommentById_NotFound() {
        // Given
        when(commentService.getCommentById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Comment> responseEntity = commentController.getCommentById(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetCommentById_InternalServerError() {
        // Given
        when(commentService.getCommentById(1)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Comment> responseEntity = commentController.getCommentById(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateComment_Success() {
        // Given
        Comment comment = new Comment(/* initialize comment */);
        when(commentService.getCommentById(1)).thenReturn(Optional.of(comment));
        when(commentService.saveComment(comment)).thenReturn(comment);

        // When
        ResponseEntity<Comment> responseEntity = commentController.updateComment(1, comment);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comment, responseEntity.getBody());
    }

    @Test
    void testUpdateComment_NotFound() {
        // Given
        Comment comment = new Comment(/* initialize comment */);
        when(commentService.getCommentById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Comment> responseEntity = commentController.updateComment(1, comment);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateComment_InternalServerError() {
        // Given
        Comment comment = new Comment(/* initialize comment */);
        when(commentService.getCommentById(1)).thenReturn(Optional.of(comment));
        when(commentService.saveComment(comment)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Comment> responseEntity = commentController.updateComment(1, comment);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteCommentById_Success() {
        // Given
        when(commentService.getCommentById(1)).thenReturn(Optional.of(new Comment()));

        // When
        ResponseEntity<Void> responseEntity = commentController.deleteCommentById(1);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteCommentById_NotFound() {
        // Given
        when(commentService.getCommentById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Void> responseEntity = commentController.deleteCommentById(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteCommentById_InternalServerError() {
        // Given
        when(commentService.getCommentById(1)).thenReturn(Optional.of(new Comment()));
        doThrow(new RuntimeException()).when(commentService).deleteCommentById(1);

        // When
        ResponseEntity<Void> responseEntity = commentController.deleteCommentById(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
