package com.stackroute.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;



@Document(collection = "movies")
public class Movie {
    @Id

    @NotNull @Min(0) @PositiveOrZero
    int imdbId;

    @NotNull @Size(min =1  , max = 60)
    String movieTitle;

    @NotNull @Size(min =  1 , max = 60)
    String posterUrl;

    @NotNull  @Min(0) @PositiveOrZero
    int rating;

    @NotNull @Size(min = 1 , max = 60)
    String yearOfRelease;

    public Movie() {
    }

    public Movie(int imdbId, String movieTitle, String posterUrl, int rating, String yearOfRelease) {
        this.imdbId = imdbId;
        this.movieTitle = movieTitle;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.yearOfRelease = yearOfRelease;
    }

    public int getImdbId() {
        return imdbId;
    }

    public void setImdbId(int imdbId) {
        this.imdbId = imdbId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(String yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "imdbId=" + imdbId +
                ", MovieTitle='" + movieTitle + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                ", rating=" + rating +
                ", yearOfRelease='" + yearOfRelease + '\'' +
                '}';
    }
}
