package com.stackroute.Controller;


import com.stackroute.Exeptions.MovieAlreadyExistException;
import com.stackroute.Exeptions.MovieNotFoundExection;
import com.stackroute.Service.MovieService;
import com.stackroute.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "movie/v1")

public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    @PostMapping("save")
    public ResponseEntity<?>saveMovie( @RequestBody Movie movie){
        ResponseEntity responseEntity;
        try {
            movieService.saveMovie(movie);
            responseEntity = new ResponseEntity<Movie>(movie , HttpStatus.CREATED);
        }
        catch (MovieAlreadyExistException ex){
            responseEntity = new ResponseEntity(ex.getMessage() , HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("view")
    public ResponseEntity<?>viewMovie(){
//        return new ResponseEntity<List<Movie>>(movieService.viewMovie() , HttpStatus.OK);
        ResponseEntity responseEntity;
        try {
            //movieService.viewMovie();
            responseEntity = new ResponseEntity<List<Movie>>(movieService.viewMovie() ,HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity(ex.getMessage() , HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?>updateMovie(@PathVariable("id") int id,@RequestBody Movie movie){
        ResponseEntity responseEntity;
        try{
            movieService.updateMovie(id,movie);
            responseEntity = new ResponseEntity<>("updated" , HttpStatus.OK);
        }
        catch (MovieNotFoundExection ex){
            responseEntity = new ResponseEntity(ex.getMessage() ,HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>deleteMovie(@PathVariable("id") int id){
        ResponseEntity responseEntity;
        try{
            movieService.deleteMovie(id);
            responseEntity = new ResponseEntity<>("deleted", HttpStatus.OK);
        }
        catch (MovieNotFoundExection ex){
            responseEntity = new ResponseEntity(ex.getMessage() , HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("search/{id}")
    public ResponseEntity<?>searchMovieById(@PathVariable("id") int id) {
        ResponseEntity responseEntity;
        //Movie movie=movieService.searchMovie(id);
        // responseEntity = new ResponseEntity<>( "found", HttpStatus.OK);
        try {
            Movie mov = movieService.searchMovieById(id);
            responseEntity = new ResponseEntity<Movie>(mov, HttpStatus.OK);
        } catch (MovieNotFoundExection ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @GetMapping("{movieTitle}")
    public ResponseEntity<?>searchMovieByTitle(@PathVariable("movieTitle") String movieTitle){
        ResponseEntity responseEntity;
        try {
            Movie movie = movieService.searchMovieByTitle(movieTitle);
            responseEntity = new ResponseEntity<Movie>(movie , HttpStatus.OK);
        }
        catch (Exception ex){
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }
}
