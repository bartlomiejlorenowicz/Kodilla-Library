package com.kodilla.library.controller;

import com.kodilla.library.domain.Loan;
import com.kodilla.library.dto.request.BorrowRequestDto;
import com.kodilla.library.dto.response.LoanResponseDto;
import com.kodilla.library.mapper.LoanMapper;
import com.kodilla.library.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/borrow")
    public LoanResponseDto borrowBook(@Valid @RequestBody BorrowRequestDto dto) {

        Loan loan = loanService.borrowBook(dto.readerId(), dto.titleId());
        return LoanMapper.toDto(loan);
    }

    @PostMapping("/return/{loanId}")
    public LoanResponseDto returnBook(@PathVariable Long loanId) {

        Loan loan = loanService.returnBook(loanId);
        return LoanMapper.toDto(loan);
    }
}