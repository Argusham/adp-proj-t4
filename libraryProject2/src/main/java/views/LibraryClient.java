/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import domain.Loan;
import java.io.*;
import java.net.Socket;
import javax.swing.*;

/**
 *
 * @author Argus
 */
public class LibraryClient {
    private Socket soc;
    private ObjectOutputStream outStream;
    // private ObjectInputStream inStream;
  
    public void connection() {

        try {
            soc = new Socket("localhost", 1234);
            System.out.println("Connected");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        // go.GUI();
    }

    public void sendData(Loan p) throws IOException {
        
        System.out.println(p+"Client");
        outStream.writeObject("loan");
        outStream.writeObject(p);
        outStream.flush();
    }

    public void streams() throws IOException {
        outStream = new ObjectOutputStream(soc.getOutputStream());
        outStream.flush();
        // inStream = new ObjectInputStream(soc.getInputStream());
    }

     public void close() throws IOException {
        soc.close();
        outStream.close();
     //   inStream.close();
    } 
}
