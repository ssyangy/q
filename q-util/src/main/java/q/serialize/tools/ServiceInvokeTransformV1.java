package q.serialize.tools;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodCallMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.MethodMappingFactory;
import q.serialize.mapping.MethodResponseMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.util.LogForException;


/**
 * mapping 1.0版本，对应传入Mapping过的Map对象，输出Mapping过的对象
 * 
 * @author jiangyongyuan.tw
 */
public class ServiceInvokeTransformV1 {

	/** 服务名 */
	private String serviceInterface;

	/** 版本号 */
	private String serviceVersion;

	/** method mapping 存储 */
	private MethodMappingFactory f;

	public ServiceInvokeTransformV1(String serviceInterface, String serviceVersion, MethodMappingFactory f) {
		this.serviceInterface = serviceInterface;
		this.serviceVersion = serviceVersion;
		this.f = f;
	}

	public Object[] transformRequest(String method, Object[] args, String[] types) throws Exception {
		// 方法没有参数
		if (ArrayUtils.isEmpty(args)) {
			return null;
		}
		// type必须和arg相等
		if (types.length != args.length) {
			String logs = LogForException.logInput(method, args, types);
			throw new IllegalStateException("Types and Args unmatch!" + logs);
		}
		MethodMapping<Object> mm = f.getMethodMapping(method);
		if (mm == null) {
			throw new IllegalArgumentException("MethodMapping not exist:" + method);
		}
		MethodCallMapping mcm = mm.getMethodCallMapping();
		MemberMapping<?>[] pms = mcm.getParameterMappings().toArray(new MemberMapping<?>[mcm.getParameterMappings().size()]);
		// XXX TODO 暂时屏蔽,根据版本号去判断
		// type,value比mapping多了一个formType参数
		if (args.length != pms.length + 1) {
			String logs = LogForException.logInput(method, args, types);
			throw new IllegalStateException("Values and Mappings unmatch! " + logs);
		}
		// 转换后结果数组
		Object[] results = new Object[pms.length];
		// 逐个映射参数
		for (int i = 0; i < results.length; i++) {
			try {
				results[i] = pms[i].getMapping().mapping(args[i + 1], null, false);
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

	public String[] transformRequestTypes(String methodName, Object[] args, String[] argTypes) throws Exception {
		String[] result = new String[argTypes.length - 1];
		System.arraycopy(argTypes, 1, result, 0, result.length);
		return result;
	}

	public Object transformResponse(String method, Object[] args, String[] origonArgTypes, Object resp) throws Exception {
		return transformResponseV1(method, args, origonArgTypes, resp, f);
	}

	/**
	 * 由于top 1.0协议任然保留,mapping如果在服务提供方生成xml/json则需要生成1.0协议或2.0协议.故解决方案为不在服务提供方生成xml /json. 此方法返回Object.
	 * 
	 * @param method
	 * @param args
	 * @param origonArgTypes
	 * @param resp
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static Object transformResponseV1(String method, Object[] args, String[] origonArgTypes, Object resp, MethodMappingFactory f) throws Exception {
		// 取到方法映射器
		MethodMapping<Object> mm = f.getMethodMapping(method);

		MethodResponseMapping<Object> mrm = mm.getMethodResponseMapping();

		// 取到执行结果映射器
		MemberMapping<Object> om = mrm.getParameterMapping();

		// 转换方法返回值
		Object result = null;
		// 第一个是formType
		Integer formType = (Integer) args[0];
		boolean withArrayItemName = false;
		// 1表示xml, 需要数组元素名
		if (formType != null && formType == 1) {
			withArrayItemName = true;
		}
		if (resp instanceof Exception) {
			log.error(LogForException.logInput(method, args, origonArgTypes));
			log.error("method call exception:", (Exception) resp);
			// 服务内部异常则返回exception，带上服务异常信息
			result = new Exception(((Exception) resp).getMessage());
		} else {
			try {
				// 正常转换结果
				result = om.mapping(resp, withArrayItemName);
			} catch (MappingException e) {
				log.error(LogForException.logOutput(method, args, origonArgTypes, resp));
				log.error("", e);
				result = e;
			} catch (OperationCodeException e) {
				// 直接返回业务错误OperationCodeException，供top转换成对外ErrorCode.
				result = e;
			} catch (Exception e) {
				log.error("", e);
				throw e;
			}

			// jiangyy : 拷贝top逻辑
			MemberMapping<Object> resultMapping = mrm.getParameterMapping();

			// 如果响应值有一个apiName,那么top这边惯例需要包一层标签
			if (resultMapping.getMappingName() != null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put(resultMapping.getMappingName(), result);
				result = map;
			}
			// jiangyy end
		}
		return result;
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
	private final static Log log = LogFactory.getLog(ServiceInvokeTransformV1.class);
}
