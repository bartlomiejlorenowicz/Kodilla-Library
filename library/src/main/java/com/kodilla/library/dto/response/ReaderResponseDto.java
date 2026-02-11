package com.kodilla.library.dto.response;

import java.time.LocalDate;

public record ReaderResponseDto(
        Long id,
        String firstName,
        String lastName,
        LocalDate accountCreated
) {}
