/**
 * 
 */
package q.serialize.mapping;


import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.mapping.FloatMapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.UnsupportSourceTypeException;

/**
 * @author xalinx at gmail dot com
 * @date Feb 26, 2010
 */
public class FloatMappingTest {
	private FloatMapping floatMapping = new FloatMapping();

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
	public void ffloat() throws MappingException, OperationCodeException {
		Float src = Float.valueOf(1);
		Float rs = floatMapping.mapping(src, null, false);
		Assert.assertEquals(src.floatValue(), rs.floatValue(), 0);
	}
	
	@Test
	public void fstring() throws MappingException, OperationCodeException {
		String src = "1.0";
		Float rs = floatMapping.mapping(src, null, false);
		Assert.assertEquals(Float.valueOf(src).floatValue(), rs.floatValue(), 0);
	}
	
	@Test
	public void flong() throws MappingException, OperationCodeException {
		Long src = Long.valueOf(1);
		Float rs = floatMapping.mapping(src, null, false);
		Assert.assertEquals(src.floatValue(), rs.floatValue(), 0);
	}
	
	@Test
	public void fint() throws MappingException, OperationCodeException {
		Integer src = Integer.valueOf(1);
		Float rs = floatMapping.mapping(src, null, false);
		Assert.assertEquals(src.floatValue(), rs.floatValue(), 0);
	}
	
	@Test
	public void fshort() throws MappingException, OperationCodeException {
		Short src = Short.valueOf("1");
		Float rs = floatMapping.mapping(src, null, false);
		Assert.assertEquals(src.floatValue(), rs.floatValue(), 0);
	}
	
	@Test
	public void fbyte() throws MappingException, OperationCodeException {
		Byte src = Byte.valueOf("1");
		Float rs = floatMapping.mapping(src, null, false);
		Assert.assertEquals(src.floatValue(), rs.floatValue(), 0);
	}

	@Test
	public void fchar() throws MappingException, OperationCodeException {
		Character cha = new Character((char) 1);
		try {
			floatMapping.mapping(cha, null, false);
		} catch (UnsupportSourceTypeException e) {
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void fdouble() throws MappingException, OperationCodeException {
		Double src = 1d;
		try {
			floatMapping.mapping(src, null, false);
		} catch (UnsupportSourceTypeException e) {
			return;
		}
		Assert.fail();
	}
	
	@Test
	public void ferrorstring() throws MappingException, OperationCodeException {
		String src = "errorfloatstring";
		try {
			floatMapping.mapping(src, null, false);
		} catch (UnsupportSourceTypeException e) {
			fail();
		} catch (MappingException e) {
			return;
		}
		fail();
	}
}
