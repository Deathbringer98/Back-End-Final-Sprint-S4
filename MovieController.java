package com.keyin.Sprint1_API.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movie_id) {
        Optional<Movie> movie = movieService.getMovieById(movie_id);
        if (movie.isPresent()) {
            return ResponseEntity.ok().body(movie.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movie_id, @RequestBody Movie movieDetails) {
        Movie updatedMovie = movieService.updateMovie(movie_id, movieDetails);
        return ResponseEntity.ok(updatedMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable(value = "id") Long movie_id) {
        movieService.deleteMovie(movie_id);
        return ResponseEntity.ok().build();
    }
}
