/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import videorental.Models.MovieCollectionModel;
import videorental.Views.AddMovie;
import videorental.Views.DeleteMovie;
import videorental.Views.MovieCollection;
import videorental.Views.UpdateMovie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class CoollectionViewController implements ActionListener{
    MovieCollection movieCollection;
    MovieCollectionModel mcm;
    
    /**
     * Constructor of the MovieCollectionController Class
     * @param movieCollection A JFrame of movieCollection form.
     */
    public CoollectionViewController(MovieCollection movieCollection){
        this.movieCollection = movieCollection;
        mcm.getInstance();
    }
    
    public void controll(){
        movieCollection.getBtnAddMovie().addActionListener(this);
        movieCollection.getBtnUpdateMovie().addActionListener(this);
        movieCollection.getBtnDeleteMovie().addActionListener(this);
        movieCollection.getBtnCancel().addActionListener(this);
        movieCollection.getjMenuItemAddMovie().addActionListener(this);
        movieCollection.getjMenuItemDeleteMovie().addActionListener(this);
        movieCollection.getjMenuItemUpdateMovie().addActionListener(this);
        movieCollection.getjMenuSearch().addActionListener(this);
        movieCollection.getjMenuView().addActionListener(this);
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
        if(e.getSource()== movieCollection.getBtnAddMovie() ||
               e.getSource()== movieCollection.getjMenuItemAddMovie()){
            AddMovie addMovieFrame  = new AddMovie();
            AddMovieController amc = new AddMovieController(addMovieFrame);
            amc.controll();
            addMovieFrame.setVisible(true);
        }
        if(e.getSource()== movieCollection.getBtnUpdateMovie()||
                e.getSource()== movieCollection.getjMenuItemUpdateMovie()){
            UpdateMovie updateMovieFrame = new UpdateMovie(movieCollection, true);
            //UpdateMovieController umc = new UpdateMovieController(updateMovieFrame);
            //umc.controll();
            updateMovieFrame.setVisible(true);
            
        }
        if(e.getSource()== movieCollection.getBtnDeleteMovie()||
                e.getSource()== movieCollection.getjMenuItemDeleteMovie()){
            DeleteMovie deleteMovieFrame = new DeleteMovie();
            //DeleteMovieController dmc = new DeleteMovieController(deleteMovieFrame);
            //dmc.controll();
            deleteMovieFrame.setVisible(true);
        }
        
        if(e.getSource()== movieCollection.getBtnCancel()){
            movieCollection.setVisible(false);
        }
    }
}
