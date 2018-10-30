package com.yada.enjoyIntegral.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pgl on 2018/10/30.
 */
public class DateUtil {
    public  static  String getNowDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = dateFormat.format(new Date());
        return format;
    }
    public static  String getNowTime(){
        DateFormat dateFormat = new SimpleDateFormat("hhmmss");
        String format = dateFormat.format(new Date());
        return format;
    }

    public static void main(String[] args) {
        System.out.println(getNowDate());
        System.out.println(getNowTime());
    }
}
