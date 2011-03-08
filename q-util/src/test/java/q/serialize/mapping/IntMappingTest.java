/**
 * 
 */
package q.serialize.mapping;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.mapping.IntMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;

// TODO: Auto-generated Javadoc
/**
 * The Class LongMappingTest.
 * 
 * @author alin
 * @date May 25, 2009
 */
public class IntMappingTest {

	private IntMapping lm = null;

	/**
	 * Sets the up before class.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		lm = new IntMapping();
	}

	/**
	 * Tear down.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.taobao.top.mapping.core.LongMapping#mappingInternal(java.lang.Object, com.taobao.top.mapping.core.ExceptionExpress, boolean)}
	 * .
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 * @throws MappingException
	 *             the mapping exception
	 */
	@Test
	public void testMapping() throws MappingException, OperationCodeException {
		Assert.assertEquals(1, lm.mapping(1, null, false).intValue());
		Assert.assertEquals(1, lm.mapping(1, null, false).intValue());
		Assert.assertEquals(1, lm.mapping((byte) 1, null, false).intValue());
		Assert.assertEquals(1, lm.mapping((short) 1, null, false).intValue());
	}

	@Test
	public void testEmpty() throws MappingException, OperationCodeException {
		try {
			lm.mapping(null, null, false);
			Assert.fail();
		} catch (MappingException e) {

		}
		try {
			lm.mapping("", null, false);
			Assert.fail();
		} catch (Exception e) {

		}
	}
}
