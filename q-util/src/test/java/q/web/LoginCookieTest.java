/**
 * 
 */
package q.web;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang at gmail dot com
 * @date May 26, 2011
 * 
 */
public class LoginCookieTest {

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
	 * Test method for {@link q.web.LoginCookie#LoginCookie(long, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLoginCookieLongStringStringString() {
		LoginCookie loginCookie = new LoginCookie(1L, "alin", "sean");
		Assert.assertEquals(loginCookie.getPeopleId(), 1L);
		Assert.assertEquals(loginCookie.getRealName(), "alin");
		Assert.assertEquals(loginCookie.getUsername(), "sean");
		Assert.assertEquals(loginCookie.getAvatarPath(), null);

	}

	/**
	 * Test method for {@link q.web.LoginCookie#LoginCookie(long, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testLoginCookieLongStringString() {
		String avatarPath = "xxx/xxx.jpg";
		LoginCookie loginCookie = new LoginCookie(1L, "alin", "sean", avatarPath);
		Assert.assertEquals(loginCookie.getPeopleId(), 1L);
		Assert.assertEquals(loginCookie.getRealName(), "alin");
		Assert.assertEquals(loginCookie.getUsername(), "sean");
		Assert.assertEquals(loginCookie.getAvatarPath(), avatarPath);
	}

}
