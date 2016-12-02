/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import videorental.Models.SearchMovieModel;
import videorental.Models.MovieCollectionModel;
import videorental.Views.AddMovie;
import videorental.Views.MovieCollection;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class AddMovieController implements ActionListener{
    AddMovie addMovieFrame;
    MovieCollectionModel movieCollectionModel;
    
    public AddMovieController(AddMovie addMovieFrame,MovieCollectionModel movieCollectionModel){
        this.addMovieFrame = addMovieFrame;
        this.movieCollectionModel = movieCollectionModel;
    }
    /**
     * Add ActionLiesteners to all the form element the addMovieFrame
     */
    public void controll(){
        addMovieFrame.getBtnAddMovie().addActionListener(this);
        addMovieFrame.getBtnCancel().addActionListener(this);
    }
    
    /**
     * Perform an action based on an action performed by the user.
     * When AddMovie is clicked, 
     * all form input are collected for database insertion.
     * When Cancel is clicked,the form visible is set to false.
     * 
     * @param e An action event
     */
    public void actionPerformed (ActionEvent e){
         if(e.getSource()== addMovieFrame.getBtnAddMovie() ){
             
             //Get form input for processing
             int movieID = Integer.parseInt(addMovieFrame.getTxfMovieId().getText());
             String movieTitle = addMovieFrame.getTxfMovieTitle().getText();
             int  yearReleased = Integer.parseInt(addMovieFrame.getTxfYearReleased().getText());
             String movieGenre = addMovieFrame.getGenreComboBox().getSelectedItem().toString();
             Double  movieRating = Double.parseDouble(addMovieFrame.getRating().getText());
             String movieDirector = addMovieFrame.getTxfDirector().getText();
             
             
             //Create a new movie object
             Movie newMovie = new Movie(movieID,movieTitle,yearReleased,
                     movieGenre,movieRating,movieDirector);
             
             //Insert movie object into database 
            movieCollectionModel.addMovie(newMovie);
            
            //Create a new collection View frame to display inserted record
            MovieCollection mc = new MovieCollection();
            movieCollectionModel =  MovieCollectionModel.getInstance();
            SearchMovieModel scm = SearchMovieModel.getInstance();
            CoollectionViewController cvc = new CoollectionViewController(mc,movieCollectionModel,scm);
            mc.getMovieCollectionTable().setModel(movieCollectionModel);
            cvc.controll();
            
            //Hide the addmovie form 
            addMovieFrame.setVisible(false);
            
            //Display the movieCollection form
            mc.setVisible(true);
             //Give feedback to user 
         }
         if(e.getSource()== addMovieFrame.getBtnCancel()){
             addMovieFrame.setVisible(false);
             
         }
     }
}
