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

import q.serialize.mapping.LongMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;
/**
 * The Class LongMappingTest.
 * 
 * @author alin
 * @date May 25, 2009
 */
public class LongMappingTest {
	
	/** The lm. */
	private LongMapping lm = null;

	/**
	 * Sets the up before class.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		lm = new LongMapping();
	}

	/**
	 * Tear down.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.taobao.top.mapping.core.LongMapping#mappingInternal(java.lang.Object, com.taobao.top.mapping.core.ExceptionExpress, boolean)}
	 * .
	 * 
	 * @throws OperationCodeException the operation code exception
	 * @throws MappingException the mapping exception
	 */
	@Test
	public void testMapping() throws MappingException, OperationCodeException {
		Assert.assertEquals(1L, lm.mapping(1L, null, false).longValue());
		Assert.assertEquals(1L, lm.mapping(1, null, false).longValue());
		Assert.assertEquals(1L, lm.mapping((byte) 1, null, false).longValue());
		Assert.assertEquals(1L, lm.mapping((short) 1, null, false).longValue());
	}
	
	@Test
	public void testEmpty() throws MappingException, OperationCodeException {
		Assert.assertNull(lm.mapping(null, null, false));
		Assert.assertEquals(null, lm.mapping("", null, false));
	}

}
