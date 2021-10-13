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
public class StudentVac {

    String injectionID;
    String place1;
    Date d1;
    String place2;
    Date d2;
    String vaccineID;
    String sID;

    public StudentVac() {
    }

    public StudentVac(String injectionID, String sID, String vaccineID, String place1, Date d1, String place2, Date d2) {
        this.injectionID = injectionID;
        this.place1 = place1;
        this.d1 = d1;
        this.place2 = place2;
        this.d2 = d2;
        this.vaccineID = vaccineID;
        this.sID = sID;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public String getPlace1() {
        return place1;
    }

    public void setPlace1(String place1) {
        this.place1 = place1;
    }

    public Date getD1() {
        return d1;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public String getPlace2() {
        return place2;
    }

    public void setPlace2(String place2) {
        this.place2 = place2;
    }

    public Date getD2() {
        return d2;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public String getsID() {
        return sID;
    }

    @Override
    public String toString() {
        return injectionID + "," + sID + "," + vaccineID + "," + place1 + "," + d1 + "," + place2 + "," + d2 + ",";
    }

    public void showStudentVac() {
        System.out.printf("|%-12s|%-12s|%-12s|%-25s|%-18s|%-25s|%-18s|\n",
                injectionID, sID, vaccineID, place1, d1, place2, d2);
    }

}
