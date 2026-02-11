package com.kodilla.library.dto.update;

import jakarta.validation.constraints.Size;

public record ReaderUpdateDto(
        @Size(min = 3, message = "First name must have at least 3 characters")
        String firstName,

        @Size(min = 3, message = "Last name must have at least 3 characters")
        String lastName
) {}
