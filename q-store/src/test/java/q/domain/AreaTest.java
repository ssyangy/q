/**
 * 
 */
package q.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.dao.ibatis.AreaDaoImpl;
import q.domain.Area;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 15, 2011
 * 
 */
public class AreaTest {

	AreaDaoImpl areaDao = new AreaDaoImpl();
	{
		// File file = new File(System.getProperty("user.dir") + "/src/main/resources/area/area.txt");
		try {
			areaDao.initArea();
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
		Area root = Area.getRootArea();
		assertEquals(34, root.getChilds().size()); // 我国有23个省、５个自治区、４个直辖市２个特别行政区共３４个省级行政区。
	}

	/**
	 * <ul>
	 * <li>331000 台州市</li>
	 * <li>331001 市辖区 *</li>
	 * <li>331002 椒江区</li>
	 * <li>331003 黄岩区</li>
	 * <li>331004 路桥区</li>
	 * <li>331021 玉环县</li>
	 * <li>331022 三门县</li>
	 * <li>331023 天台县</li>
	 * <li>331024 仙居县</li>
	 * <li>331081 温岭市</li>
	 * <li>331082 临海市</li>
	 * </ul>
	 */
	@Test
	public void testGetAreaById() {
		Area city = Area.getAreaById(331000); // 台州市
		assertEquals(9, city.getChilds().size());
		assertEquals(city, city.getMyCity());
		assertEquals(city.getParent(), city.getMyProvince());
		Area county = Area.getAreaById(331022); // 三门县
		assertEquals(city, county.getParent());
		assertEquals(city, county.getMyCity());
		assertEquals(county, county.getMyCounty());
	}

	/**
	 * <ul>
	 * <li>310000 上海市 上海</li>
	 * <li>310100 市辖区 *</li>
	 * <li>310101 黄浦区</li>
	 * <li>310103 卢湾区</li>
	 * <li>310104 徐汇区</li>
	 * <li>310105 长宁区</li>
	 * <li>310106 静安区</li>
	 * <li>310107 普陀区</li>
	 * <li>310108 闸北区</li>
	 * <li>310109 虹口区</li>
	 * <li>310110 杨浦区</li>
	 * <li>310112 闵行区</li>
	 * <li>310113 宝山区</li>
	 * <li>310114 嘉定区</li>
	 * <li>310115 浦东新区</li>
	 * <li>310116 金山区</li>
	 * <li>310117 松江区</li>
	 * <li>310118 青浦区</li>
	 * <li>310120 奉贤区</li>
	 * <li>310200 县 *</li>
	 * <li>310230 崇明县</li>
	 * </ul>
	 */
	@Test
	public void testIgnoreCity() {
		Area shanghai = Area.getAreaById(310000);// 上海市
		assertEquals("上海", shanghai.getName());
		assertEquals(18, shanghai.getChilds().size());
		assertTrue(shanghai.isProvince());
		assertFalse(shanghai.isCity());
		assertFalse(shanghai.isCounty());
		Area luwan = Area.getAreaById(310103);// 卢湾区
		assertTrue(luwan.isChild(shanghai));
		Area congming = Area.getAreaById(310230);// 崇明县
		assertTrue(congming.isChild(shanghai));
		assertFalse(congming.isCounty());
		assertTrue(congming.isCity());
		assertFalse(congming.isProvince());
	}

	@Test
	public void testGetDistrictJson() {
		Area area = Area.getAreaById(331022); // 三门县
		assertEquals("{\"id\":331022,\"name\":\"三门县\"}", area.getJson());
	}

	@Test
	public void testGetCityJson() {
		Area area = Area.getAreaById(330900); // 舟山市
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
