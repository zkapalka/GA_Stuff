package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.DTO.CommentDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {


    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping()
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        try {
            return ResponseEntity.ok(commentService.createComment(commentDTO));
        } catch (PostException e) {
            logger.error("Post not found", e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Unexpected error creating comment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        try {
            List<Comment> comments = commentService.getAllComments();
            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (Exception e) {
            logger.error("Error getting all comments", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
        try {
            Optional<Comment> commentOptional = commentService.getCommentById(id);
            return commentOptional.map(comment -> ResponseEntity.status(HttpStatus.OK).body(comment))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error getting comment by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //UPDATE

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        try {
            if (commentService.getCommentById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            comment.setCommentID(id);
            Comment updatedComment = commentService.saveComment(comment);
            return ResponseEntity.status(HttpStatus.OK).body(updatedComment);
        } catch (Exception e) {
            logger.error("Error updating comment", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommentById(@PathVariable Integer id) {
        try {
            if (commentService.getCommentById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            commentService.deleteCommentById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting comment by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

