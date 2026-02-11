package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.update.ReaderUpdateDto;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.exception.ResourceNotFoundException;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader addReader(Reader reader) {
        reader.setAccountCreatedAt(LocalDate.now());
        return readerRepository.save(reader);
    }

    @Transactional(readOnly = true)
    public List<Reader> getAllReaders() {
        return readerRepository.findAllByDeletedFalse();
    }

    public Reader updateReader(Long id, ReaderUpdateDto dto) {
        Reader reader = getReader(id);

        if (dto.firstName() != null) reader.setFirstName(dto.firstName());
        if (dto.lastName() != null) reader.setLastName(dto.lastName());

        return readerRepository.save(reader);
    }

    @Transactional(readOnly = true)
    public Reader getReader(Long id) {
        return readerRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("Reader not found"));
    }

    public void deleteReader(Long id) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reader not found"));

        reader.setDeleted(true);
        readerRepository.save(reader);
    }

    public void restoreReader(Long id) {

        Reader reader = readerRepository.findByIdIncludingDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reader not found"));

        if (!reader.isDeleted()) {
            throw new IllegalStateException("Reader is not deleted");
        }

        reader.setDeleted(false);
    }
}

