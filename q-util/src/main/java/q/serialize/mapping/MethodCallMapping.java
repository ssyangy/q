/**
 * 
 */
package q.serialize.mapping;

import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * 方法呼叫映射.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class MethodCallMapping {

	/** 方法名. */
	private String name;

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 对应的api的名字. */
	private String mappingName;

	/**
	 * Gets the mapping name.
	 * 
	 * @return the apiName
	 */
	public String getMappingName() {
		return mappingName;
	}

	/**
	 * Sets the mapping name.
	 * 
	 * @param apiName
	 *            the apiName to set
	 */
	public void setMappingName(String apiName) {
		this.mappingName = apiName;
	}

	/** 方法参数mapping列表. */
	private List<MemberMapping<?>> parameterMappings;

	/**
	 * Gets the parameter mappings.
	 * 
	 * @return the paramsMapping
	 */
	public List<MemberMapping<?>> getParameterMappings() {
		return parameterMappings;
	}

	/**
	 * Adds the parameter mapping.
	 * 
	 * @param paramMapping
	 *            the param mapping
	 */
	public void addParameterMapping(MemberMapping<?> paramMapping) {
		if (this.parameterMappings == null) {
			this.parameterMappings = new ArrayList<MemberMapping<?>>();
		}
		this.parameterMappings.add(paramMapping);
	}
}
