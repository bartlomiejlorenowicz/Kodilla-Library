package com.kodilla.library.controller;

import com.kodilla.library.domain.Loan;
import com.kodilla.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/borrow")
    public Loan borrowBook(@RequestParam Long readerId,
                           @RequestParam Long titleId) {
        return loanService.borrowBook(readerId, titleId);
    }

    @PostMapping("/return/{loanId}")
    public Loan returnBook(@PathVariable Long loanId) {
        return loanService.returnBook(loanId);
    }
}
