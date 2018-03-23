package com.zd.aoding.outsourcing.weChat.api.utils.file;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import com.zd.aoding.common.StringDate.StringUtil;

/**
 * @ClassName: DateUtil
 * @Description: 日期转换 工具类
 * @date: 2017年11月30日 上午8:59:46
 */
public class DateUtil {

	/**
	 * @fieldName: SECOND
	 * @fieldType: int
	 * @Description: 1000 毫秒 = 1秒
	 */
	public static final int SECOND = 1000;

	/**
	 * @fieldName: MINUTE
	 * @fieldType: int
	 * @Description: 一分钟 毫秒
	 */
	public static final int MINUTE = SECOND * 60;

	/**
	 * @fieldName: HOUR
	 * @fieldType: int
	 * @Description: 一小时毫秒
	 */
	public static final int HOUR = MINUTE * 60;

	/**
	 * @fieldName: DAY
	 * @fieldType: long
	 * @Description: 一天毫秒
	 */
	public static final long DAY = HOUR * 24;

	/**
	 * @fieldName: WEEK
	 * @fieldType: long
	 * @Description: 一周毫秒
	 */
	public static final long WEEK = DAY * 7;

	/**
	 * @fieldName: YEAR
	 * @fieldType: long
	 * @Description: 365天毫秒
	 */
	public static final long YEAR = DAY * 365;

	/********************** 日期格式化参数类型 ****************************/
	public static final String DATE_FORMAT_UTC = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	public static final String DATE_FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

	public static final String DATE_FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

	public static final String DATE_FORMAT_YYMMDD = "yy-MM-dd";

	public static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	

	public static final String DATE_FORMAT_YMDHMS_X = "yyyy/MM/dd HH:mm:ss";

	public static final String DATE_FORMAT_YMDHM_X = "yyyy/MM/dd HH:mm";

	public static final String DATE_FORMAT_YMD_X = "yyyy/MM/dd";
	
	

	public static final String DATE_FORMAT_YYMMDDHHMM_CN = "yy年MM月dd日 HH:mm";

	public static final String DATE_FORMAT_YMDHM_CN = "yyyy年MM月dd日  HH:mm";

	public static final String DATE_FORMAT_MMDDHHMM_CN = "MM月dd日  HH:mm";

	public static final String DATE_FORMAT_MMDD_CN = "MM月dd日";
	

	public static final String DATE_FORMAT_YMDHMSS_NUM = "yyyyMMddHHmmssSSS";

	public static final String DATE_FORMAT_YMDHMMS_NUM = "yyyyMMddHHmmss";

	public static final String DATE_FORMAT_YMDHMM_NUM = "yyyyMMddHHmm";

	public static final String DATE_FORMAT_YMD_NUM = "yyyyMMdd";

	

	/********************** 日期格式化参数类型结束 ****************************/
	private static Calendar calendar = Calendar.getInstance();
	private DateUtil(){
	}

	/**
	 * @param date 日期
	 * @param time 时间
	 * @return Calendar 合并日期和时�?
	 */
	public static Calendar mergeDateTime(Date date , Time time) {
		Calendar cal = Calendar.getInstance();
		if(date != null) {
			cal.setTime(date);
		}
		if(time != null) {
			Calendar temp = Calendar.getInstance();
			temp.setTime(time);
			cal.set(Calendar.HOUR_OF_DAY, temp.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, temp.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, temp.get(Calendar.MILLISECOND));
		}
		return cal;
	}

