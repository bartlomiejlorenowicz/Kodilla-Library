package com.kodilla.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodilla.library.domain.Copy;
import com.kodilla.library.domain.Loan;
import com.kodilla.library.domain.Reader;
import com.kodilla.library.dto.request.BorrowRequestDto;
import com.kodilla.library.service.LoanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoanController.class)
class LoanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LoanService loanService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldBorrowBook() throws Exception {
        // given
        Reader reader = new Reader();
        reader.setId(1L);

        Copy copy = new Copy();
        copy.setId(10L);

        Loan loan = new Loan();
        loan.setId(100L);
        loan.setReader(reader);
        loan.setCopy(copy);
        loan.setBorrowedAt(LocalDate.now());

        when(loanService.borrowBook(1L, 2L)).thenReturn(loan);

        BorrowRequestDto request = new BorrowRequestDto(1L, 2L);

        // when + then
        mockMvc.perform(post("/api/loans/borrow")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").value(100L));
    }

    @Test
    void shouldReturnBook() throws Exception {
        // given
        Reader reader = new Reader();
        reader.setId(1L);

        Copy copy = new Copy();
        copy.setId(10L);

        Loan loan = new Loan();
        loan.setId(100L);
        loan.setReader(reader);
        loan.setCopy(copy);
        loan.setBorrowedAt(LocalDate.now());
        loan.setReturnedAt(LocalDate.now());

        when(loanService.returnBook(anyLong())).thenReturn(loan);

        // when + then
        mockMvc.perform(post("/api/loans/return/100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").value(100L));
    }

}