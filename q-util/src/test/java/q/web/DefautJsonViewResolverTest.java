/**
 * 
 */
package q.web;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.web.exception.ErrorCodeException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 9, 2011
 * 
 */
public class DefautJsonViewResolverTest {

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

	@Test
	public void testWriteError() throws IOException {
		DefaultJsonViewResolver resolver = new DefaultJsonViewResolver();
		StringWriter writer = new StringWriter();
		resolver.writeError(writer, new ErrorCodeException("1", "xxx") {
			private static final long serialVersionUID = 1L;
		});
		Assert.assertEquals("{\"error_code\":\"1\",\"error\":\"xxx\"}", writer.toString());

	}

}
