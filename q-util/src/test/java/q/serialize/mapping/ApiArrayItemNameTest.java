package q.serialize.mapping;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import q.serialize.config.MappingConfigReader;
import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.convert.XMLConvert;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.OperationCodeException;


/**
 * 测试apiArrayItemName是否生效,对全部数据类型的测试
 * 
 * @author jiangyongyuan.tw
 */
public class ApiArrayItemNameTest extends MappingTestBase {

	protected MethodMapping<Object> getMethodMapping() {
		String configFile = "apiArrayItemNameResponse-test.xml";

		MethodMapping<Object> mm = null;
		try {
			MappingConfigReader dcr = new MappingConfigReader();
			mm = dcr.read(getFileIS(configFile));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return mm;
	}

	MethodMapping<Object> mm = getMethodMapping();
	MemberMapping<Object> rspMapping = mm.getMethodResponseMapping()
			.getParameterMapping();

	public String writeJson(ResultDemo c) throws MappingException,
			OperationCodeException {
		StringWriter w = new StringWriter();
		Convert convert = new JSONConvert(w);
		rspMapping.write(convert, false, c, false);
		//rspMapping.write(w, c, "json", false);
		return w.toString();
	}

	public String writeXml(ResultDemo c) throws MappingException,
			OperationCodeException {
		StringWriter w = new StringWriter();
		Convert convert = new XMLConvert(w);
		rspMapping.write(convert, false, c, false);
		//rspMapping.write(w, c, "xml", false);
		return w.toString();
	}

	@Test
	public void testStringList() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<String> s = new ArrayList();
		s.add("aaaa");
		c.setString(s);

		String string = writeJson(c);
		String string2 = writeXml(c);
		Assert.assertEquals(string, "{\"string\":{\"str\":[\"aaaa\"]}}");
		Assert.assertEquals(string2,
				"<string list=\"true\"><str>aaaa</str></string>");
	}

