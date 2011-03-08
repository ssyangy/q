/**
 * 
 */
package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;

import org.junit.Test;

import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mock.domain.Pojo;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 8, 2011
 * 
 */
public class ObjectMappingPojoTest extends ApiMappingXmlTestBase {

	@Test
	public void test2json() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-pojo.xml");
		StringWriter sw = new StringWriter();
		Pojo pojo = new Pojo();
		pojo.setIs3D(true);
		pojo.setTitle("jobs");
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, pojo, false);
		assertEquals("{\"is_3D\":true,\"title\":\"jobs\"}", sw.toString());
	}
}
