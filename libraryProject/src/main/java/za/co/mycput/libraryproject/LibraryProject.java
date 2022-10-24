/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package za.co.mycput.libraryproject;

import views.LibraryServer;



//import Views.LibraryGui;

/**
 *
 * @author Argus
 */
public class LibraryProject {

    public static void main(String[] args) throws Exception {
     // LibraryGui run = new LibraryGui();
      //run.GUI();
      
       LibraryServer run = new LibraryServer();
        run.gui();
        run.connect();
        run.streams();
        // run.sendData(messageToClient);
        run.communicate();
        run.close();
    }
}
