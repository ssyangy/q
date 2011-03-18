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

import q.web.exception.RequestParameterInvalidException;

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
		try {
			PeopleValidator.validateUsername("12345");
			PeopleValidator.validateUsername("12345678901234567");
			Assert.fail();
		} catch (RequestParameterInvalidException e) {
		}
		try {
			PeopleValidator.validateUsername("123456");
			PeopleValidator.validateUsername("abc123456-_");
			PeopleValidator.validateUsername("1234567890123456");
		} catch (RequestParameterInvalidException e) {
			Assert.fail();
		}
	}

	/**
	 * Test method for {@link q.web.people.PeopleValidator#validatePassword(java.lang.String)}.
	 */
	@Test
	public void testValidatePassword() {
		try {
			PeopleValidator.validateUsername("12345");
			Assert.fail();
		} catch (RequestParameterInvalidException e) {
		}
		try {
			PeopleValidator.validateUsername("12345678901234567");
			Assert.fail();
		} catch (RequestParameterInvalidException e) {
		}
		try {
			PeopleValidator.validateUsername("中文");
			Assert.fail();
		} catch (RequestParameterInvalidException e) {
		}
		try {
			PeopleValidator.validateUsername("123456");
			PeopleValidator.validateUsername("123456abc-_");
			PeopleValidator.validateUsername("1234567890123456");
		} catch (RequestParameterInvalidException e) {
			Assert.fail();
		}
	}

	@Test
	public void testValidateRealName() {
		try {
			PeopleValidator.validateRealName("");
			PeopleValidator.validateRealName("1234567890123");
			Assert.fail();
		} catch (RequestParameterInvalidException e) {
		}
		try {
			PeopleValidator.validateRealName("1");
			PeopleValidator.validateRealName("1abc-_");
			PeopleValidator.validateRealName("123456789012");
			PeopleValidator.validateRealName("中文");
		} catch (RequestParameterInvalidException e) {
			Assert.fail();
		}

	}

}
