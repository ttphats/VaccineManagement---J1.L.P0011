/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import ui.Menu;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class VaccineManagement {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu menu = new Menu("Welcome to Covid-19 Vaccine Management - @ 2021 by <SE150968 - Thái Thành Phát>");
        menu.addNewOption("1. Show information all students have been injected");
        menu.addNewOption("2. Add student's vaccine injection information");
        menu.addNewOption("3. Updating information of students' vaccine injection");
        menu.addNewOption("4. Delete student vaccine injection information");
        menu.addNewOption("5. Search for injection information by studentID");
        menu.addNewOption("6. Quit");
        
        int choice;
        do {     
            menu.printMenu();
            choice = menu.getChoice();
            switch(choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            default:
                System.out.println("Please choose from 1 to 6!!!");
                break;
        }
            
        } while (choice != 6);
    }
    
}
