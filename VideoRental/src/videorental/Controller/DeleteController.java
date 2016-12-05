/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import videorental.Models.MovieCollectionModel;
import videorental.Models.SearchMovieModel;
import videorental.Views.DeleteMovie;
import videorental.Views.MovieCollection;

/**
 * This class controls actions performed on the Delete Movie  Frame.
 * It add Action listeners to all the the form element on the Delete Movie  Frame.
 * It also triggers actions to be performed when performs any action on the JFrame or form. 
 * Its implement the ActionListener interface and has as instance variables instances of
 * DeleteMovie JFrame and the MovieCollectionModel.
 * 
 * Imported Packages include the following;
 *import java.awt.event.ActionEvent;
 *import java.awt.event.ActionListener;
 *import javax.swing.JOptionPane;
 *import videorental.Models.MovieCollectionModel;
 *import videorental.Models.SearchMovieModel;
 *import videorental.Views.DeleteMovie;
 *import videorental.Views.MovieCollection;
 * 
 * @author Ibrahim-Abdullah
 */
public class DeleteController implements ActionListener{
    public DeleteMovie deleteMovieFrame;
    MovieCollectionModel mcm;
    
    
    /**
     * Constructor of the DeleteController Class.
     * @param deleteMovieFrame An instance of Delete Movie JFrame
     */
    public DeleteController(DeleteMovie deleteMovieFrame){
        this.deleteMovieFrame = deleteMovieFrame;
        this.mcm = MovieCollectionModel.getInstance();
    }
    /**
     * Add action Listeners to all the buttons on the Delete Movie Frame.
     */
    public void controll(){
        deleteMovieFrame.getBtnCancel().addActionListener(this);
        deleteMovieFrame.getBtnDelete().addActionListener(this);
    }
 
    /**
     * Listens to actions performed on the Delete Movie  frame.
     * Depending on the action performed on the frame, a particular section 
     * of the program runs.
     * If the user click on the Delete Movie button,A movie is deleted upon confirmation form the user.
     * When the user clicks on Cancel, the movie Collection Frame is hid and the Menu Frame 
     * is displayed.
     * @param actionEvent An instance of ActionEvent Class that listens to actions performed 
     * on the JFrame.
     */
    public void actionPerformed (ActionEvent actionEvent){
        if(actionEvent.getSource()== deleteMovieFrame.getBtnDelete()){
            try{
                int movieID = Integer.parseInt(deleteMovieFrame.getTxfMovieID().getText());
            try{
                boolean success = mcm.deleteRecord(movieID);
                if(success){
                    JOptionPane.showMessageDialog(null,"Movie Record has been deleted");
                    deleteMovieFrame.setVisible(false);
                }
                else
                    JOptionPane.showMessageDialog(null,"Movie ID does not exist");
            }catch(Exception ea){
                JOptionPane.showMessageDialog(null,"Movie ID does not exist");
            }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Incorrect Movie ID");
            }
            //Check if there is a movie with the specified ID.
            //If movie exist, prompt user to confirm deletion
            //Delete if user confirms deletion
            //Otherwise, ignore deletion
            //Give feedback to user 
         }
         if(actionEvent.getSource()== deleteMovieFrame.getBtnCancel()){
            deleteMovieFrame.setVisible(false);
             mcm = MovieCollectionModel.getInstance();
             MovieCollection mc = new MovieCollection();
             SearchMovieModel scm = SearchMovieModel.getInstance();
             CoollectionViewController cvc = new CoollectionViewController(mc,mcm,scm);
             mc.getMovieCollectionTable().setModel(mcm);
             mc.setVisible(true);
             cvc.controll();
             
         }
     }
    
    
    /**
     * @return the deleteMovieFrame An instance of the Delete Movie Frame.
     */
    public DeleteMovie getDeleteMovieFrame() {
        return deleteMovieFrame;
    }

    /**
     * @param deleteMovieFrame An instance of the Delete Movie frame to set.
     */
    public void setDeleteMovieFrame(DeleteMovie deleteMovieFrame) {
        this.deleteMovieFrame = deleteMovieFrame;
    }
    
    
}
