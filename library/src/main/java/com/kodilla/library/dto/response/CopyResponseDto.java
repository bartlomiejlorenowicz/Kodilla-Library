package com.kodilla.library.dto.response;

import com.kodilla.library.domain.CopyStatus;

public record CopyResponseDto(
        Long id,
        CopyStatus status,
        Long titleId
) {}
