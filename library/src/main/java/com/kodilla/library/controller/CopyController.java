package com.kodilla.library.controller;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.dto.response.AvailableCopiesDto;
import com.kodilla.library.dto.response.CopyResponseDto;
import com.kodilla.library.mapper.CopyMapper;
import com.kodilla.library.service.CopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/copies")
public class CopyController {

    private final CopyService copyService;

    @PostMapping("/{titleId}")
    public CopyResponseDto addCopy(@PathVariable Long titleId) {

        Copy copy = copyService.addCopy(titleId);
        return CopyMapper.toDto(copy);
    }

    @GetMapping("/available/{titleId}")
    public AvailableCopiesDto countAvailableCopies(@PathVariable Long titleId) {

        long count = copyService.countAvailableCopies(titleId);
        return new AvailableCopiesDto(titleId, count);
    }
}