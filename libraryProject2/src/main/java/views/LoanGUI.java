/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;


import domain.Loan;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.*;

/**
 *
 * @author Argus
 */
public class LoanGUI extends JFrame implements ActionListener, KeyListener {

    JLabel Heading, StudentID, loanDate, dueDate, returnDate;
    JTextField txfStudentID, txfloanDate, txfdueDate, txfreturnDate;
    JPanel topPanel, centerPanel, bottomPanel;
    JButton calculateBtn, resetBtn;

    Loan loan = new Loan();
    
    
    LibraryClient Client = new LibraryClient();

    public void GUI() {
        this.setTitle("Libaray");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLayout(new BorderLayout());

        //Top panel
        topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(100, 100));

        Heading = new JLabel("Library Loan System");
        Heading.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        topPanel.add(Heading);

        this.add(topPanel, BorderLayout.NORTH);

        //CenterPanel
        centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(0, 2));

        centerPanel.setPreferredSize(new Dimension(480, 280));

        StudentID = new JLabel("StudentID:");
        txfStudentID = new JTextField();
        txfStudentID.addKeyListener(this);

        loanDate = new JLabel("Loan Date:");
        txfloanDate = new JTextField();

        dueDate = new JLabel("Due Date:");
        txfdueDate = new JTextField();

        returnDate = new JLabel("Return Date:");
        txfreturnDate = new JTextField();

        centerPanel.add(StudentID);
        centerPanel.add(txfStudentID);
        centerPanel.add(loanDate);
        centerPanel.add(txfloanDate);
        centerPanel.add(dueDate);
        centerPanel.add(txfdueDate);
        centerPanel.add(returnDate);
        centerPanel.add(txfreturnDate);

        this.add(centerPanel, BorderLayout.CENTER);

        //BottomPanel
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(0, 2));
        bottomPanel.setPreferredSize(new Dimension(0, 30));

        calculateBtn = new JButton("Calculate");
        calculateBtn.addActionListener(this);

        resetBtn = new JButton("Reset");
        resetBtn.addActionListener(this);

        bottomPanel.add(calculateBtn);
        bottomPanel.add(resetBtn);

        this.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);
        
        
        try {
            Client.connection();
            Client.streams();
        } catch (IOException IO) {
            JOptionPane.showMessageDialog(null, IO.getMessage());
        }


    }

    public void Date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(/*"dd/MM/uuuu"*/"uuuu-MM-dd");

        String due = txfdueDate.getText();
        String returned = txfreturnDate.getText();

        LocalDate date1 = LocalDate.parse(due, formatter);
        LocalDate date2 = LocalDate.parse(returned, formatter);

        long daysBetween = ChronoUnit.DAYS.between(date2, date1);

        // long MonthsBetween = ChronoUnit.MONTHS.between(date2, date2);
        if (daysBetween < 0) {
            int fine = (int) ((daysBetween * -1) * 2);
            JOptionPane.showMessageDialog(null, "fine Amount:" + " R " + fine);

        } else {
            JOptionPane.showMessageDialog(null, " NO fine");
        }

    }

    public void reset() {

        txfStudentID.setText("");
        txfloanDate.setText("");
        txfdueDate.setText("");
        txfreturnDate.setText("");
    }

    public void insertData(Loan p) {

        String Student = txfStudentID.getText();
        String Loan = txfloanDate.getText();
        String Due = txfdueDate.getText();
        String returnD = txfreturnDate.getText();

      
        p = new Loan(Student,Loan,Due,returnD);
        // DAO.AddInfo(loan);

    }

    public void getData() {

        String stu = txfStudentID.getText();
        loan = new Loan(stu);
     

        txfloanDate.setText(loan.getLoanDate());
        txfdueDate.setText(loan.getDueDate());
        txfreturnDate.setText(loan.getReturnDate());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateBtn) {
            //Insert
            insertData(loan);
           

            // Calulate Method
           // Date();
          //   System.exit(0);
        }

        if (e.getSource() == resetBtn) {
           // reset();
           String Student = txfStudentID.getText();
        String Loan = txfloanDate.getText();
        String Due = txfdueDate.getText();
        String returnD = txfreturnDate.getText();
         Loan l = new Loan(Student,Loan,Due,returnD);

        try{
              System.out.println(loan);
           Client.sendData(l);
           //run.sendData(POJO);
           }catch(IOException oe){
               System.out.println("Exception");
           }
           
           //Data is sent to the server
          /* try{
           
           run.sendData(txfStudentID.getText());
           }catch(IOException oe){
               System.out.println("Exception");
           }*/
          
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //if(e.getKeyCode() == KeyEvent.VK_ENTER){}
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            getData();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
