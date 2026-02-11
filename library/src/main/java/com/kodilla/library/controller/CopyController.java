package com.kodilla.library.controller;

import com.kodilla.library.service.CopyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/copies")
public class CopyController {

    private final CopyService copyService;

    @GetMapping("/available/{titleId}")
    public long countAvailableCopies(@PathVariable Long titleId) {
        return copyService.countAvailableCopies(titleId);
    }
}