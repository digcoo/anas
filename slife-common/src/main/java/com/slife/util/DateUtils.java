package com.slife.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chen on 2017/9/27.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe:
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils{
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
     * 广告首页发布时间显示格式化
     * @param date
     * @return
     */
    public static String formatTimeForShow(Date date){
        Calendar calendar = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar.set(Calendar.DATE,-1);
        boolean yesterday = org.apache.commons.lang3.time.DateUtils.isSameDay(calendar.getTime(),date);
        boolean today = org.apache.commons.lang3.time.DateUtils.isSameDay(calendar2.getTime(),date);
        String timeStr = DateUtils.formatDate(date,"HH:mm");
        String dateStr = DateUtils.formatDate(date,"MM-DD");
        if(yesterday){
            return "昨天 "+timeStr;
        }else if(today){
            long difference=System.currentTimeMillis()-date.getTime();
            long minute=difference/(60*1000);
            if(minute<20){
                return minute+"前";
            }
            return timeStr;
        }else{
            return dateStr;
        }
    }
    
}
