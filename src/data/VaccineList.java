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
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import util.InputData;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class VaccineList {

    ArrayList<Vaccine> vList = new ArrayList();

    private Scanner sc = new Scanner(System.in);

    public void addVaccine(Vaccine vaccine) {
        vList.add(vaccine);
    }
    
    public int searchVaccineByID(String vaccineId) {
        if (vList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < vList.size(); i++) {
            if (vList.get(i).getvID().equalsIgnoreCase(vaccineId)) {
                return i;
            }
        }
        return -1;
    }

    public Vaccine searchVaccineIDReturnObject(String vaccineId) {
        if (vList.isEmpty()) {
            return null;
        }
        for (int i = 0; i < vList.size(); i++) {
            if (vList.get(i).getvID().equalsIgnoreCase(vaccineId)) {
                return vList.get(i);
            }
        }
        return null;
    }

    public void showVaccineList() {
        System.out.println("This is the list of vaccine:");
        String header = String.format("|%-10s|%-20s|", "vID", "vName");
        System.out.println(header);
        for (int i = 0; i < vList.size(); i++) {
            vList.get(i).showVaccine();
        }
    }

    public void readFileVaccineList() {

        try {
            File file = new File("Vaccines.txt");
            if (!file.exists()) {
                return;
            }
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String details;

            while ((details = br.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(details, ",", false);
                String id = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase().trim();

                vList.add(new Vaccine(id, name));
            }
            if (((br.readLine() == null) && (vList.isEmpty()))) {
                System.out.println("The student list is empty. Nothing to print!");
            }
            fr.close();
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void writeVaccineToFile() {

        String fileName = InputData.getString("Input file's name:", "The file's name is required!!!");
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            File file = new File(fileName + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            for (Vaccine vaccine : vList) {
                bw.write(vaccine.toString());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Store the vaccine list to binary file is success");

        } catch (Exception e) {
            System.out.println("Store the vaccine list to binary file is ERROR! Please try again!");
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
