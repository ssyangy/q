package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 方法映射。包括MethodCallMapping和MethodResponeMapping.
 * 
 * @param <R>
 *            返回值转换模板
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class MethodMapping<R> {

	/** The interface name. */
	private String interfaceName;

	/**
	 * Gets the interface name.
	 * 
	 * @return the interfaceName
	 */
	public String getInterfaceName() {
		return interfaceName;
	}

	/**
	 * Sets the interface name.
	 * 
	 * @param interfaceName
	 *            the interfaceName to set
	 */
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	/** The interface version. */
	private String interfaceVersion;

	/**
	 * Gets the interface version.
	 * 
	 * @return the interfaceVersion
	 */
	public String getInterfaceVersion() {
		return interfaceVersion;
	}

	/**
	 * Sets the interface version.
	 * 
	 * @param interfaceVersion
	 *            the interfaceVersion to set
	 */
	public void setInterfaceVersion(String interfaceVersion) {
		this.interfaceVersion = interfaceVersion;
	}

	/** The method call mapping. */
	private MethodCallMapping methodCallMapping;

	/**
	 * Gets the method call mapping.
	 * 
	 * @return the methodCallMapping
	 */
	public MethodCallMapping getMethodCallMapping() {
		return methodCallMapping;
	}

	/**
	 * Sets the method call mapping.
	 * 
	 * @param methodCallMapping
	 *            the methodCallMapping to set
	 */
	public void setMethodCallMapping(MethodCallMapping methodCallMapping) {
		this.methodCallMapping = methodCallMapping;
	}

	/** The method response mapping. */
	private MethodResponseMapping<R> methodResponseMapping;

	/**
	 * Gets the method response mapping.
	 * 
	 * @return the methodResponseMapping
	 */
	public MethodResponseMapping<R> getMethodResponseMapping() {
		return methodResponseMapping;
	}

	/**
	 * Sets the method response mapping.
	 * 
	 * @param methodResponseMapping
	 *            the methodResponseMapping to set
	 */
	public void setMethodResponseMapping(MethodResponseMapping<R> methodResponseMapping) {
		this.methodResponseMapping = methodResponseMapping;
	}
	
	private String mappingConfig;

	public String getMappingConfig() {
		return mappingConfig;
	}

	public void setMappingConfig(String mappingConfig) {
		this.mappingConfig = mappingConfig;
	}
	
	

}
