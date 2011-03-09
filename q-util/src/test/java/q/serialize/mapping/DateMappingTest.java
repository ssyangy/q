/**
 * 
 */
package q.serialize.mapping;


import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.mapping.DateMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;

// TODO: Auto-generated Javadoc
/**
 * The Class DateMappingTest.
 * 
 * @author alin
 * @date May 27, 2009
 */
public class DateMappingTest {
	
	/** The dm. */
	private DateMapping dm = new DateMapping();
	
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
	 * Test mapping.
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testMapping() throws MappingException, OperationCodeException {
		Assert.assertEquals(new GregorianCalendar(2009, 0, 1).getTime().getTime(), ((Date)dm.mapping("2009-01-01 00:00:00.000", null, false)).getTime());
		Assert.assertEquals(new GregorianCalendar(2009, 0, 1).getTime().getTime(), ((Date)dm.mapping("2009-01-01 00:00:00", null, false)).getTime());
		Assert.assertEquals(new GregorianCalendar(2009, 0, 1).getTime().getTime(), ((Date)dm.mapping("2009-01-01", null, false)).getTime());
	}
}
