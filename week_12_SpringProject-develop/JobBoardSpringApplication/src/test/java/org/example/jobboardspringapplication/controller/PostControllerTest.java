package org.example.jobboardspringapplication.controller;
import org.example.jobboardspringapplication.DTO.PostDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.Tag;
import org.example.jobboardspringapplication.service.PostService;
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

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePost_Success() {
        // Given
        PostDTO postDTO = new PostDTO(/* initialize post DTO */);
        Post post = new Post(/* initialize post */);
        when(postService.createPost(postDTO)).thenReturn(post);

        // When
        ResponseEntity<Post> responseEntity = postController.createPost(postDTO);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(post, responseEntity.getBody());
    }

    @Test
    void testCreatePost_InternalServerError() {
        // Given
        PostDTO postDTO = new PostDTO(/* initialize post DTO */);
        when(postService.createPost(postDTO)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Post> responseEntity = postController.createPost(postDTO);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllPosts_Success() {
        // Given
        List<Post> posts = new ArrayList<>();
        when(postService.getAllPosts()).thenReturn(posts);

        // When
        ResponseEntity<List<Post>> responseEntity = postController.getAllPosts();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(posts, responseEntity.getBody());
    }

    @Test
    void testGetAllPosts_InternalServerError() {
        // Given
        when(postService.getAllPosts()).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Post>> responseEntity = postController.getAllPosts();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetPostById_Success() {
        // Given
        Post post = new Post(/* initialize post */);
        when(postService.getPostById(1)).thenReturn(Optional.of(post));

        // When
        ResponseEntity<Post> responseEntity = postController.getPostById(1);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(post, responseEntity.getBody());
    }

    @Test
    void testGetPostById_NotFound() {
        // Given
        when(postService.getPostById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Post> responseEntity = postController.getPostById(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetPostById_InternalServerError() {
        // Given
        when(postService.getPostById(1)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Post> responseEntity = postController.getPostById(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllCommentsForPost_Success() {
        // Given
        List<Comment> comments = new ArrayList<>();
        when(postService.getAllCommentsForPost(1)).thenReturn(comments);

        // When
        ResponseEntity<List<Comment>> responseEntity = postController.getAllCommentsForPost(1);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(comments, responseEntity.getBody());
    }

    @Test
    void testGetAllCommentsForPost_PostNotFoundException() {
        // Given
        when(postService.getAllCommentsForPost(1)).thenThrow(new PostException("Post not found"));

        // When
        ResponseEntity<List<Comment>> responseEntity = postController.getAllCommentsForPost(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllCommentsForPost_InternalServerError() {
        // Given
        when(postService.getAllCommentsForPost(1)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Comment>> responseEntity = postController.getAllCommentsForPost(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllTagsForPost_Success() {
        // Given
        List<Tag> tags = new ArrayList<>();
        when(postService.getAllTagsForPost(1)).thenReturn(tags);

        // When
        ResponseEntity<List<Tag>> responseEntity = postController.getAllTagsForPost(1);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tags, responseEntity.getBody());
    }

    @Test
    void testGetAllTagsForPost_InternalServerError() {
        // Given
        when(postService.getAllTagsForPost(1)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Tag>> responseEntity = postController.getAllTagsForPost(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testUpdatePost_Success() {
        // Given
        Post post = new Post(/* initialize post */);
        when(postService.getPostById(1)).thenReturn(Optional.of(post));
        when(postService.savePost(post)).thenReturn(post);

        // When
        ResponseEntity<Post> responseEntity = postController.updatePost(1, post);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(post, responseEntity.getBody());
    }

    @Test
    void testUpdatePost_NotFound() {
        // Given
        when(postService.getPostById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Post> responseEntity = postController.updatePost(1, new Post());

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdatePost_InternalServerError() {
        // Given
        when(postService.getPostById(1)).thenReturn(Optional.of(new Post()));
        when(postService.savePost(any())).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Post> responseEntity = postController.updatePost(1, new Post());

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testDeletePostById_Success() {
        // Given
        when(postService.getPostById(1)).thenReturn(Optional.of(new Post()));

        // When
        ResponseEntity<Void> responseEntity = postController.deletePostById(1);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testDeletePostById_NotFound() {
        // Given
        when(postService.getPostById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Void> responseEntity = postController.deletePostById(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeletePostById_InternalServerError() {
        // Given
        when(postService.getPostById(1)).thenReturn(Optional.of(new Post()));
        doThrow(new RuntimeException()).when(postService).deletePostById(1);

        // When
        ResponseEntity<Void> responseEntity = postController.deletePostById(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
