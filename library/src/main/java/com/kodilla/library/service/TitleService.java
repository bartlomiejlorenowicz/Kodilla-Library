package com.kodilla.library.service;

import com.kodilla.library.domain.Title;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TitleService {

    private final TitleRepository titleRepository;

    public Title addTitle(Title title) {
        return titleRepository.save(title);
    }

    public Title getTitle(Long id) {
        return titleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Title not found"));
    }
}
