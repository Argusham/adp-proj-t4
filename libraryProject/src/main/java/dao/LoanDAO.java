/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

//import domain.Loan;
import domain.Loan;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Argus
 */
public class LoanDAO {

    private final static String DB_URL = "jdbc:derby://localhost:1527/Libaray";
    private final static String USERNAME = "Loan";
    private final static String PASSWORD = "pass";

    public void AddInfo(Loan lib) {

        Connection connection = null;
        PreparedStatement statement = null;
        //   ResultSet rs = null; 
        String sql = "INSERT INTO LIBRARY VALUES (?,?,?,?)";
        int yes;

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);
            statement.setString(1, lib.getStudentID());
            statement.setString(2, lib.getLoanDate());
            statement.setString(3, lib.getDueDate());
            statement.setString(4, lib.getReturnDate());

            yes = statement.executeUpdate();

            if (yes > 0) {
                JOptionPane.showMessageDialog(null, "Added");
            } else {
                JOptionPane.showMessageDialog(null, "not Added");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }

                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException d) {
                JOptionPane.showMessageDialog(null, "Error" + d.getMessage());
            }

        }

    }

    public void fillTextfields(Loan lib) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM LIBRARY WHERE STUDENT_ID = ? ";

        try {
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(sql);

            statement.setString(1, lib.getStudentID());

            rs = statement.executeQuery();

            while (rs.next()) {
                rs.getString(1);
                lib.setDueDate(rs.getString(2));
                lib.setLoanDate(rs.getString(3));
                lib.setReturnDate(rs.getString(4));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                } else if (connection != null) {
                    connection.close();
                } else if (rs != null) {
                    rs.close();
                }
            } catch (SQLException d) {
                JOptionPane.showMessageDialog(null, "Error" + d.getMessage());
            }

        }

    }

}
