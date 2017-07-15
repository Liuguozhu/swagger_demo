package org.sidao.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class DateUtils2 {
	public static String DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	public static Date pureDate() {
		try {
			String s = yyyymmdd_dmf.format(new Date());
			return yyyymmdd_dmf.parse(s);
		} catch (Exception e) {
			return null;
		}

	}

	static SimpleDateFormat yyyymmdd_dmf = new SimpleDateFormat("yyyy/MM/dd");

	static SimpleDateFormat yyyymmdd_NoSlash_dmf = new SimpleDateFormat(
			"yyyyMMdd");

	static SimpleDateFormat yyyymmddhhmm_dmf = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm");

	static SimpleDateFormat yyyymm_NoSlash_dmf = new SimpleDateFormat("yyyyMM");

	public static Date backward(Date date, int step, int field) {
		Calendar tempc = Calendar.getInstance();
		tempc.setTime(date);
		tempc.add(field, -step);
		return tempc.getTime();
	}

	public static Date forward(Date date, int step, int field) {
		Calendar tempc = Calendar.getInstance();
		tempc.setTime(date);
		tempc.add(field, step);
		return tempc.getTime();
	}

	public static Date purify(Date date) {
		try {
			String s = yyyymmdd_dmf.format(date);
			return yyyymmdd_dmf.parse(s);
		} catch (Exception e) {
			return null;
		}
	}

	public static String toYYYYMM_NoSlash(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return yyyymm_NoSlash_dmf.format(d);

	}

	public static String toYYYYMMDDhhmm(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return yyyymmddhhmm_dmf.format(d);

	}

	static SimpleDateFormat YYYYMMDDhhmmss_NoSlash_dmf = new SimpleDateFormat(
			"yyyyMMdd:HH:mm:ss");

	static SimpleDateFormat YYYYMMDDhhmmss_continue = new SimpleDateFormat(
			"yyyyMMdd_HHmmss_SS");

	public static String toYYYYMMDDhhmmss_continue(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return YYYYMMDDhhmmss_continue.format(d);
	}

	public static String toYYYYMMDDhhmmss_NoSlash(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return YYYYMMDDhhmmss_NoSlash_dmf.format(d);
	}

	static SimpleDateFormat YYYYMMDDhhmmss_dmf = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");

	public static String toYYYYMMDDhhmmss(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return YYYYMMDDhhmmss_dmf.format(d);
	}

	public static String toYYYYMMDD_NoSlash(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return yyyymmdd_NoSlash_dmf.format(d);

	}

	public static String toYYYYMMDD(Date d, boolean blankIfnull) {
		if (d == null)
			if (blankIfnull)
				return "";
			else
				return null;
		return yyyymmdd_dmf.format(d);

	}

	public static Date CreateFromHMS(String hms) throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		return fmt.parse(hms);
	}

	public static Date fromYYYYMMDD(String yyyymmdd) {

		if (StringUtils.isBlank(yyyymmdd) == false) {
			SimpleDateFormat dmf;
			if (yyyymmdd.contains("/"))
				dmf = new SimpleDateFormat("yyyy/MM/dd");
			else
				dmf = new SimpleDateFormat("yyyyMMdd");
			try {
				return dmf.parse(yyyymmdd);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	public static boolean isValidTime(String format, String value) {
		try {
			SimpleDateFormat fmt = new SimpleDateFormat(format);
			if (fmt.parse(value) != null)
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static int DateCompareOnDay(Date date1, Date date2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);

		int y1,  d1;
		int y2,  d2;

		d1 = c1.get(Calendar.DAY_OF_YEAR);
		d2 = c2.get(Calendar.DAY_OF_YEAR);
		y1 = c1.get(Calendar.YEAR);
		y2 = c2.get(Calendar.YEAR);

		if (y1 > y2)
			return 1;
		if (y1 < y2)
			return -1;
		if (d1 > d2)
			return 1;
		if (d1 < d2)
			return -1;
		return 0;
	};

	public static Date getPreviousDayEnd(Date date) {
		Calendar c = Calendar.getInstance();
		int year, month, day;
		c.setTime(date);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		c.set(year, month, day - 1, 23, 59, 59);
		return c.getTime();
	}

	public static YMD getYMD(Date date) {
		YMD ymd = new YMD();

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		ymd.year = c.get(Calendar.YEAR);
		ymd.month = c.get(Calendar.MONTH) + 1;
		ymd.day = c.get(Calendar.DAY_OF_MONTH);

		ymd.yyyy = String.valueOf((ymd.year));
		ymd.yy = String.valueOf((ymd.year - 1911));
		ymd.MM = String.format("%1$02d", ymd.month);
		ymd.dd = String.format("%1$02d", ymd.day);

		return ymd;
	}

	public static Date getDayStart(Date date) {
		Calendar c = Calendar.getInstance();
		int year, month, day;
		c.setTime(date);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	public static Date getDayEnd(Date date) {
		Calendar c = Calendar.getInstance();
		int year, month, day;
		c.setTime(date);
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		c.set(year, month, day, 23, 59, 59);
		return c.getTime();
	}

	public static List<Date> getDayInternal(Date date) {
		return getDayInternal(date, date);
	}

	public static List<Date> getDayInternal(Date start, Date end) {
		if (end == null)
			end = start;
		List<Date> rtl = new ArrayList<Date>();
		rtl.add(getDayStart(start));
		rtl.add(getDayEnd(end));
		return rtl;
	}

	public static boolean isSunday(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int wd = c.get(Calendar.DAY_OF_WEEK);
		if ((wd == Calendar.SUNDAY))
			return true;
		return false;
	}

	public static boolean isWeekend(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return isWeekend(c);
	}

	public static boolean isWeekend(Calendar c) {
		int wd = c.get(Calendar.DAY_OF_WEEK);
		if ((wd == Calendar.SUNDAY) || (wd == Calendar.SATURDAY))
			return true;
		return false;
	}

	public static boolean isSameMonth(Date d1, Date d2) {
		return toYYYYMM_NoSlash(d1, true).equals(toYYYYMM_NoSlash(d2, true));

	}
}