/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 方法返回值映射.
 * 
 * @param <R>
 *            返回值转换模板
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class MethodResponseMapping<R> {

	/** 方法返回值mapping. */
	private MemberMapping<R> parameterMapping;

	/**
	 * Gets the parameter mapping.
	 * 
	 * @return the objectMapping
	 */
	public MemberMapping<R> getParameterMapping() {
		return parameterMapping;
	}

	/**
	 * Sets the parameter mapping.
	 * 
	 * @param resultMapping
	 *            the objectMapping to set
	 */
	public void setParameterMapping(MemberMapping<R> resultMapping) {
		this.parameterMapping = resultMapping;
	}

	/**
	 * The Constructor.
	 * 
	 * @param memberMapping
	 *            the member mapping
	 */
	public MethodResponseMapping(MemberMapping<R> memberMapping) {
		this.parameterMapping = memberMapping;
	}

	/**
	 * Instantiates a new method response mapping.
	 */
	public MethodResponseMapping() {
	}

}
