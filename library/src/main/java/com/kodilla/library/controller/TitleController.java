package com.kodilla.library.controller;


import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.request.TitleRequestDto;
import com.kodilla.library.dto.response.TitleResponseDto;
import com.kodilla.library.dto.update.TitleUpdateDto;
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

    @GetMapping("/{id}")
    public TitleResponseDto getTitleById(@PathVariable Long id) {

        Title title = titleService.getTitle(id);
        return TitleMapper.toDto(title);
    }

    @PatchMapping("/{id}")
    public TitleResponseDto updateTitle(@PathVariable Long id,
                                        @RequestBody TitleUpdateDto dto) {

        Title updated = titleService.updateTitle(id, dto);
        return TitleMapper.toDto(updated);
    }
}
