/**
 * 
 */
package q.serialize.mapping;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.mapping.ArrayMapping;
import q.serialize.mapping.ExceptionExpressInfo;
import q.serialize.mapping.IntMapping;
import q.serialize.mapping.IntegerMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.PrimitiveByteMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class ArrayMappingTest.
 * 
 * @version 2009-2-25
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class ArrayMappingTest {

	/**
	 * Sets the up before class.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 * 
	 * @throws java.lang.Exception 	 * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.taobao.top.mapping.core.ArrayMapping#mapping(java.lang.Object, ExceptionExpressInfo, boolean)}
	 * .
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testIntegerArrayMapping() throws MappingException, OperationCodeException {
		// List<String> --> Integer[]
		List<String> strList = Arrays.asList("1", "2");
		Integer[] integerArr = new ArrayMapping<Integer[], Integer>(
				new IntegerMapping()).mapping(strList, null, false);
		assertArrayEquals(new Integer[] { 1, 2 }, integerArr);
	}

	/**
	 * Test method for
	 * {@link com.taobao.top.mapping.core.ArrayMapping#mapping(java.lang.Object, ExceptionExpressInfo, boolean)}
	 * .
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testIntArrayMapping() throws MappingException, OperationCodeException {
		// List<String> --> int[]
		List<String> strList = Arrays.asList("1", "2");
		ArrayMapping<int[], Integer> intArrayMapping = new ArrayMapping<int[], Integer>(
				new IntMapping());
		assertEquals(int[].class, intArrayMapping.getMappingType());
		assertEquals(int.class, intArrayMapping.getComponentMapping()
				.getMappingType());
		int[] intArr = intArrayMapping.mapping(strList, null, false);
		assertArrayEquals(new int[] { 1, 2 }, intArr);
		// int[] --> int[]
		int[] int2 = intArrayMapping.mapping(intArr, null, false);
		// 确保是直接拷贝的
		assertTrue(intArr == int2);
		// string --> int[]
		assertArrayEquals(int2, intArrayMapping.mapping("1,2", null, false));
		try {
			assertArrayEquals(int2, intArrayMapping.mapping("1,not number", null, false));
			fail();
		} catch (MappingException e) {
		}
	}

	/**
	 * Test primitive array mapping.
	 * 
	 * @throws MappingException the mapping exception
	 * @throws OperationCodeException the operation code exception
	 */
	@Test
	public void testPrimitiveArrayMapping() throws MappingException, OperationCodeException {
		// List<byte> --> byte[]
		List<String> strList = Arrays.asList("1", "2");
		ArrayMapping<byte[], Byte> primitiveByteArrayMapping = new ArrayMapping<byte[], Byte>(
				new PrimitiveByteMapping());
		assertEquals(byte[].class, primitiveByteArrayMapping.getMappingType());
		assertEquals(byte.class, primitiveByteArrayMapping
				.getComponentMapping().getMappingType());
		byte[] byteArr = primitiveByteArrayMapping.mapping(strList, null, false);
		assertArrayEquals(new byte[] { 1, 2 }, byteArr);
		byte[] byte2 = primitiveByteArrayMapping.mapping(byteArr, null, false);
		// 确保是直接拷贝的
		assertTrue(byte2 == byteArr);
	}

}
