package com.stackroute.Service;

import com.stackroute.Exeptions.MovieAlreadyExistException;
import com.stackroute.Exeptions.MovieNotFoundExection;
import com.stackroute.domain.Movie;

import java.util.List;
//import com.sun.tools.javac.util.List;

public interface MovieService {
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistException;
    public List<Movie> viewMovie();
    public Movie updateMovie(int id,Movie movie) throws MovieNotFoundExection;
    public Movie deleteMovie(int id) throws MovieNotFoundExection;
   // public List<Movie> searchMovie();
    public Movie searchMovieById(int id) throws MovieNotFoundExection;
    public Movie searchMovieByTitle(String movieTitle);
}
