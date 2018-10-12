package com.stackroute.Repository;


import com.stackroute.Repository.MovieRepository;
import com.stackroute.domain.Movie;
//import org.graalvm.compiler.lir.sparc.SPARCMove;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;


//This is integrated test we need DB server
@RunWith(SpringRunner.class)
@DataMongoTest
//@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setImdbId(1);
        movie.setMovieTitle("Jhony");
        movie.setPosterUrl("Jenny");
        movie.setRating(10);
        movie.setYearOfRelease("2012");

    }

    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }


    @Test
    public void testSaveUser(){
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertEquals(1,fetchUser.getImdbId());

    }

    @Test
    public void testSaveUserFailure(){
        Movie testUser = new Movie(1,"john","palash.com",10,"2011");
        movieRepository.save(movie);
        Movie fetchUser = movieRepository.findById(movie.getImdbId()).get();
        Assert.assertNotSame(movie,fetchUser);
    }

    @Test
    public void testGetAllUser(){
        Movie u = new Movie(0,"Johny","kevinshah.com",9,"2013");
        Movie u1 = new Movie(3,"Johny1","jfkds",9,"2028");
        movieRepository.save(u);
        movieRepository.save(u1);

        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("Johny",list.get(0).getMovieTitle());
        Assert.assertEquals("Johny1" , list.get(1).getMovieTitle());
    }

    @Test
    public void testSearch(){
        Movie mov1 = new Movie(4, "fight club" , "1st rule of fight club is you dont talk about fight club" , 8 , "1998");
        Movie mov2 = new Movie(2, "Inception" , "1st rule of fight club is you dont talk about fight club" , 8 , "1999");
        movieRepository.save(mov1);
        Movie fetchmovie = movieRepository.findById(mov1.getImdbId()).get();
        Assert.assertEquals(4 , fetchmovie.getImdbId());
    }

    @Test
    public void testSearchByTitle(){
        Movie mov1 = new Movie(1,"dhoni","fgfhgfhgfh",9,"2222");
        Movie mov2 = new Movie(2,"Kalho na ho","fgfhgfhgfh",9,"2222");
        Movie mov3 = new Movie(3,"Dhoom","fgfhgfhgfh",9,"2222");
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        movieRepository.save(mov3);
        Movie fetcmovie = movieRepository.findByMovieTitle(mov1.getMovieTitle());
        Assert.assertEquals("Kalho na ho" ,mov2.getMovieTitle());
    }

    @Test
    public void testUpdateById(){
        Movie mov1 = new Movie(1,"dhoni","fgfhgfhgfh",9,"2222");
        Movie mov2 = new Movie(2,"Kalho na ho","fgfhgfhgfh",9,"2222");
        Movie mov3 = new Movie(3,"Dhoom","fgfhgfhgfh",9,"2222");
        movieRepository.save(mov1);
        movieRepository.save(mov2);
        movieRepository.save(mov3);
        mov1.setMovieTitle("Intern");
        Movie fetchmovie = movieRepository.findById(mov1.getImdbId()).get();
        System.out.println("sdd" + mov1);
        Assert.assertEquals("Intern" , mov1.getMovieTitle());
    }

    @Test
    public void testDeleteById(){
        movieRepository.save(movie);
        movieRepository.delete(movie);
        Assert.assertEquals(Optional.empty() , movieRepository.findById(movie.getImdbId()));
    }

}