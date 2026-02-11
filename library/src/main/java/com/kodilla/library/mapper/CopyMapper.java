package com.kodilla.library.mapper;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.dto.response.CopyResponseDto;

public class CopyMapper {

    public static CopyResponseDto toDto(Copy copy) {
        return new CopyResponseDto(
                copy.getId(),
                copy.getStatus(),
                copy.getTitle().getId()
        );
    }
}
