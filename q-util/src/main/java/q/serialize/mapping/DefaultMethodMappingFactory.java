package q.serialize.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * A factory for creating DefaultMethodMapping objects.
 */
public class DefaultMethodMappingFactory implements MethodMappingFactory {

	/** The method mappings. */
	private Map<String, MethodMapping<Object>> methodMappings = new HashMap<String, MethodMapping<Object>>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.MethodMappingFactory#getMethodMapping(
	 * java.lang.String)
	 */
	public MethodMapping<Object> getMethodMapping(String method) {
		return this.methodMappings.get(method);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.MethodMappingFactory#addMethodMapping(
	 * com.taobao.top.traffic.mapping.MethodMapping)
	 */
	public void addMethodMapping(MethodMapping<Object> mapping) {
		//System.out.println(mapping.getMethodCallMapping().getMappingName());
		if (mapping.getMethodCallMapping() != null && mapping.getMethodCallMapping().getName() != null) {
			if (this.methodMappings.containsKey(mapping.getMethodCallMapping()
					.getName())) {
				throw new IllegalStateException(
						"MethodCallMapping already exists:"
								+ mapping.getMethodCallMapping().getName());
			}
			this.methodMappings.put(mapping.getMethodCallMapping().getName(),
					mapping);
		}
	}

	public int size() {
		return this.methodMappings == null ? 0 : this.methodMappings.size();
	}
	
}
