package com.stackroute.Service;

import com.stackroute.Exeptions.MovieAlreadyExistException;
import com.stackroute.Exeptions.MovieNotFoundExection;
import com.stackroute.Repository.MovieRepository;
import com.stackroute.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Qualifier("impl2")
public class MovieServiceImpl2 implements MovieService{
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl2(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie saveMovie(Movie movie) throws MovieAlreadyExistException {
        if(movieRepository.existsById(movie.getImdbId())){
            throw new MovieAlreadyExistException("user already exist");
        }
        Movie saveMovie = movieRepository.save(movie);
        if(saveMovie == null){
            throw new MovieAlreadyExistException("user already exist");
        }
        return saveMovie;
    }

    @Override
    public List<Movie> viewMovie() {
        return (List)movieRepository.findAll();
    }

    @Override
    public Movie updateMovie(int id,Movie movie) throws MovieNotFoundExection {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundExection("content not found");
        }
        Movie updateMovie =movieRepository.findById(id).get();
        movieRepository.save(movie);
        return updateMovie;
    }

    @Override
    public Movie deleteMovie(int id) throws MovieNotFoundExection {
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundExection("nothing to delete");
        }
        Movie mov = movieRepository.findById(id).get();
        movieRepository.delete(mov);
        return mov;
    }

    @Override
    public Movie searchMovieById(int id) throws MovieNotFoundExection{
        if(!movieRepository.existsById(id)){
            throw new MovieNotFoundExection("No result");
        }
        Movie mov = movieRepository.findById(id).get();
        return mov;
    }

    @Override
    public Movie searchMovieByTitle(String movieTitle){
        Movie movie = movieRepository.findByMovieTitle(movieTitle);
        return movie;
    }
}
