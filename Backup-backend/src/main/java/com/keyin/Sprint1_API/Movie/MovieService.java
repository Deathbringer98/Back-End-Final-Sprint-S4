package com.keyin.Sprint1_API.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
        logger.info("Fetching all movies");
        return movieRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Movie> getMovieById(Long movie_id) {
        logger.info("Fetching movie by ID: {}", movie_id);
        return movieRepository.findById(movie_id);
    }

    @Transactional
    public Movie addMovie(Movie movie) {
        logger.info("Adding new movie: {}", movie.getTitle());
        return movieRepository.save(movie);
    }

    @Transactional
    public void deleteMovie(Long movie_id) {
        logger.info("Deleting movie with ID: {}", movie_id);
        if (!movieRepository.existsById(movie_id)) {
            throw new MovieNotFoundException(movie_id);
        }
        movieRepository.deleteById(movie_id);
    }

    @Transactional
    public Movie updateMovie(Long movie_id, Movie movieDetails) {
        logger.info("Updating movie with ID: {}", movie_id);
        Movie movie = movieRepository.findById(movie_id)
                .orElseThrow(() -> new MovieNotFoundException(movie_id));

        movie.setTitle(movieDetails.getTitle());
        movie.setRelease_year(movieDetails.getRelease_year());
        movie.setGenre(movieDetails.getGenre());
        movie.setDirector(movieDetails.getDirector());
        movie.setMain_actor(movieDetails.getMain_actor());
        movie.setRating(movieDetails.getRating());
        movie.setRuntime(movieDetails.getRuntime());

        return movieRepository.save(movie);
    }

    @Transactional(readOnly = true)
    public List<Movie> searchMovies(String query) {
        logger.info("Searching movies with query: {}", query);
        return movieRepository.findByTitleContainingIgnoreCase(query);
    }
}
