package com.keyin.Sprint1_API.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long movie_id) {
        return movieRepository.findById(movie_id);
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public void deleteMovie(Long movie_id) {
        movieRepository.deleteById(movie_id);
    }

    public Movie updateMovie(Long movie_id, Movie movieDetails) {
        Movie movie = movieRepository.findById(movie_id).orElseThrow(() -> new RuntimeException("Movie not found"));

        movie.setTitle(movieDetails.getTitle());
        movie.setRelease_year(movieDetails.getRelease_year());
        movie.setGenre(movieDetails.getGenre());
        movie.setDirector(movieDetails.getDirector());
        movie.setMain_actor(movieDetails.getMain_actor());
        movie.setRating(movieDetails.getRating());
        movie.setRuntime(movieDetails.getRuntime());

        return movieRepository.save(movie);
    }
}
