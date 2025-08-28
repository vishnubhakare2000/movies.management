package com.example.movies.service;

import com.example.movies.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {
    Page<Movie> findAll(Pageable pageable);
    Movie findById(String id);
    Movie create(Movie movie);
    Movie update(String id, Movie movie);
    void delete(String id);
}
