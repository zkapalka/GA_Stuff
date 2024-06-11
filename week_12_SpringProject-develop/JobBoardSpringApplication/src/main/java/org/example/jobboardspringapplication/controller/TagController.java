package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.DTO.TagsDTO;
import org.example.jobboardspringapplication.model.Tag;
import org.example.jobboardspringapplication.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {

    private static final Logger logger = LoggerFactory.getLogger(TagController.class);

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    //CREATE
    @PostMapping
    public ResponseEntity<List<Tag>> createTag(@RequestBody TagsDTO tagsDTO) {
        try {
            return ResponseEntity.ok(tagService.createTag(tagsDTO));
        } catch (Exception e) {
            logger.error("Error creating tag", e);
            return ResponseEntity.badRequest().build();
        }
    }

    //READ
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {
        try {
            List<Tag> tags = tagService.getAllTags();
            return ResponseEntity.status(HttpStatus.OK).body(tags);
        } catch (Exception e) {
            logger.error("Error getting all tags", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Integer id) {
        try {
            Optional<Tag> tagOptional = tagService.getTagById(id);
            return tagOptional.map(tag -> ResponseEntity.status(HttpStatus.OK).body(tag))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        } catch (Exception e) {
            logger.error("Error getting tag by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Integer id, @RequestBody Tag tag) {
        try {
            if (tagService.getTagById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            tag.setTagID(id);
            Tag updatedTag = tagService.saveTag(tag);
            return ResponseEntity.status(HttpStatus.OK).body(updatedTag);
        } catch (Exception e) {
            logger.error("Error updating tag", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTagById(@PathVariable Integer id) {
        try {
            if (tagService.getTagById(id).isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            tagService.deleteTagById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            logger.error("Error deleting tag by ID", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

