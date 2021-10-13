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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import util.InputData;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class StudentList {

    ArrayList<Student> stList = new ArrayList();

    private Scanner sc = new Scanner(System.in);

    public void readFileStudentList() {

        try {

            FileReader fr = new FileReader("Students.txt");
            BufferedReader br = new BufferedReader(fr);
            String details;

            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",", false);
                String id = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase().trim();

                stList.add(new Student(id, name));
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void showStudentList() {
        System.out.println("This is the list of student:");
        String header = String.format("|%-10s|%-20s|", "sID", "sName");
        System.out.println(header);
        for (int i = 0; i < stList.size(); i++) {
            stList.get(i).showStudent();
        }
    }

    public void searchStudentByID(String sID) {
        int Count = 0;
        for (int i = 0; i < stList.size(); i++) {
            if (stList.get(i).getsID().indexOf(sID) >= 0) {
                System.out.println("Here is the Student "
                        + "that you want to search");
                stList.get(i).toString();
                Count++;
            }
        }
        if (Count == 0) {
            System.out.println("This student does not exist!!!");
        }
    }

    public Student searchStudentIDReturnObject(String studentId) {
        if (stList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < stList.size(); i++) {
            if (stList.get(i).getsID().equalsIgnoreCase(studentId)) {
                return stList.get(i);
            }
        }
        return null;
    }

    public int checkStudentByID(String sID) {
        if (stList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < stList.size(); i++) {
            if (stList.get(i).getsID().equalsIgnoreCase(sID)) {
                return i;
            }
        }
        return -1;
    }

    public void writeStudentToFile() {

        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File("Students.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            for (Student student : stList) {
                bw.write(student.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Store the new student list to binary file is success");

        } catch (Exception e) {
            System.out.println("Store the new student list to binary file is ERROR! Please try again!");
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

}
