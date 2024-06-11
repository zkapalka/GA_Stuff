package org.example.jobboardspringapplication.service;

import org.example.jobboardspringapplication.DTO.CommentDTO;
import org.example.jobboardspringapplication.DTO.PostDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.model.Comment;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.Tag;
import org.example.jobboardspringapplication.model.User;
import org.example.jobboardspringapplication.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final IPostRepo postRepo;
    private final IUserRepo userRepo;
    private final ICommentRepo commentRepo;

    @Autowired
    public PostService(IPostRepo postRepo, IUserRepo userRepo, ICommentRepo commentRepo, ITagRepo tagRepo) {
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.commentRepo = commentRepo;
    }

    //Mapping DTO
    private Post mapToPost(PostDTO postDTO) throws Exception {
        Post post = new Post();
        Integer userId = postDTO.getUserID();
        post.setPostTitle(postDTO.getPostTitle());
        post.setPostContent(postDTO.getPostContent());
        post.setPostLikes(postDTO.getPostLikes());
        post.setMediaUrl(postDTO.getMediaUrl());
        post.setUser(userRepo.findById(userId).orElseThrow(() -> new Exception("User not found")));

        return post;
    }

    //CREATE

    public Post createPost(PostDTO postDTO) throws PostException {
        try {
            if (postDTO != null) {
                Integer userID = postDTO.getUserID();
                User existingUser = userRepo.findById(userID)
                        .orElseThrow(() -> new PostException("User not found"));

                Post post = mapToPost(postDTO);
                post.setUser(existingUser);
                existingUser.getPosts().add(post);
                userRepo.save(existingUser);
                return postRepo.save(post);
            } else {
                throw new NullPointerException("PostDTO is null");
            }
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error creating post: " + e.getMessage());
            throw new PostException("Failed to create post: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("PostDTO is null: " + e.getMessage());
            throw new PostException("PostDTO is null: " + e.getMessage());
        } catch (PostException e) {
            throw e; // Re-throw PostException as is
        } catch (Exception e) {
            throw new PostException("Unexpected error creating post: " + e.getMessage());
        }
    }



    //READ

    public List<Post> getAllPosts() {
        try {
            return postRepo.findAll();
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving posts: " + e.getMessage());
            throw new DataAccessResourceFailureException("Failed to retrieve posts", e);
        }
    }

    public Optional<Post> getPostById(Integer id) {
        try {
            return postRepo.findById(id);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving the post with ID " + id + ": " + e.getMessage());
            throw new PostException("Failed to get post: " + e.getMessage());
        }
    }

    public List<Comment> getAllCommentsForPost(Integer postId) {
        try {
            Optional<Post> postOptional = postRepo.findById(postId);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                return post.getComments();
            } else {
                throw new PostException("Post with ID " + postId + " not found");
            }
        } catch (Exception e) {
            throw new PostException("Error retrieving post");
        }
    }

    public List<Tag> getAllTagsForPost(Integer postId) {
        try {
            Optional<Post> postOptional = postRepo.findById(postId);
            if (postOptional.isPresent()) {
                Post post = postOptional.get();
                return post.getTags();
            } else {
                throw new PostException("Post with ID " + postId + " not found");
            }
        } catch (Exception e) {
            throw new PostException("Error retrieving post");
        }
    }


    //UPDATE

    public Post savePost(Post post) {
        try {
            return postRepo.save(post);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while updating the post" + e.getMessage());
            throw new PostException("Failed to update post: " + e.getMessage());
        }
    }

    //DELETE

    public void deletePostById(Integer id) {
        try {
            postRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new PostException("Post with ID " + id + " not found");
        } catch (DataAccessException e) {
            System.err.println("An error occurred while trying to delete the post" + e.getMessage());
            throw new PostException("Failed to delete post:  " + e.getMessage());
        }
    }
}
