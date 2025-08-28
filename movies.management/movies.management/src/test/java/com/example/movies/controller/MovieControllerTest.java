package com.example.movies.controller;

import com.example.movies.entity.Movie;
import com.example.movies.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @MockBean MovieService service;

    @Test
    void list_returns200() throws Exception {
        Movie m = new Movie(); m.setTitle("Inception");
        Page<Movie> page = new PageImpl<>(List.of(m));
        when(service.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/movies"))
               .andExpect(status().isOk());
    }

    @Test
    void create_validationError_returns400() throws Exception {
        // title required — रिकामं पाठवलं तर 400
        Movie m = new Movie(); m.setTitle(""); // invalid
        mockMvc.perform(post("/movies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(m)))
               .andExpect(status().isBadRequest());
    }
}
