/**
 * 
 */
package q.util;

import java.net.UnknownHostException;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang at gmail dot com
 * @date Apr 9, 2011
 * 
 */
public class IdCreatorTest {

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

	/**
	 * id e.g: 946771200000(timestamp)19(counter)104(ip flag)0(version)
	 * 
	 * Test method for {@link q.util.IdCreator#getLongId()}.
	 * 
	 * @throws UnknownHostException
	 */
	@Test
	public void testGetLongId() throws UnknownHostException {
		long first = IdCreator.getLongId();
		for (int i = 0; i < 1000; i++) {
			long second = IdCreator.getLongId();
			Assert.assertTrue((second - first) % (1000 * 10) == 0); // 1000 indicate ip flag length
			first = IdCreator.getLongId();
		}
	}

	/**
	 * 
	 * Ref:
	 * 
	 * java: long 8 bytes signed (two's complement). Ranges from -9,223,372,036,854,775,808 to +9,223,372,036,854,775,807.
	 * 
	 * mysql: BIGINT 8 bytes signed ranges from -9,223,372,036,854,775,808 to 9,223,372,036,854,775,807, unsigned 18,446,744,073,709,551,615
	 * 
	 */
	@Test
	public void testGetLongIdLength() {
		Calendar afterCal = Calendar.getInstance();
		afterCal.set(2041, 0, 1, 0, 0, 0);
		Calendar baseCal = Calendar.getInstance();
		baseCal.set(2011, 0, 1, 0, 0, 0);
		System.out.println(afterCal.getTimeInMillis() - baseCal.getTimeInMillis());
		Assert.assertTrue(10000L * 10000 * 10000 > afterCal.getTimeInMillis() - baseCal.getTimeInMillis());
		Assert.assertTrue(afterCal.getTimeInMillis() - baseCal.getTimeInMillis() > 1000L * 10000 * 10000);
	}

}
