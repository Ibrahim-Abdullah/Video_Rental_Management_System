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
import videorental.Views.Menu;
import videorental.Views.MovieCollection;
import videorental.Views.SearchMovie;
import videorental.Views.UpdateMovie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class MenuController implements ActionListener {
    Menu menuFrame;
    MovieCollectionModel movieCollectionModel;
    /**
     * Controller of the MenuController Class
     * Instantiate the menuFrame instance variable of the class 
     * @param menuFrame An instance of the menu JFrame.
     */
    public MenuController(Menu menuFrame, MovieCollectionModel movieCollectionModel ){
        this.menuFrame = menuFrame;
        this.movieCollectionModel = movieCollectionModel;
    }
    
    /**
     * Add Action listeners to all the form items on the 
     * menuFrame form. 
     */
    public void control(){
        
        menuFrame.getBtnAddMovie().addActionListener(this);
        menuFrame.getBtnSearchMovie().addActionListener(this);
        menuFrame.getBtnViewMovie().addActionListener(this);
        menuFrame.getBtnExit().addActionListener(this);
        menuFrame.getjMenuItemAddMovie().addActionListener(this);
        menuFrame.getjMenuItemDeleteMovie().addActionListener(this);
        menuFrame.getjMenuItemEditMovie().addActionListener(this);
        menuFrame.getjMenuItemViewCollection().addActionListener(this);
        menuFrame.getjMenuItemSearchMovie().addActionListener(this);
        menuFrame.getMovieMenu().addActionListener(this);
        menuFrame.getViewMenu().addActionListener(this);
        menuFrame.getSearchMenu().addActionListener(this);
    }
    /**
     * When the the AddMovie button is clicked, the AddMovie form is made visible to
     * enter movie details 
     * When ViewMovies Button is clicked, all the movies are displayed in a table.
     * When SearchMovie Button is clicked, the Search Movie form is made visible
     * When Exit Button is clicked, the applications is closed.
     * @param e An action event
     */
    public void actionPerformed (ActionEvent e){
        
        if(e.getSource()== menuFrame.getBtnAddMovie() ||
                e.getSource()==menuFrame.getjMenuItemAddMovie()){
            //Make the addMovie form visible
            //Creat an instance of the AddMovie Controller 
            //Call the control method of the Addmovie controller
            AddMovie addMovieFrame = new AddMovie();
            AddMovieController amc = new AddMovieController(addMovieFrame,movieCollectionModel);
            amc.controll();
            addMovieFrame.setVisible(true);
        }
        
        if(e.getSource()== menuFrame.getBtnSearchMovie()|| 
                e.getSource()==menuFrame.getjMenuItemSearchMovie()){
            //Create an instance of the Search form 
            //Create an instance of the SearchMovie Controller 
            //Call the controll methos of the SearchMovie Controller
            //Set the Search movie form visible
            SearchMovie searchMovieFrame = new SearchMovie();
            SearchMovieController smc = new SearchMovieController(searchMovieFrame);
            smc.controll();
            searchMovieFrame.setVisible(true);
            
            
        }
        if(e.getSource()== menuFrame.getBtnViewMovie()||
                e.getSource()==menuFrame.getjMenuItemViewCollection()){
            //Create an instance of the View form 
            //Create an instance of the View Controller 
            //Call the controll methos of the View Controller
            //Set the Search movie form visible
            MovieCollection collectionViewFrame = new MovieCollection();
            SearchMovieModel scm = SearchMovieModel.getInstance();
            //MovieCollectionModel mcm = new MovieCollectionModel(collectionViewFrame);
            CoollectionViewController cvc = new CoollectionViewController(collectionViewFrame,
                    movieCollectionModel,scm);
            cvc.controll();
            collectionViewFrame.getMovieCollectionTable().setModel(movieCollectionModel);
            collectionViewFrame.setVisible(true);
            
        }
        if(e.getSource()== menuFrame.getBtnExit()){
            System.exit(0);
            
        }
        
        if(e.getSource()== menuFrame.getjMenuItemDeleteMovie()){
            DeleteMovie deleteMovieFrame = new DeleteMovie();
            DeleteController dmc = new DeleteController(deleteMovieFrame);
            dmc.controll();
            deleteMovieFrame.setVisible(true);
        }
        
        if(e.getSource()== menuFrame.getjMenuItemEditMovie()){
            
            UpdateMovie updateMovieFrame = new UpdateMovie(menuFrame, true);
            UpdateController umc = new UpdateController(updateMovieFrame);
            umc.controll();
            updateMovieFrame.setVisible(true);
        }
        
//        if(e.getSource()== menuFrame.getBtnAddMovie() ;
//        
//        if(e.getSource()== menuFrame.getBtnAddMovie() ;
                
                

}
}
