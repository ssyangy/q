/**
 * 
 */
package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mock.domain.Pojo;

/**
 * @author seanlinwang at gmail dot com
 * @date May 13, 2011
 * 
 */
public class ObjectMappingSwitcherTest extends ApiMappingXmlTestBase {

	@Test
	public void testSwitcherJson() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-switcher.xml");
		StringWriter sw = new StringWriter();
		Pojo pojo = new Pojo();
		pojo.setIs3D(true);
		pojo.setTitle("xxx");
		pojo.setZ(true);
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, false, pojo, false);
		assertEquals("{\"is_3D\":true,\"title\":\"xxx\",\"z\":1}", sw.toString());
	}

	@Test
	public void testPojo2JsonWhenNoSwitcher() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-switcher.xml");
		StringWriter sw = new StringWriter();
		Pojo pojo = new Pojo();
		pojo.setIs3D(false);
		pojo.setTitle("xxx");
		pojo.setZ(false);
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, false, pojo, false);
		assertEquals("{\"title\":\"xxx\",\"z\":0}", sw.toString());
	}

	
	@Test
	public void testMap2JsonWhenHasSwitcher() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-switcher.xml");
		StringWriter sw = new StringWriter();
		Map<String, Object> pojo = new HashMap<String, Object>();
		pojo.put("is3D", true);
		pojo.put("title", "xxx");
		pojo.put("z", true);
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, false, pojo, false);
		assertEquals("{\"is_3D\":true,\"title\":\"xxx\",\"z\":1}", sw.toString());
	}
	

	@Test
	public void testMap2JsonWhenNoSwitcher() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-switcher.xml");
		StringWriter sw = new StringWriter();
		Map<String, Object> pojo = new HashMap<String, Object>();
		pojo.put("is3D", false);
		pojo.put("title", "xxx");
		pojo.put("z", false);
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, false, pojo, false);
		assertEquals("{\"title\":\"xxx\",\"z\":0}", sw.toString());
	}

}
