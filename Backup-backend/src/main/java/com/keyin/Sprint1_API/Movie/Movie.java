package com.keyin.Sprint1_API.Movie;

import jakarta.persistence.*;


@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 500)
    private String title;

    @Column(name = "genre", length = 500)
    private String genre;

    @Column(name = "director", length = 500)
    private String director;

    @Column(name = "actor", length = 500)
    private String actor;

    @Column(name = "release_year")
    private int releaseYear;

    // Constructors
    public Movie() {}

    public Movie(String title, String genre, String director, String actor, int releaseYear) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.actor = actor;
        this.releaseYear = releaseYear;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
