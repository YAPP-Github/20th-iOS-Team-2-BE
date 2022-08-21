package com.yapp.supporter.util;

/**
 * Author : daehwan2yo
 * Date : 2022/08/11
 * Info :
 **/
public class DateUtils {
    public static String yearMonth(String year, String month) {
        if (month.length() == 1) {
            month = "0" + month;
        }
        return year + "-" + month;
    }
}
