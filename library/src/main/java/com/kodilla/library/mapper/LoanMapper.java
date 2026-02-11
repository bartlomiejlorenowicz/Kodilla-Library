package com.kodilla.library.mapper;

import com.kodilla.library.domain.Loan;
import com.kodilla.library.dto.response.LoanResponseDto;

public class LoanMapper {

    public static LoanResponseDto toDto(Loan loan) {
        return new LoanResponseDto(
                loan.getId(),
                loan.getReader().getId(),
                loan.getCopy().getId(),
                loan.getBorrowedAt(),
                loan.getReturnedAt()
        );
    }
}
