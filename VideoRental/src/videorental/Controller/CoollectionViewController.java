/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import videorental.Models.MovieCollectionModel;
import videorental.Models.SearchMovieModel;
import videorental.Views.AddMovie;
import videorental.Views.DeleteMovie;
import videorental.Views.MovieCollection;
import videorental.Views.SearchMovie;
import videorental.Views.UpdateMovie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class CoollectionViewController implements ActionListener{
    MovieCollection movieCollection;
    MovieCollectionModel mcm;
    SearchMovieModel scm;
    /**
     * Constructor of the MovieCollectionController Class
     * @param movieCollection A JFrame of movieCollection form.
     */
    public CoollectionViewController(MovieCollection movieCollection,
            MovieCollectionModel movieCollectionModel,SearchMovieModel scm){
        this.movieCollection = movieCollection;
        this.mcm = movieCollectionModel;
        this.scm = SearchMovieModel.getInstance();
        
    }
    
    public void controll(){
        //this.movieCollection.getMovieCollectionTable().setModel(mcm);
        movieCollection.getBtnAddMovie().addActionListener(this);
        movieCollection.getBtnUpdateMovie().addActionListener(this);
        movieCollection.getBtnDeleteMovie().addActionListener(this);
        movieCollection.getBtnCancel().addActionListener(this);
        movieCollection.getjMenuItemAddMovie().addActionListener(this);
        movieCollection.getjMenuItemDeleteMovie().addActionListener(this);
        movieCollection.getjMenuItemUpdateMovie().addActionListener(this);
        movieCollection.getjMenuItemViewCollection().addActionListener(this);
        movieCollection.getjMenuItemSearchMovie().addActionListener(this);
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
            AddMovieController amc = new AddMovieController(addMovieFrame,mcm);
            movieCollection.setVisible(false);
            addMovieFrame.setVisible(true);
            amc.controll();
           
        }
        if(e.getSource()== movieCollection.getBtnUpdateMovie()||
                e.getSource()== movieCollection.getjMenuItemUpdateMovie()){
            UpdateMovie updateMovieFrame = new UpdateMovie(movieCollection, true);
            UpdateController umc = new UpdateController(updateMovieFrame);
            umc.controll();
            updateMovieFrame.setVisible(true);
            
        }
        if(e.getSource()== movieCollection.getBtnDeleteMovie()||
                e.getSource()== movieCollection.getjMenuItemDeleteMovie()){
            DeleteMovie deleteMovieFrame = new DeleteMovie();
            DeleteController dmc = new DeleteController(deleteMovieFrame);
            dmc.controll();
            deleteMovieFrame.setVisible(true);
        }
        
        if(e.getSource()== movieCollection.getBtnCancel()){
            movieCollection.setVisible(false);
        }
        
        if(e.getSource()== movieCollection.getjMenuItemSearchMovie()){
            SearchMovie searchMovieFrame = new SearchMovie();
            SearchMovieController smc = new SearchMovieController(searchMovieFrame);
            smc.controll();
            searchMovieFrame.setVisible(true);
            
        }
    }
}
