package com.stu.fastpan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

/**
 * 进行时间处理的工具类
 * 
 *
 */
public class DateUtil {
	public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT = "yyyy-MM-dd";

	/**
	 * 把字符串按照指定的格式转换成日期类型.
	 * 
	 * @param dateString
	 *            日期字符串, 非空
	 * @param format
	 *            日期字格式, 非空
	 * @return 日期
	 * @throws ParseException 
	 */
	public static Date parseDate(String dateString, String format) throws ParseException {
		return new SimpleDateFormat(format).parse(dateString);

	}

	/**
	 * 按指定的格式将日期转为换字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 日期字符串
	 * @author 吴春海 Molo.Wu
	 * @since 2014-2-17
	 */
	public static String formatDate(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 */
	public static long getDaySub(Date beginDateStr, Date endDateStr) {
		long day = 0;
		try {
			day = (beginDateStr.getTime() - endDateStr.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return day;
	}

	/**
	 * 时间相减得到天数
	 * 
	 * @param beginDateStr
	 * @param endDateStr
	 * @return long
	 */
	public static long getDaySub(String beginDateStr, String endDateStr, String format) {
		long day = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			java.util.Date beginDate = sdf.parse(beginDateStr);
			java.util.Date endDate = sdf.parse(endDateStr);

			day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return day;
	}

	/**
	 * 获得系统当前日期(指定格式)
	 * 
	 * @param format
	 * @return
	 */
	public static String getCursorDate(String format) {
		String date = "";
		SimpleDateFormat sdf = null;
		if (format != null && !"".equals(format.trim())) {
			sdf = new SimpleDateFormat(format);
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}
		date = sdf.format(Calendar.getInstance().getTime());

		return date;
	}

	/**
	 * 获得指定日期date的下一天
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getNextDate(String date, String format) {
		String date2 = "";
		SimpleDateFormat sdf = null;
		if (format != null && !"".equals(format.trim())) {
			sdf = new SimpleDateFormat(format);
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}

		Calendar ca = Calendar.getInstance();
		try {
			ca.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ca.add(Calendar.DATE, 1);
		date2 = sdf.format(ca.getTime());
		return date2;
	}

	/**
	 * 获得指定日期date加n个月
	 * 
	 * @param date
	 * @param format
	 * @param n
	 * @return
	 */
	public static String addMonth(String date, String format, int n) {
		String date2 = "";
		SimpleDateFormat sdf = null;
		if (format != null && !"".equals(format.trim())) {
			sdf = new SimpleDateFormat(format);
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		}

		Calendar ca = Calendar.getInstance();
		try {
			ca.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ca.add(Calendar.MONTH, n);
		date2 = sdf.format(ca.getTime());
		return date2;
	}

	/**
	 * 获取今天是星期几
	 * 
	 * @return
	 */
	public static String getCursorWeek() {
		String week = "";
		Calendar cdate = Calendar.getInstance();
		if (cdate.get(Calendar.DAY_OF_WEEK) == 1) {
			week = "星期日";
		} else if (cdate.get(Calendar.DAY_OF_WEEK) == 2) {
			week = "星期一";
		} else if (cdate.get(Calendar.DAY_OF_WEEK) == 3) {
			week = "星期二";
		} else if (cdate.get(Calendar.DAY_OF_WEEK) == 4) {
			week = "星期三";
		} else if (cdate.get(Calendar.DAY_OF_WEEK) == 5) {
			week = "星期四";
		} else if (cdate.get(Calendar.DAY_OF_WEEK) == 6) {
			week = "星期五";
		} else if (cdate.get(Calendar.DAY_OF_WEEK) == 7) {
			week = "星期六";
		}
		return week;
	}

	/**
	 * 获得系统当前日期(指定格式)
	 * 
	 * @param format
	 * @return
	 */
	public static Date getCursorDate() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 把长整型的日期转换成需要格式的字符串
	 * 
	 * @param longdate
	 * @param formater
	 * @return
	 */
	public static String getDateByLong(long longdate, String formater) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(longdate);
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(ca.getTime());
	}

	/**
	 * 获得本周的第一天
	 * 
	 * @param formater
	 */
	public static String getFirstDayOfWeek(String formater) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Calendar ca = Calendar.getInstance();
		ca.setTime(getCursorDate());
		int num = ca.get(Calendar.DAY_OF_WEEK);
		ca.add(Calendar.DATE, 1 - num);
		str = sdf.format(ca.getTime());
		return str;
	}

	/**
	 * 获得本周的最后一天
	 * 
	 * @param formater
	 */
	public static String getLastDayOfWeek(String formater) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Calendar ca = Calendar.getInstance();
		ca.setTime(getCursorDate());
		int num = ca.get(Calendar.DAY_OF_WEEK);
		ca.add(Calendar.DATE, 7 - num);
		str = sdf.format(ca.getTime());
		return str;
	}

	/**
	 * 获得本月的第一天
	 * 
	 * @param formater
	 */
	public static String getFirstDayOfMonth(String formater) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Calendar ca = Calendar.getInstance();
		ca.setTime(getCursorDate());
		ca.set(Calendar.DATE, 1);
		str = sdf.format(ca.getTime());
		return str;
	}

