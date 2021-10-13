/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import data.StVacList;
import ui.Menu;
import util.InputData;

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
        menu.addNewOption("6. Store data to file");
        menu.addNewOption("7. Quit");

        StVacList stVac = new StVacList();
        stVac.readFileInjection();

        int choice;
        String response;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    stVac.showInformationOfAllStudentInjection();
                    break;
                case 2:
                    System.out.println("You are preparing to " + "add student's vaccine injection information!\n");
                    stVac.addNewInjectionForDoctor();
                    break;
                case 3:
                    System.out.println("You are preparing to " + "update student's vaccine injection information!\n");
                    stVac.updateInjecton();
                    break;
                case 4:
                    System.out.println("You are preparing to " + "delete student's vaccine injection information!\n");
                    stVac.deleteInjectionByID();
                    break;
                case 5:
                    String injectionID;
                    do {
                        System.out.println("You are required to input "
                                + "a Injection's ID to search");
                        injectionID = InputData.getString("Input injection ID: ", "The injection ID is required!");
                        stVac.searchInjectionByID(injectionID);
                        response = InputData.getString("Do you want to continue to search injection?(YES/NO):", "Please input your choice(YES/NO)").toUpperCase();

                    } while (response.startsWith("Y"));
                    break;
                case 6:
                    System.out.println("You are preparing to write the injection list to a file");
                    stVac.writeToFile();
                    break;
                default:
                    System.out.println("Please choose from 1 to 6!!!");
                    break;
            }

        } while (choice != 6);
    }

}
