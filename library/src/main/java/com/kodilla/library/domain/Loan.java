package com.kodilla.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "copy_id")
    private Copy copy;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private Reader reader;

    private LocalDate borrowedAt;

    private LocalDate returnedAt;

    public Loan(Copy copy, Reader reader, LocalDate borrowedAt) {
        this.copy = copy;
        this.reader = reader;
        this.borrowedAt = borrowedAt;
    }
}
