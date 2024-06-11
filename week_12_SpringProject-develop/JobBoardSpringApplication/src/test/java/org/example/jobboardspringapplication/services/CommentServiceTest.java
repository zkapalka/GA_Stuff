package org.example.jobboardspringapplication.services;

import org.example.jobboardspringapplication.DTO.CommentDTO;
import org.example.jobboardspringapplication.exceptions.CommentException;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.repo.ICommentRepo;
import org.example.jobboardspringapplication.repo.IPostRepo;
import org.example.jobboardspringapplication.repo.IUserRepo;
import org.example.jobboardspringapplication.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.client.RestTemplate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private ICommentRepo commentRepo;

    @Mock
    private IUserRepo userRepo;

    @Mock
    private IPostRepo postRepo;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateComment_DataIntegrityViolationException() {
        CommentDTO commentDTO = new CommentDTO();
        when(postRepo.findById(1)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(PostException.class, () -> commentService.createComment(commentDTO));
    }

    @Test
    void testCreateComment_PostNotFoundException() {
        CommentDTO commentDTO = new CommentDTO();
        when(postRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(PostException.class, () -> commentService.createComment(commentDTO));
    }



    @Test
    void testGetAllComments() {
        List<Comment> comments = Collections.emptyList();
        when(commentRepo.findAll()).thenReturn(comments);

        List<Comment> result = commentService.getAllComments();

        assertEquals(comments, result);
    }

    @Test
    void testGetCommentById() {
        Integer id = 1;
        Comment comment = new Comment();
        when(commentRepo.findById(id)).thenReturn(Optional.of(comment));

        Optional<Comment> result = commentService.getCommentById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetCommentById_NotFound() {
        Integer id = 1;
        when(commentRepo.findById(id)).thenReturn(Optional.empty());

        Optional<Comment> result = commentService.getCommentById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveComment() {
        Comment comment = new Comment();
        when(commentRepo.save(comment)).thenReturn(comment);

        Comment result = commentService.saveComment(comment);

        assertNotNull(result);
    }

    @Test
    void testDeleteCommentById() {
        Integer id = 1;

        assertDoesNotThrow(() -> commentService.deleteCommentById(id));
        verify(commentRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteCommentById_NotFound() {
        Integer id = 1;
        doThrow(EmptyResultDataAccessException.class).when(commentRepo).deleteById(id);

        assertThrows(CommentException.class, () -> commentService.deleteCommentById(id));
    }
}
