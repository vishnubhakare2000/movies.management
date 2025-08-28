package com.example.movies.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.UUID;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;

    private String director;

    @Min(value = 1888, message = "Release year seems invalid")
    private Integer releaseYear;

    private String genre;

    @Min(value = 1, message = "Rating must be between 1 and 10")
    @Max(value = 10, message = "Rating must be between 1 and 10")
    private Integer rating;

    @PrePersist
    public void ensureId() {
        if (this.id == null || this.id.isBlank()) {
            this.id = UUID.randomUUID().toString();
        }
    }

    // getters & setters (Lombok वापरत असाल तर @Data/@Getter/@Setter वापरा)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}
