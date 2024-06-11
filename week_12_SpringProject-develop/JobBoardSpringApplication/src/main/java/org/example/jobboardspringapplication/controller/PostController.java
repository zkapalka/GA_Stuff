package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.DTO.PostDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.Tag;
import org.example.jobboardspringapplication.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    //CREATE
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO) {
        try {
            return ResponseEntity.ok(postService.createPost(postDTO));
        } catch (Exception e) {
            logger.error("Error creating post", e);
            return ResponseEntity.badRequest().build();
        }
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        try {
            List<Post> posts = postService.getAllPosts();
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        } catch (Exception e) {
            logger.error("Error getting all posts", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        try {
            Optional<Post> postOptional = postService.getPostById(id);
            return postOptional.map(post -> ResponseEntity.status(HttpStatus.OK).body(post))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error getting post by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getAllCommentsForPost(@PathVariable Integer id) {
        try {
            List<Comment> commentsOfPosts = postService.getAllCommentsForPost(id);
            return ResponseEntity.ok().body(commentsOfPosts);
        } catch (PostException e) {
            logger.error("Post not found", e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Error getting comments for post", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}/tags")
    public ResponseEntity<List<Tag>> getAllTagsForPost(@PathVariable Integer id) {
        try {
            List<Tag> tagsOfPosts = postService.getAllTagsForPost(id);
            return ResponseEntity.ok().body(tagsOfPosts);
        } catch (Exception e) {
            logger.error("Error getting tags for post", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        try {
            if (postService.getPostById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            post.setPostID(id);
            Post updatedPost = postService.savePost(post);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPost);
        } catch (Exception e) {
            logger.error("Error updating post", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePostById(@PathVariable Integer id) {
        try {
            if (postService.getPostById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            postService.deletePostById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting post by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

