package org.example.jobboardspringapplication.services;

import org.example.jobboardspringapplication.exceptions.TagException;
import org.example.jobboardspringapplication.model.Tag;
import org.example.jobboardspringapplication.repo.IPostRepo;
import org.example.jobboardspringapplication.repo.ITagRepo;
import org.example.jobboardspringapplication.service.TagService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {

    @Mock
    private ITagRepo tagRepo;

    @Mock
    private IPostRepo postRepo;

    @InjectMocks
    private TagService tagService;

    @Test
    void testGetAllTags() {
        List<Tag> tags = Collections.emptyList();
        when(tagRepo.findAll()).thenReturn(tags);

        List<Tag> result = tagService.getAllTags();

        assertEquals(tags, result);
    }

    @Test
    void testGetTagById() {
        Integer id = 1;
        Tag tag = new Tag();
        when(tagRepo.findById(id)).thenReturn(Optional.of(tag));

        Optional<Tag> result = tagService.getTagById(id);

        assertTrue(result.isPresent());
    }

    @Test
    void testGetTagById_NotFound() {
        Integer id = 1;
        when(tagRepo.findById(id)).thenReturn(Optional.empty());

        Optional<Tag> result = tagService.getTagById(id);

        assertTrue(result.isEmpty());
    }

    @Test
    void testSaveTag() {
        Tag tag = new Tag();
        when(tagRepo.save(tag)).thenReturn(tag);

        Tag result = tagService.saveTag(tag);

        assertNotNull(result);
    }

    @Test
    void testDeleteTagById() {
        Integer id = 1;

        assertDoesNotThrow(() -> tagService.deleteTagById(id));
        verify(tagRepo, times(1)).deleteById(id);
    }

    @Test
    void testDeleteTagById_NotFound() {
        Integer id = 1;
        doThrow(EmptyResultDataAccessException.class).when(tagRepo).deleteById(id);

        assertThrows(TagException.class, () -> tagService.deleteTagById(id));
    }
}
