/**
 *
 */
package q.biz.impl;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.commons.image.ImageKit;

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

	/*
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
        String fileName="2.png";
		BufferedImage originImage=ImageKit.load(new FileInputStream( new File(System.getProperty("user.dir")+"/src/test/resources/picture/"+fileName)));
		int originWidth = originImage.getWidth();
		int originHeight = originImage.getHeight();
		ImageKit.save(originImage, System.getProperty("user.dir")+"/target/origin.png","png");
		ImageKit.save(originImage, System.getProperty("user.dir")+"/target/origin2.jpg","jpg");

		BufferedImage tagImage = new BufferedImage(originWidth, originHeight, BufferedImage.TYPE_INT_RGB);
		Image temp = originImage.getScaledInstance(originWidth, originHeight, Image.SCALE_SMOOTH);
		Graphics g = tagImage.getGraphics();
		g.drawImage(temp, 0, 0, null);
		g.dispose();
		ImageKit.save(tagImage, System.getProperty("user.dir")+"/target/origin3.png","png");
	}


}
