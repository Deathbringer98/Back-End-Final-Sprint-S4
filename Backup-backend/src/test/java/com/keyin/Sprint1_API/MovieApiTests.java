package com.keyin.Sprint1_API;

import com.keyin.Sprint1_API.Movie.Movie;
import com.keyin.Sprint1_API.Movie.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieApiTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MovieRepository movieRepository;

    private Movie testMovie;

    @BeforeEach
    public void setup() {
        // Initialize a test movie object
        testMovie = new Movie();
        testMovie.setTitle("Test Movie");
        testMovie.setGenre("Test Genre");
        testMovie.setRating(8.5);
        testMovie.setRuntime(120);
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testCreateMovie() throws Exception {
        String jsonContent = "{ \"title\": \"Test Movie\", \"genre\": \"Test Genre\", \"rating\": 8.5, \"runtime\": 120 }";

        mockMvc.perform(post("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isCreated())  // Expecting 201 Created
                .andExpect(jsonPath("$.title").value("Test Movie"))
                .andExpect(jsonPath("$.genre").value("Test Genre"))
                .andExpect(jsonPath("$.rating").value(8.5))
                .andExpect(jsonPath("$.runtime").value(120))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testReadMovie() throws Exception {
        // First, save a movie to the database
        Movie savedMovie = movieRepository.save(testMovie);

        // Then, try to retrieve it
        mockMvc.perform(get("/api/movies/{id}", savedMovie.getMovie_id()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(savedMovie.getTitle()))
                .andExpect(jsonPath("$.genre").value(savedMovie.getGenre()));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testUpdateMovie() throws Exception {
        // First, save a movie to the database
        Movie savedMovie = movieRepository.save(testMovie);

        // Update the movie details
        String jsonContent = "{ \"title\": \"Updated Movie\", \"genre\": \"Updated Genre\", \"rating\": 9.0, \"runtime\": 130 }";

        mockMvc.perform(put("/api/movies/{id}", savedMovie.getMovie_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())  // Expecting 200 OK
                .andExpect(jsonPath("$.title").value("Updated Movie"))
                .andExpect(jsonPath("$.genre").value("Updated Genre"))
                .andDo(print());
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testDeleteMovie() throws Exception {
        // First, save a movie to the database
        Movie savedMovie = movieRepository.save(testMovie);

        // Then, delete the movie
        mockMvc.perform(delete("/api/movies/{id}", savedMovie.getMovie_id()))
                .andExpect(status().isNoContent());  // Expecting 204 No Content

        // Verify the movie is deleted
        MvcResult result = mockMvc.perform(get("/api/movies/{id}", savedMovie.getMovie_id()))
                .andExpect(status().isNotFound())
                .andReturn();

        // Adjust the assertion based on the actual response body or check only the status code
        assertTrue(result.getResponse().getStatus() == 404);
    }
}
