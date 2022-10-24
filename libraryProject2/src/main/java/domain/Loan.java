/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author Argus
 */
public class Loan implements Serializable {
    private String StudentID;
    private String loanDate;
    private String dueDate;
    private String returnDate;
    

    public Loan(String StudentID) {
        this.StudentID = StudentID;
    }
    
    

    public Loan(String StudentID, String loanDate, String dueDate, String returnDate) {
        this.StudentID = StudentID;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    public Loan() {
      
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "LibraryPOJO{" + "StudentID=" + StudentID + ", loanDate=" + loanDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + '}';
    }   
    
}
