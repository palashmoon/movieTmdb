package com.stackroute.Repository;


import com.stackroute.domain.Movie;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends MongoRepository<Movie,Integer> {
   public Movie findByMovieTitle(String movieTitle);
}