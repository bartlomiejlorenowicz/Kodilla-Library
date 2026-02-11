package com.kodilla.library.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReaderRequestDto(
        @NotBlank
        @Size(min = 2)
        String firstName,

        @NotBlank
        String lastName,

        @NotNull
        LocalDate accountCreated
) {}
