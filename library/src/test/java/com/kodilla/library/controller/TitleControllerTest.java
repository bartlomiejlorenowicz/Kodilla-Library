package com.kodilla.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.library.domain.Title;
import com.kodilla.library.dto.request.TitleRequestDto;
import com.kodilla.library.service.TitleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TitleController.class)
class TitleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TitleService titleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateTitle() throws Exception {
        // given
        Title title = new Title();
        title.setId(1L);
        title.setTitle("1984");
        title.setAuthor("Orwell");
        title.setPublicationYear(1949);

        when(titleService.addTitle(any(Title.class)))
                .thenReturn(title);

        TitleRequestDto request =
                new TitleRequestDto("1984", "Orwell", 1949);

        // when + then
        mockMvc.perform(post("/api/titles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("1984"))
                .andExpect(jsonPath("$.author").value("Orwell"));
    }

    @Test
    void shouldReturnAllTitles() throws Exception {
        // given
        Title title = new Title();
        title.setId(1L);
        title.setTitle("1984");
        title.setAuthor("Orwell");

        when(titleService.getAllTitles())
                .thenReturn(List.of(title));

        // when + then
        mockMvc.perform(get("/api/titles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("1984"));
    }

    @Test
    void shouldReturnTitleById() throws Exception {
        // given
        Title title = new Title();
        title.setId(1L);
        title.setTitle("1984");

        when(titleService.getTitle(1L))
                .thenReturn(title);

        // when + then
        mockMvc.perform(get("/api/titles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

}