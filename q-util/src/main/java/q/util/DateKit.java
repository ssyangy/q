/**
 * taobao.com 2008 copyright
 */
package q.util;

import static java.util.Calendar.DATE;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The Class DateKit.
 * 
 * @version 2010-3-27
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 */
public class DateKit {

	/**
	 * Ymd or ymdhms2 date.
	 * 
	 * @param str
	 *            the str
	 * 
	 * @return the date
	 * 
	 * @throws ParseException
	 *             the parse exception
	 */
	public static Date ymdOrYmdhms2Date(String str) throws ParseException {
		if (str == null)
			return null;
		if (str.length() != 10 && str.length() != 19 && str.length() != 23) {
			throw new ParseException("error date:" + str, 0);
		}

		char[] strs = str.toCharArray();
		Calendar cal = null;
		try {
			int year = parseInt(strs, 0, 4);
			int month = parseInt(strs, 5, 7) - 1;
			int date = parseInt(strs, 8, 10);
			if (strs.length >= 19) {
				int hrs = parseInt(strs, 11, 13);
				int min = parseInt(strs, 14, 16);
				int sec = parseInt(strs, 17, 19);
				cal = new GregorianCalendar(year, month, date, hrs, min, sec);
				if (strs.length == 23) {
					int sss = parseInt(strs, 20, 23);
					cal.set(Calendar.MILLISECOND, sss);
				}
			} else {
				cal = new GregorianCalendar(year, month, date);
			}
			return cal.getTime();
		} catch (ParseException e) {
			throw e;
		}
	}

	/**
	 * 将字符数组的指定位转换为int
	 * 
	 * @param strs
	 * @param beginindex
	 * @param endindex
	 * @return
	 * @throws Exception
	 */
	private static int parseInt(char[] strs, int beginindex, int endindex) throws ParseException {
		int result = 0;
		int b = 1;
		for (int i = endindex - 1; i >= beginindex; i--) {
			if (strs[i] < 48 || strs[i] > 57) {
				throw new ParseException("Parse error,can't parse char to int . ", 0);
			}
			result = result + (strs[i] - 48) * b;
			b *= 10;
		}
		return result;
	}

	/**
	 * 2011-03-11 11:24:20
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Ymdhms(Date date) {
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int y = c.get(GregorianCalendar.YEAR);
		int m = c.get(GregorianCalendar.MONTH) + 1;
		int d = c.get(GregorianCalendar.DAY_OF_MONTH);
		int h = c.get(GregorianCalendar.HOUR_OF_DAY);
		int minute = c.get(GregorianCalendar.MINUTE);
		int s = c.get(GregorianCalendar.SECOND);

		char[] str = new char[19];
		str[4] = str[7] = '-';
		str[0] = (char) (y / 1000 + '0');
		str[1] = (char) (y / 100 % 10 + '0');
		str[2] = (char) (y / 10 % 10 + '0');
		str[3] = (char) (y % 10 + '0');

		str[5] = (char) (m / 10 + '0');
		str[6] = (char) (m % 10 + '0');
		str[8] = (char) (d / 10 + '0');
		str[9] = (char) (d % 10 + '0');

		str[10] = ' ';

		str[13] = str[16] = ':';
		str[11] = (char) (h / 10 + '0');
		str[12] = (char) (h % 10 + '0');
		str[14] = (char) (minute / 10 + '0');
		str[15] = (char) (minute % 10 + '0');
		str[17] = (char) (s / 10 + '0');
		str[18] = (char) (s % 10 + '0');

		return new String(str);
	}

	/**
	 * 03-12 11:24
	 * 
	 * @param date
	 * @return
	 */
	public static String date2Mdhm(Date date) {
		if (null == date)
			return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int m = c.get(GregorianCalendar.MONTH) + 1;
		int d = c.get(GregorianCalendar.DAY_OF_MONTH);
		int h = c.get(GregorianCalendar.HOUR_OF_DAY);
		int minute = c.get(GregorianCalendar.MINUTE);

		char[] str = new char[11];
		str[0] = (char) (m / 10 + '0');
		str[1] = (char) (m % 10 + '0');
		str[2] = '-';
		str[3] = (char) (d / 10 + '0');
		str[4] = (char) (d % 10 + '0');
		str[5] = ' ';
		str[6] = (char) (h / 10 + '0');
		str[7] = (char) (h % 10 + '0');
		str[8] = ':';
		str[9] = (char) (minute / 10 + '0');
		str[10] = (char) (minute % 10 + '0');
		return new String(str);
	}

	public static String date2SinaStyle(Date before, Date base) {
		StringBuilder sb = new StringBuilder(16); // "2011-11-12 24:24".length==16
		long seconds = (base.getTime() - before.getTime()) / 1000;
		if (seconds <= 0) {
			sb.append("10秒前");
		} else if (seconds <= 50) {
			sb.append(seconds % 10 == 0 ? seconds : seconds / 10 * 10 + 10);
			sb.append("秒前");
		} else if (seconds <= 60) { // in one minute
			sb.append("1分钟前");
		} else if (seconds <= 3600) { // in one hour
			sb.append(seconds / 60);
			sb.append("分钟前");
		} else if (seconds > 3600) { // more than one hour
			Calendar baseCal = new GregorianCalendar();
			baseCal.setTime(base);
			Calendar beforeCal = new GregorianCalendar();
			beforeCal.setTime(before);
			if (beforeCal.get(YEAR) != baseCal.get(YEAR)) { // different year
				sb.append(beforeCal.get(YEAR));
				sb.append('-');
				sb.append(beforeCal.get(MONTH) + 1);
				sb.append('-');
				sb.append(beforeCal.get(DATE));
			} else { // the same year
				if (beforeCal.get(MONTH) == baseCal.get(MONTH) && beforeCal.get(DATE) == baseCal.get(Calendar.DATE)) {
					sb.append("今天"); // today
				} else { // not today
					sb.append(beforeCal.get(MONTH) + 1);
					sb.append("月");
					sb.append(beforeCal.get(DATE));
					sb.append("日");
				}
			}
			sb.append(' ');
			int hour = beforeCal.get(HOUR_OF_DAY);
			if (hour < 10) {
				sb.append(0);
			}
			sb.append(hour);
			sb.append(':');
			int min = beforeCal.get(MINUTE);
			if (min < 10) {
				sb.append(0);
			}
			sb.append(min);
		}
		return sb.toString();
	}

	/**
	 * @param started
	 * @return
	 */
	public static String date2Md(Date date) {
		if (null == date)
			return null;
		Calendar c = new GregorianCalendar();
		c.setTime(date);
		int m = c.get(GregorianCalendar.MONTH) + 1;
		int d = c.get(GregorianCalendar.DAY_OF_MONTH);
		StringBuilder sb = new StringBuilder(6);
		if (m < 10) {
			sb.append(0);
		}
		sb.append(m);
		sb.append('-');
		if (d < 10) {
			sb.append(0);
		}
		sb.append(d);
		return sb.toString();
	}

	/**
	 * Check the date is exists.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean isExists(int year, int month, int day) {
		if (year < 1 || month < 1 || month > 12 || day < 1 || day > 31) {
			return false;
		}
		month = month - 1;
		GregorianCalendar gc = new GregorianCalendar(year, month, day);
		return (gc.get(Calendar.YEAR) == year) && (gc.get(Calendar.MONTH) == month) && (gc.get(Calendar.DAY_OF_MONTH) == day);
	}

}
