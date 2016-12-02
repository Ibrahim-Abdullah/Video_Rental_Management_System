/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class Movie {
    private int ID;
    private String title;
    private int  yearReleased;
    private String genre;
    private double rating;
    private String director;

    Movie(int movieID, String movieTitle, int yearReleased, String movieGenre,
            double movieRating, String movieDirector) {
        this.ID = movieID;
        this.title = movieTitle;
        this.yearReleased = yearReleased;
        this.genre = movieGenre;
        this.rating = movieRating;
        this.director = movieDirector;
    }

    public Movie() {
        this.ID = 0;
        this.title = null;
        this.yearReleased = 0;
        this.genre = null;
        this.rating = 0.0;
        this.director = null;
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @param year the year to set
     */
    public void setYearReleased(int year) {
        this.yearReleased = year;
    }
    
    public int getYearReleased(){
        return this.yearReleased;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }
    
}
