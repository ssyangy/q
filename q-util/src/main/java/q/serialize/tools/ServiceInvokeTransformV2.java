package q.serialize.tools;

import java.io.Writer;
import java.util.HashMap;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import q.serialize.convert.Convert;
import q.serialize.convert.ConvertFactory;
import q.serialize.convert.DefaultConvertFactory;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodCallMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.MethodMappingFactory;
import q.serialize.mapping.MethodResponseMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.util.LogForException;
import q.serialize.util.StringBuilderWriter;
import q.serialize.util.Utils;


/**
 * mapping 2.0 版本,对应输入的api的map,输出xml/json
 * 
 * @author jiangyongyuan.tw
 */
public class ServiceInvokeTransformV2 {
	/** top xml/json协议版本号2.0 */
	public final static String TOP_PROTOCOL_VERSION_2 = "2.0";

	/**
	 * 对应ProtocolConstants.P_FORMAT
	 * 
	 * @see {ProtocolConstants.P_FORMAT}
	 */
	public final static String P_FORMAT = "format";

	/** 服务名 */
	private String serviceInterface;

	/** 版本号 */
	private String serviceVersion;

	/** method mapping 存储 */
	private MethodMappingFactory methodMappingFactory;

	/** 对象转换 */
	private ConvertFactory convertFactory;

	public ServiceInvokeTransformV2(String serviceInterface, String serviceVersion, MethodMappingFactory f) {
		this.serviceInterface = serviceInterface;
		this.serviceVersion = serviceVersion;
		this.methodMappingFactory = f;
		this.convertFactory = new DefaultConvertFactory();
	}

	public ServiceInvokeTransformV2(String serviceInterface, String serviceVersion, MethodMappingFactory f, ConvertFactory cf) {
		this.serviceInterface = serviceInterface;
		this.serviceVersion = serviceVersion;
		this.methodMappingFactory = f;
		this.convertFactory = cf;
	}

	public Object[] transformRequest(String method, Object[] args, String[] types) throws Exception {
		MethodMapping<Object> mm = methodMappingFactory.getMethodMapping(method);
		if (mm == null) {
			throw new IllegalArgumentException("MethodMapping not exist:" + method);
		}
		MethodCallMapping mcm = mm.getMethodCallMapping();
		if (CollectionUtils.isEmpty(mcm.getParameterMappings())) {
			return ArrayUtils.EMPTY_OBJECT_ARRAY;
		}
		MemberMapping<?>[] pms = mcm.getParameterMappings().toArray(new MemberMapping<?>[mcm.getParameterMappings().size()]);

		// 转换后结果数组
		Object[] results = new Object[pms.length];
		HashMap<String, Object> input;
		try {
			// 2.0版本,Object[1]是一个map
			input = (HashMap<String, Object>) args[1];
		} catch (Exception e) {
			throw new Exception("hsf接入isp端已升级为top-mapping-2.xx,需要升级api定义文件，加上hsf provider地址");
		}

		// 逐个映射参数
		for (int i = 0; i < results.length; i++) {
			try {
				MemberMapping<?> memberMapping = pms[i];
				// 取的真实数据
				Object mappingData = ApiInputMappingHsf.mapping(input, memberMapping);
				results[i] = memberMapping.mapping(mappingData, false);
			} catch (MappingException e) {
				LogForException.logInput(method, args, types);
				log.error("Mapping parameter exception:", e);
				throw e;
			} catch (Exception e) {
				LogForException.logInput(method, args, types);
				log.error("Mapping parameter runtime exeption:", e);
				throw e;
			}
		}
		return results;
	}

	public String[] transformRequestTypes(String method, Object[] args, String[] types) throws Exception {
		MethodMapping<Object> mm = methodMappingFactory.getMethodMapping(method);
		if (mm == null) {
			throw new IllegalArgumentException("MethodMapping not exist:" + method);
		}
		MethodCallMapping mcm = mm.getMethodCallMapping();
		if (CollectionUtils.isEmpty(mcm.getParameterMappings())) {
			return ArrayUtils.EMPTY_STRING_ARRAY;
		}
		MemberMapping<?>[] pms = mcm.getParameterMappings().toArray(new MemberMapping<?>[mcm.getParameterMappings().size()]);

		String[] typesArg = new String[pms.length];
		int typeIndex = 0;
		for (MemberMapping<?> param : pms) {
			typesArg[typeIndex] = param.getMappingType();
			typeIndex++;
		}

		return typesArg;
	}

	private void initTrueResult(String method, String format, Object resp, Object[] result) throws Exception {
		if (resp instanceof Throwable) { // hsf maybe input throwable
			Throwable t = (Throwable) resp;
			result[0] = new Exception(t.toString());
			return;
		}
		// 取到方法映射器
		MethodMapping<Object> mm = methodMappingFactory.getMethodMapping(method);
		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();
		// 取到执行结果映射器
		MemberMapping<Object> om = mrm.getParameterMapping();
		if (format == null)
			format = Utils.JSON_FORMAT;
		// stringbuilderwriter is more efficient than stringwriter
		Writer writer = new StringBuilderWriter(1024);
		Convert convert = convertFactory.getConvert(format, writer);

		if (om == null || resp == null || convert == null) {
			if (Utils.JSON_FORMAT.equals(format)) {
				result[0] = "null";
				return;
			} else {
				result[0] = "";
				return;
			}
		}
		convert.convertBefore(om);// start convert
		try {
			om.write(convert, resp, false);
		} catch (OperationCodeException e) {
			result[0] = e;
			return;
		} catch (Throwable e) {
			throw new Exception(e.toString());
		}
		convert.convertDown(om);// end convert
		result[0] = writer.toString();
		return;
	}

	/**
	 * 生成2.0 xml /json,待1.0全部废弃后,使用此方法处理返回,现使用transformResponse方法
	 */
	public Object transformResponseV2(String method, String format, Object resp) throws Exception {
		Object[] result = new Object[2];
		long start = System.nanoTime();
		this.initTrueResult(method, format, resp, result);
		result[1] = System.nanoTime() - start;
		return result;
	}

	/**
	 * 由于top
	 * 1.0协议任然保留,mapping如果在服务提供方生成xml/json则需要生成1.0协议或2.0协议.故解决方案为不在服务提供方生成xml
	 * /json. 此方法返回Object.
	 * 
	 * @param method
	 * @param args
	 * @param onArorigonArgTypesgTypes
	 * @param resp
	 * @param methodMappingFactory
	 * @return
	 * @throws Exception
	 */
	public Object transformResponse(String method, Object[] args, String[] onArorigonArgTypesgTypes, Object resp) throws Exception {

		// 2.0版本,Object[1]是一个map
		HashMap<String, Object> input = (HashMap<String, Object>) args[1];

		String version = (String) input.get("v");

		// 如果协议是2.0,直接输出xml,json
		if (TOP_PROTOCOL_VERSION_2.equals(version)) {
			String format = (String) input.get("format");
			Object v2rsp = transformResponseV2(method, format, resp);
			assert v2rsp instanceof Object[] : "v2 response object isn't Object[]";
			return v2rsp;
		} else
			// 如果协议是1.0,返回对象
			return ServiceInvokeTransformV1.transformResponseV1(method, args, onArorigonArgTypesgTypes, resp, methodMappingFactory);
	}

	// for < hsf-1.4.3
	public String getServiceInterface() {
		return serviceInterface;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public String serviceInterfaceName() {
		return serviceInterface;
	}

	public String serviceVersion() {
		return serviceVersion;
	}

	/** The Constant log. */
	private final static Log log = LogFactory.getLog(ServiceInvokeTransformV2.class);

}
