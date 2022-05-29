package com.example.movieratingsoftware.Controller;

import com.example.movieratingsoftware.Model.Movie;
import com.example.movieratingsoftware.Service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    /**
     * Get all the domain data.
     */
    @GetMapping()
    public ResponseEntity<List> getAllMovie(){
        return  ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovie());
    }
    /**
     * Get a specific data by passing an id.
     * @param id id of the data
     */
    @GetMapping("{id}")
    ResponseEntity<Object> getMovieByID(@PathVariable Integer id){
        if (!movieService.isMovieByID(id)){
            System.out.println("I was here1");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("Not Found, no ride with that id", HttpStatus.BAD_REQUEST));
        }
        System.out.println("I was here2");
        return  ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieById(id));
    }
    /**
     * Add new data.
     * @param movie date to be added
     * @param errors errors if any found from the date validation
     */
    @PostMapping()
    ResponseEntity<Api> addMovie(@RequestBody @Valid Movie movie, Errors errors){
        try {
            check(errors);
            return (movieService.addMovie(movie)) ? ResponseEntity.status(HttpStatus.CREATED).body(new Api("Adding was successful!", HttpStatus.CREATED)) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Adding was NOT successful!!", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @PutMapping("{id}")
    ResponseEntity<Api> updateMovie(@RequestBody @Valid Movie movie, Errors errors){
        try {
            check(errors);
            return (movieService.addMovie(movie)) ? ResponseEntity.status(HttpStatus.CREATED).body(new Api("Update was successful!", HttpStatus.CREATED)) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Adding was NOT successful!!", HttpStatus.INTERNAL_SERVER_ERROR));
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api(e.getMessage(), HttpStatus.BAD_REQUEST));
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Api> removeMovie(@PathVariable String id){
        if (!movieService.isMovieByID(Integer.valueOf(id))){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Api("no movie with that id", HttpStatus.BAD_REQUEST));
        }
        return (movieService.removeMovie(id)) ? ResponseEntity.status(HttpStatus.CREATED).body(new Api("Deleting was successful!", HttpStatus.OK)) : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Api("Adding was NOT successful!!", HttpStatus.INTERNAL_SERVER_ERROR));
    }


    /**
     * Checks if they are any errors from the given errors class, if any error found throw the appropriate message.
     * @param errors
     */
    public void check(Errors errors) throws IllegalArgumentException{
        if (errors.hasErrors()){
            String error = errors.getFieldError().getDefaultMessage();
            throw new IllegalArgumentException(error);
        }
    }
}
