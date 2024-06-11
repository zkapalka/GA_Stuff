package org.example.jobboardspringapplication.controller;

import org.example.jobboardspringapplication.DTO.TagsDTO;
import org.example.jobboardspringapplication.model.Tag;
import org.example.jobboardspringapplication.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TagControllerTest {

    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTag_Success() {
        // Given
        TagsDTO tagsDTO = new TagsDTO(/* initialize tag DTO */);
        Tag tag = new Tag(/* initialize tag */);
        when(tagService.createTag(tagsDTO)).thenReturn(Collections.singletonList(tag));

        // When
        ResponseEntity<List<Tag>> responseEntity = tagController.createTag(tagsDTO);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(Collections.singletonList(tag), responseEntity.getBody());
    }

    @Test
    void testCreateTag_InternalServerError() {
        // Given
        TagsDTO tagsDTO = new TagsDTO(/* initialize tag DTO */);
        when(tagService.createTag(tagsDTO)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Tag>> responseEntity = tagController.createTag(tagsDTO);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllTags_Success() {
        // Given
        List<Tag> tags = new ArrayList<>();
        when(tagService.getAllTags()).thenReturn(tags);

        // When
        ResponseEntity<List<Tag>> responseEntity = tagController.getAllTags();

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tags, responseEntity.getBody());
    }

    @Test
    void testGetAllTags_InternalServerError() {
        // Given
        when(tagService.getAllTags()).thenThrow(new RuntimeException());

        // When
        ResponseEntity<List<Tag>> responseEntity = tagController.getAllTags();

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testGetTagById_Success() {
        // Given
        Tag tag = new Tag(/* initialize tag */);
        when(tagService.getTagById(1)).thenReturn(Optional.of(tag));

        // When
        ResponseEntity<Tag> responseEntity = tagController.getTagById(1);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tag, responseEntity.getBody());
    }

    @Test
    void testGetTagById_NotFound() {
        // Given
        when(tagService.getTagById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Tag> responseEntity = tagController.getTagById(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetTagById_InternalServerError() {
        // Given
        when(tagService.getTagById(1)).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Tag> responseEntity = tagController.getTagById(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateTag_Success() {
        // Given
        Tag tag = new Tag(/* initialize tag */);
        when(tagService.getTagById(1)).thenReturn(Optional.of(tag));
        when(tagService.saveTag(tag)).thenReturn(tag);

        // When
        ResponseEntity<Tag> responseEntity = tagController.updateTag(1, tag);

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tag, responseEntity.getBody());
    }

    @Test
    void testUpdateTag_NotFound() {
        // Given
        when(tagService.getTagById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Tag> responseEntity = tagController.updateTag(1, new Tag());

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateTag_InternalServerError() {
        // Given
        when(tagService.getTagById(1)).thenReturn(Optional.of(new Tag()));
        when(tagService.saveTag(any())).thenThrow(new RuntimeException());

        // When
        ResponseEntity<Tag> responseEntity = tagController.updateTag(1, new Tag());

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteTagById_Success() {
        // Given
        when(tagService.getTagById(1)).thenReturn(Optional.of(new Tag()));

        // When
        ResponseEntity<Void> responseEntity = tagController.deleteTagById(1);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteTagById_NotFound() {
        // Given
        when(tagService.getTagById(1)).thenReturn(Optional.empty());

        // When
        ResponseEntity<Void> responseEntity = tagController.deleteTagById(1);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteTagById_InternalServerError() {
        // Given
        when(tagService.getTagById(1)).thenReturn(Optional.of(new Tag()));
        doThrow(new RuntimeException()).when(tagService).deleteTagById(1);

        // When
        ResponseEntity<Void> responseEntity = tagController.deleteTagById(1);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
    }
}
