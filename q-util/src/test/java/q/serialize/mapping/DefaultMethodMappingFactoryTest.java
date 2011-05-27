/**
 * 
 */
package q.serialize.mapping;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import q.serialize.mapping.DefaultMethodMappingFactory;
import q.serialize.mapping.MethodCallMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.MethodResponseMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class DefaultMethodMappingFactoryTest.
 * 
 * @version 2009-2-19
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class DefaultMethodMappingFactoryTest {

	/**
	 * Sets the up before class.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Tear down after class.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * Sets the up.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Tear down.
	 * 
	 * @throws java.lang.Exception
	 *             * @throws Exception the exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.taobao.top.mapping.core.DefaultMethodMappingFactory#addMapping(com.taobao.top.mapping.core.MethodMapping)}
	 * .
	 */
	@Test
	public void testAddInvalidMethodMapping() {
		DefaultMethodMappingFactory f = new DefaultMethodMappingFactory();
		MethodMapping<Object> mm = new MethodMapping<Object>();
		f.addMethodMapping(mm);
		assertEquals(0, f.size());
		MethodResponseMapping<Object> mrm = new MethodResponseMapping<Object>();
		mm.setMethodResponseMapping(mrm);
		f.addMethodMapping(mm);
		assertEquals(0, f.size());
		MethodCallMapping mcm = new MethodCallMapping();
		mm.setMethodCallMapping(mcm);
		f.addMethodMapping(mm);
		assertEquals(0, f.size());
	}

	@Test
	public void testValidMethodMapping() {
		DefaultMethodMappingFactory f = new DefaultMethodMappingFactory();
		MethodMapping<Object> mm = new MethodMapping<Object>();
		MethodCallMapping mcm = new MethodCallMapping();
		mcm.setName("methodA");
		mm.setMethodCallMapping(mcm);
		f.addMethodMapping(mm);
		MethodMapping<Object> GetMM = f.getMethodMapping(mcm.getName());
		assertTrue(mm == GetMM);
	}
}
