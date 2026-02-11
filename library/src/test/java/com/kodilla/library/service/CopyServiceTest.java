package com.kodilla.library.service;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.CopyStatus;
import com.kodilla.library.domain.Title;
import com.kodilla.library.repository.CopyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CopyServiceTest {

    @Mock
    private CopyRepository copyRepository;

    @Mock
    private TitleService titleService;

    @InjectMocks
    private CopyService copyService;

    @Test
    void shouldAddCopyWithAvailableStatus() {
        // given
        Long titleId = 1L;
        Title title = new Title();
        title.setId(titleId);

        when(titleService.getTitle(titleId)).thenReturn(title);
        when(copyRepository.save(any(Copy.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Copy result = copyService.addCopy(titleId);

        // then
        assertNotNull(result);
        assertEquals(CopyStatus.AVAILABLE, result.getStatus());
        assertEquals(title, result.getTitle());

        verify(copyRepository, times(1)).save(any(Copy.class));
    }

    @Test
    void shouldCountAvailableCopies() {
        // given
        Long titleId = 1L;

        Copy copy1 = new Copy();
        Copy copy2 = new Copy();

        when(copyRepository.findByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE))
                .thenReturn(List.of(copy1, copy2));

        // when
        long count = copyService.countAvailableCopies(titleId);

        // then
        assertEquals(2, count);
        verify(copyRepository, times(1))
                .findByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE);
    }
}