/**
 * 
 */
package q.biz.impl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author seanlinwang at gmail dot com
 * @date Jun 2, 2011
 * 
 */
public class DefaultPictureServiceTest {
	private static DefaultPictureService pictureService = null;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pictureService = (DefaultPictureService) BizTestSupport.getBean("pictureService");
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
	 * Test method for {@link q.biz.impl.DefaultPictureService#replaceBiaoqing(java.lang.String)}.
	 */
	@Test
	public void testReplaceBiaoqing() {
		Assert.assertEquals("<img src=\"http://qimg.net/biaoqing/1.gif\" />", pictureService.replaceBiaoqing("[微笑]"));
		Assert.assertEquals("..<img src=\"http://qimg.net/biaoqing/1.gif\" />..", pictureService.replaceBiaoqing("..[微笑].."));
		Assert.assertEquals("<img src=\"http://qimg.net/biaoqing/1.gif\" />.<img src=\"http://qimg.net/biaoqing/2.gif\" />.", pictureService.replaceBiaoqing("[微笑].[撇嘴]."));
	}

}
