/**
 * 
 */
package q.dao.ibatis;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.domain.Area;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 15, 2011
 * 
 */
public class AreaDaoImplTest {

	AreaDaoImpl areaDao = new AreaDaoImpl();
	{
		//File file = new File(System.getProperty("user.dir") + "/src/main/resources/area/area.txt");
		try {
			areaDao.init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
	 * Test method for {@link q.dao.ibatis.AreaDaoImpl#getRootArea()}.
	 */
	@Test
	public void testGetRootArea() {
		Area root = areaDao.getRootArea();
		assertEquals(34, root.getChilds().size()); // 我国有23个省、５个自治区、４个直辖市２个特别行政区共３４个省级行政区。
	}

	@Test
	public void testGetAreaById() {
		Area area = areaDao.getAreaById(331000); // 台州市
		// 331002椒江区;331003黄岩区;331004路桥区;331021玉环县;331022三门县;331023天台县;331024仙居县;331081温岭市;331082临海市
		assertEquals(9, area.getChilds().size());
		area = areaDao.getAreaById(331022); // 三门县
		assertEquals(331000, area.getParent().getId());
	}

	@Test
	public void testGetDistrictJson() {
		Area area = areaDao.getAreaById(331022); // 三门县
		assertEquals("{\"id\":331022,\"name\":\"三门县\"}", area.getJson());
	}

	@Test
	public void testGetCityJson() {
		Area area = areaDao.getAreaById(330900); // 舟山市
		assertEquals("{\"id\":330900,\"name\":\"舟山市\",\"childs\":[" + "{\"id\":330902,\"name\":\"定海区\"}," + "{\"id\":330903,\"name\":\"普陀区\"}," + "{\"id\":330921,\"name\":\"岱山县\"}," + "{\"id\":330922,\"name\":\"嵊泗县\"}]}", area.getJson());
	}

	@Test
	public void testGetChildsJson() {
		Area beijing = new Area();
		beijing.setId(110000);
		beijing.setFullName("北京市");
		beijing.setName("北京市");
		Area congwen = new Area();
		congwen.setId(110103);
		congwen.setFullName("崇文区");
		congwen.setName("崇文区");
		Area xuanwu = new Area();
		xuanwu.setId(110104);
		xuanwu.setFullName("宣武区");
		xuanwu.setName("宣武区");
		beijing.addChild(congwen);
		beijing.addChild(xuanwu);
		assertEquals("[{\"id\":110103,\"name\":\"崇文区\"}," + "{\"id\":110104,\"name\":\"宣武区\"}]", beijing.getChildsJson());
	}

}
