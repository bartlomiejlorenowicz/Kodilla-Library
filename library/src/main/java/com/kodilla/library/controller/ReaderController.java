package com.kodilla.library.controller;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.domain.ReaderDto;
import com.kodilla.library.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping
    public Reader addReader(@Valid @RequestBody ReaderDto readerDto) {
        Reader reader = new Reader();
        reader.setFirstName(readerDto.firstName());
        reader.setLastName(readerDto.lastName());
        reader.setAccountCreatedAt(readerDto.accountCreated());

        return readerService.addReader(reader);
    }
}
