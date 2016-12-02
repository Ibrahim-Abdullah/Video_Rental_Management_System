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
import videorental.Views.MovieCollection;
import videorental.Views.SearchMovie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class SearchMovieController implements ActionListener{
    SearchMovie searchMovieFrame;
    SearchMovieModel searchMovieModel;
    
    public SearchMovieController(SearchMovie searchMovieFrame){
        this.searchMovieFrame = searchMovieFrame;
        this.searchMovieModel = SearchMovieModel.getInstance();
        
    }
    /**
     * Add ActionLiesteners to all the form element the addMovieFrame
     */
    public void controll(){
        searchMovieFrame.getBtnSearch().addActionListener(this);
        searchMovieFrame.getBtnCancel().addActionListener(this);
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
         if(e.getSource()== searchMovieFrame.getBtnSearch() ){
            String searchBy = searchMovieFrame.getJcbmSearchBy().getSelectedItem().toString();
            String keyword = searchMovieFrame.getTxfKeyword().getText();
            if(searchBy.equalsIgnoreCase("Title")){
                searchMovieModel.searchByTitle(keyword);
                MovieCollectionModel mcm = MovieCollectionModel.getInstance();
                MovieCollection mc = new MovieCollection();
                CoollectionViewController mvc = new CoollectionViewController(mc,mcm,searchMovieModel);
                mc.getMovieCollectionTable().setModel(searchMovieModel);
                searchMovieFrame.setVisible(false);
                mc.setVisible(true);
                mvc.controll();
            }
            
            if(searchBy.equalsIgnoreCase("Year Released")){
                searchMovieModel.searchByYearReleased(Integer.valueOf(keyword));
                MovieCollectionModel mcm = MovieCollectionModel.getInstance();
                MovieCollection mc = new MovieCollection();
                CoollectionViewController mvc = new CoollectionViewController(mc,mcm,searchMovieModel);
                mc.getMovieCollectionTable().setModel(searchMovieModel);
                searchMovieFrame.setVisible(false);
                mc.setVisible(true);
                mvc.controll();
            }
            
            if(searchBy.equalsIgnoreCase("Genre")){
                searchMovieModel.searchByGenre(keyword);
                MovieCollectionModel mcm = MovieCollectionModel.getInstance();
                MovieCollection mc = new MovieCollection();
                CoollectionViewController mvc = new CoollectionViewController(mc,mcm,searchMovieModel);
                mc.getMovieCollectionTable().setModel(searchMovieModel);
                searchMovieFrame.setVisible(false);
                mc.setVisible(true);
                mvc.controll();
            }
            if(searchBy.equalsIgnoreCase("Title")){
                searchMovieModel.searchByTitle(keyword);
                MovieCollectionModel mcm = MovieCollectionModel.getInstance();
                MovieCollection mc = new MovieCollection();
                CoollectionViewController mvc = new CoollectionViewController(mc,mcm,searchMovieModel);
                mc.getMovieCollectionTable().setModel(searchMovieModel);
                searchMovieFrame.setVisible(false);
                mc.setVisible(true);
                mvc.controll();
            }
            if(searchBy.equalsIgnoreCase("Rating")){
                searchMovieModel.searchByRating(Double.valueOf(keyword));
                MovieCollectionModel mcm = MovieCollectionModel.getInstance();
                MovieCollection mc = new MovieCollection();
                CoollectionViewController mvc = new CoollectionViewController(mc,mcm,searchMovieModel);
                mc.getMovieCollectionTable().setModel(searchMovieModel);
                searchMovieFrame.setVisible(false);
                mc.setVisible(true);
                mvc.controll();
            }
            if(searchBy.equalsIgnoreCase("Director")){
                searchMovieModel.searchByDirector(keyword);
                MovieCollectionModel mcm = MovieCollectionModel.getInstance();
                MovieCollection mc = new MovieCollection();
                CoollectionViewController mvc = new CoollectionViewController(mc,mcm,searchMovieModel);
                mc.getMovieCollectionTable().setModel(searchMovieModel);
                searchMovieFrame.setVisible(false);
                mc.setVisible(true);
                mvc.controll();
            }
            //Search Database for movie
            //Show movies found in a table or list 
            //Otherwise, prompt user that movie does not exist
            
         }
         if(e.getSource()== searchMovieFrame.getBtnCancel()){
             searchMovieFrame.setVisible(false);
             
         }
     }
}
