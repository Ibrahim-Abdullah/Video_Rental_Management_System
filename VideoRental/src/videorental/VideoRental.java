/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videorental;

import videorental.Controller.MenuController;
import videorental.Views.Menu;

/**
 *
 * @author Ibrahim-Abdullah
 */
public class VideoRental {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menuFrame = new Menu();
        MenuController mc = new MenuController(menuFrame);
        mc.control();
        menuFrame.setVisible(true);
    }
    
}
