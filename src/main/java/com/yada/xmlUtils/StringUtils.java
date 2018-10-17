package com.yada.xmlUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author TANDONG on 2016/8/26.
 */
public class StringUtils {
    /**
     * 获取当前时间
     *
     * @return 当前时间（yyyyMMddHHmmss）
     */
    public String getStringToDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        return df.format(new Date());// new Date()为获取当前系统时间
    }

    /**
     * 计算天数
     *
     * @param later 后面的时间
     * @param front 前面的时间
     * @return 时间差（天）
     */
    public Long getTimeDifference(String later, String front) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        Long date=0L;
        try{
            date=( dft.parse(later).getTime()- dft.parse(front).getTime()+1000000)/(3600*24*1000);
        }catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }
}
