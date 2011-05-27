/**
 * 
 */
package q.serialize.mock;

import static org.junit.Assert.assertEquals;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import q.serialize.convert.Convert;
import q.serialize.convert.JSONConvert;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mock.domain.Pojo;
import q.serialize.mock.domain.PojoArray;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 8, 2011
 * 
 */
public class ObjectMappingPojoTest extends ApiMappingXmlTestBase {

	@Test
	public void testPojo2json() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-pojo.xml");
		StringWriter sw = new StringWriter();
		Pojo pojo = new Pojo();
		pojo.setIs3D(true);
		pojo.setTitle("jobs");
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, false, pojo, false);
		assertEquals("{\"is_3D\":true,\"title\":\"jobs\"}", sw.toString());
	}
	
	@Test
	public void testPojoArray2json() throws MappingException, OperationCodeException {
		MemberMapping<?> rspMapping = getMemberMapping("object-mapping-pojo-array.xml");
		StringWriter sw = new StringWriter();
		PojoArray pojoArray = new PojoArray();
		Pojo pojo = new Pojo();
		pojo.setIs3D(true);
		pojo.setTitle("jobs");
		List<Pojo> pojos = new ArrayList<Pojo>();
		pojos.add(pojo);
		pojoArray.setMany(pojos);
		pojoArray.setSingle(pojo);
		
		Convert convert = new JSONConvert(sw);
		rspMapping.write(convert, false, pojoArray, false);
		assertEquals("{\"many\":[{\"is_3D\":true,\"title\":\"jobs\"}],\"single\":{\"is_3D\":true,\"title\":\"jobs\"}}", sw.toString());
	}
}
