package org.example.jobboardspringapplication.service;

import org.example.jobboardspringapplication.DTO.TagsDTO;
import org.example.jobboardspringapplication.exceptions.PostException;
import org.example.jobboardspringapplication.exceptions.TagException;
import org.example.jobboardspringapplication.model.Post;
import org.example.jobboardspringapplication.model.Tag;

import org.example.jobboardspringapplication.repo.IPostRepo;
import org.example.jobboardspringapplication.repo.ITagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final ITagRepo tagRepo;
    private final IPostRepo postRepo;

    @Autowired
    public TagService(ITagRepo tagRepo, IPostRepo postRepo){
        this.tagRepo = tagRepo;
        this.postRepo = postRepo;
    }

    //Mapping DTO
    private Tag mapToTag(TagsDTO tagsDTO) throws Exception {
        Tag tag = new Tag();
        Integer postid = tagsDTO.getPostID();
        Post post = postRepo.findById(postid)
                .orElseThrow(() -> new Exception("Post not found for ID: " + postid));
        tag.setTagName(tagsDTO.getTagName());
        tag.setTagDescription(tagsDTO.getTagDescription());
        tag.getPost().add(post);
        return tag;
    }

//CREATE


//    public Tag createTag(Tag tag) {
//        try {
//            return tagRepo.save(tag);
//        } catch (DataIntegrityViolationException e) {
//            System.err.println("Error creating tag: " + e.getMessage());
//            throw new TagException("Failed to create tag: " + e.getMessage());
//        }
//    }

    public List<Tag> createTag(TagsDTO tagsDTO) throws TagException{
        try {
            if (tagsDTO != null) {
                Integer postId = tagsDTO.getPostID();
                Post existingPost = postRepo.findById(postId)
                        .orElseThrow(() -> new PostException("Post not found"));

                Tag tag = mapToTag(tagsDTO);
                tag.getPost().add(existingPost);
                existingPost.getTags().add(tag);
                tagRepo.save(tag);
                // Save the associations
                postRepo.save(existingPost);
                return Collections.singletonList(tagRepo.save(tag));
            } else {
                throw new NullPointerException("TagsDTO is null");
            }
        } catch (DataIntegrityViolationException e) {
            System.err.println("Error creating tag: " + e.getMessage());
            throw new TagException("Failed to create tag: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("TagsDTO is null: " + e.getMessage());
            throw new TagException("TagsDTO is null: " + e.getMessage());
        } catch (Exception e) {
            throw new TagException("Unexpected error creating tag: " + e.getMessage());
        }
    }

    //READ

    public List<Tag> getAllTags() {
        try {
            return tagRepo.findAll();
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving tags: " + e.getMessage());
            throw new DataAccessResourceFailureException("Failed to retrieve tags", e);
        }
    }

    public Optional<Tag> getTagById(Integer id) {
        try {
            return tagRepo.findById(id);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while retrieving the tag with ID " + id + ": " + e.getMessage());
            throw new TagException("Failed to get tag: " + e.getMessage());
        }
    }

    //UPDATE

    public Tag saveTag(Tag tag) {
        try {
            return tagRepo.save(tag);
        } catch (DataAccessException e) {
            System.err.println("An error occurred while updating the tag" + e.getMessage());
            throw new TagException("Failed to update tag: " + e.getMessage());
        }
    }

    //DELETE

    public void deleteTagById(Integer id) {
        try {
            tagRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new TagException("Tag with ID " + id + " not found");
        } catch (DataAccessException e) {
            System.err.println("An error occurred while trying to delete the tag" + e.getMessage());
            throw new TagException("Failed to delete tag:  " + e.getMessage());
        }
    }
}
