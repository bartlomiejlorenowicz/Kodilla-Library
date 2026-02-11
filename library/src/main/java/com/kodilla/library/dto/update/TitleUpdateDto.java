package com.kodilla.library.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record TitleUpdateDto(

        @Size(min = 3, message = "title must have at least 3 characters")
        String title,

        @Size(min = 3, message = "author must have at least 3 characters")
        String author,

        @Min(value = 1, message = "Publication year must be positive")
        Integer publicationYear
) {}
