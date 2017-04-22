package com.ignitesolutions.omdb.Models;

/**
 * Created by Mihir on 4/22/2017.
 */

public class GridItemMovies {
    String movieTitle;
    String moviePosterPath;
    String movieReleaseYear;
    String movieCast;
    String moviePlot;
    String movieID;

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMoviePosterPath() {
        return moviePosterPath;
    }

    public void setMoviePosterPath(String moviePosterPath) {
        this.moviePosterPath = moviePosterPath;
    }

    public String getMovieReleaseYear() {
        return movieReleaseYear;
    }

    public void setMovieReleaseYear(String movieReleaseYear) {
        this.movieReleaseYear = movieReleaseYear;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public String getMoviePlot() {
        return moviePlot;
    }

    public void setMoviePlot(String moviePlot) {
        this.moviePlot = moviePlot;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
}
