package com.kodilla.library.mapper;


import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.request.TitleRequestDto;
import com.kodilla.library.dto.response.TitleResponseDto;

public class TitleMapper {

    public static Title toEntity(TitleRequestDto dto) {
        Title title = new Title();
        title.setTitle(dto.title());
        title.setAuthor(dto.author());
        title.setPublicationYear(dto.publicationYear());
        return title;
    }

    public static TitleResponseDto toDto(Title title) {
        return new TitleResponseDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublicationYear()
        );
    }
}
