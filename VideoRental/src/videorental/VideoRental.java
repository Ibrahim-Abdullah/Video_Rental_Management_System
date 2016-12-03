/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental;

import videorental.Controller.MenuController;
import videorental.Models.MovieCollectionModel;
import videorental.Views.Menu;
import videorental.Views.MovieCollection;

/**
 * This the main class that provide serve as an entry point into 
 * the MovieCollection application. It makes the menu visible for the user 
 * to start navigating the application.
 * 
 * @author Ibrahim-Abdullah
 */
public class VideoRental {

    /**
     * This the main class of the program.
     * It create an instance of the MenuFrame, MovieCollectionModel and 
     * MenuControll to start the application.
     * It set the MenuFrame visible for the user to start navigating through the 
     * application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menuFrame = new Menu();
        MovieCollectionModel mcm = MovieCollectionModel.getInstance();
        MenuController mc = new MenuController(menuFrame,mcm);
        mc.control();
        menuFrame.setVisible(true);
    }
    
}
