/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class Student {

    String sID;
    String sName;

    public Student() {
    }

    public Student(String sID, String sName) {
        this.sID = sID;
        this.sName = sName;
    }

    public String getsID() {
        return sID;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    @Override
    public String toString() {
        return String.format("|%-10s|%-20s|\n", 
                            sID, sName );
    }
    
   public void showStudent() {
       System.out.printf("|%-10s|%-20s|\n", 
                            sID, sName );
   }
    
    
    
    

}
