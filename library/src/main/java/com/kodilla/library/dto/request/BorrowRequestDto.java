package com.kodilla.library.dto.request;

import jakarta.validation.constraints.NotNull;

public record BorrowRequestDto(
        @NotNull
        Long readerId,

        @NotNull
        Long titleId
) {}