	/**
	 * 获得本月的最后一天
	 * 
	 * @param formater
	 */
	public static String getLastDayOfMonth(String formater) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Calendar ca = Calendar.getInstance();
		ca.setTime(getCursorDate());
		ca.add(Calendar.MONTH, 1);
		ca.set(Calendar.DATE, 1);
		ca.add(Calendar.DATE, -1);
		str = sdf.format(ca.getTime());
		return str;
	}

	/**
	 * 获得下本季度的第一天
	 * 
	 * @param formater
	 */
	public static String getFirstDayOfQuarter(String formater) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Calendar ca = Calendar.getInstance();
		ca.setTime(getCursorDate());
		int num = ca.get(Calendar.MONTH);
		ca.set(Calendar.MONTH, num / 3 * 3);
		ca.set(Calendar.DATE, 1);
		str = sdf.format(ca.getTime());
		return str;
	}

	/**
	 * 获得下本季度的最后一天
	 * 
	 * @param formater
	 */
	public static String getLastDayOfQuarter(String formater) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		Calendar ca = Calendar.getInstance();
		ca.setTime(getCursorDate());
		int num = ca.get(Calendar.MONTH);
		ca.set(Calendar.MONTH, (num / 3 + 1) * 3 - 1);
		ca.set(Calendar.DATE, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DATE, -1);
		str = sdf.format(ca.getTime());
		return str;
	}

	/**
	 * 把长整型的日期转换成需要格式的字符串
	 * 
	 * @param longdate
	 * @param formater
	 * @return
	 */
	public static String getDateByLong(String longdate, String formater) {
		Calendar ca = Calendar.getInstance();
		long date = ca.getTimeInMillis();
		try {
			date = Long.parseLong(longdate);
		} catch (Exception e) {
		}
		ca.setTimeInMillis(date);
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		return sdf.format(ca.getTime());
	}

	/**
	 * 把指定格式的日期转换为长整型
	 * 
	 * @param longdate
	 * @param formater
	 * @return
	 */
	public static long getLongByDate(String date, String formater) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(formater);
		try {
			ca.setTime(sdf.parse(date));
		} catch (Exception e) {
			System.out.println("日期" + date + "不是" + formater + "格式的！");
		}
		return ca.getTimeInMillis();
	}

	/**
	 * 把时间精确到日并转换成long型
	 * 
	 * @param longdate
	 * @return
	 */
	public static long getNoTime(String longdate) {
		Calendar ca = Calendar.getInstance();
		long temp = ca.getTimeInMillis();
		try {
			temp = Long.parseLong(longdate);
		} catch (Exception e) {
		}
		ca.setTimeInMillis(temp);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tempdate = sdf.format(ca.getTime());
		try {
			ca.setTime(sdf.parse(tempdate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ca.getTimeInMillis();
	}

	/**
	 * 把时间精确到天如果为空就返回当前日期
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar parse(Date date) {
		Calendar ca = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String tempdate = sdf.format(date);
		try {
			ca.setTime(sdf.parse(tempdate));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ca;
	}

	/**
	 * 把固定格式的日期字符串转换成日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date parse(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date2 = null;
		if (date != null && !"".equals(date.trim())) {
			try {
				date2 = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date2;
	}

	/**
	 * 把固定格式的日期字符串转换成日期
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parse(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String temp = "";
		if (date != null) {
			try {
				temp = sdf.format(date);
			} catch (Exception e) {
				System.out.println(date + "不是【" + format + "】格式的日期！");
			}
		}
		return temp;
	}

	/**
	 * 月份加减
	 * 
	 * @param curDate
	 * @param months
	 * @return
	 */
	public static String addMonths(int months, String format) {
		String date = "";
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH, months);
		date = new SimpleDateFormat(format).format(ca.getTime());
		return date;
	}

	/**
	 * 把日期date的parm参数的值加上number
	 * 
	 * @param date
	 * @param parm
	 *            (参数如：Calendar.MONTH)
	 * @param number
	 * @return
	 */
	public static Date add(Date date, int parm, int number) {
		Calendar ca = Calendar.getInstance();
		ca.setTime(date);
		ca.add(parm, number);
		return ca.getTime();
	}

	public static String getMonth() {
		String[] month = { "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };
		GregorianCalendar calendar = new GregorianCalendar();
		return month[calendar.get(calendar.MONTH)];
	}

	public static int getYear() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(calendar.YEAR);
	}

	public static int getDay() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(calendar.DAY_OF_MONTH);
	}

	public static int getWeek() {
		GregorianCalendar calendar = new GregorianCalendar();
		return calendar.get(calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获得年周数 格式 2001301 按php的算法,周1---周日(跨年可能存在问题)
	 * 
	 * @return
	 */
	public static int getYearWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);// 为了应付php推迟一天
		int weekint = calendar.get(calendar.WEEK_OF_YEAR);
		// int weekday = calendar.get(calendar.DAY_OF_WEEK);
		String year = "";
		int returnValue = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		year = sdf.format(calendar.getTime());
		// /**************特殊处理**************/
		// if(weekday==1 && weekint>1){
		// weekint = weekint-1;
		// }
		// /***************************/
		if (weekint < 10) {
			returnValue = Integer.valueOf(year + "0" + weekint);
		} else {
			returnValue = Integer.valueOf(year + weekint);
		}
		return returnValue;
	}

	/**
	 * 获得当前日期前一周数 格式 2001301
	 * 
	 * @return
	 */
	public static int getYearWeekPre() {
		Calendar calendar = Calendar.getInstance();
		String year = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		year = sdf.format(calendar.getTime());
		calendar.add(Calendar.DATE, -1);// 为了应付php推迟一天
		calendar.add(Calendar.WEEK_OF_YEAR, -1);
		int weekint = calendar.get(calendar.WEEK_OF_YEAR);
		int returnValue = 0;
		if (weekint < 10) {
			returnValue = Integer.valueOf(year + "0" + weekint);
		} else {
			returnValue = Integer.valueOf(year + weekint);
		}
		return returnValue;
	}

	/**
	 * 获得当前日期前一月年月格式 2001301
	 * 
	 * @return
	 */
	public static int getYearMonth() {
		return Integer.valueOf(DateUtil.getCursorDate("yyyyMM"));
	}

	/**
	 * 获得年月格式 2001301
	 * 
	 * @return
	 */
	public static int getYearMonthPre() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		return new Integer(sdf.format(calendar.getTime()));
	}

	/**
	 * 获得年月日 格式 200130101
	 * 
	 * @return
	 */
	public static int getYearMonthDay() {
		return Integer.valueOf(DateUtil.getCursorDate("yyyyMMdd"));
	}

	/**
	 * 获得年月日 格式 200130101
	 * 
	 * @return
	 */
	public static int getYearMonthDayPre() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return new Integer(sdf.format(calendar.getTime()));
	}

	/**
	 * 获得年周数 格式 2001301 按php的算法,周1---周日(跨年可能存在问题)
	 * 
	 * @return
	 */
	public static int getYearWeek(String date, String format) {
		Calendar calendar = Calendar.getInstance();
		Date day = DateUtil.parse(date, format);
		calendar.setTime(day);
		calendar.add(Calendar.DATE, -1);// 为了应付php推迟一天
		int weekint = calendar.get(calendar.WEEK_OF_YEAR);
		String year = "";
		int returnValue = 0;
		// int weekday = calendar.get(calendar.DAY_OF_WEEK);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		year = sdf.format(calendar.getTime());
		// /**************特殊处理**************/
		// if(weekday==1 && weekint>1){
		// weekint = weekint-1;
		// }
		// /***************************/
		if (weekint < 10) {
			returnValue = Integer.valueOf(year + "0" + weekint);
		} else {
			returnValue = Integer.valueOf(year + weekint);
		}
		return returnValue;
	}

	/**
	 * 获取未来七天
	 * 
	 * @return
	 */
	public static Map<String, String> getNextSevenDay() {

		String date = DateUtil.getCursorDate("yyyy-MM-dd");
		Map<String, String> dateMap = new TreeMap<String, String>();
		Calendar cdate = Calendar.getInstance();

		for (int i = 0; i < 7; i++) {
			cdate.setTime(DateUtil.parse(date, "yyyy-MM-dd"));
			String week = "";
			if (cdate.get(Calendar.DAY_OF_WEEK) == 1) {
				week = "星期日";
			} else if (cdate.get(Calendar.DAY_OF_WEEK) == 2) {
				week = "星期一";
			} else if (cdate.get(Calendar.DAY_OF_WEEK) == 3) {
				week = "星期二";
			} else if (cdate.get(Calendar.DAY_OF_WEEK) == 4) {
				week = "星期三";
			} else if (cdate.get(Calendar.DAY_OF_WEEK) == 5) {
				week = "星期四";
			} else if (cdate.get(Calendar.DAY_OF_WEEK) == 6) {
				week = "星期五";
			} else if (cdate.get(Calendar.DAY_OF_WEEK) == 7) {
				week = "星期六";
			}
			dateMap.put(date, week);
			date = DateUtil.getNextDate(date, "yyyy-MM-dd");
		}

		return dateMap;
	}

	/**
	 * 取php格式当前时间戳
	 * 
	 * @return 当前时间戳
	 */
	public static int getPhpCurrentTimeMillis() {
		long currentTime = System.currentTimeMillis();
		return Integer.valueOf(String.valueOf(currentTime / 1000));
	}

	/**
	 * 取php格式当前时间戳
	 * 
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static int getPhpCurrentTimeMillis(String format) {
		Date date = parse(getCursorDate(format), format);
		return Integer.valueOf(String.valueOf(date.getTime() / 1000));
	}

	/**
	 * 取php格式时间戳
	 * 
	 * @param dateString
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static int getPhpTimeMillis(String dateString, String format) {
		Date date = parse(dateString, format);
		return Integer.valueOf(String.valueOf(date.getTime() / 1000));
	}

	/**
	 * 根据php10位整型时间获取时间所属年
	 * 
	 * @param time
	 * @return
	 */
	public static String getYearFromPhpTimeMillis(long time) {
		Calendar cdate = Calendar.getInstance();
		cdate.setTimeInMillis(time * 1000);
		return String.valueOf(cdate.get(cdate.YEAR));
	}

	/**
	 * 根据php10位整型时间获取时间所属年
	 * 
	 * @param time
	 * @return
	 */
	public static String getOlineYearFromPhpTimeMillis(long time) {
		Calendar cdate = Calendar.getInstance();
		cdate.setTimeInMillis(time * 1000);
		int year = cdate.get(cdate.YEAR);
		if (year < 2009) {
			year = 2009;
		}
		return String.valueOf(year);
	}

	/**
	 * 获取上星期一（0点）的时间
	 * 
	 * @return
	 */
	public static Integer getLastWeek() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		return DateUtil.getPhpCurrentTimeMillis("yyyy-MM-dd") - ((day - 2) + 7) * 24 * 60 * 60;
	}

	/**
	 * 获取本周星期一（0点）的时间
	 * 
	 * @return
	 */
	public static Integer getThisWeek() {
		Calendar c = Calendar.getInstance();
		int day = c.get(Calendar.DAY_OF_WEEK);
		return DateUtil.getPhpCurrentTimeMillis("yyyy-MM-dd") - (day - 2) * 24 * 60 * 60;
	}

	/**
	 * 根据秒数获取年月日
	 * 
	 * @param date
	 *            秒数
	 * @return 类似于 20140811
	 */
	public static int getDayMonthYear(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int time = Integer.valueOf(sdf.format(new Date(date * 1000l)));
		return time;
	}

	/**
	 * 把固定格式的日期字符串转换成日期
	 * 
	 * @param millis
	 * @return
	 */
	public static Date parse(long millis) {
		Calendar ca = Calendar.getInstance();
		ca.setTimeInMillis(millis);
		return ca.getTime();
	}

	/**
	 * 获取今天结束时间
	 * 
	 * @return
	 */
	public static int getTodayEndTime() {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.set(Calendar.HOUR, 24);
		todayEnd.set(Calendar.MINUTE, 0);
		todayEnd.set(Calendar.SECOND, 0);
		todayEnd.set(Calendar.MILLISECOND, 0);
		long endtime = todayEnd.getTime().getTime();
		return Integer.valueOf(String.valueOf(endtime / 1000));
	}

	/**
	 * 获取今天开始时间
	 * 
	 * @return
	 */
	public static int getTodayStartTime() {
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		long starttime = todayStart.getTime().getTime();
		return Integer.valueOf(String.valueOf(starttime / 1000));
	}

	/**
	 * 根据秒数获取年月
	 * 
	 * @param date
	 *            秒数
	 * @return 类似于 201408
	 */
	public static int getMonthYear(long date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		int time = Integer.valueOf(sdf.format(new Date(date * 1000l)));
		return time;
	}

	/**
	 * 根据秒数转化为日期
	 * 
	 * @param time
	 *            秒数
	 * @return 类似于 2014-08-11 14:37:58
	 */
	public static String getDateByPhpTimeMillis(long time) {
		Date date2 = new Date(time * 1000l);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date2);
	}

	private final static SimpleDateFormat dateTimeFormatter;

	// ~ Static fields/initializers
	// =============================================

	public final static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
	public final static String dateTimePatternUs = "EEE MMM dd HH:mm:ss z yyyy";
	public final static String dateTimePatternMonth1 = "yyyyMM";
	public final static String dateTimePatternMonth2 = "yyyy-MM";
	public final static String dateTimePatternUsStr = "CST";
	private final static String shortDatePattern = "yyMMdd";

	private static String timePattern = "HH:mm";

	static {
		dateTimeFormatter = new SimpleDateFormat(dateTimePattern);
	}

	/**
	 * change the give date by set year,month,day,hour,minute and second.
	 * 
	 * @version create by yanchaomin on 2006-6-1
	 * @param thisDate
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Calendar changeDate(Date thisDate, int year, int month, int day, int hour, int minute, int second) {
		Calendar calendar = Calendar.getInstance();
		if (thisDate != null) {
			calendar.setTime(thisDate);
		}
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		calendar.add(Calendar.HOUR, hour);
		calendar.add(Calendar.MINUTE, minute);
		calendar.add(Calendar.SECOND, second);
		return calendar;
	}

	// ~ Methods
	// ================================================================

	/**
	 * change now by set year,month,day,hour,minute and second.
	 * 
	 * @version create by yanchaomin on 2006-6-1
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Calendar changeDate(int year, int month, int day, int hour, int minute, int second) {
		return changeDate(null, year, month, day, hour, minute, second);
	}

	/**
	 * combine a end date by give year,month,day the time is 23:59:59
	 * 
	 * @version create by yanchaomin on 2006-6-1
	 * @param sYear
	 * @param sMonth
	 * @param sDate
	 * @return
	 */
	public static Date combineDateEnd(String sYear, String sMonth, String sDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sYear));
		cal.set(Calendar.MONTH, Integer.parseInt(sMonth) - 1);
		cal.set(Calendar.DATE, Integer.parseInt(sDate));
		setEndTime(cal);
		return cal.getTime();
	}

	// ************* end day ****************
	/**
	 * combine a start date by give year,month,date, the time is 00:00:00
	 * 
	 * @version create by yanchaomin on 2006-6-1
	 * @param sYear
	 * @param sMonth
	 * @param sDate
	 * @return
	 */
	public static Date combineDateStart(String sYear, String sMonth, String sDate) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(sYear));
		cal.set(Calendar.MONTH, Integer.parseInt(sMonth) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(sDate));
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * Sometimes the Datetime is baneful, such as make reporting. This method is
	 * remove time info.And set 0:0:00
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertDateTimeToDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setStartTime(cal);
		Date d = cal.getTime();
		return d;
	}

	/**
	 * This method generates a string representation of a date based on the
	 * System Property 'dateFormat' in the format you specify on input
	 * 
	 * @param aDate
	 *            A date to convert
	 * @return a string representation of the date
	 */
	public static final String convertDateToString(Date aDate) {
		return getDateTime(getDatePattern(), aDate);
	}

	public static final String convertDateToString(Date aDate, String datePattern) {
		return getDateTime(datePattern, aDate);
	}

	/**
	 * 将日期转换成(日期+时间)的字符串
	 * 
	 * @param aDate
	 * @return
	 * @author pengzhirong
	 */
	public static final String convertDateTimeToString(Date aDate) {
		return getDateTime(dateTimePattern, aDate);
	}

	/**
	 * This method converts a String to a date using the datePattern
	 * 
	 * @param strDate
	 *            the date to convert (in format MM/dd/yyyy)
	 * @return a date object
	 * 
	 * @throws ParseException
	 */
	public static Date convertStringToDate(String strDate) throws ParseException {
		Date aDate = null;

		try {

			aDate = convertStringToDate(getDatePattern(), strDate);
		} catch (ParseException pe) {
			// log.error("Could not convert '" + strDate + "' to a date,
			// throwing exception");
			pe.printStackTrace();
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());

		}

		return aDate;
	}

	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date convertStringToDate(String aMask, String strDate) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static final Date convertStringToDate(String aMask, String strDate, Locale locale) throws ParseException {
		SimpleDateFormat df = null;
		Date date = null;
		df = new SimpleDateFormat(aMask, locale);

		try {
			date = df.parse(strDate);
		} catch (ParseException pe) {
			// log.error("ParseException: " + pe);
			throw new ParseException(pe.getMessage(), pe.getErrorOffset());
		}

		return (date);
	}

	public static String fmtTodayToFive() {
		Calendar calendar = Calendar.getInstance();
		String yy = String.valueOf(calendar.get(Calendar.YEAR) % 100);
		if (yy.length() == 1) {
			yy = "0" + yy;
		}
		String mmm = String.valueOf(calendar.get(Calendar.DAY_OF_YEAR));
		if (mmm.length() == 1) {
			mmm = "00" + mmm;
		}
		if (mmm.length() == 2) {
			mmm = "0" + mmm;
		}
		return yy + mmm;
	}

	public static Date getAddDay(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, days);
		Date d = cal.getTime();
		return d;
	}

	// ************* start years *********

	/**
	 * This method returns the current date in the format: MM/dd/yyyy
	 * 
	 * @return the current date
	 * @throws ParseException
	 */
	public static Calendar getCalendarOfToday() throws ParseException {
		Date today = new Date();
		SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

		// This seems like quite a hack (date -> string -> date),
		// but it works ;-)
		String todayAsString = df.format(today);
		Calendar cal = new GregorianCalendar();
		cal.setTime(convertStringToDate(todayAsString));

		return cal;
	}

	/**
	 * This method attempts to convert an Oracle-formatted date in the form
	 * dd-MMM-yyyy to mm/dd/yyyy.
	 * 
	 * @param aDate
	 *            date from database as a string
	 * @return formatted string for the ui
	 */
	public static final String getDate(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDatePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static final String getDate(Date aDate, String pattern) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(pattern);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * Return locale specific datePattern (default is MM/dd/yyyy)
	 * 
	 * @return a string representing the date pattern on the UI
	 */
	public static String getDatePattern() {
		// return I18nUtil.getInstance().getMessage("date.format","MM/dd/yyyy");
		return "yyyy-MM-dd";
	}

	public static final String getDateTime(Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate != null) {
			df = new SimpleDateFormat(getDateTimePattern());
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	/**
	 * This method generates a string representation of a date's date/time in
	 * the format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param aDate
	 *            a date object
	 * @return a formatted string representation of the date
	 * 
	 * @see java.text.SimpleDateFormat
	 */
	public static final String getDateTime(String aMask, Date aDate) {
		SimpleDateFormat df = null;
		String returnValue = "";

		if (aDate == null) {
			// log.error("aDate is null!");
		} else {
			df = new SimpleDateFormat(aMask);
			returnValue = df.format(aDate);
		}

		return (returnValue);
	}

	public static String getDateTimePattern() {
		return getDatePattern() + " HH:mm:ss";
	}

	public static Date getEndOfAllTime() {
		return getEndOfThisYear();
	}

	public static Date getEndOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	/**
	 * End of last week
	 * 
	 * @return
	 */
	public static Date getEndOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNext30days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 30);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNext60days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 60);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNext7days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 2);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNextWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfNextYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 2);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	// ********** end days ***********
	/**
	 * get the last datetime of today create by yanchaomin on 2006-5-28
	 * 
	 * @return
	 */
	public static Date getEndOfThisDay() {
		Calendar cal = Calendar.getInstance();
		setEndTime(cal);
		Date d = cal.getTime();
		return d;
	}

	public static Date getEndOfThisDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setEndTime(cal);
		Date d = cal.getTime();
		return d;
	}

	/**
	 * get the last date of current month
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getEndOfThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfThisMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfThisQuarter() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, getQuarterEndMonth(cal.getTime()));
		return getEndOfThisMonth(cal.getTime());
	}

	/**
	 * get the last date of current week
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getEndOfThisWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getEndOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 7);
		setEndTime(cal);
		return cal.getTime();
	}

	/**
	 * get the last date of current year
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getEndOfThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DATE, -1);
		setEndTime(cal);
		return cal.getTime();
	}

	public static Date getNow() {
		return new Date();
	}

	public static String getNowStr() {
		return dateTimeFormatter.format(new Date());
	}

	public static Date getNumOfDaysBeforeNow(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -days);
		return cal.getTime();
	}

	public static int getQuarterEndMonth(Date date) {
		return getQuarterStartMonth(date) + 2;
	}

	public static int getQuarterStartMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int startMonth = 0;
		switch (cal.get(Calendar.MONTH)) {
		case 0:
			startMonth = 0;
			break;
		case 1:
			startMonth = 0;
			break;
		case 2:
			startMonth = 0;
			break;
		case 3:
			startMonth = 3;
			break;
		case 4:
			startMonth = 3;
			break;
		case 5:
			startMonth = 3;
			break;
		case 6:
			startMonth = 6;
			break;
		case 7:
			startMonth = 6;
			break;
		case 8:
			startMonth = 6;
			break;
		case 9:
			startMonth = 9;
			break;
		case 10:
			startMonth = 9;
			break;
		case 11:
			startMonth = 9;
			break;

		default:
			break;
		}

		return startMonth;
	}

	public final static String getShortDateString() {
		SimpleDateFormat sdf = new SimpleDateFormat(shortDatePattern);
		return sdf.format(new Date());
	}

	public static Date getStartOfAllTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2005);
		return cal.getTime();
	}

	public static Date getStartOfLast30days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfLast60days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -60);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfLast7days() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		setStartTime(cal);
		return cal.getTime();
	}

	// ***************** end years ****************
	// ***************** start month **************
	public static Date getStartOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	// ******* end months ***********
	// ******* start weeks **********
	/**
	 * Start of last week
	 * 
	 * @return
	 */
	public static Date getStartOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, -1);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfNextMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfNextWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.WEEK_OF_YEAR, 1);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfNextYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, 1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * get the first datetime of today create by yanchaomin on 2006-5-28
	 * 
	 * @return
	 */
	public static Date getStartOfThisDay() {
		Calendar cal = Calendar.getInstance();
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * get the first date of current month
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getStartOfThisMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisLastMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, -1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisQuarter() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, getQuarterStartMonth(cal.getTime()));
		return getStartOfThisMonth(cal.getTime());
	}

	/**
	 * get the first date of current week
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getStartOfThisWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	public static Date getStartOfThisWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, 1);
		setStartTime(cal);
		return cal.getTime();
	}

	/**
	 * get the first date of current year
	 * 
	 * @version create by yanchaomin on 2006-5-26
	 * @return
	 */
	public static Date getStartOfThisYear() {
		Calendar cal = Calendar.getInstance();
		setStartTime(cal);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	// ************ end weeks *********
	// ************ start day *********
	public static Date getToday() {
		return new Date();
	}

	/**
	 * Returns tomorrow
	 * 
	 * @return
	 */
	public static Date getTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 1);
		return cal.getTime();
	}

	/**
	 * Returns yesterday
	 * 
	 * @return
	 */
	public static Date getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.getTime();
	}

	/**
	 * Set the calendar time to 23:59:59
	 */
	private static void setEndTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
	}

	/**
	 * Set the calendar time to 0:0:00
	 * 
	 * @param cal
	 */
	private static void setStartTime(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	private DateUtil() {
	}

	public static int compareDate(String startDay, String endDay, int stype) {
		int n = 0;
		String[] u = { "天", "月", "年" };
		String formatStyle = stype == 1 ? "yyyy-MM" : "yyyy-MM-dd";

		endDay = endDay == null ? getDateTime("yyyy-MM-dd", new Date()) : endDay;

		SimpleDateFormat df = new SimpleDateFormat(formatStyle);
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		try {
			c1.setTime(df.parse(startDay));
			c2.setTime(df.parse(endDay));
		} catch (Exception e3) {
			System.out.println("wrong occured");
		}
		// List list = new ArrayList();
		while (!c1.after(c2)) { // 循环对比，直到相等，n 就是所要的结果
			// list.add(df.format(c1.getTime())); // 这里可以把间隔的日期存到数组中 打印出来
			n++;
			if (stype == 1) {
				c1.add(Calendar.MONTH, 1); // 比较月份，月份+1
			} else {
				c1.add(Calendar.DATE, 1); // 比较天数，日期+1
			}
		}
		n = n - 1;
		if (stype == 2) {
			n = (int) n / 365;
		}
		return n;
	}

	public static int dayForWeek(String pTime) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(format.parse(pTime));
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static int dayForWeek(Date pTime) throws Exception {
		Calendar c = Calendar.getInstance();
		c.setTime(pTime);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return dayForWeek;
	}

	public static int compareDate(Date begin, Date end) {
		String datePattern = "yyyy-MM-dd";
		String beginStr = getDate(begin, datePattern);
		String endStr = getDate(end, datePattern);
		int c = compareDate(beginStr, endStr, 0);
		return c + 1;
	}

	public static Date getAddTime(Date date, int time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, time);
		Date d = cal.getTime();
		return d;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(DateUtil.dayForWeek("2012-09-01"));
		System.out.println(convertDateTimeToString(DateUtil.getAddDay(new Date(), -68)));
		System.out.println(DateUtil.getAddDay(new Date(), 1));
		System.out.println(new Date().getTime());
		System.out.println(convertStringToDate(dateTimePattern, "2013-09-01 00:00:00").getTime());
	}

	public static Date getDateBeforeHour(Date date, int hour) {
		Date returnDate = date;
		returnDate.setHours(returnDate.getHours() - hour);
		return returnDate;
	}

	/**
	 * 得到一个日期在当月是属于上半月 还是下半月 1-上半月 2-下半月 0-异常
	 * 
	 * @param date
	 * @return
	 */
	public static short getDateHalfInMonth(Date date) {
		short returnInt = 0;
		String monthStr = getDateTime("yyyy-MM", date);
		try {
			Date dateEnd = convertStringToDate(dateTimePattern, monthStr + "-15 23:59:59");
			if (date.compareTo(dateEnd) > 0) {
				returnInt = 2;
			} else {
				returnInt = 1;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnInt;
	}
}
