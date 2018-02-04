package com.slife.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chen on 2017/9/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class DateUtils {
    /**
     * 根据传入的日期格式化输出
     * yyyy-MM-dd 的字符串时间
     *
     * @param date      时间
     * @param formatStr yyyy-MM-dd等时间串
     * @return
     */
    public static String formatDate(Date date, String formatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        String outDate = formatter.format(date);
        return outDate;
    }
    
    /**
     * 判断是否是同一天
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2){
    	return date1.getYear()==date2.getYear() && date1.getMonth()==date2.getMonth() && date1.getDay()==date2.getDay();
    }
    
    /**
     * 根据传入的日期格式化输出
     * yyyy-MM-dd 的字符串时间
     *
     * @param date      时间
     * @param formatStr yyyy-MM-dd等时间串
     * @return
     * @throws ParseException 
     */
    public static Date parseDate(String dateStr, String formatStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
        return formatter.parse(dateStr);
    }
    
}
