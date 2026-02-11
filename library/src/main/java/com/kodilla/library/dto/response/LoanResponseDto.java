package com.kodilla.library.dto.response;

import java.time.LocalDate;

public record LoanResponseDto(
        Long loanId,
        Long readerId,
        Long copyId,
        LocalDate rentDate,
        LocalDate returnDate
) {
}
