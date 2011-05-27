/**
 * 
 */
package q.domain;

import static org.junit.Assert.*;

import java.util.List;

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
public class DegreeTest {

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
	 * Test method for {@link q.domain.Degree#valid(int)}.
	 */
	@Test
	public void testValid() {
		assertFalse(Degree.valid(8));
		assertTrue(Degree.valid(9));
		assertTrue(Degree.valid(10));
		assertTrue(Degree.valid(11));
		assertTrue(Degree.valid(12));
		assertTrue(Degree.valid(13));
		assertTrue(Degree.valid(14));
		assertTrue(Degree.valid(15));
		assertTrue(Degree.valid(16));
		assertFalse(Degree.valid(17));
	}

	/**
	 * Test method for {@link q.domain.Degree#getAll()}.
	 */
	@Test
	public void testGetAll() {
		List<Degree> degrees = Degree.getAll();
		assertEquals(8, degrees.size());
	}

}
