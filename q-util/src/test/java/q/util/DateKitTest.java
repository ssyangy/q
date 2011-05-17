/**
 * 
 */
package q.util;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 26, 2011
 * 
 */
public class DateKitTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDate2SinaStyleLastYear() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 22, 22);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.YEAR, 2011);
		Date now = beforeCal.getTime();
		Assert.assertEquals("2010-6-25 22:22", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2SinaStyleTheSameYear() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 22, 22);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.MONTH, 7);
		Date now = beforeCal.getTime();
		Assert.assertEquals("6月25日 22:22", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2SinaStyleTheSameDayMoreOrEqualsOneHour() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 21, 22, 22);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.HOUR_OF_DAY, 23);
		Date now = beforeCal.getTime();
		Assert.assertEquals("今天 21:22", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2SinaStyleTheSameDayPrefixZero() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 1, 1, 0);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.HOUR_OF_DAY, 23);
		Date now = beforeCal.getTime();
		Assert.assertEquals("今天 01:01", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2SinaStyleMoreOneMinuteLessOrEqualsOneHour() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 0, 0);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.MINUTE, 5);
		Date now = beforeCal.getTime();
		Assert.assertEquals("5分钟前", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2SinaStyleOneHour() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 0, 0);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.MINUTE, 60);
		Date now = beforeCal.getTime();
		Assert.assertEquals("60分钟前", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2SinaStyleInOneMinute() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 22, 0);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.SECOND, 5);
		Date now = beforeCal.getTime();
		Assert.assertEquals("10秒前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 0);
		now = beforeCal.getTime();
		Assert.assertEquals("10秒前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 10);
		now = beforeCal.getTime();
		Assert.assertEquals("10秒前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 15);
		now = beforeCal.getTime();
		Assert.assertEquals("20秒前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 45);
		now = beforeCal.getTime();
		Assert.assertEquals("50秒前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 50);
		now = beforeCal.getTime();
		Assert.assertEquals("50秒前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 55);
		now = beforeCal.getTime();
		Assert.assertEquals("1分钟前", DateKit.date2SinaStyle(before, now));

		beforeCal.set(Calendar.SECOND, 60);
		now = beforeCal.getTime();
		Assert.assertEquals("1分钟前", DateKit.date2SinaStyle(before, now));
	}

	/**
	 * 创建时间超前于web服务器
	 */
	@Test
	public void testDate2SinaStyleViewBeforeCreate() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 22, 0);
		Date before = beforeCal.getTime();
		beforeCal.set(Calendar.HOUR_OF_DAY, 1);
		Date now = beforeCal.getTime();
		Assert.assertEquals("10秒前", DateKit.date2SinaStyle(before, now));
	}

	@Test
	public void testDate2Mdhm() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 22, 0);
		Assert.assertEquals("06-25 22:22", DateKit.date2Mdhm(beforeCal.getTime()));
	}

	@Test
	public void testDate2YMdhms() {
		Calendar beforeCal = Calendar.getInstance();
		beforeCal.set(2010, 5, 25, 22, 22, 0);
		Assert.assertEquals("2010-06-25 22:22:00", DateKit.date2Ymdhms(beforeCal.getTime()));
	}

	@Test
	public void testIsExists() {
		Assert.assertFalse(DateKit.isExists(2000, 0, 31));
		Assert.assertFalse(DateKit.isExists(2000, 4, 31));

		Assert.assertTrue(DateKit.isExists(2000, 1, 31));
		Assert.assertTrue(DateKit.isExists(2000, 2, 29));
		Assert.assertTrue(DateKit.isExists(2004, 2, 29));
	}

}
