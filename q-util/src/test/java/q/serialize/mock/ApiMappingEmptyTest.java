/**
 * 
 */
package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Test;



/**
 * @author xalinx at gmail dot com
 * @date Dec 8, 2009
 */
public class ApiMappingEmptyTest extends ApiMappingXmlTestBase {


	@Test
	public void testMCEmpty() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mcr-empty.xml");
		input("v", "2.0");
		assertRequest(new String[]{}, file);
	}
	
	@Test
	public void testMCEmptyV1() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mcr-empty.xml");
		// 外部传递的参数
		input("v", "1.0");
		assertRequest(new String[]{}, file);
	}
	
	@Test
	public void testMREmpty() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mcr-empty.xml");
		input("v", "2.0");
		input("format", "json");
		Object test = testDefaultResponse(file, null);
		assertEquals("null", test);
		input("format", "xml");
		test = testDefaultResponse(file, null);
		assertEquals("", test);
	}
}
