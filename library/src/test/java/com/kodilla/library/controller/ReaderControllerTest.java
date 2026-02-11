package com.kodilla.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.request.ReaderRequestDto;
import com.kodilla.library.service.ReaderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReaderController.class)
class ReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ReaderService readerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateReader() throws Exception {
        // given
        Reader reader = new Reader();
        reader.setId(1L);
        reader.setFirstName("Jan");
        reader.setLastName("Kowalski");
        reader.setAccountCreatedAt(LocalDate.now());

        when(readerService.addReader(any(Reader.class)))
                .thenReturn(reader);

        ReaderRequestDto request =
                new ReaderRequestDto("Jan", "Kowalski");

        // when + then
        mockMvc.perform(post("/api/readers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Jan"))
                .andExpect(jsonPath("$.lastName").value("Kowalski"));
    }

    @Test
    void shouldReturnAllReaders() throws Exception {
        // given
        Reader reader = new Reader();
        reader.setId(1L);
        reader.setFirstName("Jan");
        reader.setLastName("Kowalski");

        when(readerService.getAllReaders())
                .thenReturn(List.of(reader));

        // when + then
        mockMvc.perform(get("/api/readers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].firstName").value("Jan"));
    }

    @Test
    void shouldReturnReaderById() throws Exception {
        // given
        Reader reader = new Reader();
        reader.setId(1L);
        reader.setFirstName("Jan");
        reader.setLastName("Kowalski");

        when(readerService.getReader(1L))
                .thenReturn(reader);

        // when + then
        mockMvc.perform(get("/api/readers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void shouldDeleteReader() throws Exception {
        mockMvc.perform(delete("/api/readers/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldRestoreReader() throws Exception {
        mockMvc.perform(patch("/api/readers/1/restore"))
                .andExpect(status().isNoContent());
    }
}