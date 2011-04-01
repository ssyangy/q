package q.util;

import junit.framework.Assert;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 10, 2011
 * 
 */
public class StringKitTest {

	@Test
	public void testCapitalize() {
		Assert.assertEquals(null, StringKit.capitalize(null));
		Assert.assertEquals("", StringKit.capitalize(""));
		Assert.assertEquals(" ", StringKit.capitalize(" "));
		Assert.assertEquals("Test", StringKit.capitalize("test"));
		Assert.assertEquals("Test", StringKit.capitalize("Test"));
		Assert.assertEquals("T", StringKit.capitalize("t"));
		Assert.assertEquals("T", StringKit.capitalize("T"));
		Assert.assertEquals("ATest", StringKit.capitalize("aTest"));
		Assert.assertEquals("ATest", StringKit.capitalize("ATest"));
	}

	/**
	 * StringKit.capitalize() is 5 times speed than org.apache.commons.lang.StringUtils.caplitalize()
	 */
	private void testCapitalizeSpeed() {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++)
			StringUtils.capitalize("Tsdaf");
		long end = System.currentTimeMillis();
		System.out.println(end - start);

		start = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++)
			StringKit.capitalize("Tsdaf");
		end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
	@Test
	public void testEscapeJson() {
		Assert.assertEquals("\\\"", StringKit.escapeJson("\r\n\""));
	}

	public static void main(String[] args) {
		new StringKitTest().testCapitalizeSpeed();
	}
}
