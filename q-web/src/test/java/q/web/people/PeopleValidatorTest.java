/**
 * 
 */
package q.web.people;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 14, 2011
 *
 */
public class PeopleValidatorTest {

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
	 * Test method for {@link q.web.people.PeopleValidator#validateEmail(java.lang.String)}.
	 */
	@Test
	public void testValidateEmail() {
		Assert.assertTrue(PeopleValidator.validateEmail("xxx@gmail.com"));
	}

	/**
	 * Test method for {@link q.web.people.PeopleValidator#validateUsername(java.lang.String)}.
	 */
	@Test
	public void testValidateUsername() {
		Assert.assertTrue(PeopleValidator.validateUsername("xxxxxx"));
	}

	/**
	 * Test method for {@link q.web.people.PeopleValidator#validatePassword(java.lang.String)}.
	 */
	@Test
	public void testValidatePassword() {
		Assert.assertTrue(PeopleValidator.validatePassword("123456"));
	}
	
	

}
