/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open  the template in the editor.
 */
package util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class InputData {

    private static Scanner sc = new Scanner(System.in);

    public static int getAnInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static int getAnInteger(String inputMsg, String errorMsg, int lowerBound, int upperBound) {
        int n, tmp;
        //nếu đầu vào lower > upper thì đổi chỗ
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static double getADouble(String inputMsg, String errorMsg, double lowerBound, double upperBound) {
        double n, tmp;
        if (lowerBound > upperBound) {
            tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getID(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String id;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static Date getADate(String inputMsg, String errorMsg) {
        String inputStr;
        Date d = new Date(System.currentTimeMillis());
        while (true) {
            System.out.println(inputMsg);
            inputStr = sc.nextLine().trim();
            long t = BooleanDate.toDate(inputStr);
            if (t < 0) {
                System.out.println(errorMsg);
            } else {
                d = new Date(t);
                return d;
            }

        }
    }

    public static Date getADate(String inputStr) {
        Date d = new Date(System.currentTimeMillis());
        while (true) {
            long t = BooleanDate.toDate(inputStr);
            if (t < 0) {
            } else {
                d = new Date(t);
                return d;
            }

        }
    }

    public static long checkDateToDate(Date date1, Date date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        long noDay = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        return noDay;
    }
        public static boolean getYesNo(String inputMsg) {
        while (true) {
            System.out.print(inputMsg);
            String response = sc.nextLine().trim();
            if (response.length() == 0 || response.isEmpty()) {
                System.err.println("Don't accept empty or blank!");
                continue;
            }
            if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes") || response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no")) {
                if (response.equalsIgnoreCase("y") || response.equalsIgnoreCase("yes"))
                    return true;
                if (response.equalsIgnoreCase("n") || response.equalsIgnoreCase("no"))
                    return false;
            }
            else
                System.err.println("Wrong format! Format is Yes-Y/No-N!\n");
        }
    }

    //main() để test thử yêu cầu nhập mã số theo định dạng cho
//    public static void main(String[] args) {
//        String id = getID("Input ID(DXXXXX): ", "Your input must be under "
//                + "the format of DXXXXX, X stands for a digit",
//               "^[D|d]\\d{5}$");
//       System.out.println("Your ID: " + id);
//
//        Date d = getADate("Input a date value yyyy/d/m: ", "Inputted date is invalid! Please try again:");
//
//        System.out.println("Your Date: " + d);
//
//    }
}
