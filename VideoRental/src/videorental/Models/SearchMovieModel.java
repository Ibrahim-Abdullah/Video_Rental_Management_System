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
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import videorental.Controller.Movie;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class SearchMovieModel extends AbstractTableModel {

    public String[] colHeader = null;
    public ArrayList<Movie> movieList;
    public static SearchMovieModel searchMovieModel = null; 


    public static SearchMovieModel getInstance() {     //I am using the Singleton pattern here
        if (getSearchMovieModel() == null){
            searchMovieModel = new SearchMovieModel();
        }
        return getSearchMovieModel();
    }
    
    public SearchMovieModel() {
        super();
        movieList = new ArrayList<Movie>();

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
     * Search for movies according to the title of the movie
     * @param title The title of the movie
     */
    public void searchByTitle(String title) {
        movieList = new ArrayList<Movie>();
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");
            PreparedStatement ps = con.prepareStatement("select * from Movies where Title=?");
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
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
            ps.close();
            con.close();
                    System.out.println("Success: " + ps.execute("select * from Books where Title=title"));
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
        }
    }

    /**
     * Search for movies from the database according the year movies were released.
     * @param yearReleased  The specified year of the movie to be searched for.
     */
    public void searchByYearReleased(int yearReleased) {
        movieList = new ArrayList<Movie>();
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");
            PreparedStatement ps = con.prepareStatement("select * from Movies where YearReleased=?");
            ps.setInt(1, yearReleased);
            ResultSet rs = ps.executeQuery();
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
            ps.close();
            con.close();
                    //System.out.println("Success: " + ps.execute("select * from Books where Title=title"));
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
        }
    }

    /**
     * Search for movie according to Genre.
     * @param genre The specified genre of the Movie to be searched for.
     */
    public void searchByGenre(String genre) {
        movieList = new ArrayList<Movie>();
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");
            PreparedStatement ps = con.prepareStatement("select * from Movies where Genre=?");
            ps.setString(1, genre);
            ResultSet rs = ps.executeQuery();
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
                rs.close();
                ps.close();
                con.close();
            }
                    System.out.println("Success: " + ps.execute("select * from Books where Title=title"));
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
        }
    }

    /**
     * Search movie record according to Ratings 
     * @param rating The specified rating for the search
     */
    public void searchByRating(double rating) {
        movieList = new ArrayList<Movie>();
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");
            PreparedStatement ps = con.prepareStatement("select * from Movies where Rating=?");
            ps.setDouble(1, rating);
            ResultSet rs = ps.executeQuery();
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
                rs.close();
                ps.close();
                con.close();
            }
                    System.out.println("Success: " + ps.execute("select * from Movies where Title=title"));
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
        }
    }

    /**
     * Search for movie records according to director name 
     * @param director The name of the director
     */
    public void searchByDirector(String director) {
        movieList = new ArrayList<Movie>();
        try {
            Connection con = null;
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = java.sql.DriverManager.getConnection(
                "jdbc:mysql://localhost/videorental?user=root&password=0030104018profib");
            PreparedStatement ps = con.prepareStatement("select * from Movies where Director=?");
            ps.setString(1, director);
            ResultSet rs = ps.executeQuery();
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
                ps.close();
                con.close();
                    //System.out.println("Success: " + ps.execute("select * from Movies where Title=title"));
                } catch (Exception e) {
                    System.out.println("Error " + e.toString());
        }
    }

    /**
     * @return The column headers for the table 
     */
    public String[] getColHeader() {
        return colHeader;
    }

    /**
     * @return A reference to the movie ArrayList in the model
     */
    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    /**
     * @return An instance of movieCollectionModel in the this class
     */
    public static SearchMovieModel getSearchMovieModel() {
        return searchMovieModel;
    }
}

