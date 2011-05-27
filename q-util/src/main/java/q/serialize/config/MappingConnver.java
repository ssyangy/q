package q.serialize.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import q.serialize.mapping.DefaultSwitcher;
import q.serialize.mapping.HasMembersMapping;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MemberMapping;


/**
 * 运行期间,将MemberMapping的映射关系互换,此类目的为annotation获得mapping时,可以将其关系互换.<br>
 * 
 * 用于解决通过annotation配置，mappingName与name互换的问题,查看MappingConfigReader的方法parseMember(boolean
 * mappingDirection 第一个参数mappingDirection
 * 
 * @see MappingConfigReader.parseMember
 * @see MappingConfigWriter中输出methodCall时的呼唤。
 * 
 * @author jiangyongyuan.tw
 * 
 */
public class MappingConnver {

	/**
	 * 转换mapping的名字与switcher的值,以便MappingClassReader可以正常使用
	 * 
	 * @param mm
	 * @return
	 */
	public MemberMapping<?> connver(MemberMapping<?> source) {

		Mapping<?> mapping = source.getMapping();

		// 处理HasMemberMapping
		if (mapping instanceof HasMembersMapping) {
			Map<String, MemberMapping<?>> memberMappings = mapping
					.getMemberMappings();
			for (Map.Entry<String, MemberMapping<?>> entry : memberMappings
					.entrySet()) {
				MemberMapping<?> value = entry.getValue();
				connver(value);
			}
		}

		// change mappingName
		changeName2MappingName(source);

		// change switcher
		if (source.getSwitcher() != null) {
			changeSwitcher((DefaultSwitcher) source.getSwitcher());
		}

		return source;
	}

	/**
	 * 修改mappingName,name
	 */
	private void changeName2MappingName(MemberMapping<?> source) {
		// get name
		String name = source.getName();
		String mappingName = source.getMappingName();

		// change each other
		String tmp = name;
		name = mappingName;
		mappingName = tmp;

		// set to the distination MemberMapping
		source.setName(name);
		source.setMappingName(mappingName);
	}

	/**
	 * 修改switcher
	 */
	private void changeSwitcher(DefaultSwitcher switcher) {
		Map<Object, Object> cases = switcher.getCases();
		Map<Object, Object> newCase = new HashMap<Object, Object>();
		for (Entry<Object, Object> entry : cases.entrySet()) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			newCase.put(value, key);
		}
		switcher.setCases(newCase);
	}
}