package q.serialize.tools;

import java.util.HashMap;
import java.util.Map;

import q.serialize.mapping.HasMembersMapping;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MemberMapping;


/**
 * 由top做的mapping转为服务提供方自行转换
 * 
 * @author jiangyy
 */
public class ApiInputMappingHsf {

	/**
	 * 获得传入的简单数据,如string或int等,ObjectMapping直接返回Map,由ObjectMapping自行取数据。
	 * 
	 * @param input
	 * @param param
	 * @return
	 */
	public static Object mapping(Map<String, Object> input,
			MemberMapping<?> param) {
		Object value = null;
		if (param.getName() == null) {
			// jiangyy 10-29日单元测试,此跑不通,@see {HsfSpringMappingTest}
			// Collection<MemberMapping<?>> members = param.getMapping()
			// .getMemberMappings().values();
			// Map<String, Object> apiMap = new HashMap<String, Object>(members
			// .size());
			Mapping<?> mapping = param.getMapping();

			// XXX 如果是对象类型,返回整个map,由ObjectMapping自行去Map取数据
			if (mapping instanceof HasMembersMapping<?>) {
				// Map<String, Object> apiMap2 = new HashMap<String,
				// Object>(members
				// .size());
				//
				// //获得复杂数据
				// ObjectMapping<?> obm = (ObjectMapping<?>) mapping;
				// Collection<MemberMapping<?>> memberMappings = obm
				// .getMemberMappings().values();
				//				
				// for (MemberMapping<?> mmp : memberMappings) {
				// Object mapping2 = mapping(input, mmp);
				// apiMap2.put(mmp.getName(), mapping2);
				// }
				// apiMap.put(param.getName(),apiMap2);
				return input;
			} else {
				Map<String, Object> apiMap = new HashMap<String, Object>();
				apiMap.put(param.getName(), singleMapping(input, param
						.getName(), param.getMappingType()));
			}
		}
		// 存在和api参数对应的
		else {
			value = singleMapping(input, param.getName(), param
					.getMappingType());
		}
		return value;
	}

	private static Object singleMapping(Map<String, Object> input, String apiParamName,
			String remoteType) {
		Object value = null;
		// XXX session_nick和session_id,app_key,api_key需要特殊处理 zixue
		// TODO byte[]数组目前表示图片，一个api只有一张图片，先写死 zixue
		if (byte[].class.getName().equals(remoteType)) {
			value = input.get(apiParamName);
		} else {
			value = (String) input.get(apiParamName);
		}
		return value;
	}
}
