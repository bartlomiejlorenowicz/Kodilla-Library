package com.kodilla.library.service;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader addReader(Reader reader) {
        return readerRepository.save(reader);
    }

    public Reader getReader(Long id) {
        return readerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Reader not found"));
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }
}

