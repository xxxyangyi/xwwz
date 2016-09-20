package com.hand.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform {
	public static String dateToString(Date time){
		try{
	    SimpleDateFormat formatter;
	    formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
	    String ctime = formatter.format(time);
	    return ctime;
		}
		catch(Exception ex){
			return "错误日期";
		}
	}
}
