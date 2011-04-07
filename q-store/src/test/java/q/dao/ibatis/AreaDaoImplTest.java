package q.dao.ibatis;

import java.io.File;

import junit.framework.Assert;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;

public class AreaDaoImplTest {
	private static AreaDaoImpl ipDao = new AreaDaoImpl();

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ipDao.setIpFile(new FileSystemResource(new File(System.getProperty("user.dir") + "/src/test/resources/data/qqwry.dat")));
		ipDao.initIp();
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
	public void testGetCountryArea() {
		String[] temp = ipDao.getCountryArea("59.78.9.123");
		Assert.assertEquals("上海交通大学闵行校区D9楼", temp[0] + temp[1]);
	}

}
