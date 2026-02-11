package com.kodilla.library.service;

import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.update.TitleUpdateDto;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TitleServiceTest {

    @Mock
    private TitleRepository titleRepository;

    @InjectMocks
    private TitleService titleService;

    @Test
    void shouldAddTitle() {
        // given
        Title title = new Title();
        title.setTitle("1984");
        title.setAuthor("Marek");
        title.setPublicationYear(1949);

        when(titleRepository.save(title)).thenReturn(title);

        // when
        Title result = titleService.addTitle(title);

        // then
        assertEquals("1984", result.getTitle());
        verify(titleRepository, times(1)).save(title);
    }

    @Test
    void shouldReturnTitleById() {
        // given
        Long id = 1L;
        Title title = new Title();
        title.setId(id);

        when(titleRepository.findById(id))
                .thenReturn(Optional.of(title));

        // when
        Title result = titleService.getTitle(id);

        // then
        assertEquals(id, result.getId());
    }

    @Test
    void shouldThrowExceptionWhenTitleNotFound() {
        // given
        when(titleRepository.findById(1L))
                .thenReturn(Optional.empty());

        // when + then
        assertThrows(NotFoundException.class,
                () -> titleService.getTitle(1L));
    }

    @Test
    void shouldUpdateTitle() {
        // given
        Long id = 1L;

        Title title = new Title();
        title.setId(id);
        title.setTitle("Old");
        title.setAuthor("Old Author");
        title.setPublicationYear(2000);

        TitleUpdateDto dto = new TitleUpdateDto("New", "New Author", 2020);

        when(titleRepository.findById(id))
                .thenReturn(Optional.of(title));
        when(titleRepository.save(title))
                .thenReturn(title);

        // when
        Title result = titleService.updateTitle(id, dto);

        // then
        assertEquals("New", result.getTitle());
        assertEquals("New Author", result.getAuthor());
        assertEquals(2020, result.getPublicationYear());
    }

    @Test
    void shouldReturnAllTitles() {
        // given
        Title title = new Title();
        when(titleRepository.findAll())
                .thenReturn(List.of(title));

        // when
        List<Title> result = titleService.getAllTitles();

        // then
        assertEquals(1, result.size());
        verify(titleRepository, times(1)).findAll();
    }
}