package org.example.jobboardspringapplication.services;

import org.example.jobboardspringapplication.DTO.PostDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.repo.IPostRepo;
import org.example.jobboardspringapplication.repo.IUserRepo;
import org.example.jobboardspringapplication.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private IPostRepo postRepo;

    @Mock
    private IUserRepo userRepo;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void testCreatePost_UserNotFoundException() {
        PostDTO postDTO = new PostDTO();
        postDTO.setUserID(1);
        when(userRepo.findById(1)).thenReturn(Optional.empty());

        assertThrows(PostException.class, () -> postService.createPost(postDTO));
    }

    @Test
    void testCreatePost_DataIntegrityViolationException() {
        PostDTO postDTO = new PostDTO();
        postDTO.setUserID(1);
        when(userRepo.findById(1)).thenReturn(Optional.of(new User()));
        when(postRepo.save(any(Post.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(PostException.class, () -> postService.createPost(postDTO));
    }

    @Test
    void testGetAllPosts() {
        List<Post> posts = Collections.emptyList();
        when(postRepo.findAll()).thenReturn(posts);

        List<Post> result = postService.getAllPosts();

        assertEquals(posts, result);
    }

    @Test
    void testGetPostById() {
        Integer id = 1;
        Post post = new Post();
        when(postRepo.findById(id)).thenReturn(Optional.of(post));

        Optional<Post> result = postService.getPostById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetPostById_NotFound() {
        Integer id = 1;
        when(postRepo.findById(id)).thenReturn(Optional.empty());

        Optional<Post> result = postService.getPostById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testGetAllCommentsForPost() {
        Integer postId = 1;
        Post post = new Post();
        post.setPostID(postId);
        when(postRepo.findById(postId)).thenReturn(Optional.of(post));

        assertDoesNotThrow(() -> postService.getAllCommentsForPost(postId));
    }

    @Test
    void testGetAllCommentsForPost_PostNotFoundException() {
        Integer postId = 1;
        when(postRepo.findById(postId)).thenReturn(Optional.empty());

        assertThrows(PostException.class, () -> postService.getAllCommentsForPost(postId));
    }

    @Test
    void testGetAllTagsForPost() {
        Integer postId = 1;
        Post post = new Post();
        post.setPostID(postId);
        when(postRepo.findById(postId)).thenReturn(Optional.of(post));

        assertDoesNotThrow(() -> postService.getAllTagsForPost(postId));
    }

    @Test
    void testGetAllTagsForPost_PostNotFoundException() {
        Integer postId = 1;
        when(postRepo.findById(postId)).thenReturn(Optional.empty());

        assertThrows(PostException.class, () -> postService.getAllTagsForPost(postId));
    }

    @Test
    void testSavePost() {
        Post post = new Post();
        when(postRepo.save(post)).thenReturn(post);

        Post result = postService.savePost(post);

        assertNotNull(result);
    }

    @Test
    void testDeletePostById() {
        Integer id = 1;

        assertDoesNotThrow(() -> postService.deletePostById(id));
        verify(postRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeletePostById_NotFound() {
        Integer id = 1;
        doThrow(EmptyResultDataAccessException.class).when(postRepo).deleteById(id);

        assertThrows(PostException.class, () -> postService.deletePostById(id));
    }
}
