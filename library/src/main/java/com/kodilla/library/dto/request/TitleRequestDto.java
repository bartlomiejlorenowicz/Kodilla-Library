package com.kodilla.library.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TitleRequestDto(
        @NotBlank
        String title,

        @NotBlank
        String author,

        @NotNull
        @Positive
        Integer publicationYear
) {}
