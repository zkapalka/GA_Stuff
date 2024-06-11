package org.example.jobboardspringapplication.service;

import org.example.jobboardspringapplication.DTO.CommentDTO;
import org.example.jobboardspringapplication.DTO.NotificationsDTO;
import org.example.jobboardspringapplication.exceptions.CommentException;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.repo.ICommentRepo;
import org.example.jobboardspringapplication.repo.IPostRepo;
import org.example.jobboardspringapplication.repo.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final ICommentRepo commentRepo;
    private final IUserRepo userRepo;
    private final IPostRepo postRepo;

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public CommentService(ICommentRepo commentRepo, IUserRepo userRepo, IPostRepo postRepo){
        this.commentRepo = commentRepo;
        this.userRepo = userRepo;
        this.postRepo = postRepo;
    }

    //Mapping DTO
    private Comment mapToComment(CommentDTO commentDTO) throws Exception {
        Comment comment = new Comment();
        Integer userId = commentDTO.getUserID();
        Integer postId = commentDTO.getPostID();
        comment.setCommentText(commentDTO.getCommentText());
        comment.setCommentLikes(commentDTO.getCommentLikes());

        User user = userRepo.findById(userId).orElseThrow(() -> new Exception("User not found"));
        comment.setUser(user);

        Post post = postRepo.findById(postId).orElseThrow(() -> new Exception("Post not found"));
        comment.setPost(post);

        return comment;
    }

    private NotificationsDTO createNotificationDTO(CommentDTO commentDTO) {
        return new NotificationsDTO(commentDTO.getCommentText(), commentDTO.getUserID(), commentDTO.getPostID());
    }

    public Comment createComment(CommentDTO commentDTO) throws PostException {
        try {
            if (commentDTO != null) {
                Integer postID = commentDTO.getPostID();
            Post post = postRepo.findById(postID)
                    .orElseThrow(() -> new PostException("Post not found"));
// Creating and mapping the comment to post
            Comment comment = mapToComment(commentDTO);
            comment.setPost(post);
            Comment savedComment = commentRepo.save(comment);
            post.getComments().add(savedComment);
            postRepo.save(post);

            //This set up the NotificationDTO to send to Notification microservice
            NotificationsDTO notificationDTO = createNotificationDTO(commentDTO);
            restTemplate.postForEntity("http://localhost:8081/notifications", notificationDTO, NotificationsDTO .class);


            return savedComment;
        } else {
                throw new NullPointerException("CommentDTO is null");
            }
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error creating comment: " + e.getMessage());
            throw new PostException("Failed to create comment: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("commentDTO is null: " + e.getMessage());
            throw new PostException("commentDTO is null: " + e.getMessage());
        } catch (PostException e) {
            throw e; // Re-throw PostException as is
        } catch (Exception e) {
            throw new PostException("Unexpected error creating comment: " + e.getMessage());
        }
    }


    //READ
    public List<Comment> getAllComments() {
        try {
            return commentRepo.findAll();
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving comments: " + e.getMessage());
            throw new DataAccessResourceFailureException("Failed to retrieve comments", e);
        }
    }

    public Optional<Comment> getCommentById(Integer id) {
        try {
            return commentRepo.findById(id);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving the comment with ID " + id + ": " + e.getMessage());
            throw new CommentException("Failed to get comment: " + e.getMessage());
        }
    }

    //UPDATE
    public Comment saveComment(Comment comment) {
        try {
            return commentRepo.save(comment);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while updating the comment" + e.getMessage());
            throw new CommentException("Failed to update comment: " + e.getMessage());
        }
    }

    //DELETE
    public void deleteCommentById(Integer id) {
        try {
            commentRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CommentException("Comment with ID " + id + " not found");
        } catch (DataAccessException e) {
            System.err.println("An error occurred while trying to delete the comment" + e.getMessage());
            throw new CommentException("Failed to delete comment:  " + e.getMessage());
        }
    }
}