package com.kodilla.library.controller;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.domain.ReaderDto;
import com.kodilla.library.dto.request.ReaderRequestDto;
import com.kodilla.library.dto.response.ReaderResponseDto;
import com.kodilla.library.mapper.ReaderMapper;
import com.kodilla.library.service.ReaderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/readers")
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping
    public ReaderResponseDto addReader(@Valid @RequestBody ReaderRequestDto readerDto) {
        Reader reader = ReaderMapper.toEntity(readerDto);
        Reader saved = readerService.addReader(reader);

        return ReaderMapper.toDto(saved);
    }

    @GetMapping
    public List<ReaderResponseDto> getAllReaders() {

        return readerService.getAllReaders()
                .stream()
                .map(ReaderMapper::toDto)
                .toList();
    }

}
