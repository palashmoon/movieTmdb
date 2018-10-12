package com.stackroute;

import com.stackroute.Repository.MovieRepository;
import com.stackroute.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MovieTmdbApplication {




//		@Autowired
//		private MovieRepository movieRepository;
//		@Override
//		public void onApplicationEvent(ContextRefreshedEvent event) {
//			Movie movie = new Movie(5,"rahul","comments1",1,"a");
//			movieRepository.save(movie);
//			Movie movie2 = new Movie(6,"dravid","comments2",3,"b");
//			movieRepository.save(movie2);
//		}
//		@Override
//		public void run(String... args){
//			Movie movie = new Movie(7,"Lionel","comments3",4,"c");
//			movieRepository.save(movie);
//			Movie movie2 = new Movie(8,"messi","comments4",6,"d");
//			movieRepository.save(movie2);
//		}
	public static void main(String[] args) {
		SpringApplication.run(MovieTmdbApplication.class, args);
	}
}
