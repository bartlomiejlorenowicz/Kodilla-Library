package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.update.ReaderUpdateDto;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.ReaderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderService readerService;

    @Test
    void shouldAddReaderAndSetAccountCreatedAt() {
        // given
        Reader reader = new Reader();
        reader.setFirstName("Jan");
        reader.setLastName("Kowalski");

        when(readerRepository.save(any(Reader.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Reader result = readerService.addReader(reader);

        // then
        assertNotNull(result.getAccountCreatedAt());
        verify(readerRepository, times(1)).save(reader);
    }

    @Test
    void shouldReturnAllNotDeletedReaders() {
        // given
        Reader reader = new Reader();
        reader.setDeleted(false);

        when(readerRepository.findAllByDeletedFalse())
                .thenReturn(List.of(reader));

        // when
        List<Reader> result = readerService.getAllReaders();

        // then
        assertEquals(1, result.size());
        verify(readerRepository).findAllByDeletedFalse();
    }

    @Test
    void shouldUpdateReader() {
        // given
        Long id = 1L;

        Reader reader = new Reader();
        reader.setId(id);
        reader.setFirstName("Old");
        reader.setLastName("Name");

        ReaderUpdateDto dto = new ReaderUpdateDto("New", "Surname");

        when(readerRepository.findByIdAndDeletedFalse(id))
                .thenReturn(Optional.of(reader));

        when(readerRepository.save(reader)).thenReturn(reader);

        // when
        Reader result = readerService.updateReader(id, dto);

        // then
        assertEquals("New", result.getFirstName());
        assertEquals("Surname", result.getLastName());
    }

    @Test
    void shouldThrowNotFoundWhenReaderNotExists() {
        // given
        when(readerRepository.findByIdAndDeletedFalse(1L))
                .thenReturn(Optional.empty());

        // when + then
        assertThrows(NotFoundException.class,
                () -> readerService.getReader(1L));
    }
}