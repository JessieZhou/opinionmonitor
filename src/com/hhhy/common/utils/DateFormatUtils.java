package com.hhhy.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateFormatUtils {
    private static final Logger logger = Logger
            .getLogger(DateFormatUtils.class);
    public static final String yyyyMMddhhmmss = "yyyy-MM-dd HH:mm:ss";
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String yyyyMMdd2 = "yyyy/MM/dd";

    public static String formatTime(final long time, final String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        return sdf.format(time);
    }

    public static String getTodayFormat(final String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        return sdf.format(new Date());
    }
    
    public static long getTime(String date, String pattern) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern(pattern);
        return sdf.parse(date).getTime();
    }
    
    public static boolean isToday(long time){
    	Calendar calDateA = Calendar.getInstance();
    	calDateA.setTimeInMillis(time);
    	Calendar calDateB = Calendar.getInstance();
    	calDateB.setTimeInMillis(System.currentTimeMillis());
    	return calDateA.get(Calendar.YEAR) == calDateB.get(Calendar.YEAR)
                && calDateA.get(Calendar.MONTH) == calDateB.get(Calendar.MONTH)
                &&  calDateA.get(Calendar.DAY_OF_MONTH) == calDateB.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @param args
     * @throws ParseException 
     */
    public static void main(String[] args) throws ParseException {
        logger.info(getTodayFormat(DateFormatUtils.yyyyMMddhhmmss));
        String s = "sdfsd&nbspdsfsdf";
        logger.info(s.replaceAll("&nbsp", ""));
        logger.info(DateFormatUtils.formatTime(DateFormatUtils.getTime("2014-09-01", "yyyy-MM-dd")+24*60*60*1000, "yyyy-MM-dd HH:mm:ss"));
    }

}
