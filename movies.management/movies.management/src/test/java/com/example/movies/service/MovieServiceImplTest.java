package com.example.movies.service;

import com.example.movies.entity.Movie;
import com.example.movies.exception.MovieNotFoundException;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.MovieServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.data.domain.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class MovieServiceImplTest {

    private MovieRepository repo;
    private MovieService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(MovieRepository.class);
        service = new MovieServiceImpl(repo);
    }

    @Test
    void findAll_returnsPage() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by("title"));
        Movie m = new Movie(); m.setTitle("Test");
        when(repo.findAll(pageable)).thenReturn(new PageImpl<>(List.of(m)));
        Page<Movie> page = service.findAll(pageable);
        assertEquals(1, page.getContent().size());
    }

    @Test
    void findById_notFound_throws() {
        when(repo.findById("x")).thenReturn(Optional.empty());
        assertThrows(MovieNotFoundException.class, () -> service.findById("x"));
    }

    @Test
    void create_setsIdAndSaves() {
        Movie m = new Movie(); m.setTitle("T");
        when(repo.save(any(Movie.class))).thenAnswer(inv -> inv.getArgument(0));
        Movie saved = service.create(m);
        assertNotNull(saved.getId());
    }
}
