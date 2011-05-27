package q.serialize.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating DefaultMemberMapping objects.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 8, 2011
 * 
 */
public class DefaultMemberMappingFactory implements MemberMappingFactory {
	private Map<String, MemberMapping<Object>> memberMappings = new HashMap<String, MemberMapping<Object>>();

	public MemberMapping<Object> getMemberMapping(String method) {
		return this.memberMappings.get(method);
	}

	public void addMemberMapping(MemberMapping<Object> mapping, String name) {
		if (this.memberMappings.containsKey(name)) {
			throw new IllegalStateException("MemberMapping already exists:" + name);
		}
		this.memberMappings.put(name, mapping);
	}

	public int size() {
		return this.memberMappings == null ? 0 : this.memberMappings.size();
	}

}
