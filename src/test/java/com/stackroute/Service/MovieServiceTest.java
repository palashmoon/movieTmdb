package com.stackroute.Service;

import com.stackroute.Exeptions.MovieAlreadyExistException;
import com.stackroute.Exeptions.MovieNotFoundExection;
import com.stackroute.Repository.MovieRepository;
import com.stackroute.Service.MovieServiceImpl;
import com.stackroute.domain.Movie;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie;

    //Create a mock for UserRepository
    @Mock//test double
            MovieRepository movieRepository;

    //Inject the mocks as dependencies into UserServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list = null;


    @Before
    public void setUp() {
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie.setImdbId(1);
        movie.setMovieTitle("inception");
        movie.setPosterUrl("Jenny");
        movie.setRating(10);
        movie.setYearOfRelease("2012");
        list = new ArrayList<>();
        list.add(movie);


    }

    @Test
    public void saveUserTestSuccess() throws MovieAlreadyExistException {

        when(movieRepository.save((Movie) any())).thenReturn(movie);
        Movie savedUser = movieService.saveMovie(movie);
        Assert.assertEquals(movie, savedUser);

        //verify here verifies that userRepository save method is only called once
        verify(movieRepository, times(1)).save(movie);

    }

    @Test(expected = MovieAlreadyExistException.class)
    public void saveUserTestFailure() throws MovieAlreadyExistException {
        when(movieRepository.save((Movie) any())).thenReturn(null);
        Movie savedUser = movieService.saveMovie(movie);
        System.out.println("savedUser" + savedUser);
        Assert.assertEquals(movie, savedUser);
//add verify
       /*doThrow(new UserAlreadyExistException()).when(userRepository).findById(eq(101));
       userService.saveUser(user);*/


    }

    @Test
    public void getAllUser() {

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> userlist = movieService.viewMovie();
        Assert.assertEquals(list, userlist);

        //add verify
    }

    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundExection {
        Movie mov1 = new Movie(2, "dhoni", "fgfhgfhgfh", 9, "2222");
        Movie mov2 = new Movie(3, "dhoni2", "fgfhgfhgfh", 9, "2222");
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        when(movieRepository.existsById(mov2.getImdbId())).thenReturn(true);
        when(movieRepository.findById(mov2.getImdbId())).thenReturn(Optional.of(mov2));
        Movie deleteMovieById = movieService.deleteMovie(3);
        System.out.println("sds" + deleteMovieById);
        Assert.assertEquals(mov2, deleteMovieById);
    }

    @Test
    public void searchMovieById() throws MovieNotFoundExection {
        Movie mov1 = new Movie(2, "dhoni", "fgfhgfhgfh", 9, "2222");
        Movie mov2 = new Movie(3, "dhoni2", "fgfhgfhgfh", 9, "2222");
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        when(movieRepository.existsById(mov1.getImdbId())).thenReturn(true);
        when(movieRepository.findById(mov1.getImdbId())).thenReturn(Optional.ofNullable(mov1));
        Movie searchMovieById = movieService.searchMovieById(2);
        System.out.println("sdsds:" + searchMovieById);
        Assert.assertEquals(mov1, searchMovieById);
    }

    @Test
    public void searchMovieByTitle() throws MovieNotFoundExection {
        Movie mov1 = new Movie(4, "Swadesh", "sscdsdss", 10, "2005");
        movieRepository.save(movie);
        movieRepository.save(mov1);
        when(movieRepository.findByMovieTitle(mov1.getMovieTitle())).thenReturn(mov1);
        Movie searchMovieByTitle = movieService.searchMovieByTitle("Swadesh");
        System.out.println("sds" + searchMovieByTitle);
        Assert.assertEquals(mov1, searchMovieByTitle);
    }


    @Test
    public void testUpdateById() throws MovieNotFoundExection {
        Movie mov1 = new Movie(1,"dhoni","fgfhgfhgfh",9,"2222");
        Movie mov2 = new Movie(2,"Kalho na ho","fgfhgfhgfh",9,"2222");
        Movie mov3 = new Movie(3,"Dhoom","fgfhgfhgfh",9,"2222");
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        movieRepository.save(mov3);
        mov1.setMovieTitle("Intern");
        when(movieRepository.existsById(mov1.getImdbId())).thenReturn(true);
        when(movieRepository.findById(mov1.getImdbId())).thenReturn(Optional.ofNullable(mov1));
        Movie updateById = movieService.updateMovie(mov1.getImdbId() , mov1);
        System.out.println("sds" + updateById);
        Assert.assertEquals(mov1 , updateById);
    }

}
