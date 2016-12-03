/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import videorental.Models.MovieCollectionModel;
import videorental.Models.SearchMovieModel;
import videorental.Views.MovieCollection;
import videorental.Views.UpdateMovie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class UpdateController implements ActionListener{
    UpdateMovie updateMovieFrame;
    MovieCollectionModel movieCollectionModel;
    private int index;
    
    public UpdateController(UpdateMovie updateMovieFrame){
        this.updateMovieFrame = updateMovieFrame;
        movieCollectionModel = MovieCollectionModel.getInstance();
        this.index = -1;
    }
    /**
     * Add ActionLiesteners to all the form element the addMovieFrame
     */
    public void controll(){
        updateMovieFrame.getBtnCancel().addActionListener(this);
        updateMovieFrame.getBtnRetrieveRecord().addActionListener(this);
        updateMovieFrame.getBtnUpdateRecord().addActionListener(this);
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
        
        //Update Movie Details
         if(e.getSource()== updateMovieFrame.getBtnUpdateRecord() ){
             
             int movieID = Integer.parseInt(updateMovieFrame.getTxfMovieID().getText());
             String movieTitle = updateMovieFrame.getTxfMovieTitle().getText();
             int  yearReleased = Integer.parseInt(updateMovieFrame.getTxfYearReleased().getText());
             String movieGenre = updateMovieFrame.getjComboBoxGenre().getSelectedItem().toString();
             double  movieRating = Double.parseDouble(updateMovieFrame.getTxfRating().getText());
             String movieDirector = updateMovieFrame.getTxfDirector().getText();
             
             Movie movie = new Movie(movieID,movieTitle,yearReleased,
                     movieGenre,movieRating,movieDirector);
             
             //Update the record of the movie with the new data
             //Give feedback to user 
             movieCollectionModel.updateMovie(movie,index);
             resetUpdateField();
         }
         if(e.getSource()== updateMovieFrame.getBtnCancel()){
             updateMovieFrame.setVisible(false);
             movieCollectionModel = MovieCollectionModel.getInstance();
             MovieCollection mc = new MovieCollection();
             SearchMovieModel scm = SearchMovieModel.getInstance();
             CoollectionViewController cvc = new CoollectionViewController(mc,movieCollectionModel,scm);
             mc.getMovieCollectionTable().setModel(movieCollectionModel);
             mc.setVisible(true);
             cvc.controll();
             
         }
         if(e.getSource()== updateMovieFrame.getBtnRetrieveRecord()){
             int movieID = Integer.parseInt(updateMovieFrame.getTxfMovieID().getText());
             //Retrieve the record of the movie and display in the form
             ArrayList<Movie> movieList = movieCollectionModel.getMovieList();
             
             //int m = -1;
             Movie movie = null;
             for(int i = 0; i < movieList.size(); i++){
                 if(movieList.get(i).getID()==movieID){
                     index = i;
                     movie = movieList.get(i);
                     break;
                 }
             }
             if(index == -1){
                 JOptionPane.showMessageDialog(null,"Movie does not exist.");
             }
             else{
                 updateMovieFrame.getTxfMovieTitle().setText(movie.getTitle());
                 updateMovieFrame.getTxfYearReleased().setText(Integer.toString(movie.getYearReleased()));
                 updateMovieFrame.getTxfRating().setText(Double.toString(movie.getRating()));
                 updateMovieFrame.getTxfDirector().setText(movie.getDirector());
                 updateMovieFrame.getjComboBoxGenre().setSelectedItem(movie.getGenre());
             }
         }
     }
    
    public void setIndex(int movieID){
             ArrayList<Movie> movieList = movieCollectionModel.getMovieList();
             
             //int m = -1;
             //Movie movie = null;
             for(int i = 0; i < movieList.size(); i++){
                 if(movieList.get(i).getID()==movieID){
                     index = i;
                     //movie = movieList.get(i);
                     break;
                 }
             }
    }
    
    private void resetUpdateField(){
                 updateMovieFrame.getTxfMovieTitle().setText("");
                 updateMovieFrame.getTxfYearReleased().setText("");
                 updateMovieFrame.getTxfRating().setText("");
                 updateMovieFrame.getTxfDirector().setText("");
                 updateMovieFrame.getjComboBoxGenre().setSelectedItem("Action");
    }
}
