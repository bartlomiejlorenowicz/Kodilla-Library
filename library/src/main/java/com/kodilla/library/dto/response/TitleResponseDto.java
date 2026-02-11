package com.kodilla.library.dto.response;

public record TitleResponseDto(

       Long id,
       String title,
       String author,
       Integer publicationYear
)
{}
