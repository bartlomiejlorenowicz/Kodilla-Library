package com.kodilla.library.service;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.CopyStatus;
import com.kodilla.library.domain.Loan;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.exception.BusinessException;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.LoanRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private ReaderService readerService;

    @Mock
    private CopyRepository copyRepository;

    @InjectMocks
    private LoanService loanService;

    @Test
    void shouldBorrowBookWhenCopyAvailable() {
        // given
        Long readerId = 1L;
        Long titleId = 10L;

        Reader reader = new Reader();
        reader.setId(readerId);

        Copy copy = new Copy();
        copy.setId(100L);
        copy.setStatus(CopyStatus.AVAILABLE);

        when(readerService.getReader(readerId)).thenReturn(reader);
        when(copyRepository.findByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE))
                .thenReturn(List.of(copy));

        when(loanRepository.save(any(Loan.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        Loan result = loanService.borrowBook(readerId, titleId);

        // then
        assertNotNull(result);
        assertEquals(reader, result.getReader());
        assertEquals(copy, result.getCopy());
        assertNotNull(result.getBorrowedAt());
        assertEquals(CopyStatus.RENTED, copy.getStatus());

        verify(loanRepository, times(1)).save(any(Loan.class));
    }

    @Test
    void shouldThrowBusinessExceptionWhenNoAvailableCopies() {
        // given
        Long readerId = 1L;
        Long titleId = 10L;

        Reader reader = new Reader();
        when(readerService.getReader(readerId)).thenReturn(reader);
        when(copyRepository.findByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE))
                .thenReturn(List.of());

        // when + then
        BusinessException ex = assertThrows(BusinessException.class,
                () -> loanService.borrowBook(readerId, titleId));

        assertEquals("No available copies", ex.getMessage());
        verify(loanRepository, never()).save(any());
    }

    @Test
    void shouldReturnBookAndSetCopyAvailable() {
        // given
        Long loanId = 1L;

        Copy copy = new Copy();
        copy.setStatus(CopyStatus.RENTED);

        Loan loan = new Loan();
        loan.setId(loanId);
        loan.setCopy(copy);
        loan.setBorrowedAt(LocalDate.now().minusDays(2));
        loan.setReturnedAt(null);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        // when
        Loan result = loanService.returnBook(loanId);

        // then
        assertNotNull(result.getReturnedAt());
        assertEquals(CopyStatus.AVAILABLE, result.getCopy().getStatus());
        verify(loanRepository, times(1)).findById(loanId);
    }

    @Test
    void shouldThrowNotFoundExceptionWhenLoanNotExists() {
        // given
        Long loanId = 999L;
        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        // when + then
        NotFoundException ex = assertThrows(NotFoundException.class,
                () -> loanService.returnBook(loanId));

        assertEquals("Loan not found", ex.getMessage());
    }
}