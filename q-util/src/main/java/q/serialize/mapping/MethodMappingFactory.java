package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating MethodMapping objects.
 */
public interface MethodMappingFactory {

	/**
	 * 获取factory中的MethodMapping.
	 * 
	 * @param hodmethod
	 *            the method
	 * 
	 * @return the method mapping
	 */
	MethodMapping<Object> getMethodMapping(String hodmethod);

	/**
	 * 添加MethodMapping.
	 * 
	 * @param api
	 *            the api
	 */
	void addMethodMapping(MethodMapping<Object> api);

	/**
	 * method size
	 * 
	 * @return
	 */
	int size();
}
