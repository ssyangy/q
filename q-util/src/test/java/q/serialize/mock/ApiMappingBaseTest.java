package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import q.serialize.mock.domain.Pojo;
import q.serialize.mock.domain.PojoWithListPojoProperty;
import q.serialize.util.MapKit;

public class ApiMappingBaseTest extends ApiMappingXmlTestBase {

	@Test
	public void testMCMap() throws Exception {
		String file = getFileName("api-mapping-mc-map.xml");
		input("name", "alin");
		input("result_count", 1);
		input("is_3D", true);
		Object[] req = testRequest(file);
		Map map = (Map) req[0];
		assertEquals(map.get("title"), "alin");
		assertEquals(map.get("resultCount"), 1);
		assertEquals(map.get("is3D"), true);
	}

	@Test
	public void testMCPojo() throws Exception {
		String file = getFileName("api-mapping-mc-pojo.xml");
		input("name", "alin");
		input("result_count", 1);
		input("is_3D", true);
		Object[] req = testRequest(file);
		Pojo pojo = (Pojo) req[0];
		assertEquals(pojo.getTitle(), "alin");
		assertEquals(pojo.getResultCount(), 1);
		assertEquals(pojo.getIs3D(), true);

		String[] types = testRequestTypes(file);
		Assert.assertArrayEquals(new String[] { Pojo.class.getCanonicalName() }, types);
	}

	@Test
	public void testJsonMRPojo() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-pojo.xml");
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		Object test = testDefaultResponse(file, c);
		assertEquals("{\"created\":\"2009-12-31 00:00:00\",\"is_3D\":true,\"modified\":\"2009-12-31 00:00:00\",\"result_count\":3}", test);
	}

	@Test
	public void testXmlMRPojo() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-pojo.xml");
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		input("format", "xml");
		Object test = testDefaultResponse(file, c);
		assertEquals("<created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count>", test);
	}

	@Test
	public void testJsonMRMap() {

	}

	@Test
	public void testXmlMRMap() {
		// ssertEquals(
		// "<count>1</count>" +
		// "<pojos list=\"true\">" +
		// "<pojo><created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count></pojo>"
		// +
		// "<pojo><created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count></pojo>"
		// +
		// "</pojos>",
		// test);
	}

	@Test
	public void testXmlMRPojoWithListPojoProperty() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-pojo-withlistpojoproperty.xml");
		// 返回对象
		PojoWithListPojoProperty pojoWithListPojoProperty = new PojoWithListPojoProperty();
		pojoWithListPojoProperty.setCount(1);
		ArrayList<Pojo> pojos = new ArrayList<Pojo>();
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		pojos.add(c);
		pojos.add(c);
		pojoWithListPojoProperty.setPojos(pojos);
		input("format", "xml");
		Object test = testDefaultResponse(file, pojoWithListPojoProperty);
		assertEquals("<count>1</count>" + "<pojos list=\"true\">" + "<pojo><created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count></pojo>" + "<pojo><created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count></pojo>" + "</pojos>", test);
	}

	@Test
	public void testV1XmlMRPojoWithListPojoProperty() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-pojo-withlistpojoproperty.xml");
		// 返回对象
		PojoWithListPojoProperty pojoWithListPojoProperty = new PojoWithListPojoProperty();
		pojoWithListPojoProperty.setCount(1);
		ArrayList<Pojo> pojos = new ArrayList<Pojo>();
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		pojos.add(c);
		pojoWithListPojoProperty.setPojos(pojos);
		input("v", "1.0");
		input("format", "xml");
		Object test = testDefaultResponse(file, pojoWithListPojoProperty);
		Map map = (Map) test;
		assertEquals("{count=1, pojos=[{pojo={is_3D=true, result_count=3, created=Thu Dec 31 00:00:00 CST 2009, modified=2009-12-31 00:00:00}}]}", MapKit.dumpMap(map));
	}

	@Test
	public void testV1JsonMRPojoWithListPojoProperty() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-pojo-withlistpojoproperty.xml");
		// 返回对象
		PojoWithListPojoProperty pojoWithListPojoProperty = new PojoWithListPojoProperty();
		pojoWithListPojoProperty.setCount(1);
		ArrayList<Pojo> pojos = new ArrayList<Pojo>();
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		pojos.add(c);
		pojoWithListPojoProperty.setPojos(pojos);
		input("v", "1.0");
		input("format", "json");
		Object test = testDefaultResponse(file, pojoWithListPojoProperty);
		Map map = (Map) test;
		assertEquals("{count=1, pojos=[{is_3D=true, result_count=3, created=Thu Dec 31 00:00:00 CST 2009, modified=2009-12-31 00:00:00}]}", MapKit.dumpMap(map));
	}

	@Test
	public void testJsonMRPojoWithWrapApiName() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-withwrapapiname.xml");
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		Object test = testDefaultResponse(file, c);
		assertEquals("{\"wrap_name\":{\"created\":\"2009-12-31 00:00:00\",\"is_3D\":true,\"modified\":\"2009-12-31 00:00:00\",\"result_count\":3}}", test);
	}

	@Test
	public void testXmlMRPojoWithWrapApiName() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-withwrapapiname.xml");
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		Object test = testDefaultResponse(file, c);
		input("format", "xml");
		test = testDefaultResponse(file, c);
		assertEquals("<wrap_name><created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count></wrap_name>", test);
	}

}
