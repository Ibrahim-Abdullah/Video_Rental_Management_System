/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental.Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;
import net.proteanit.sql.DbUtils;
import videorental.Controller.Movie;
import videorental.Views.MovieCollection;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class MovieCollectionModel extends AbstractTableModel {

    public String[] colHeader = null;
    public ArrayList<Movie> movieList;
    public static MovieCollectionModel movieCollectionModel = null; 


    public static MovieCollectionModel getInstance() {     //I am using the Singleton pattern here
        if (getMovieCollectionModel() == null){
            movieCollectionModel = new MovieCollectionModel();
        }
        return getMovieCollectionModel();
    }
    
    public MovieCollectionModel() {
        super();
        movieList = new ArrayList<>();
        fetchTableData();

    }

    public ArrayList<Movie> getStudentArrayList() {
        return getMovieList();
    }

    @Override
    public int getColumnCount() {
        return getColHeader().length;
    }

    public Object getValueAt(int row, int col) {
        Movie movie = getMovieList().get(row);

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
        return getColHeader()[col];
    }

    public void setValueAt(Object value, int row, int col) {
        Movie movie;
        movie = getMovieList().get(row);
        switch (col) {
            case 0:
                movie.setID(Integer.valueOf((Integer) value));
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
                movie.setRating(Double.valueOf((Double) value));
                break;
            case 5:
                movie.setDirector(String.valueOf(value));
                break;
        }

        fireTableCellUpdated(row, col); //Updating the view
    }

    @Override
    public int getRowCount() {
        if (getMovieList() != null) {
            return getMovieList().size();
        }
        return 0;
    }

    /**
     * Fetch data in the database and populate the tables in the View Collection
     */
    private void fetchTableData() {
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");

            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Movies");

            ResultSetMetaData meta = rs.getMetaData();
            int numberOfColumns = meta.getColumnCount();
            colHeader = new String[numberOfColumns];
            for (int i = 1; i <= numberOfColumns; i++) {
                colHeader[i - 1] = meta.getColumnName(i);
            }

            while (rs.next()) {
                Movie mov = new Movie();
                mov.setID(rs.getInt(1));
                mov.setTitle(rs.getString(2));
                mov.setYearReleased(rs.getInt(3));
                mov.setGenre(rs.getString(4));
                mov.setRating(rs.getDouble(5));
                mov.setDirector(rs.getString(6));
                 
                getMovieList().add(mov);
            }
            rs.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            System.out.println(e);
            System.exit(0);
        }
    }

    public boolean addMovie(Movie movie) {
        boolean success;
        Connection conn = null;
        try {
            //Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");

            PreparedStatement ps;
            ps = conn.prepareStatement("Insert into Movies set MovieID=?,Title=?,YearReleased=?,Genre=?,Rating=?,Director=?");
            ps.setInt(1, movie.getID());
            ps.setString(2, movie.getTitle());
            ps.setInt(3, movie.getYearReleased());
            ps.setString(4, movie.getGenre());
            ps.setDouble(5, movie.getRating());
            ps.setString(6, movie.getDirector());
            success = ps.execute();
            System.out.print(success);
            getMovieList().add(movie);
            ps.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error" + e.toString());
            return false;
        }

        //check this for problems
        //getMovieCollectionModel().fireTableDataChanged();
        return success;
    }
    
public void updateMovie(Movie movie, int index) {
        //boolean success;
        try {
            Connection conn = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");

            PreparedStatement ps = conn.prepareStatement("update Movies set Title=?, "
                    + "YearReleased=?, Genre=?, Rating=?, Director=?"
                    + "where MovieID="+ " '"+ movie.getID()+" '");
            ps.setString(1, movie.getTitle());
            ps.setInt(2,movie.getYearReleased());
            ps.setString(3, movie.getGenre());
            ps.setDouble(4, movie.getRating());
            ps.setString(5, movie.getDirector());
            ps.execute();
            movieList.set(index, movie);
            
//            if(ps!=null){
//                ps.close();
//            }
//            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error" + e.toString());
            //return false;
        }
        
        //bookModel.fireTableDataChanged();
        //return success;
    }
    public boolean deleteRecord(int stId) {
        boolean success = true;
        try {
                Connection con = null;
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                con = java.sql.DriverManager.getConnection(
                            "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");
                PreparedStatement ps = con.prepareStatement("delete from Movies where MovieID=?");
                ps.setInt(1, stId);
                success = ps.execute();
                movieList = new ArrayList<Movie>();
                fetchTableData();
                
//                if(ps!=null){
//                    ps.close();
//                }
//                con.close();
                System.out.println(success);
                return success;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,"Error" + e.toString());
                    return false;
                }
        }
    

    /**
     * @return the colHeader
     */
    public String[] getColHeader() {
        return colHeader;
    }

    /**
     * @return the movieList
     */
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    /**
     * @return the movieCollectionModel
     */
    public static MovieCollectionModel getMovieCollectionModel() {
        return movieCollectionModel;
    }
}
