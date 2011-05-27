/**
 * 
 */
package q.domain;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 16, 2011
 * 
 */
public class GenderTest {

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
	 * Test method for {@link q.domain.Gender#valid(int)}.
	 */
	@Test
	public void testValid() {
		Assert.assertTrue(Gender.valid(1));
		Assert.assertTrue(Gender.valid(2));
		Assert.assertFalse(Gender.valid(3));
		Assert.assertFalse(Gender.valid(0));
	}

}