	/**
	 * 得到标准的日期
	 * @return String
	 */
	public static String getDateST() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMD,Locale.CANADA);
		return dateFormat.format(new Date());
	}

	/**
	 * 得到标准的日期 时间
	 * @return String
	 */
	public static String getDateTimeST() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
		return dateFormat.format(new Date());
	}

	
	/** 
	 * @Title: getTodayFormat 
	 * @Description: 获取当前 日期 
	 * @date 2017年11月30日 上午10:17:38 
	 * @param format 格式
	 * @return String
	 */
	public static String getTodayFormat(String format) {
		return getFormatDateTime(new Date(),format);
	}

	/**
	 * 得到指定时间的时间日期格式化
	 * @param date 指定的时间
	 * @param format 时间日期格式
	 * @return
	 */
	public static String getFormatDateTime(Date date , String format) {
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}
	
	/** 
	 * @Title: getYear 
	 * @Description: 获取当前 年份
	 * @date 2017年11月30日 上午10:19:13 
	 * @return 年
	 * @return int
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		return calendar.get(Calendar.YEAR);
	}

	
	/** 
	 * @Title: getMonth 
	 * @Description: 获取 当前月份
	 * @date 2017年11月30日 上午10:20:11 
	 * @return 月份
	 * return int
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return calendar.get(Calendar.MONTH) + 1;
	}

	
	/** 
	 * @Title: getDate 
	 * @Description: 获取当前日期是 几号
	 * @date 2017年11月30日 上午10:20:56 
	 * @return 几号
	 * @return int
	 */
	public static int getDate() {
		Calendar calendar = Calendar.getInstance();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return calendar.get(Calendar.DATE);
	}

	/** 
	 * @Title: getHour 
	 * @Description:获取 当前 时 
	 * @date 2017年11月30日 上午10:24:42 
	 * @return 当前时间
	 * @return int
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return calendar.get(Calendar.HOUR);
	}

	/** 
	 * @Title: getMinute 
	 * @Description: 获取 当前 分钟
	 * @date 2017年11月30日 上午10:25:37 
	 * @return 当前 分钟
	 * @return int
	 */
	public static int getMinute() {
		Calendar calendar = Calendar.getInstance();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return calendar.get(Calendar.MINUTE);
	}

	/** 
	 * @Title: getSecond 
	 * @Description: 获取 当前 秒
	 * @date 2017年11月30日 上午10:25:56 
	 * @return int
	 */
	public static int getSecond() {
		Calendar calendar = Calendar.getInstance();
		Date trialTime = new Date();
		calendar.setTime(trialTime);
		return calendar.get(Calendar.SECOND);
	}

	
	/** 
	 * @Title: getDayOfMonth 
	 * @Description: 获取 当前 日期 是当前月份的 几号
	 * @date 2017年11月30日 上午10:26:28 
	 * @return 当前月份的 几号
	 * @return int
	 */
	public static int getDayOfMonth() {
		return getDayOfMonth(new Date());
	}

	/** 
	 * @Title: getDayOfMonth 
	 * @Description: 得到月中的第几天
	 * @date 2017年11月30日 上午10:30:32 
	 * @param trialTime
	 * @return 得到月中的第几天
	 * @return int
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/** 
	 * @Title: getDayOfWeek 
	 * @Description: 获取今天周中的第几天,7 为星期天 
	 * @date 2017年11月30日 上午10:31:42 
	 * @return 获取今天周中的第几天,7 为星期天 
	 * @return int
	 */
	public static int getDayOfWeek() {
		return getDayOfWeek(new Date());
	}

	
	/** 
	 * @Title: getDayOfWeek 
	 * @Description: 得到周中的第几天 ,7 为星期天
	 * @date 2017年11月30日 上午10:33:30 
	 * @param date 
	 * @return 得到周中的第几天 ,7 为星期天
	 * @return int
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1;
	}

	
	/** 
	 * @Title: getAM_PM 
	 * @Description: 得到今天早上下午
	 * @date 2017年11月30日 上午10:34:56 
	 * @return 得到今天 早上 下午
	 * @return int 0:上午，1：下午
	 */
	public static int getAM_PM() {
		return getAM_PM(new Date());
	}


	/** 
	 * @Title: getAM_PM 
	 * @Description:  得到早上下午
	 * @date 2017年11月30日 上午10:36:32 
	 * @param trialTime
	 * @return AM = 0 PM = 1;
	 * @return int 0:上午，1：下午
	 */
	public static int getAM_PM(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.AM_PM);
	}

	/** 
	 * @Title: getCountMonthDay 
	 * @Description: 获取月分的最大天数
	 * @date 2017年11月30日 上午10:38:01 
	 * @param date
	 * @return 获取月分的最大天数
	 * @return int
	 */
	public static int getCountMonthDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); 
		return calendar.getMaximum(Calendar.DATE);
	}


	/** 
	 * @Title: getFirstDayOfWeek 
	 * @Description: 获取 日期 在周中的 开始时间
	 * @date 2017年11月30日 上午10:46:09 
	 * @param date
	 * @return 获取 日期 在周中的 开始时间
	 * @return Date
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c.getTime();
	}

	/** 
	 * @Title: getLastDayOfWeek 
	 * @Description:获取 日期 在周中的 结束时间
	 * @date 2017年11月30日 上午10:47:13 
	 * @param date 获取 日期 在周中的 结束时间
	 * @return Date
	 */
	public static Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
		c.add(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c.getTime();
	}

	
	/** 
	 * @Title: getWeekOfYear 
	 * @Description: 获取当前日期是该年中第几周
	 * @date 2017年11月30日 上午10:57:30 
	 * @param date
	 * @return 获取当前日期是该年中第几周
	 * @return int
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setMinimalDaysInFirstWeek(7);
		c.setTime(date);
		return c.get(Calendar.WEEK_OF_YEAR);
	}

	
	/** 
	 * @Title: getCountWeekOfYear 
	 * @Description: 获取 某年有几周
	 * @date 2017年11月30日 上午10:58:36 
	 * @param year 年份
	 * @return 获取 某年有几周
	 * @return int
	 */
	public static int getCountWeekOfYear(int year) {
		Calendar c = new GregorianCalendar();
		c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		return getWeekOfYear(c.getTime());
	}

	
	/** 
	 * @Title: getBeginMonthDate 
	 * @Description: 获取 该日期 的 所在月份的 起始 日期
	 * @date 2017年11月30日 上午10:59:49 
	 * @param date
	 * @return  获取 该日期 的 所在月份的 起始 日期
	 * @return Date
	 */
	public static Date getBeginMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	
	/** 
	 * @Title: getEndMonthDate 
	 * @Description: 获取 该日期 的 所在月份的 起始 日期
	 * @date 2017年11月30日 上午11:01:09 
	 * @param date
	 * @return 获取 该日期 的 所在月份的 起始 日期
	 * @return Date
	 */
	public static Date getEndMonthDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 放入你的日期
		calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
		calendar.add(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	
	/** 
	 * @Title: getBeginYearDate 
	 * @Description: 获取 该日期 的 所在年份的 起始 日期
	 * @date 2017年11月30日 上午11:01:38 
	 * @param date
	 * @return 获取 该日期 的 所在年份的 起始 日期
	 * @return Date
	 */
	public static Date getBeginYearDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // 放入你的日期
		calendar.set(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/** 
	 * @Title: getBeginYearDate 
	 * @Description: 获取 该日期 的 所在年份的 结束 日期
	 * @date 2017年11月30日 上午11:01:38 
	 * @param date
	 * @return 获取 该日期 的 所在年份的 结束 日期
	 * @return Date
	 */
	public static Date getEndYearDate(Date trialTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(trialTime); 
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DATE, calendar.getMaximum(Calendar.DATE));
		calendar.add(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/** 
	 * @Title: getYear 
	 * @Description: 获取 几年的 前 或者 几年后的今天
	 * @date 2017年11月30日 上午11:03:50 
	 * @param itmp
	 * @return  获取 几年的 前 或者 几年后的今天
	 * @return Date
	 */
	public static Date getYear(int itmp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, itmp);
		return calendar.getTime();
	}

	
	/** 
	 * @Title: getMonth 
	 * @Description: 获取 几个月的 前 或者 几个月后的今天
	 * @date 2017年11月30日 上午11:05:25 
	 * @param itmp
	 * @param date
	 * @return  获取 几个月的 前 或者 几个月后的今天
	 * @return Date
	 */
	public static Date getMonth(int itmp , Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, itmp);
		return calendar.getTime();
	}

	/** 
	 * @Title: getDate 
	 * @Description: 获取 前几天 或者 后几天的日期
	 * @date 2017年11月30日 上午11:06:23 
	 * @param itmp
	 * @return 获取 前几天 或者 后几天的日期
	 * @return Date
	 */
	public static Date getDate(int itmp) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, itmp);
		return calendar.getTime();
	}

	
	
	/** 
	 * @Title: getBeginDateTime 
	 * @Description: 获取 该日期 开始时间 
	 * @date 2017年11月30日 上午11:10:01 
	 * @param date
	 * @return  获取 该日期 开始时间 
	 * @return Date
	 */
	public static Date getBeginDateTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.AM_PM, 0);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	/** 
	 * @Title: getBeginHourDate 
	 * @Description: 获取 该日期 时间 的 最小整点时间 
	 * @date 2017年11月30日 上午11:11:13 
	 * @param date
	 * @return 获取 该日期 时间 的 最小整点时间  如 ：输入  2017-12-04 10:59:59 ，得到 2017-12-04 00:00:00
	 * @return Date
	 */
	public static Date getBeginHourDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}

	
	/** 
	 * @Title: getEndDateTime 
	 * @Description: 获取 该日期 的结束 日期
	 * @date 2017年11月30日 上午11:12:49 
	 * @param date
	 * @return 获取 该日期 的结束 日期
	 * @return Date
	 */
	public static Date getEndDateTime(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getBeginDateTime(date));
		calendar.set(Calendar.AM_PM, 0);
		calendar.add(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/** 
	 * @Title: isInToday 
	 * @Description:判断指定的时间是否是今天
	 * @author: HCD
	 * @date 2017年11月30日 上午11:13:38 
	 * @param date
	 * @return 判断指定的时间是否是今天
	 * @return boolean
	 */
	public static boolean isInToday(Date date) {
		boolean flag = false;
		Date now = new Date();
		String fullFormat = getFormatDateTime(now, DateUtil.DATE_FORMAT_YMD);
		String beginString = fullFormat + " 00:00:00";
		String endString = fullFormat + " 23:59:59";
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMDHMS);
		try {
			Date beginTime = df.parse(beginString);
			Date endTime = df.parse(endString);
			flag = date.before(endTime) && date.after(beginTime);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return flag;
	}

	
	/** 
	 * @Title: getTodayEndTime 
	 * @Description: 获取今天结束时间
	 * @date 2017年11月30日 上午11:15:12 
	 * @return 获取今天结束时间
	 * @return Date
	 */
	public static Date getTodayEndTime() {
		String endString = getFormatDateTime( new Date(),DateUtil.DATE_FORMAT_YMD)
			+ " 23:59:59";
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMDHMS);
		Date endTime = new Date();
		try {
			endTime = df.parse(endString);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return endTime;
	}

	/** 
	 * @Title: getTodayStaTime 
	 * @Description:获取今天开始时间
	 * @date 2017年11月30日 上午11:15:27 
	 * @return 获取今天开始时间
	 * @return Date
	 */
	public static Date getTodayStaTime() {
		String endString = getFormatDateTime(new Date(),DateUtil.DATE_FORMAT_YMD)
			+ " 00:00:01";
		DateFormat df = new SimpleDateFormat(DateUtil.DATE_FORMAT_YMDHMS);
		Date endTime = new Date();
		try {
			endTime = df.parse(endString);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return endTime;
	}

	
	/** 
	 * @Title: compareDay 
	 * @Description: 获取 两个日期 间的 毫秒数
	 * @date 2017年11月30日 上午11:15:57 
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return 获取 两个日期 间的 毫秒数
	 * @return long
	 */
	public static long compareDay(Date beginDate , Date endDate) {
		Calendar endDateYears = new GregorianCalendar();
		endDateYears.setTime(endDate);
		Calendar beginYears = new GregorianCalendar();
		beginYears.setTime(beginDate);
		long diffMillis = endDateYears.getTimeInMillis()- beginYears.getTimeInMillis();
		return diffMillis / (24 * 60 * 60 * 1000);
	}


	/** 
	 * @Title: inDate 
	 * @Description: 判断是否属于这个日期范围
	 * @date 2017年11月30日 上午11:18:02 
	 * @param date 设定时间 
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @return  0:在两个时间中; -1:在开始时间之后; 1:在结束时间之前
	 * @return Integer
	 */
	public static Integer inDate(Date date , Date beginDate , Date endDate) {
		if(date.after(beginDate) && date.before(endDate)) {
			return 0;
		}
		else if(date.after(endDate)) {
			return 1;
		}
		else {
			return -1;
		}
	}

	/** 
	 * @Title: compareHour 
	 * @Description: 获取 两个 日前 之间 相隔的 小时毫秒
	 * @date 2017年11月30日 上午11:22:13 
	 * @param beginDate
	 * @param endDate
	 * @return  获取 两个 日前 之间 相隔的 小时毫秒
	 * @return long
	 */
	public static long compareHour(Date beginDate , Date endDate) {
		Calendar beginYears = new GregorianCalendar();
		beginYears.setTime(beginDate);
		long diffMillis = endDate.getTime() - beginYears.getTimeInMillis();
		return diffMillis / (60 * 60 * 1000);
	}

	
	/** 
	 * @Title: compareMillis 
	 * @Description: 比较当前系统时间与参数时间的差，结果为系统时间-参数时间
	 * @date 2017年11月30日 上午11:23:35 
	 * @param beginDate
	 * @return 比较当前系统时间与参数时间的差，结果为系统时间-参数时间
	 * @return long
	 */
	public static long compareMillis(Date beginDate) {
		Calendar beginYears = new GregorianCalendar();
		beginYears.setTime(beginDate);
		return System.currentTimeMillis() - beginYears.getTimeInMillis();
	}

	
	/** 
	 * @Title: compareMillis 
	 * @Description: 计算两个时间的差
	 * @date 2017年11月30日 上午11:24:16 
	 * @param beginDate
	 * @param endDate
	 * @return long
	 */
	public static long compareMillis(Date beginDate , Date endDate) {
		Calendar beginYears = new GregorianCalendar();
		beginYears.setTime(beginDate);
		Calendar endYears = new GregorianCalendar();
		endYears.setTime(endDate);
		return endYears.getTimeInMillis() - beginYears.getTimeInMillis();
	}

	

	/**
	 * <OPTION value="o">保密</OPTION> <OPTION value="z1">白羊座(3月21--4月19日)</OPTION>
	 * <OPTION value="z2">金牛座(4月20--5月20日)</OPTION>
	 * <OPTION value="z3">双子座(5月21--6月21日)</OPTION>
	 * <OPTION value="z4">巨蟹座(6月22--7月22日)</OPTION>
	 * <OPTION value="z5">狮子座(7月23--8月22日)</OPTION>
	 * <OPTION value="z6">处女座(8月23--9月22日)</OPTION>
	 * <OPTION value="z7">天秤座(9月23--10月23日)</OPTION>
	 * <OPTION value="z8">天蝎座(10月24--11月21日)</OPTION>
	 * <OPTION value="z9">射手座(11月22--12月21日)</OPTION>
	 * <OPTION value="z10">魔羯座(12月22--1月19日)</OPTION>
	 * <OPTION value="z11">水瓶座(1月20--2月18日)</OPTION>
	 * <OPTION value="z12">双鱼座(2月19--3月20日)</OPTION>
	 * @param date 生日日期
	 * @return int 更具上边得到星座
	 */
	static public int getBirthStar(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
		if(dateFormat.format(date).equals("1970-01-01"))
			return 0;
		dateFormat = new SimpleDateFormat("MM");
		int mm = StringUtil.toInt(dateFormat.format(date));
		dateFormat = new SimpleDateFormat("dd");
		int dd = StringUtil.toInt(dateFormat.format(date));
		if((mm == 3 && dd >= 21) || (mm == 4 && dd <= 19))
			return 1;
		if((mm == 4 && dd >= 20) || (mm == 5 && dd <= 20))
			return 2;
		if((mm == 5 && dd >= 21) || (mm == 6 && dd <= 21))
			return 3;
		if((mm == 6 && dd >= 22) || (mm == 7 && dd <= 22))
			return 4;
		if((mm == 7 && dd >= 23) || (mm == 8 && dd <= 22))
			return 5;
		if((mm == 8 && dd >= 23) || (mm == 9 && dd <= 22))
			return 6;
		if((mm == 9 && dd >= 23) || (mm == 10 && dd <= 23))
			return 7;
		if((mm == 10 && dd >= 24) || (mm == 11 && dd <= 21))
			return 8;
		if((mm == 11 && dd >= 22) || (mm == 12 && dd <= 21))
			return 9;
		if((mm == 12 && dd >= 22) || (mm == 1 && dd <= 19))
			return 10;
		if((mm == 1 && dd >= 20) || (mm == 2 && dd <= 18))
			return 11;
		if((mm == 2 && dd >= 19) || (mm == 3 && dd <= 20))
			return 12;
		return 0;
	}

	
	/** 
	 * @Title: getGmtDate 
	 * @Description: 字符串 转日期
	 * @date 2017年11月30日 上午11:27:59 
	 * @param gmt
	 * @return 字符串 转日期
	 * @return Date
	 */
	public static Date getGmtDate(String gmt) {
		if(StringUtil.isNULL(gmt))
			return new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
			DateUtil.DATE_FORMAT_YMDHMS);
		TimeZone timezone = TimeZone.getTimeZone(gmt);
		dateFormat.setTimeZone(timezone);
		try {
			String fullDate = dateFormat.format(new Date());
			dateFormat.setTimeZone(TimeZone.getDefault());
			return dateFormat.parse(fullDate);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	/** 
	 * @Title: toSqlDate 
	 * @Description: 日期 转换 sql 日期
	 * @date 2017年11月30日 上午11:28:34 
	 * @param date
	 * @return 日期 转换 sql 日期
	 * @return java.sql.Date
	 */
	public static java.sql.Date toSqlDate(Date date) {
		if(date == null)
			return null;
		return new java.sql.Date(date.getTime());
	}

	/** 
	 * @Title: toJavaDate 
	 * @Description: sql 日期 转换 日期 
	 * @date 2017年11月30日 上午11:29:02 
	 * @param date
	 * @return sql 日期 转换 日期 
	 * @return Date
	 */
	public static Date toJavaDate(java.sql.Date date) {
		if(date == null)
			return null;
		return new Date(date.getTime());
	}

	/** 
	 * @Title: minus 
	 * @Description: 获取 指定时间 与 当前 时间 的 小时差  例如相差: 38:15:00
	 * @date 2017年11月30日 上午11:30:15 
	 * @param date
	 * @return  获取 指定时间 与 当前 时间 的 小时差  例如相差: 38:15:00
	 * @return String
	 */
	public static String minus(Date date) {
		Date now = new Date();
		if(now.after(date)) {
			return "0";
		}
		else {
			long time = date.getTime() - now.getTime();
			int hour = (int) (time / (60 * 60 * 1000));
			int minute = (int) ((time % (60 * 60 * 1000)) / (60 * 1000));
			int second = (int) (((time % (60 * 60 * 1000)) % (60 * 1000)) / 1000 + 1);
			if(second == 60) {
				second = 0;
				minute += 1;
			}
			if(minute == 60) {
				minute = 0;
				hour += 1;
			}
			return "" + (hour < 10 ? ("0" + hour) : hour) + ":"
				+ (minute < 10 ? ("0" + minute) : minute) + ":"
				+ (second < 10 ? ("0" + second) : second);
		}
	}

	
	/** 
	 * @Title: getTimeLength 
	 * @Description: 毫秒数 转换 成 年月日
	 * @date 2017年11月30日 下午12:32:16 
	 * @param time
	 * @return 毫秒数 转换 成 年月日
	 * @return String
	 */
	public static String getTimeLength(long time) {
		StringBuffer timeLengthBuffer = new StringBuffer();
		long year = time / (365 * 24 * 3600);
		time = time % (365 * 24 * 3600);
		long month = time / (30 * 24 * 3600);
		time = time % (30 * 24 * 3600);
		long day = time / (24 * 3600);
		time = time % (24 * 3600);
		long hour = time / 3600;
		time = time % 3600;
		long min = time / 60;
		time = time % 60;
		long sec = time;
		timeLengthBuffer.append(year == 0 ? "" : year + "年");
		timeLengthBuffer.append(month == 0 ? "" : year + "月");
		timeLengthBuffer.append(day == 0 ? "" : year + "日");
		timeLengthBuffer.append(hour == 0 ? "" : hour + "小时");
		timeLengthBuffer.append(min == 0 ? "0�?" : min + "分钟");
		timeLengthBuffer.append(sec == 0 ? "0�?" : sec + "秒");
		return timeLengthBuffer.toString();
	}

	/** 
	 * @Title: toDateCST 
	 * @Description: string 日期 转换 成 date
	 * @date 2017年11月30日 下午12:33:59 
	 * @param dateStr
	 * @return string 日期 转换 成 date
	 * @return Date
	 */
	public static Date toDateCST(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMDHM);
		try {
			Date date = sdf.parse(dateStr);
			return date;
		}
		catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/** 
	 * @Title: toDate 
	 * @Description: string 类型 转换 yyyy-MM-dd
	 * @date 2017年11月30日 下午12:35:37 
	 * @param dateStr
	 * @return string 类型 转换 yyyy-MM-dd
	 * @return Date
	 */
	public static Date toDate(String dateStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMD);
		try {
			Date date = sdf.parse(dateStr);
			return date;
		}
		catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}

	/** 
	 * @Title: getWeek 
	 * @Description: 根据状态值 获取 星期
	 * @date 2017年11月30日 下午12:37:53 
	 * @param week
	 * @return 根据状态值 获取 星期
	 * @return String
	 */
	public static String getWeek(int week) {
		String str = "";
		switch (week) {
			case 1:
				str = "星期一";
				break;
			case 2:
				str = "星期二";
				break;
			case 3:
				str = "星期三";
				break;
			case 4:
				str = "星期四";
				break;
			case 5:
				str = "星期五";
				break;
			case 6:
				str = "星期六";
				break;
			case 7:
				str = "星期日";
				break;
		}
		return str;
	}

	
	/** 
	 * @Title: longFormat 
	 * @Description: 时间戳转换 string 日期
	 * @date 2017年11月30日 下午12:39:00 
	 * @param ts 时间戳
	 * @param format 需要转换成的格式
	 * @return 时间戳转换 string 日期
	 * @return String
	 */
	public static String longToStringFormat(long ts , String format) {
		Date date = new Date(ts);
		SimpleDateFormat df = new SimpleDateFormat(format);
		String result = df.format(date);
		return result;
	}

	
	/** 
	 * @Title: longToYMDHMS 
	 * @Description:时间戳 转化为 默认格式的 时间字符串 yyyy-MM-dd HH:mm:ss
	 * @date 2017年11月30日 下午12:51:10 
	 * @param ts 时间戳
	 * @return 时间戳 转化为 默认格式的 时间字符串 yyyy-MM-dd HH:mm:ss
	 * @return String
	 */
	public static String longToYMDHMS(long ts) {
		Date date = new Date(ts);
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
		String result = df.format(date);
		return result;
	}

	/** 
	 * @Title: longToYMD 
	 * @Description: 时间戳 转化为 默认格式的 时间字符串 yyyy-MM-dd
	 * @date 2017年11月30日 下午12:53:56 
	 * @param 时间戳
	 * @return 时间戳 转化为 默认格式的 时间字符串 yyyy-MM-dd
	 * @return String
	 */
	public static String longToYMD(long ts) {
		Date date = new Date(ts);
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YMD);
		String result = df.format(date);
		return result;
	}

	/** 
	 * @Title: YMDToLong 
	 * @Description: string(yyyy-MM-dd)类型转换为long类型
	 * @date 2017年11月30日 下午12:59:36 
	 * @param date string(yyyy-MM-dd)类型
	 * @return string(yyyy-MM-dd)类型转换为long类型
	 * @throws ParseException 异常
	 * @return Long 时间戳
	 */
	public static Long YMDToLong(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMD);
		Date dt2 = sdf.parse(date);
		long lTime = dt2.getTime();
		return lTime;
	}

	/** 
	 * @Title: YMDHMSTolong 
	 * @Description: string(yyyy-MM-dd HH:mm:ss)类型转换为long类型
	 * @date 2017年11月30日 下午1:01:12 
	 * @param date  string(yyyy-MM-dd HH:mm:ss)类型
	 * @return string(yyyy-MM-dd HH:mm:ss)类型转换为long类型
	 * @throws ParseException 异常
	 * @return Long 时间戳
	 */
	public static Long YMDHMSTolong(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
		Date dt2 = sdf.parse(date);
		long lTime = dt2.getTime();
		return lTime;
	}

	/** 
	 * @Title: getFirstEndOfDate 
	 * @Description:获取某一个日期的起止时间戳
	 * @date 2017年11月30日 下午1:02:55 
	 * @param date 
	 * @return 获取某一个日期的起止时间戳
	 * @throws ParseException 
	 * @return long[]
	 */
	public static long[] getFirstEndOfDate(String date) throws ParseException {
		long[] result = new long[2];
		SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT_YMD);
		Date d = df.parse(date);
		long sts = d.getTime();
		result[0] = sts;
		result[1] = sts + 86400000 - 1;
		return result;
	}

	/** 
	 * @Title: getjieGuo 
	 * @Description: 判断是否属于这个日期范围，转换天判断
	 * @date 2017年11月30日 下午1:03:48 
	 * @param date 实际进入时间，或者出入时间
	 * @param beginDate 记录进入时间
	 * @param endDate 记录出入时间
	 * @return 0:在两个时间中; 1:不在里面;
	 * @throws ParseException 
	 * @return int 0:在两个时间中; 1:不在里面;
	 */
	public static int getjieGuo(Date date , Date beginDate , Date endDate) throws ParseException {
		Long dates = DateUtil.YMDToLong(DateUtil.longToYMD(date.getTime()));
		Long beginDates = DateUtil.YMDToLong(DateUtil.longToYMD(beginDate.getTime()));
		Long endDates = DateUtil.YMDToLong(DateUtil.longToYMD(endDate.getTime()));
		if(dates >= beginDates && dates <= endDates) {
			return 0;
		}
		else {
			return 1;
		}
	}

	/** 
	 * @Title: stringToLong 
	 * @Description: 将string(yyyy-MM-dd HH:mm:ss)类型转换为long类型
	 * @date 2017年11月30日 下午1:05:26 
	 * @param string  
	 * @return 将string(yyyy-MM-dd HH:mm:ss)类型转换为long类型
	 * @throws ParseException 
	 * @return Long
	 */
	public static Long stringToLong(String string) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_YMDHMS);
		Date dt2 = sdf.parse(string);
		long lTime = dt2.getTime();
		return lTime;
	}

	
	/** 
	 * @Title: parse 
	 * @Description: 将字符串解析成date 错误返回null
	 * @date 2017年11月30日 下午1:08:25 
	 * @param str
	 * @param format 为null时默认 yyyy-MM-dd HH:mm:ss
	 * @return  将字符串解析成date 错误返回null
	 * @return Date
	 */
	public static Date parse(String str , String format) {
		if(format == null || "".equals(format)) {
			format = DATE_FORMAT_YMDHMS;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(str);
			return date;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/** 
	 * @Title: format 
	 * @Description: 将字符串解析成date 错误返回null
	 * @date 2017年11月30日 下午1:11:38 
	 * @param date 日期 
	 * @param format 为null时 默认 yyyy-MM-dd HH:mm:ss
	 * @return date 转化成 String 类型 
	 * @return String
	 */
	public static String format(Date date , String format) {
		if(date == null) {
			return null;
		}
		if(format == null || "".equals(format)) {
			format = DATE_FORMAT_YMDHMS;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	
	/** 
	 * @Title: parse 
	 * @Description: string转换 date
	 * @date 2017年11月30日 下午1:14:28 
	 * @param str 
	 * @return string转换 date(yyyy-MM-dd HH:mm:ss)
	 * @return Date
	 */
	public static Date parse(String str) {
		return parse(str, DATE_FORMAT_YMDHMS);
	}

	/**
	 * 新建当前日期，并按公司日期格式输出
	 * @return
	 */
	/*public static String now() {
		return format(new Date());
	}*/

	/**
	 * 按公司日期格式输出
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, null);
	}

	
	/** 
	 * @Title: format 
	 * @Description: string 类型转换 string 
	 * @date 2017年11月30日 下午1:40:06 
	 * @param str
	 * @param format
	 * @return  string 类型转换 string (指定格式)
	 * @return String
	 */
	public static String format(String str , String format) {
		return format(parse(str), format);
	}

	
	/** 
	 * @Title: getLong 
	 * @Description: string 转换 时间戳
	 * @date 2017年11月30日 下午1:39:34 
	 * @param str
	 * @return string 转换 时间戳
	 * @return long
	 */
	public static long getLong(String str) {
		Date date = parse(str);
		return date.getTime();
	}

	
	/** 
	 * @Title: converJavaSqlDate 
	 * @Description:  date转换为数据库需要的date
	 * @date 2017年11月30日 下午2:00:28 
	 * @param date
	 * @return  date转换为数据库需要的date 只用于数据库的存入
	 * @return java.sql.Date
	 */
	public static java.sql.Date converJavaSqlDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	/** 
	 * @Title: converJavaSqlDate 
	 * @Description:  将字符串的日期转换成数据库需要的date 只用于数据库的存入
	 * @date 2017年11月30日 下午2:00:56 
	 * @param str
	 * @return 将字符串的日期转换成数据库需要的date 只用于数据库的存入
	 * @return java.sql.Date
	 */
	public static java.sql.Date converJavaSqlDate(String str) {
		return converJavaSqlDate(parse(str));
	}

	/** 
	 * @Title: converDate 
	 * @Description: date转换为数据库需要的date 只用于数据库的读取
	 * @date 2017年11月30日 下午2:01:23 
	 * @param date
	 * @return date转换为数据库需要的date 只用于数据库的读取
	 * @return Date
	 */
	public static Date converDate(java.sql.Date date) {
		return new Date(date.getTime());
	}

	/** 
	 * @Title: converDate 
	 * @Description: 将字符串的日期转换成数据库需要的date
	 * @date 2017年11月30日 下午2:01:44 
	 * @param str
	 * @return 将字符串的日期转换成数据库需要的date 默认公司的日期形式"yyyy-MM-dd HH:mm:ss"
	 * @return Date
	 */
	public static Date converDate(String str) {
		return converDate(converJavaSqlDate(str));
	}

	/** 
	 * @Title: buildMongoDate 
	 * @Description: 构建存储到mongo的日期类型
	 * @date 2017年11月30日 下午2:02:04 
	 * @param date
	 * @return 构建存储到mongo的日期类型
	 * @return Date
	 */
	public static Date buildMongoDate(Date date) {
		if(date == null) {
			return date;
		}
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + 8);
		return calendar.getTime();
	}

	/** 
	 * @Title: buildMongoDate 
	 * @Description: 构建存储到mongo的日期类型
	 * @date 2017年11月30日 下午2:02:18 
	 * @return 构建存储到mongo的日期类型
	 * @return Date
	 */
	public static Date buildMongoDate() {
		return buildMongoDate(new Date());
	}

	/** 
	 * @Title: getNextYear 
	 * @Description: 获取明年的今天
	 * @date 2017年11月30日 下午2:02:32 
	 * @return 获取明年的今天
	 * @return String
	 */
	public static String getNextYear() {
		Calendar calendar = Calendar.getInstance();
		calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, 1);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(calendar.getTime());
	}

	/** 
	 * @Title: getTodayStartTime 
	 * @Description: 获取今天开始时间戳
	 * @date 2017年11月30日 下午2:02:47 
	 * @return 获取今天开始时间戳
	 * @return long
	 */
	public static long getTodayStartTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTimeInMillis();
	}

	/** 
	 * @Title: getThisMonthSatDay 
	 * @Description: 获取上月第一天：
	 * @date 2017年11月30日 下午2:03:03 
	 * @return 获取上月第一天：
	 * @return Date
	 */
	public static Date getThisMonthSatDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		// 设置为1号,当前日期既为本月第一天
		c.set(Calendar.DAY_OF_MONTH, 1);
		String first = format.format(c.getTime());
		return parse(first, DATE_FORMAT_YYMMDD);
	}

	/** 
	 * @Title: getThisMonthEndDay 
	 * @Description: 获取上月最后一天
	 * @date 2017年11月30日 下午2:03:22 
	 * @return 获取上月最后一天
	 * @return Date
	 */
	public static Date getThisMonthEndDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MONTH, -1);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		String last = format.format(ca.getTime());
		return parse(last, DATE_FORMAT_YYMMDD);
	}

	/** 
	 * @Title: getYearMonth 
	 * @Description: 获取上月 ：年月
	 * @date 2017年11月30日 下午2:03:35 
	 * @return 获取上月 ：年月
	 * @return String
	 */
	public static String getYearMonth() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		String yearMonth = format.format(c.getTime());
		return yearMonth;
	}

	/** 
	 * @Title: thisMonthOneDay 
	 * @Description: 本月第一天
	 * @date 2017年11月30日 下午2:03:55 
	 * @return 本月第一天
	 * @return String
	 */
	public static String thisMonthOneDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		// 获取前月的第一天
		Calendar cal_1 = Calendar.getInstance();// 获取当前日期
		cal_1.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(cal_1.getTime());
		return firstDay;
	}

	/** 
	 * @Title: thisMonthLastDay 
	 * @Description:本月结束时间
	 * @date 2017年11月30日 下午2:04:16 
	 * @return 本月结束时间
	 * @return String
	 */
	public static String thisMonthLastDay() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 11:59:59");
		// 获取前月的最后一天
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.MONTH, 1);
		cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}

	/** 
	 * @Title: getYesterDaySta 
	 * @Description: 获取昨天结束时间
	 * @date 2017年11月30日 下午2:04:30 
	 * @return 获取昨天结束时间
	 * @return String
	 */
	public static String getYesterDaySta() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.DATE, -1);
		String lastDay = format.format(cale.getTime());
		return lastDay;
	}

	/**
	 * @Title: getAppointDate
	 * @Description: 获取指定天数的日期
	 * @date 2017年11月11日 上午9:09:47
	 * @param day 天数
	 * @return Date
	 */
	public static Date getAppointDate(int day) {
		Calendar cale = Calendar.getInstance();
		cale.add(Calendar.DATE, day);
		return cale.getTime();
	}

	public static void main(String[] args) {
		// System.out.println("thisMonthOneDay()="+thisMonthOneDay());
		// System.out.println("thisMonthLastDay()="+thisMonthLastDay());
		 System.out.println("getTodayStaTime()="+getLong("2017-12-01 00:00:00"));
		// System.out.println("getTodayEndTime()="+DateUtil.parse("2017-09-01 00:00:00",null));
		// System.out.println("getTodayEndTime()="+DateUtil.long2YMDHMS(1508036285353L));
		// System.out.println("getTodayEndTime()="+DateUtil.long2YMDHMS(1508036347573L));
		System.out.println("getAppointDate()=" + getFormatDateTime(getLastDayOfWeek(new Date()),DATE_FORMAT_YMDHMS));
		System.out.println( getBeginHourDate(new Date()) );
		System.out.println( getAppointDate(-90));
		// System.out.println("getTodayEndTime()="+DateUtil.parse(DateUtil.getYesterDaySta(),null));
		// System.out.println("getTodayEndTime()="+(new Date().getYear()+1900)+"-"+(new
		// Date().getMonth()+1));
	}
}
