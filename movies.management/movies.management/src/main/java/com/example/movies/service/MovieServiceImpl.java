package com.example.movies.service;

import com.example.movies.entity.Movie;
import com.example.movies.exception.MovieNotFoundException;
import com.example.movies.repository.MovieRepository;
import com.example.movies.service.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository repo;

  

    public MovieServiceImpl(MovieRepository repo) {
		super();
		this.repo = repo;
	}



	@Override
    public Page<Movie> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public Movie findById(String id) {
        return repo.findById(id).orElseThrow(() -> new MovieNotFoundException(id));
    }

    @Override
    public Movie create(Movie movie) {
        movie.setId(null); // @PrePersist generates UUID
        return repo.save(movie);
    }

    @Override
    public Movie update(String id, Movie updated) {
        Movie existing = findById(id);
        existing.setTitle(updated.getTitle());
        existing.setDirector(updated.getDirector());
        existing.setReleaseYear(updated.getReleaseYear());
        existing.setGenre(updated.getGenre());
        existing.setRating(updated.getRating());
        return repo.save(existing);
    }

    @Override
    public void delete(String id) {
        Movie existing = findById(id);
        repo.delete(existing);
    }
}