	@Test
	public void testStringArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		String[] s = { "aaaa" };
		c.setStrings(s);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"strings\":{\"str\":[\"aaaa\"]}}");
		Assert.assertEquals(string2,
				"<strings list=\"true\"><str>aaaa</str></strings>");
	}

	@Test
	public void testIntegerList() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Integer> list = new ArrayList<Integer>();
		list.add(new Integer(1));

		c.setI1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"i1\":{\"int\":[1]}}");
		Assert.assertEquals(string2, "<i1 list=\"true\"><int>1</int></i1>");
	}

	@Test
	public void testIntegerArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		Integer[] list = { new Integer(1) };

		c.setI1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"i1s\":{\"int\":[1]}}");
		Assert.assertEquals(string2, "<i1s list=\"true\"><int>1</int></i1s>");
	}

	@Test
	public void testIntArray() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		int[] list = { 1 };

		c.setI2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"i2s\":{\"int\":[1]}}");
		Assert.assertEquals(string2, "<i2s list=\"true\"><int>1</int></i2s>");
	}

	@Test
	public void testLongList() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Long> list = new ArrayList<Long>();
		list.add(1L);
		c.setL1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"l1\":{\"long\":[1]}}");
		Assert.assertEquals(string2, "<l1 list=\"true\"><long>1</long></l1>");
	}

	@Test
	public void testLongArray() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		Long[] list = { new Long(1L) };

		c.setL1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"l1s\":{\"long\":[1]}}");
		Assert.assertEquals(string2, "<l1s list=\"true\"><long>1</long></l1s>");
	}

	@Test
	public void testlongArray() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		long[] list = { 1L };

		c.setL2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"l2s\":{\"long\":[1]}}");
		Assert.assertEquals(string2, "<l2s list=\"true\"><long>1</long></l2s>");
	}

	@Test
	public void testFloatList() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Float> list = new ArrayList<Float>();
		list.add(1.0F);
		c.setF1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"f1\":{\"f\":[1.0]}}");
		Assert.assertEquals(string2, "<f1 list=\"true\"><f>1.0</f></f1>");
	}

	@Test
	public void testFloatArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		Float[] list = { new Float(1.0) };

		c.setF1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"f1s\":{\"f\":[1.0]}}");
		Assert.assertEquals(string2, "<f1s list=\"true\"><f>1.0</f></f1s>");
	}

	@Test
	public void testfongArray() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		float[] list = { 1L };

		c.setF2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"f2s\":{\"f\":[1.0]}}");
		Assert.assertEquals(string2, "<f2s list=\"true\"><f>1.0</f></f2s>");
	}

	@Test
	public void testDoubleList() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Double> list = new ArrayList<Double>();
		list.add(1.0);
		c.setD1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"d1\":{\"d\":[1.0]}}");
		Assert.assertEquals(string2, "<d1 list=\"true\"><d>1.0</d></d1>");
	}

	@Test
	public void testDoubleArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		Double[] list = { new Double(1.0) };

		c.setD1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"d1s\":{\"d\":[1.0]}}");
		Assert.assertEquals(string2, "<d1s list=\"true\"><d>1.0</d></d1s>");
	}

	@Test
	public void testdoubleArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		double[] list = { 1L };

		c.setD2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"d2s\":{\"d\":[1.0]}}");
		Assert.assertEquals(string2, "<d2s list=\"true\"><d>1.0</d></d2s>");
	}

	@Test
	public void testBooleanList() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Boolean> list = new ArrayList<Boolean>();
		list.add(new Boolean(true));
		c.setB1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"b1\":{\"b\":[true]}}");
		Assert.assertEquals(string2, "<b1 list=\"true\"><b>true</b></b1>");
	}

	@Test
	public void testBooleanArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		Boolean[] list = { new Boolean(true) };

		c.setB1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"b1s\":{\"b\":[true]}}");
		Assert.assertEquals(string2, "<b1s list=\"true\"><b>true</b></b1s>");
	}

	@Test
	public void testbooleanArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		boolean[] list = { true };

		c.setB2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"b2s\":{\"b\":[true]}}");
		Assert.assertEquals(string2, "<b2s list=\"true\"><b>true</b></b2s>");
	}

	@Test
	public void testCharacterList() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Character> list = new ArrayList<Character>();
		list.add(new Character('1'));
		c.setC1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"c1\":{\"c\":[\"1\"]}}");
		Assert.assertEquals(string2, "<c1 list=\"true\"><c>1</c></c1>");
	}

	@Test
	public void testCharacterArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		Character[] list = { new Character('1') };

		c.setC1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"c1s\":{\"c\":[\"1\"]}}");
		Assert.assertEquals(string2, "<c1s list=\"true\"><c>1</c></c1s>");
	}

	@Test
	public void testcharArray() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		char[] list = { '1' };

		c.setC2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"c2s\":{\"c\":[\"1\"]}}");
		Assert.assertEquals(string2, "<c2s list=\"true\"><c>1</c></c2s>");
	}

	@Test
	public void tesByteList() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		List<Byte> list = new ArrayList<Byte>();
		Byte b = 0x01;
		list.add(b);
		c.setByte1(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"byte1\":{\"byte\":[1]}}");
		Assert.assertEquals(string2, "<byte1 list=\"true\"><byte>1</byte></byte1>");
	}

	@Test
	public void testByteArray() throws MappingException,
			OperationCodeException {
		ResultDemo c = new ResultDemo();

		Byte[] list = { 0x01 };

		c.setByte1s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"byte1s\":{\"byte\":[1]}}");
		Assert.assertEquals(string2, "<byte1s list=\"true\"><byte>1</byte></byte1s>");
	}

	@Test
	public void testbyteArray() throws MappingException, OperationCodeException {
		ResultDemo c = new ResultDemo();

		byte[] list = { 0x01 };

		c.setByte2s(list);

		String string = writeJson(c);
		String string2 = writeXml(c);

		Assert.assertEquals(string, "{\"byte2s\":{\"byte\":[1]}}");
		Assert.assertEquals(string2, "<byte2s list=\"true\"><byte>1</byte></byte2s>");
	}
	
	private static class ResultDemo {

		List<String> string;
		String[] strings;

		List<Integer> i1;
		Integer[] i1s;

		int[] i2s;

		List<Long> l1;
		Long[] l1s;

		long[] l2s;

		List<Float> f1;
		Float[] f1s;

		float[] f2s;

		List<Double> d1;
		Double[] d1s;

		double[] d2s;

		List<Boolean> b1;
		Boolean[] b1s;

		boolean[] b2s;

		List<Character> c1;
		Character[] c1s;

		char[] c2s;

		List<Byte> byte1;
		Byte[] byte1s;

		byte[] byte2s;

		public List<String> getString() {
			return string;
		}

		public void setString(List<String> string) {
			this.string = string;
		}

		public String[] getStrings() {
			return strings;
		}

		public void setStrings(String[] strings) {
			this.strings = strings;
		}

		public List<Integer> getI1() {
			return i1;
		}

		public void setI1(List<Integer> i1) {
			this.i1 = i1;
		}

		public Integer[] getI1s() {
			return i1s;
		}

		public void setI1s(Integer[] i1s) {
			this.i1s = i1s;
		}

		public int[] getI2s() {
			return i2s;
		}

		public void setI2s(int[] i2s) {
			this.i2s = i2s;
		}

		public List<Long> getL1() {
			return l1;
		}

		public void setL1(List<Long> l1) {
			this.l1 = l1;
		}

		public Long[] getL1s() {
			return l1s;
		}

		public void setL1s(Long[] l1s) {
			this.l1s = l1s;
		}

		public long[] getL2s() {
			return l2s;
		}

		public void setL2s(long[] l2s) {
			this.l2s = l2s;
		}

		public List<Float> getF1() {
			return f1;
		}

		public void setF1(List<Float> f1) {
			this.f1 = f1;
		}

		public Float[] getF1s() {
			return f1s;
		}

		public void setF1s(Float[] f1s) {
			this.f1s = f1s;
		}

		public float[] getF2s() {
			return f2s;
		}

		public void setF2s(float[] f2s) {
			this.f2s = f2s;
		}

		public List<Double> getD1() {
			return d1;
		}

		public void setD1(List<Double> d1) {
			this.d1 = d1;
		}

		public Double[] getD1s() {
			return d1s;
		}

		public void setD1s(Double[] d1s) {
			this.d1s = d1s;
		}

		public double[] getD2s() {
			return d2s;
		}

		public void setD2s(double[] d2s) {
			this.d2s = d2s;
		}

		public List<Boolean> getB1() {
			return b1;
		}

		public void setB1(List<Boolean> b1) {
			this.b1 = b1;
		}

		public Boolean[] getB1s() {
			return b1s;
		}

		public void setB1s(Boolean[] b1s) {
			this.b1s = b1s;
		}

		public boolean[] getB2s() {
			return b2s;
		}

		public void setB2s(boolean[] b2s) {
			this.b2s = b2s;
		}

		public List<Character> getC1() {
			return c1;
		}

		public void setC1(List<Character> c1) {
			this.c1 = c1;
		}

		public Character[] getC1s() {
			return c1s;
		}

		public void setC1s(Character[] c1s) {
			this.c1s = c1s;
		}

		public char[] getC2s() {
			return c2s;
		}

		public void setC2s(char[] c2s) {
			this.c2s = c2s;
		}

		public List<Byte> getByte1() {
			return byte1;
		}

		public void setByte1(List<Byte> byte1) {
			this.byte1 = byte1;
		}

		public Byte[] getByte1s() {
			return byte1s;
		}

		public void setByte1s(Byte[] byte1s) {
			this.byte1s = byte1s;
		}

		public byte[] getByte2s() {
			return byte2s;
		}

		public void setByte2s(byte[] byte2s) {
			this.byte2s = byte2s;
		}
	}
}
