/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import videorental.Models.MovieCollectionModel;
import videorental.Models.SearchMovieModel;
import videorental.Views.AddMovie;
import videorental.Views.DeleteMovie;
import videorental.Views.MovieCollection;
import videorental.Views.SearchMovie;
import videorental.Views.UpdateMovie;

/**
 * This class controls actions performed on the MovieCollecction Frame.
 * It add Action listeners to all the the form element on the MovieCollecction Frame.
 * It also triggers actions to be performed when performs any action on the JFrame or form. 
 * Its implement the ActionListener interface and has as instance variables instances of the
 * MovieCollection, MovieCollectionModel and SearchMovieModel.
 * Imported Packages include the following;
 * import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * import videorental.Models.MovieCollectionModel;
 * import videorental.Models.SearchMovieModel;
 * import videorental.Views.AddMovie;
 * import videorental.Views.DeleteMovie;
 * import videorental.Views.MovieCollection;
 * import videorental.Views.SearchMovie;
 * import videorental.Views.UpdateMovie;
 * 
 * @author Ibrahim-Abdullah
 */
public class CoollectionViewController implements ActionListener{
    MovieCollection movieCollection;
    MovieCollectionModel mcm;
    SearchMovieModel scm;
    
    
    /**
     * Constructor of the MovieCollectionController Class
     * @param movieCollection An instance of the MovieCollection JFrame
     * @param movieCollectionModel An instance of the MovieCollectionModel class.
     * @param scm An instance of the SearchMovieModel class.
     */
    public CoollectionViewController(MovieCollection movieCollection,
            MovieCollectionModel movieCollectionModel,SearchMovieModel scm){
        this.movieCollection = movieCollection;
        this.mcm = movieCollectionModel;
        this.scm = SearchMovieModel.getInstance();
        
    }
    /**
     * Add Action Listeners to all the form element and Menu Items of the 
     * MovieCollection Frame.
     */
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
        movieCollection.getBtnExport().addActionListener(this);
        //movieCollection.getMovieCollectionTable().addActionListener(this);
    }
    /**
     * Listens to actions performed on the Movie Collection  frame.
     * Depending on the action performed on the frame, a particular section 
     * of the program runs.
     * If the user click on the AddMovie button, the add movie form is displayed 
     * for the user to enter details of the new Movie.
     * When the user clicks on Exit, the movie Collection Frame is hid and the Menu Frame 
     * is displayed.
     * @param actionEvent An instance of ActionEvent Class that listens to actions performed 
     * on the JFrame.
     */
    public void actionPerformed (ActionEvent actionEvent){
        if(actionEvent.getSource()== movieCollection.getBtnAddMovie()){
            AddMovie addMovieFrame  = new AddMovie();
            AddMovieController amc = new AddMovieController(addMovieFrame,mcm);
            //movieCollection.setVisible(false);
            addMovieFrame.setVisible(true);
            amc.controll();
           
        }
        if(actionEvent.getSource()== movieCollection.getjMenuItemAddMovie()){
            AddMovie addMovieFrame  = new AddMovie();
            AddMovieController amc = new AddMovieController(addMovieFrame,mcm);
            movieCollection.setVisible(false);
            addMovieFrame.setVisible(true);
            amc.controll();
        }
        if(actionEvent.getSource()== movieCollection.getjMenuItemUpdateMovie()){
            UpdateMovie updateMovieFrame = new UpdateMovie(movieCollection, true);
            UpdateController umc = new UpdateController(updateMovieFrame);
            umc.controll();
            updateMovieFrame.setVisible(true);
            
        }
        if(actionEvent.getSource()== movieCollection.getBtnUpdateMovie()){
            int row = movieCollection.getMovieCollectionTable().getSelectedRow();
            
            //Get the content of the selected row.
            String movieID = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,0).toString();
            String MovieTitle = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,1).toString();
            String YearReleased = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,2).toString();
            String MovieGenre = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,3).toString();
            String movieRating = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,4).toString();
            String movieDirector = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,5).toString();
            
            //Populate the Update form with the Data in the selected row.
            UpdateMovie updateMovieFrame = new UpdateMovie(movieCollection,true);
            
            updateMovieFrame.getTxfMovieTitle().setText(MovieTitle);
            updateMovieFrame.getTxfMovieID().setText(movieID);
            updateMovieFrame.getTxfYearReleased().setText(YearReleased);
            updateMovieFrame.getTxfRating().setText(movieRating);
            updateMovieFrame.getTxfDirector().setText(movieDirector);
            updateMovieFrame.getjComboBoxGenre().setSelectedItem(MovieGenre);
            
            UpdateController uc = new UpdateController(updateMovieFrame);
            uc.setIndex(Integer.valueOf(movieID));
            movieCollection.setVisible(false);
            uc.controll();
            updateMovieFrame.setVisible(true);

            
            
            
            
        }
        if(actionEvent.getSource()== movieCollection.getjMenuItemDeleteMovie()){
            DeleteMovie deleteMovieFrame = new DeleteMovie();
            DeleteController dmc = new DeleteController(deleteMovieFrame);
            dmc.controll();
            deleteMovieFrame.setVisible(true);
        }
        if(actionEvent.getSource()== movieCollection.getBtnDeleteMovie()){
            int row = movieCollection.getMovieCollectionTable().getSelectedRow();
            
            //Get the content of the selected row.
            String movieID = movieCollection.getMovieCollectionTable().getModel().getValueAt(row,0).toString();
//            DeleteMovie deleteMovieFrame = new DeleteMovie();
            
//            deleteMovieFrame.getTxfMovieID().setText(movieID);
//            DeleteController dmc = new DeleteController(deleteMovieFrame);
//            dmc.controll();
              boolean success = mcm.deleteRecord(Integer.valueOf(movieID));
              if(!success)
                  JOptionPane.showMessageDialog(null,"Movie has been deleted");
              else
                  JOptionPane.showMessageDialog(null,"Movie Could not be deleted");
              movieCollection.setVisible(false);
              movieCollection.setVisible(true);
              mcm = MovieCollectionModel.getInstance();
              movieCollection.getMovieCollectionTable().setModel(mcm);
              //movieCollection.setVisible(false);
            //deleteMovieFrame.setVisible(true);
            
        }
        if(actionEvent.getSource()== movieCollection.getBtnCancel()){
            movieCollection.setVisible(false);
        }
        
        if(actionEvent.getSource()== movieCollection.getjMenuItemSearchMovie()){
            SearchMovie searchMovieFrame = new SearchMovie();
            SearchMovieController smc = new SearchMovieController(searchMovieFrame);
            smc.controll();
            searchMovieFrame.setVisible(true);
            
        }
        if(actionEvent.getSource()== movieCollection.getBtnExport()){
            exportToExcel(movieCollection.getMovieCollectionTable(),"Movies.xls");
            JOptionPane.showMessageDialog(null,"Movies has been exported to Excel File");
            
        }
    }
    
private void exportToExcel(JTable table, String file){
    try{
        TableModel model = table.getModel();
        try (FileWriter excel = new FileWriter(file,false)) {
            for(int i = 0; i < model.getColumnCount(); i++){
                excel.write(model.getColumnName(i) + "\t");
            }
            
            excel.write("\n");
            
            for(int i=0; i< model.getRowCount(); i++) {
                for(int j=0; j < model.getColumnCount(); j++) {
                    excel.write(model.getValueAt(i,j).toString()+"\t");
                }
                excel.write("\n");
            }
        }

    }catch(IOException e){ System.out.println(e); }
}
}
