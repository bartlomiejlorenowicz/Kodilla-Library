package com.kodilla.library.service;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.CopyStatus;
import com.kodilla.library.domain.Loan;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.exception.BusinessException;
import com.kodilla.library.exception.NotFoundException;
import com.kodilla.library.repository.CopyRepository;
import com.kodilla.library.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final ReaderService readerService;
    private final CopyRepository copyRepository;

    public Loan borrowBook(Long readerId, Long titleId) {

        Reader reader = readerService.getReader(readerId);

        Copy copy = copyRepository
                .findByTitleIdAndStatus(titleId, CopyStatus.AVAILABLE)
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new BusinessException("No available copies"));

        copy.setStatus(CopyStatus.RENTED);

        Loan loan = new Loan();
        loan.setReader(reader);
        loan.setCopy(copy);
        loan.setBorrowedAt(LocalDate.now());

        return loanRepository.save(loan);
    }

    public Loan returnBook(Long loanId) {

        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new NotFoundException("Loan not found"));

        if (loan.getReturnedAt() != null) {
            throw new BusinessException("Already returned");
        }

        loan.setReturnedAt(LocalDate.now());
        loan.getCopy().setStatus(CopyStatus.AVAILABLE);

        return loan;
    }
}
