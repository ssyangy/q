package q.serialize.mock;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import q.serialize.mock.domain.Pojo;

public class ApiMappingArrayTest extends ApiMappingXmlTestBase {

	@Test
	public void testMCSimpleArray() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mc-array-simple.xml");
		// 外部传递的参数
		input("sa", "s,a");
		input("ca", "c,a");
		input("ba", "true,false");
		input("bytea", "1,2");
		input("shorta", "1,2");
		input("ia", "1,2");
		input("la", "1,2");
		input("fa", "1.0,2.0");
		input("da", "1.0,2.0");
		input("name", "title1");
		Object[] reqs = testRequest(file);
		assertArrayEquals(new Object[] { new String[] { "s", "a" }, new char[] { 'c', 'a' }, new boolean[] { true, false }, new byte[] { 1, 2 }, new short[] { 1, 2 }, new int[] { 1, 2 }, new long[] { 1, 2 }, new float[] { 1.0f, 2.0f }, new double[] { 1.0d, 2.0d } }, reqs);
	}



	@Test
	public void testMCPojoArray() throws Exception {
		input("name", "title1");
		Object[] reqs = testRequest(getFileName("api-mapping-mc-array-pojo.xml"));
		assertArrayEquals(new Object[] { null }, reqs);
	}

	@Test
	public void testMRJsonBooleanArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setBl(Arrays.asList(new Boolean[] { true, false }));
		c.setBa(new boolean[] { true, false });
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("{\"ba\":{\"b\":[true,false]}," + "\"bl\":{\"b\":[true,false]}}", rsp);
	}

	@Test
	public void testMRXmlBooleanArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setBl(Arrays.asList(new Boolean[] { true, false }));
		c.setBa(new boolean[] { true, false });
		input("format", "xml");
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("<ba list=\"true\"><b>true</b><b>false</b></ba><bl list=\"true\"><b>true</b><b>false</b></bl>", rsp);
	}

	@Test
	public void testMRJsonCharArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setCl(Arrays.asList(new Character[] { 'c', 'l' }));
		c.setCa(new char[] { 'c', 'a' });
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("{\"ca\":{\"c\":[\"c\",\"a\"]}," + "\"cl\":{\"c\":[\"c\",\"l\"]}}", rsp);
	}

	@Test
	public void testMRXmlCharArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setCl(Arrays.asList(new Character[] { 'c', 'l' }));
		c.setCa(new char[] { 'c', 'a' });
		input("format", "xml");
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("<ca list=\"true\"><c>c</c><c>a</c></ca>" + "<cl list=\"true\"><c>c</c><c>l</c></cl>", rsp);
	}

	@Test
	public void testMRStringArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setSl(Arrays.asList(new String[] { "s", "l" }));
		c.setSl2(c.getSl());
		c.setSa(new String[] { "s", "a" });
		c.setSa2(c.getSa());
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("{\"sa\":{\"s\":[\"s\",\"a\"]}," + "\"sa2\":{\"s2\":[\"s\",\"a\"]}," + "\"sl\":{\"s\":[\"s\",\"l\"]}," + "\"sl2\":{\"s2\":[\"s\",\"l\"]}}", rsp);
	}

	@Test
	public void testMRIntArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setIl(Arrays.asList(1, 2));
		c.setIl2(c.getIl());
		c.setIa(new int[] { 1, 2 });
		c.setIa2(c.getIa());
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("{\"ia\":{\"i\":[1,2]}," + "\"ia2\":{\"i2\":[1,2]}," + "\"il\":{\"i\":[1,2]}," + "\"il2\":{\"i2\":[1,2]}}", rsp);
	}

	@Test
	public void testMRDoubleArray() throws Exception {
		// mapping配置文件地址
		ResultDemo c = new ResultDemo();
		c.setDl(Arrays.asList(1.0, 2.0));
		c.setDl2(c.getDl());
		c.setDa(new double[] { 1.0, 2.0 });
		c.setDa2(c.getDa());
		Object rsp = testDefaultResponse(getFileName("api-mapping-mr-array-simple.xml"), c);
		assertEquals("{\"da\":{\"d\":[1.0,2.0]}," + "\"da2\":{\"d2\":[1.0,2.0]}," + "\"dl\":{\"d\":[1.0,2.0]}," + "\"dl2\":{\"d2\":[1.0,2.0]}}", rsp);
	}

	@Test
	public void testMRMultiArray() throws Exception {
		String file = getFileName("api-mapping-mr-array-multi.xml");
		// mapping配置文件地址
		List<int[]> intsList = Arrays.asList(new int[][] { { 1, 2 }, { 1, 2, 3 } });
		assertEquals("{\"iaa\":{\"ia\":[[1,2],[1,2,3]]}}", testDefaultResponse(file, intsList));
		int[][] intss = new int[][] { { 1, 2 }, { 1, 2, 3 } };
		assertEquals("{\"iaa\":{\"ia\":[[1,2],[1,2,3]]}}", testDefaultResponse(file, intss));
	}

	@Test
	public void testJsonMRSetPojo() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-array-struct.xml");
		Set<Pojo> set = new HashSet<Pojo>();
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		set.add(c);
		Object test = testDefaultResponse(file, set);
		assertEquals("{\"domains\":{\"domain\":[{\"created\":\"2009-12-31 00:00:00\",\"is_3D\":true,\"modified\":\"2009-12-31 00:00:00\",\"result_count\":3}]}}", test);
	}

	@Test
	public void testJsonMRListPojo() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-array-struct.xml");
		List<Pojo> list = new ArrayList<Pojo>();
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		list.add(c);
		Object test = testDefaultResponse(file, list);
		assertEquals("{\"domains\":{\"domain\":[{\"created\":\"2009-12-31 00:00:00\",\"is_3D\":true,\"modified\":\"2009-12-31 00:00:00\",\"result_count\":3}]}}", test);
	}

	@Test
	public void testJsonMRArrayPojo() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-array-struct.xml");
		Pojo[] array = new Pojo[1];
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		array[0] = c;
		Object test = testDefaultResponse(file, array);
		assertEquals("{\"domains\":{\"domain\":[{\"created\":\"2009-12-31 00:00:00\",\"is_3D\":true,\"modified\":\"2009-12-31 00:00:00\",\"result_count\":3}]}}", test);
	}

	@Test
	public void testXmlMRSetPojo() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-array-struct.xml");
		Set<Pojo> set = new HashSet<Pojo>();
		// 返回对象
		Pojo c = new Pojo();
		c.setIs3D(true);
		c.setResultCount(3);
		Calendar created = Calendar.getInstance();
		created.set(2009, 11, 31, 0, 0, 0);
		c.setCreated(created.getTime());
		c.setModified(created.getTime());
		set.add(c);
		input("format", "xml");
		Object test = testDefaultResponse(file, set);
		assertEquals("<domains list=\"true\"><domain><created>2009-12-31 00:00:00</created><is_3D>true</is_3D><modified>2009-12-31 00:00:00</modified><result_count>3</result_count></domain></domains>", test);
	}

	@Test
	public void testMRStructArrayNullResponse() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mr-array-struct.xml");
		Object test = testDefaultResponse(file, null);
		assertEquals("null", test);
	}

	private class ResultDemo {
		int i;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		List<String> sl;

		public List<String> getSl() {
			return sl;
		}

		public void setSl(List<String> sl) {
			this.sl = sl;
		}

		private List<String> sl2;

		public List<String> getSl2() {
			return sl2;
		}

		public void setSl2(List<String> sl2) {
			this.sl2 = sl2;
		}

		String[] sa;

		public String[] getSa() {
			return sa;
		}

		public void setSa(String[] sa) {
			this.sa = sa;
		}

		String[] sa2;

		public String[] getSa2() {
			return sa2;
		}

		public void setSa2(String[] sa2) {
			this.sa2 = sa2;
		}

		char[] ca;

		public char[] getCa() {
			return ca;
		}

		public void setCa(char[] ca) {
			this.ca = ca;
		}

		List<Character> cl;

		public List<Character> getCl() {
			return cl;
		}

		public void setCl(List<Character> cl) {
			this.cl = cl;
		}

		boolean[] ba;

		public boolean[] getBa() {
			return ba;
		}

		public void setBa(boolean[] ba) {
			this.ba = ba;
		}

		List<Boolean> bl;

		public List<Boolean> getBl() {
			return bl;
		}

		public void setBl(List<Boolean> bl) {
			this.bl = bl;
		}

		List<Integer> il;

		public List<Integer> getIl() {
			return il;
		}

		public void setIl(List<Integer> il) {
			this.il = il;
		}

		List<Integer> il2;

		public List<Integer> getIl2() {
			return il2;
		}

		public void setIl2(List<Integer> il2) {
			this.il2 = il2;
		}

		int[] ia;

		public int[] getIa() {
			return ia;
		}

		public void setIa(int[] ia) {
			this.ia = ia;
		}

		int[] ia2;

		public int[] getIa2() {
			return ia2;
		}

		public void setIa2(int[] ia2) {
			this.ia2 = ia2;
		}

		List<Double> dl;

		List<Double> dl2;

		double[] da;

		double[] da2;

		public List<Double> getDl() {
			return dl;
		}

		public void setDl(List<Double> dl) {
			this.dl = dl;
		}

		public List<Double> getDl2() {
			return dl2;
		}

		public void setDl2(List<Double> dl2) {
			this.dl2 = dl2;
		}

		public double[] getDa() {
			return da;
		}

		public void setDa(double[] da) {
			this.da = da;
		}

		public double[] getDa2() {
			return da2;
		}

		public void setDa2(double[] da2) {
			this.da2 = da2;
		}

	}
}
