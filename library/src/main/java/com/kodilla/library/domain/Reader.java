package com.kodilla.library.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate accountCreatedAt;

    public Reader(String firstName, String lastName, LocalDate accountCreatedAt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreatedAt = accountCreatedAt;
    }
}
