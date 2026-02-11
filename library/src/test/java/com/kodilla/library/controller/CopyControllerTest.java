package com.kodilla.library.controller;

import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.CopyStatus;
import com.kodilla.library.domain.Title;
import com.kodilla.library.service.CopyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CopyController.class)
class CopyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CopyService copyService;

    @Test
    void shouldAddCopy() throws Exception {
        // given
        Title title = new Title();
        title.setId(1L);

        Copy copy = new Copy();
        copy.setId(10L);
        copy.setTitle(title);
        copy.setStatus(CopyStatus.AVAILABLE);

        when(copyService.addCopy(1L)).thenReturn(copy);

        // when + then
        mockMvc.perform(post("/api/copies/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.titleId").value(1L));
    }

    @Test
    void shouldReturnAvailableCopiesCount() throws Exception {
        // given
        when(copyService.countAvailableCopies(1L)).thenReturn(5L);

        // when + then
        mockMvc.perform(get("/api/copies/available/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titleId").value(1L))
                .andExpect(jsonPath("$.availableCopies").value(5));
    }
}