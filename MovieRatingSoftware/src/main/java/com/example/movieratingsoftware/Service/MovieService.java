package com.example.movieratingsoftware.Service;

import com.example.movieratingsoftware.Controller.Api;
import com.example.movieratingsoftware.Model.Movie;
import com.example.movieratingsoftware.Repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovie() {
        return movieRepository.findAll();
    }

    public boolean isMovieByID(Integer id) {
        return movieRepository.existsById(id);
    }

    public Movie getMovieById(Integer id) {
        return movieRepository.findById(id).get();
    }

    public boolean addMovie(Movie movie) {
         movieRepository.save(movie);
         return true;
    }

    public boolean updateMovie(Integer id) {

        return true;
    }

    public boolean removeMovie(String id) {
        movieRepository.delete(getMovieById(Integer.valueOf(id)));
        return true;
    }
}
