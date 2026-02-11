package com.kodilla.library.controller;


import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.request.TitleRequestDto;
import com.kodilla.library.dto.response.TitleResponseDto;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.TitleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/titles")
public class TitleController {

    private final TitleService titleService;

    @PostMapping
    public TitleResponseDto addTitle(@Valid @RequestBody TitleRequestDto dto) {

        Title title = TitleMapper.toEntity(dto);
        Title saved = titleService.addTitle(title);

        return TitleMapper.toDto(saved);
    }

    @GetMapping
    public List<TitleResponseDto> getAllTitles() {

        return titleService.getAllTitles()
                .stream()
                .map(TitleMapper::toDto)
                .toList();
    }
}
