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
 *
 * @author Ibrahim-Abdullah
 */
public class DeleteController implements ActionListener{
    public DeleteMovie deleteMovieFrame;
    MovieCollectionModel mcm;
    
    
    public DeleteController(DeleteMovie deleteMovieFrame){
        this.deleteMovieFrame = deleteMovieFrame;
        this.mcm = MovieCollectionModel.getInstance();
    }
    
    public void controll(){
        deleteMovieFrame.getBtnCancel().addActionListener(this);
        deleteMovieFrame.getBtnDelete().addActionListener(this);
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
        if(e.getSource()== deleteMovieFrame.getBtnDelete()){
            int movieID = Integer.parseInt(deleteMovieFrame.getTxfMovieID().getText());
            try{
                boolean success = mcm.deleteRecord(movieID);
                if(!success){
                    JOptionPane.showMessageDialog(null,"Book has been deleted");
                    deleteMovieFrame.setVisible(false);
                    
                }
                else
                    JOptionPane.showMessageDialog(null,"Book ID does not exist");
            }catch(Exception ea){
                JOptionPane.showMessageDialog(null,"Incorrect Book ID");
            }
            //Check if there is a movie with the specified ID.
            //If movie exist, prompt user to confirm deletion
            //Delete if user confirms deletion
            //Otherwise, ignore deletion
            //Give feedback to user 
         }
         if(e.getSource()== deleteMovieFrame.getBtnCancel()){
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
     * @return the deleteMovieFrame
     */
    public DeleteMovie getDeleteMovieFrame() {
        return deleteMovieFrame;
    }

    /**
     * @param deleteMovieFrame the deleteMovieFrame to set
     */
    public void setDeleteMovieFrame(DeleteMovie deleteMovieFrame) {
        this.deleteMovieFrame = deleteMovieFrame;
    }
    
    
}
