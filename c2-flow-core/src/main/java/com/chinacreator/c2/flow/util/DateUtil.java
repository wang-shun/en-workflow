package com.chinacreator.c2.flow.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateUtil {
	
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static String longToDateString(Date date, String format){
		if(null==format || "".equals(format)){
			format = YYYY_MM_DD_HH_MM_SS;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String timeStr = "";
		if(null!=date){
			timeStr = sdf.format(date);
		}
		return timeStr;
	}
	
	/**
	 * 字符串转换成日期
	 * 
	 * @param dateStr 需要被转换的日期字符串
	 * @param formatStr 日期格式表达式
	 * @return 转换后的日期
	 */
	public static Date StrToDate(String dateStr, String formatStr) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		Date date = null;
		if(null!=dateStr && !"".equals(dateStr.trim())){
			date = format.parse(dateStr);
		}
		return date;
	}
	
	/**
	 * 日期转换成字符串
	 * 
	 * @param date 需要被转换的日期
	 * @param formatStr 日期格式表达式
	 * @return 转换后的字符串
	 */
	public static String dateToStr(Date date, String formatStr) throws Exception{
		if(date == null){
			return "";
		}
		SimpleDateFormat sdf=new SimpleDateFormat(formatStr);  
		String str=sdf.format(date); 
		return str;
	}
	
	/**
	 * 获取某一年的所有周末
	 * @param year 年份
	 * @return 周末的日期列表
	 */
	public static List<Date> getChinaWeekends(int year) {
		List<Date> list = new ArrayList<Date>();

		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.set(year, 0, 1);

		for (int day = 1; day <= cal.getActualMaximum(Calendar.DAY_OF_YEAR); day++) {
			cal.set(Calendar.DAY_OF_YEAR, day);

			int weekDay = cal.get(Calendar.DAY_OF_WEEK);
			if (weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY) {
				list.add(cal.getTime());
			}
		}

		return list;
	}

}
