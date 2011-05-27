package q.serialize.tools;

import org.junit.Assert;
import org.junit.Test;

import q.serialize.tools.Api2MappingXmlTestBase;


public class Api2MappingXmlTestBaseTest extends Api2MappingXmlTestBase {

	String xmlPath = "/q/serialize/tools/";
	
	@Test
	public void testCheckMappingXmlStringStringStringClassOfQ() {
		String fileds = "iid,created";
		Assert.assertTrue(checkMappingXml(xmlPath, "taobao.item.add-mapping.xml", fileds, Item.class));

		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-NoMethodCall-mapping.xml", fileds, Item.class));

		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-NoName-mapping.xml", fileds, Item.class));
	
		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-NoApiName-mapping.xml", fileds, Item.class));
		
		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-NoMethodResponse-mapping.xml", fileds, Item.class));
		
		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-NoMethodResponse-mapping.xml", fileds, Item.class));

		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-ExtraField-mapping.xml", fileds, Item.class));

		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-MissingField-mapping.xml", fileds, Item.class));

		Assert.assertFalse(checkMappingXml(xmlPath, "taobao.item.add-WrongField-mapping.xml", fileds, Item.class));

	}
	

	@Test
	public void testCheckMappingXmlStringStringStringClassOfQString() {
	}

	@Test
	public void testGetBeanMap() {
	}

}
