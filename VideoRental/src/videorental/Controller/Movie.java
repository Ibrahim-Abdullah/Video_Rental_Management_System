/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

/**
 * This is model a movie Object with its properties. 
 * It has a instance variables MovieID, Title,Year Released,Genre,Rating and Director of the movie.
 * 
 * @author Ibrahim-Abdullah
 * 
 */
public class Movie {
    private int ID;
    private String title;
    private int  yearReleased;
    private String genre;
    private double rating;
    private String director;
    
    /**
     * This a constructor of the Movie class
     * @param movieID The ID of the movie
     * @param movieTitle Title of movie
     * @param yearReleased The year the movie was released
     * @param movieGenre Genre of the movie
     * @param movieRating Rating of the movie, out of 10
     * @param movieDirector The name of the Director of the movie.
     */
    Movie(int movieID, String movieTitle, int yearReleased, String movieGenre,
            double movieRating, String movieDirector) {
        this.ID = movieID;
        this.title = movieTitle;
        this.yearReleased = yearReleased;
        this.genre = movieGenre;
        this.rating = movieRating;
        this.director = movieDirector;
    }
    /**
     * A default constructor of the Movie class.
     * It set all the instance variables of the class to their default values.
     */
    public Movie() {
        this.ID = 0;
        this.title = null;
        this.yearReleased = 0;
        this.genre = null;
        this.rating = 0.0;
        this.director = null;
    }

    /**
     * @return Return the ID of the movie
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID The ID of the movie to be set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return Get the title of the Movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The tile of the Movie to be set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param year The year the movie was released.
     */
    public void setYearReleased(int year) {
        this.yearReleased = year;
    }
    
    /**
     * 
     * @return Get the year the movie was released.
     */
    public int getYearReleased(){
        return this.yearReleased;
    }

    /**
     * @return The Genre of the Movie
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre The genre of the movie to set.
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return Get the rating of the movie to be set.
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating The Rating of the movie to be set.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return Get the director of the movie
     */
    public String getDirector() {
        return director;
    }

    /**
     * Set the director of a movie
     * @param director The director of the movie to be set
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
}
