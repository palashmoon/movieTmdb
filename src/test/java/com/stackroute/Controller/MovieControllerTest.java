package com.stackroute.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.Controller.MovieController;
import com.stackroute.Exeptions.MovieAlreadyExistException;
import com.stackroute.Service.MovieService;
import com.stackroute.Service.MovieServiceImpl;
import com.stackroute.domain.Movie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

//find out difference between @Mock and @MockBean

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {


    @Autowired
    private MockMvc mockMvc;
    private Movie movie;

    @MockBean
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
        movie = new Movie();
        movie.setImdbId(1);
        movie.setMovieTitle("inception");
        movie.setPosterUrl("Jenny");
        movie.setRating(10);
        movie.setYearOfRelease("2012");
        list = new ArrayList();

        list.add(movie);
    }

//    @After
//    public void tearDown(){
//
//        movieController.deleteMovie();
//    }

    @Test
    public void saveUser() throws Exception {
        when(movieService.saveMovie(any())).thenReturn(movie );
        mockMvc.perform(MockMvcRequestBuilders.post("/movie/v1/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void saveUserFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieAlreadyExistException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/movie/v1/save")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllUser() throws Exception {
        when(movieService.viewMovie()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/v1/view")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void updateMovie() throws Exception {
        when(movieService.updateMovie(anyInt() , eq(movie))).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/v1/update")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void deleteMovie() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/v1/delete")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void searchMovieById() throws Exception {
        when(movieService.searchMovieById(anyInt())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/v1/search")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    public void searchMovieByTitle() throws Exception {
        when(movieService.searchMovieByTitle(anyString())).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/movie/v1/movieTitle")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}