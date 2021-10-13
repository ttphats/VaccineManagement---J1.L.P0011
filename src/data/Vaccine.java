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
public class Vaccine {

    String vID;
    String vName;

    public Vaccine() {
    }

    public Vaccine(String vID, String vName) {
        this.vID = vID;
        this.vName = vName;
    }

    public String getvID() {
        return vID;
    }

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    @Override
    public String toString() {
        return String.format("|%-10s|%-20s|\n", vID, vName);
    }
    public void showVaccine() {
        System.out.printf("|%-10s|%-20s|\n", vID, vName);
    }
    
    

    
}
