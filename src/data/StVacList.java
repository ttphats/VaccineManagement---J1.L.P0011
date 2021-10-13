/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.StringTokenizer;
import util.InputData;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class StVacList {

    ArrayList<StudentVac> stVacList;
    StudentList stList;
    VaccineList vList;

    public StVacList() {
        stVacList = new ArrayList();
        stList = new StudentList();
        stList.readFileStudentList();
        vList = new VaccineList();
        vList.readFileVaccineList();
    }

    public void addAllStudentVaccineInjection(String studentID) {
        String injectionID, sID, vaccineID, place1, place2;
        Date d1, d2;
        int pos;

        do {
            injectionID = InputData.getID("Input injection ID(IXXXXX): ", "The format of injection ID is IXXXXX (X stands for a digit)", "^[I|i]\\d{5}$");
            pos = searchInjectionByID(injectionID);
            if (pos >= 0) {
                System.out.println("The injection ID is already exists. " + "Input another one!");
            }
        } while (pos != -1);

        vList.showVaccineList();
        do {
            vaccineID = InputData.getID("Input vaccine ID(Covid-VXXX): ", "The format of vaccine ID is Covid-VXXX (X stands for a digit)", "^[C|c]{1}[O|o]{1}[V|v]{1}[I|i]{1}[D|d]{1}[-]{1}[V|v]{1}\\d{3}$");
            pos = vList.searchVaccineByID(vaccineID);
            if (pos < 0) {
                System.out.println("There is no vaccine with the id \"" + vaccineID + "\"! Try again!");
            }
        } while (pos < 0);

        sID = studentID;
        place1 = InputData.getString("Input the first place that you inject the vaccine: ", "The first place's information is required!");
        place2 = InputData.getString("Input the second place that you inject the vaccine: ", "The second place's information is required!");
        d1 = InputData.getADate("Input the first date that you inject the vaccine(yyyy/m/d): ", "Inputted date is invalid! Please try again:");
        //kiem tra mui vaccine thu 2 phai duoc tiem sau mui thu nhat 28 ngay
        while (true) {
            d2 = InputData.getADate("Input the sencond date that you inject the vaccine(yyyy/m/d): ", "Date is invalid! Try again!");
            if (InputData.checkDateToDate(d1, d2) >= 28) {
                break;
            } else {
                System.out.println("Invalid! The date of the 2nd injection must be after 28 days count from the 1st day. Try again!");
            }
        }

        stVacList.add(new StudentVac(injectionID, sID, vaccineID, place1, d1, place2, d2));
        System.out.println("A new Injection is added sucessfully");

    }

    public void addFirstStudentVaccineInjection(String studentID) {
        String injectionID, sID, vaccineID, place1;
        Date d1;
        int pos;

        do {
            injectionID = InputData.getID("Input injection ID(IXXXXX): ", "The format of injection ID is IXXXXX (X stands for a digit)", "^[I|i]\\d{5}$");
            pos = searchInjectionByID(injectionID);
            if (pos >= 0) {
                System.out.println("The injection ID is already exists. " + "Input another one!");
            }
        } while (pos != -1);

        vList.showVaccineList();

        do {
            vaccineID = InputData.getID("Input vaccine ID(Covid-VXXX): ", "The format of vaccine ID is Covid-VXXX (X stands for a digit)", "^[C|c]{1}[O|o]{1}[V|v]{1}[I|i]{1}[D|d]{1}[-]{1}[V|v]{1}\\d{3}$");
            pos = vList.searchVaccineByID(vaccineID);
            if (pos < 0) {
                System.out.println("There is no vaccine with the id \"" + vaccineID + "\"! Try again!");
            }
        } while (pos < 0);

        sID = studentID;
        place1 = InputData.getString("Input the first place that you inject the vaccine: ", "The first place's information is required!");
        d1 = InputData.getADate("Input the first date that you inject the vaccine (yyyy/m/d): ", "Inputted date is invalid! Please try again:");

        stVacList.add(new StudentVac(injectionID, sID, vaccineID, place1, d1, place1, d1));
        System.out.println("A new First Injection is added sucessfully");
        //Hoi xem co muon tiem tiep mui vaccine thu 2
        Date d = new Date(System.currentTimeMillis());
        if (InputData.checkDateToDate(d1, d) >= 28) {
            if (InputData.getYesNo("This student injected more than 28 days ago, would you like to add the 2nd injection for this student? [Yes-Y/No-N]: ")) {
                addSecondStudentVaccineInjection(sID, d1);
            } else {
                System.out.println("\nAdd injection information success!\n");
            }
        } else {
            System.out.println("\nAdd injection information success!\n");
        }

    }

    public void addSecondStudentVaccineInjection(String studentId, Date d1) {
        String place2 = InputData.getString("Input the second place that you inject the vaccine: ", "The second place's information is required!");
        Date d2;
        while (true) {
            d2 = InputData.getADate("Enter 2nd Injection Date (yyyy/m/d): ", "Date is invalid! Try again!");
            if (InputData.checkDateToDate(d1, d2) >= 28) {
                break;
            } else {
                System.out.println("Invalid! The date of the 2nd injection must be after 28 days count from the 1st day. Try again!");
            }
        }
        for (int i = 0; i < stVacList.size(); i++) {
            if (stVacList.get(i).getsID().equalsIgnoreCase(studentId)) {
                stVacList.get(i).setPlace2(place2);
                stVacList.get(i).setD2(d2);
            }
        }
        System.out.println("\nAdd injection information success! This student completed 2 injections!\n");
    }

    public void addNewInjectionForDoctor() {
        do {
            int pos;
            String studentID;
            do {
                stList.showStudentList();
                studentID = InputData.getID("Input student ID(SE/SS/SAXXXXXX): ", "The format of student ID is SE/SS/SAXXXXXX (X stands for a digit)", "^[S|s]{1}[E|e|A|a|S|s]\\d{6}$");
                pos = stList.checkStudentByID(studentID);
                if (pos < 0) {
                    System.out.println("The student ID does not exists. " + "Input another one!");
                }
            } while (pos < 0);

            StudentVac studentInfo = null;
            for (int i = 0; i < stVacList.size(); i++) {
                if (stVacList.get(i).getsID().equalsIgnoreCase(studentID)) {
                    studentInfo = stVacList.get(i);
                }
            }
            if (studentInfo != null) {
                if ((studentInfo.getPlace1() != null && studentInfo.getPlace2() != null)) {
                    System.out.println("This student has injected 2 vaccine before!");
                } else {
                    if (InputData.getYesNo("This student has injected the 1st injection. Would you like to input the 2nd injection information now? [Yes-Y/No-N]: ")) {
                        addSecondStudentVaccineInjection(studentID, studentInfo.getD2());
                        writeToFile();
                    } else {
                        System.out.println("You choose not! That student's injection information remains the same!");
                    }
                }
            } else {
                //Submenu for doctor
                System.out.println("\nThis student has no injection information yet! What do you want to do?");
                System.out.println("1. Add 1st injection information");
                System.out.println("2. Add both 1st and 2nd injection information");
                System.out.println("3. Cancel");
                int choice = InputData.getAnInteger("Enter your choice: ", "\nWrong format or Out of range choice! Try again!\n", 1, 3);
                switch (choice) {
                    case 1:
                        addFirstStudentVaccineInjection(studentID);
                        writeToFile();
                        break;
                    case 2:
                        addAllStudentVaccineInjection(studentID);
                        writeToFile();
                        break;
                    case 3:
                        System.out.println("\nCancel! Do not add this student's injection information!\n");
                        break;
                }
            }
        } while (InputData.getYesNo("Do you want to add another new injection? [Yes-Y/No-N]: "));
        System.out.println();
    }

    public void updateInjecton() {
        String injectionID = InputData.getID("Input injection ID(IXXXXX): ", "The format of injection ID is IXXXXX (X stands for a digit)", "^[I|i]\\d{5}$");
        StudentVac injectionInfo = searchInjectionReturnObjectByID(injectionID);
        if (injectionInfo == null) {
            System.out.println("\nInjection does not exist! Nothing to update!\n");
        } else {
            System.out.println("\nHere is the information of this Injection:");
            System.out.printf("| Injection ID: %s\n", injectionInfo.getInjectionID());
            System.out.printf("| Student ID: %s, Student Name: %s\n", injectionInfo.getsID(), stList.searchStudentIDReturnObject(injectionInfo.getInjectionID()).getsName());
            System.out.printf("| Vaccine ID: %s, Vaccine Name: %s\n", injectionInfo.getVaccineID(), vList.searchVaccineIDReturnObject(injectionInfo.getInjectionID()).getvName());
            System.out.printf("| 1st Injection date: %10s, 1st Injection place: %s\n", injectionInfo.getD1(), injectionInfo.getD2());
            System.out.printf("| 2nd Injection date: %10s, 2nd Injection place: %s\n", injectionInfo.getD2() == null ? "No infor" : injectionInfo.getD2(), injectionInfo.getPlace2() == null ? "No infor" : injectionInfo.getPlace2());
            Date d2;
            String place2;
            if (InputData.getYesNo("\nDo you want to update 2nd information of this injection? [Yes-Y/No-N]: ")) {
                place2 = InputData.getString("Input the second place that you inject the vaccine: ", "The second place's information is required!");
                while (true) {
                    d2 = InputData.getADate("Input the sencond date that you inject the vaccine(yyyy/m/d): ", "Date is invalid! Try again!");
                    if (InputData.checkDateToDate(injectionInfo.getD1(), d2) >= 28) {
                        break;
                    } else {
                        System.out.println("Invalid! The date of the 2nd injection must be after 28 days count from the 1st day. Try again!");
                    }
                }
                injectionInfo.setD2(d2);
                injectionInfo.setPlace2(place2);
                System.out.println("\nHere is the information of this Injection:");
                System.out.printf("| Injection ID: %s\n", injectionInfo.getInjectionID());
                System.out.printf("| Student ID: %s, Student Name: %s\n", injectionInfo.getsID(), stList.searchStudentIDReturnObject(injectionInfo.getInjectionID()).getsName());
                System.out.printf("| Vaccine ID: %s, Vaccine Name: %s\n", injectionInfo.getVaccineID(), vList.searchVaccineIDReturnObject(injectionInfo.getInjectionID()).getvName());
                System.out.printf("| 1st Injection date: %10s, 1st Injection place: %s\n", injectionInfo.getD1(), injectionInfo.getD2());
                System.out.printf("| 2nd Injection date: %10s, 2nd Injection place: %s\n", injectionInfo.getD2() == null ? "No infor" : injectionInfo.getD2(), injectionInfo.getPlace2() == null ? "No infor" : injectionInfo.getPlace2());
                writeToFile();
            } else {
                System.out.println("\nNot accepted! Not updated! Information about injection remains the same!\n");
            }
        }
    }

    public int searchInjectionByID(String injectionID) {
        if (stVacList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < stVacList.size(); i++) {
            if (stVacList.get(i).getInjectionID().equalsIgnoreCase(injectionID)) {
                return i;
            }
        }
        return -1;
    }

    public int searchVaccineByID(String vaccineID) {
        if (stVacList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < stVacList.size(); i++) {
            if (stVacList.get(i).getVaccineID().equalsIgnoreCase(vaccineID)) {
                return i;
            }
        }
        return -1;
    }

    public StudentVac searchVaccineByIDReturnObject(String vaccineID) {
        if (stVacList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < stVacList.size(); i++) {
            if (stVacList.get(i).getVaccineID().equalsIgnoreCase(vaccineID)) {
                return stVacList.get(i);
            }
        }
        return null;
    }

    public void writeToFile() {
        if (stVacList.isEmpty()) {
            System.out.println("Injection list is empty! Nothing to save!\n");
            return;
        }

        FileWriter fw = null;
        BufferedWriter bw = null;

        try {
            File file = new File("injection.dat");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            for (StudentVac injection : stVacList) {
                bw.write(injection.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Store the injection list to binary file is success");

        } catch (Exception e) {
            System.out.println("Store the injection list to binary file is ERROR! Please try again!");
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //Show to main
    public void showInformationOfAllStudentInjection() {
        if (stVacList.isEmpty()) {
            System.out.println("Injection list is empty! Nothing to show!\n");
        } else {
            String header = String.format("|%-12s|%-12s|%-12s|%-25s|%-18s|%-25s|%-18s|", "Injection ID", "Student ID", "Vaccine ID", "1st Injection Place", "1st Injection Date", "2nd Injection Place", "2nd Injection Date");
            System.out.println(header);
            for (int i = 0; i < stVacList.size(); i++) {
                stVacList.get(i).showStudentVac();
            }
            System.out.println();
        }
    }

    //Load from file
    public void readFileInjection() {
        String injectionID, sID, vaccineID, place1, place2, inputDate1, inputDate2;
        Date d1, d2;

        try {

            FileReader fr = new FileReader("Injection.dat");
            BufferedReader br = new BufferedReader(fr);
            String details;

            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",", false);
                
                injectionID = stk.nextToken().toUpperCase().trim();
                sID = stk.nextToken().toUpperCase().trim();
                vaccineID = stk.nextToken().toUpperCase().trim();
                place1 = stk.nextToken().toUpperCase().trim();
                inputDate1 = stk.nextToken().trim();
                d1 = InputData.getADate(inputDate1);
                place2 = stk.nextToken().toUpperCase().trim();
                inputDate2 = stk.nextToken().trim();
                d2 = InputData.getADate(inputDate2);

                stVacList.add(new StudentVac(injectionID, sID, vaccineID, place1, d1,
                        place2.equals("null") ? null : place2,
                        inputDate2.equals("null") ? null : d2));

            }
            fr.close();
            br.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public StudentVac searchInjectionReturnObjectByID(String injectionID) {
        if (stVacList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < stVacList.size(); i++) {
            if (stVacList.get(i).getInjectionID().equalsIgnoreCase(injectionID)) {
                return stVacList.get(i);
            }
        }
        return null;

    }

    public void deleteInjectionByID() {
        String ID;
        String response;
        int pos;
        ID = InputData.getString("Input Injection ID to delete: ", "Injection ID is required!");
        pos = searchInjectionByID(ID);
        System.out.println("------------------------------------");
        if (pos == -1) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("Are you sure to delete this injection?(YES/NO):");
            response = InputData.getString("Do you want to continue to delete another Injection?(YES/NO):", "Please input your choice(YES/NO)").toUpperCase();
            if (response.startsWith("Y")) {
                stVacList.remove(pos);
                System.out.println("The selected Injection is deleted successfully!");
            } else {
                System.out.println("Delete is cancelled!");
            }

        }
    }

}
