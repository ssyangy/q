package q.serialize.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Assert;

import q.serialize.config.MappingConfigReader;
import q.serialize.mapping.DefaultMethodMappingFactory;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.MethodMappingFactory;
import q.serialize.tools.ServiceInvokeTransformV2;

/**
 * mapping文件测试类,使用方法： public class TopMappingMockTest extends TopMappingMock {
 * 
 * @Test public void testMappingFile() throws Exception { // mapping配置文件地址 String file = "apiArrayItemNameResponse-test.xml"; // 外部传递的参数 input("aid", 1); // Object 内部返回对象 ResultDemo c = new ResultDemo(); List<String> s = new ArrayList<String>(); s.add("aaaa"); c.setString(s);
 * 
 *       Object test = testDefaultResponse(file, c); Assert.assertEquals("{\"string\":{\"str\":[\"aaaa\"]}}", test); } }
 * @author jiangyongyuan.tw
 */
public class ApiMappingXmlTestBase {
	/**
	 * @param string
	 * @return
	 */
	protected String getFileName(String string) {
		return System.getProperty("user.dir") + "/src/test/resources/q/serialize/mock/" + string;
	}

	protected FileInputStream getFileInput(String filename) throws FileNotFoundException {
		return new FileInputStream(getFileName(filename));
	}

	MappingConfigReader r = new MappingConfigReader();
	MethodMappingFactory f = new DefaultMethodMappingFactory();

	/**
	 * 输入参数
	 */
	HashMap<String, Object> input = new HashMap<String, Object>();
	
	/** The dcr. */
	private MappingConfigReader dcr = new MappingConfigReader();

	/**
	 * Gets the member mapping.
	 * 
	 * @param configFile
	 *            the config file
	 * 
	 * @return the member mapping
	 */
	protected MemberMapping<Object> getMemberMapping(String configFile) {
		MemberMapping<Object> mm = null;
		try {
			mm = dcr.readMemberMapping(getFileInput(configFile));
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}
		return mm;
	}

	/**
	 * 模拟输入参数
	 * 
	 * @param key
	 * @param value
	 */
	public void input(String key, Object value) {
		if (value instanceof byte[]) {
			input.put(key, value);
		} else {
			// 非byte类型,全部转为字符串处理
			input.put(key, value + "");
		}
	}

	/**
	 * 通过映射文件,将response返回值映射成输出，2.0为xml格式，1.0为对象结构
	 * 
	 * @param file
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object testXmlResponse(String file, Object response) throws Exception {
		input.put("format", "xml");
		return testDefaultResponse(file, response);
	}

	/**
	 * 测试请求参数是否转换成功 通过读取映射文件file，将input内的参数映射成服务提供方对象
	 * 
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public Object[] testRequest(String filename) throws Exception {
		MethodMapping<Object> api = r.read(new FileInputStream(new File(filename)));
		try {
			f.addMethodMapping(api);
		} catch (Exception e) {
			e.printStackTrace();
		}

		ServiceInvokeTransformV2 form = new ServiceInvokeTransformV2(api.getInterfaceName(), api.getInterfaceVersion(), f);

		if (input.get("v") == null)
			input.put("v", "2.0");

		Object[] ar = { "nouse", input };
		String[] type = { "nouse" };
		return form.transformRequest(api.getMethodCallMapping().getName(), ar, type);
	}

	public String[] testRequestTypes(String filename) throws Exception {
		MethodMapping<Object> api = r.read(new FileInputStream(new File(filename)));
		if (null == f.getMethodMapping(api.getMethodCallMapping().getName())) {
			f.addMethodMapping(api);
		}

		ServiceInvokeTransformV2 form = new ServiceInvokeTransformV2(api.getInterfaceName(), api.getInterfaceVersion(), f);

		if (input.get("v") == null)
			input.put("v", "2.0");

		Object[] ar = { "nouse", input };
		String[] type = { "nouse" };
		return form.transformRequestTypes(api.getMethodCallMapping().getName(), ar, type);
	}

	public void assertRequest(Object[] expect, String filename) throws Exception {
		Assert.assertArrayEquals(expect, testRequest(filename));
	}

	public void assertRequestTypes(String[] expect, String filename) throws Exception {
		Assert.assertArrayEquals(expect, testRequestTypes(filename));
	}

	/**
	 * 通过映射文件,将response返回值映射成输出，2.0为json格式，1.0为对象结构
	 * 
	 * @param file
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public Object testDefaultResponse(String file, Object response) throws Exception {
		MethodMapping<Object> api = r.read(new FileInputStream(new File(file)));
		try {
			f.addMethodMapping(api);
		} catch (Exception e) {

		}
		ServiceInvokeTransformV2 form = new ServiceInvokeTransformV2(api.getInterfaceName(), api.getInterfaceVersion(), f);

		if (input.get("v") == null)
			input.put("v", "2.0");
		else if (input.get("v").equals("1.0")) {
			return testV1DefaultResponse(file, response, api);
		}

		Object[] ar = { "nouse", input };
		String[] type = { "nouse" };
		Object[] transformResponse2 = (Object[]) form.transformResponse(api.getMethodCallMapping().getName(), ar, type, response);
		Object transformResponse = transformResponse2[0];
		return transformResponse;
	}

	/**
	 * 测试v1返回的结构
	 * 
	 * @param file
	 * @param response
	 * @param api
	 * @return
	 * @throws Exception
	 */
	public Object testV1DefaultResponse(String file, Object response, MethodMapping<Object> api) throws Exception {
		ServiceInvokeTransformV2 form = new ServiceInvokeTransformV2(api.getInterfaceName(), api.getInterfaceVersion(), f);
		int formType = "xml".equals(input.get("format")) ? 1 : 0;
		Object[] ar = { formType, input };
		String[] type = { "" };
		Object transformResponse = form.transformResponse(api.getMethodCallMapping().getName(), ar, type, response);
		return transformResponse;
	}
}
