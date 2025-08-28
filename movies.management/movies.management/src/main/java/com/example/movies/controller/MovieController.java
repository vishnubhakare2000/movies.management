package com.example.movies.controller;

import com.example.movies.entity.Movie;
import com.example.movies.service.MovieService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService service;
    public MovieController(MovieService service) { this.service = service; }

    // GET /movies?page=0&size=10&sort=title,asc
    @GetMapping
    public ResponseEntity<Page<Movie>> list(@ParameterObject Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    // GET /movies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getById(@PathVariable String id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // POST /movies
    @PostMapping
    public ResponseEntity<Movie> create(@Valid @RequestBody Movie movie) {
        Movie saved = service.create(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // PUT /movies/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Movie> update(@PathVariable String id, @Valid @RequestBody Movie movie) {
        return ResponseEntity.ok(service.update(id, movie));
    }

    // DELETE /movies/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
