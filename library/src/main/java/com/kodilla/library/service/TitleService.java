package com.kodilla.library.service;

import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.update.TitleUpdateDto;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Title updateTitle(Long id, TitleUpdateDto dto) {
        Title title = getTitle(id);

        if (dto.title() != null) title.setTitle(dto.title());
        if (dto.author() != null) title.setAuthor(dto.author());
        if (dto.publicationYear() != null) title.setPublicationYear(dto.publicationYear());

        return titleRepository.save(title);
    }

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }
}
