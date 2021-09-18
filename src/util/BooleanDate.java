/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 *
 * @author SE150968 - Thai Thanh Phat
 */
public class BooleanDate {
    public static boolean isLeap(int y) {
        boolean result = false;
        if ((y % 400 == 0) || ((y % 4 == 0) && (y % 100 != 0))) result = true;
        return result;
    }
    public static boolean valid (int y, int m, int d) {
    if (y < 0 || m < 0 || m>12 || d<0 || d > 31) return false;
    int maxD = 31;
    if (m==4 || m == 6 || m == 9 || m == 11) maxD = 30;
    else if (m == 2) {
        if (isLeap(d)) maxD = 29;
        else maxD = 28;
    }
    return d <= maxD;
}
    public static long toDate(String ymd) {
        StringTokenizer stk = new StringTokenizer(ymd, "/-");
        int y = Integer.parseInt(stk.nextToken());
        int m = Integer.parseInt(stk.nextToken());
        int d = Integer.parseInt(stk.nextToken());
        if (!valid(y,m,d)) return -1;
        Calendar cal = Calendar.getInstance();
        cal.set(y, m-1, d);
        long t = cal.getTime().getTime();
        return t;
    }
    
    
    
}
