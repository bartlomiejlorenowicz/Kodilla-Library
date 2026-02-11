package com.kodilla.library.repository;

import com.kodilla.library.domain.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
