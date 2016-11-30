/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Models;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import videorental.Controller.Movie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class MovieCollectionModel extends AbstractTableModel{
    String[] colHeader = {"MovieID","Title","Released Year", "Genre", 
    "Rating", "Director"};
    private ArrayList<Movie> movieList;
    static MovieCollectionModel movieCollectionModel = null;

    public static MovieCollectionModel getInstance() {     //I am using the Singleton pattern here
        if (movieCollectionModel == null) {
            movieCollectionModel = new MovieCollectionModel();
        }
        return movieCollectionModel;
    }

    public MovieCollectionModel() {
        super();
        movieList = null;
        //fetchTableData();
        
    }

    public ArrayList getStudentArrayList() {
        return movieList;
    }

    @Override
    public int getColumnCount() {
        return colHeader.length;
    }
    
    public Object getValueAt(int row, int col) {
        Movie movie = movieList.get(row);

        if (col == 0) {
            return movie.getID();
        } else if (col == 1) {
            return movie.getTitle();
        } else if (col == 2) {
            return movie.getYearReleased();
        } else if (col == 3) {
            return movie.getGenre();
        } else if (col == 4) {
            return movie.getRating();
        } else if (col == 5) {
            return movie.getDirector();
        }
        return movie;
    }
    
    /**
     *
     * @param col
     * @return
     */
    @Override
    public String getColumnName(int col) {
        return colHeader[col];//otherwise returns A, B, C etc
    }

    public void setValueAt(Object value, int row, int col) {
        Movie movie;
        movie = movieList.get(row);
        switch (col) {
            case 0:
                movie.setID(Integer.valueOf((Integer)value));
                break;
            case 1:
                movie.setTitle(String.valueOf(value));
                break;
            case 2:
                movie.setYearReleased(Integer.valueOf((Integer) value));
                break;
            case 3:
                movie.setGenre(String.valueOf(value));
                break;
            case 4:
                movie.setRating(Double.valueOf((Double)value));
                break;
            case 5:
                movie.setDirector(String.valueOf(value));
                break;
        }
               
        fireTableCellUpdated(row, col); //Updating the view
    }

    @Override
    public int getRowCount() {
        if(movieList != null)
            return movieList.size();
        return 0;
    }
    
    public void setArrayListOfBooks(ArrayList<Movie> arr){
        movieList = arr;
    }
}
