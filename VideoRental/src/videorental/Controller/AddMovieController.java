/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import videorental.Models.SearchMovieModel;
import videorental.Models.MovieCollectionModel;
import videorental.Views.AddMovie;
import videorental.Views.MovieCollection;

/**
 *This class controls the addition of new movies into the collection.
 * It add Action listeners to all the the form element on the AddMovie JFrame.
 * It also triggers actions to be performed when performs any action on the JFrame or form. 
 * Its implement the ActionListener interface and has as instance variables 
 * addMovieFrame and movieCollectionModel.
 * 
 * Its import the following 
 * import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * import videorental.Models.SearchMovieModel;
 * import videorental.Models.MovieCollectionModel;
 * import videorental.Views.AddMovie;
 * import videorental.Views.MovieCollection;
 * @author Ibrahim-Abdullah
 */
public class AddMovieController implements ActionListener{
    AddMovie addMovieFrame;
    MovieCollectionModel movieCollectionModel;
    
    
    /**
     * Constructor of the AddMovieController class.
     * @param addMovieFrame AddMovie Frame for entering the details of a new movie
     * to be added to the collection.
     * @param movieCollectionModel An instance of the MovieCollectionModel for inserting
     * the details of the new movie into the database.
     */
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
     * Listens to actions performed on the AddMovie form.
     * Depending on the action performed on the frame, a particular section 
     * of the program runs.
     * If the user click on the AddMovie button, all the content of the TextField 
     * and ComboBox are retrieved and validated for insertion into the database.
     * When the user clicks on Cancel, the Add movie form is hid and the CollectioView
     * is displayed.
     * @param actionEvent An instance of ActionEvent Class that listens to actions performed 
     * on the JFrame.
     */
    public void actionPerformed (ActionEvent actionEvent){
         if(actionEvent.getSource()== addMovieFrame.getBtnAddMovie() ){
             
             //Get form input for processing
            boolean success = fieldValidation();
            if(success){
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
            
            //Get an instance of the movieCollectionModel class.
            movieCollectionModel =  MovieCollectionModel.getInstance();
            
            //Create an instance of theh SearchMovieModel class.
            SearchMovieModel scm = SearchMovieModel.getInstance();
            
            //Create an instance of the CollectionViewController class.
            CoollectionViewController cvc = new CoollectionViewController(mc,movieCollectionModel,scm);
            
            //Set the model of the MovieCollectionTable on the Collection View
            //Frame to the instance of the movieCollectionModel created.
            mc.getMovieCollectionTable().setModel(movieCollectionModel);
            
            //Invokes the controll method of the CollecctionViewController class to 
            //add Action Listener to all the form element.
            cvc.controll();
            
            //Hide the addmovie form 
            addMovieFrame.setVisible(false);
            
            //Display the movieCollection form
            mc.setVisible(true);
             //Give feedback to user 
                }
                else{
                    JOptionPane.showMessageDialog(null,"Movie Record Could not Inserted into Database");
                }
         }
         
         //Its is executed when the user clicks on the cancel button.
         if(actionEvent.getSource()== addMovieFrame.getBtnCancel()){
             
             //Hide the Add  Movie form element
             addMovieFrame.setVisible(false);
             
         }
     }
    
public Boolean fieldValidation(){
        boolean success = false;
        try{
            //long id = new Long(studentId).longValue();
            long id = Long.parseLong(addMovieFrame.getTxfMovieId().getText());
            if(id <= 0){
                    JOptionPane.showMessageDialog(null,"Incorrect Incorrect MovieID");
                }
            //Check if Year of Admission is of the format
            try{
                int yearReleased = Integer.parseInt(addMovieFrame.getTxfYearReleased().getText());
                if(yearReleased <= 1900 || yearReleased >= 2016){
                    JOptionPane.showMessageDialog(null,"Incorrect Year Format");
                }
                
                try{
                    double movieRating  = Double.parseDouble(addMovieFrame.getRating().getText());
                    if(movieRating< 0.0 || movieRating > 10.0){
                        success =false;
                        JOptionPane.showMessageDialog(null,"Incorrect Rating.Range is between [0.0 - 10.0]");
                    }
                    else{
                    success = true;
                    }
                    
                }
                catch(NumberFormatException e){
                    success = false;
                    if(addMovieFrame.getRating().getText().equals("")){
                        JOptionPane.showMessageDialog(null,"Enter Movie Rating");
                    }
                    else{
                    JOptionPane.showMessageDialog(null,"Incorrect Rating: Range[0.0 - 10.0]");
                    }
                }
            }
            catch(NumberFormatException e){
                success = false;
            if(addMovieFrame.getTxfYearReleased().getText().equals("")){
                JOptionPane.showMessageDialog(null,"Enter Year Movie was Released");
            }
            else{
                JOptionPane.showMessageDialog(null,"Incorrect Year [eg. 2014]");
            }
            }
            if(addMovieFrame.getGenreComboBox().getSelectedItem().toString().equalsIgnoreCase("Select Program of Study")){
                success = false;
                JOptionPane.showMessageDialog(null,"Select Movie Genre");
            }
        }
        catch(NumberFormatException e){
            success = false;
            if(addMovieFrame.getTxfMovieId().getText().equals("")){
                JOptionPane.showMessageDialog(null,"Enter MovieID");
            }
            else{
            JOptionPane.showMessageDialog(null,"Incorrect Movie ID:Should be a number"); 
            }
        }
        return success;
    }
}
