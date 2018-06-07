package com.cms.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarDateOP {
	//需要注意的是：月份是从0开始的，比如说如果输入5的话，实际上显示的是4月份的最后一天，千万不要搞错了哦  
    public static String getLastDayOfMonth(String ym) {     
        Calendar cal = Calendar.getInstance();     
        cal.set(Calendar.YEAR, Integer.parseInt(ym.substring(0,4)));     
        cal.set(Calendar.MONTH, Integer.parseInt(ym.substring(4, 6)));  
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        
       return  new   SimpleDateFormat( "yyyyMMdd ").format(cal.getTime());  
    }   
    public static String getFirstDayOfMonth(String ym) {     
        Calendar cal = Calendar.getInstance(); 
        cal.set(Calendar.YEAR, Integer.parseInt(ym.substring(0,4)));     
        cal.set(Calendar.MONTH, Integer.parseInt(ym.substring(4, 6)));
        
       return   new   SimpleDateFormat( "yyyyMMdd ").format(cal.getTime());  
    }   
}
