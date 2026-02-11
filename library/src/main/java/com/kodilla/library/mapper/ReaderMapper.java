package com.kodilla.library.mapper;

import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.request.ReaderRequestDto;
import com.kodilla.library.dto.response.ReaderResponseDto;

public class ReaderMapper {

    public static Reader toEntity(ReaderRequestDto dto) {
        Reader reader = new Reader();
        reader.setFirstName(dto.firstName());
        reader.setLastName(dto.lastName());
        reader.setAccountCreatedAt(dto.accountCreated());
        return reader;
    }

    public static ReaderResponseDto toDto(Reader reader) {
        return new ReaderResponseDto(
                reader.getId(),
                reader.getFirstName(),
                reader.getLastName(),
                reader.getAccountCreatedAt()
        );
    }
}
