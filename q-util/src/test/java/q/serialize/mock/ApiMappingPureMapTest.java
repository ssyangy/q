/**
 * 
 */
package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;


/**
 * @author xalinx at gmail dot com
 * @date Aug 24, 2010
 */
public class ApiMappingPureMapTest extends ApiMappingXmlTestBase {

	@Test
	public void testMCPureMap() throws Exception {
		String file = getFileName("api-mapping-mc-puremap.xml");
		input("name", "alin");
		input("result_count", 1);
		input("is_3D", true);
		Object[] req = testRequest(file);
		Map map = (Map) req[0];
		assertEquals(map.get("name"), "alin");
		assertEquals(map.get("result_count"), "1");
		assertEquals(map.get("is_3D"), "true");

		String[] types = testRequestTypes(file);
		Assert.assertArrayEquals(new String[] { Map.class.getCanonicalName() }, types);
	}

}
