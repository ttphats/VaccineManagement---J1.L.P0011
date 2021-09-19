/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Date;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class StudentVac extends Student {
    String vID;
    String place1;
    Date d1;
    String place2;
    Date d2;

    public StudentVac(String sID, String sName) {
        super(sID, sName);
    }

    public StudentVac(String sID, String vID, String place1, Date d1, String place2, Date d2) {
        this.sID = sID;
        this.vID = vID;
        this.place1 = place1;
        this.d1 = d1;
        this.place2 = place2;
        this.d2 = d2;
    }

    public StudentVac(Student st) {
    }
    
    


    
    
    
}
