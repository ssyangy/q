package q.serialize.mock;

import org.junit.Test;


/**
 * @author xalinx at gmail dot com
 * @date Nov 19, 2009
 */
public class ApiMappingSwitcherTest extends ApiMappingXmlTestBase {

	@Test
	public void testMappingMethodCall() throws Exception {
		// mapping配置文件地址
		String file = getFileName("api-mapping-mc-switcher.xml");
		// 外部传递的参数
		input("s", "s");
		input("c", "c");
		input("b", "true");
		input("byte", "1");
		input("short", "1");
		input("i", "1");
		input("l", "1");
		input("f", "1.0");
		input("d", "1.0");
		assertRequest(new Object[] { "x", 'x', false, (byte) 2, (short) 2, 2, 2l, 2.0f, 2.0 }, file);
	}

	@Test
	public void testMappingMethodResponse() throws Exception {
	}

}
