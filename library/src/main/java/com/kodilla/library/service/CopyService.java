package com.kodilla.library.service;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.CopyStatus;
import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CopyService {

    private final CopyRepository copyRepository;
    private final TitleService titleService;

    public Copy addCopy(Long titleId) {
        Title title = titleService.getTitle(titleId);

        Copy copy = new Copy();
        copy.setTitle(title);
        copy.setStatus(CopyStatus.AVAILABLE);

        return copyRepository.save(copy);
    }

    public long countAvailableCopies(Long titleId) {
        return copyRepository
                .findByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE)
                .size();
    }
}

