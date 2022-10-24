/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

//import Views.LibraryClient;
import domain.Loan;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import javax.swing.*;

/**
 *
 * @author Argus
 */
public class LibraryServer extends JFrame implements ActionListener {

    private ServerSocket sSoc;
    private Socket soc;
    //private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private JPanel panelButton, panelTextArea;
    private JButton btnExit;
    private JTextArea txtFromClient;
    String l;

    public void gui() {
        //Gui Inistialize Socket
        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        txtFromClient = new JTextArea(7, 20);

        btnExit = new JButton("Exit");
        btnExit.addActionListener(this);
        btnExit.setVisible(false);

        panelButton = new JPanel();
        panelButton.setLayout(new GridLayout());
        panelButton.add(btnExit);
        this.add(panelButton, BorderLayout.NORTH);
        //
        panelTextArea = new JPanel();
        panelTextArea.setLayout(new FlowLayout());
        panelTextArea.add(txtFromClient);
        this.add(panelTextArea, BorderLayout.CENTER);

        this.setVisible(true);

        //Inistilize Connection
        try {
            sSoc = new ServerSocket(1234);
            System.out.println("waiting for connection");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void connect() throws IOException {
        soc = sSoc.accept();
        System.out.println("Connection made");
        txtFromClient.append(InetAddress.getLocalHost() + " has connected \n");
    }

    public void streams() throws IOException {
        inStream = new ObjectInputStream(soc.getInputStream());
        // outStream = new ObjectOutputStream(soc.getOutputStream());
        //  outStream.flush();
    }

    public Loan readData() throws Exception {

        Loan loan = (Loan) inStream.readObject();
        System.out.println(loan + "Server");

        return loan;

    }

    /*public void sendData(String messageToDB) throws IOException {
        outStream.writeObject(messageToDB);

        //Write to the DAO
    }*/
    public void communicate() throws Exception {

        Loan loan = new Loan();

        String ID = loan.getStudentID();
        String LD = loan.getLoanDate();
        String DD = loan.getDueDate();
        String RD = loan.getReturnDate();

        do {
            l = (String) inStream.readObject();
            if (l.equals("loan")) {
                System.out.println("Read Data");

                txtFromClient.append(ID + DD + RD + LD);

                //txtFromClient.append(readData().toString());
            }

        } while (!l.equalsIgnoreCase("Terminate"));
        btnExit.setVisible(true);

    }

    public void close() throws IOException {
        // outStream.close();
        inStream.close();
        //  soc.close();
        //    sSoc.close();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnExit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) throws Exception {

    }

}
