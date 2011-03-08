package q.serialize.mapping;

/**
 * A factory for creating MemberMapping objects.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 8, 2011
 *
 */
public interface MemberMappingFactory {

	/**
	 * 获取factory中的MethodMapping.
	 * 
	 * @param hodmethod
	 *            the method
	 * 
	 * @return the method mapping
	 */
	MemberMapping<Object> getMemberMapping(String name);

	/**
	 * 添加MethodMapping.
	 * 
	 * @param api
	 *            the api
	 * @param name TODO
	 */
	void addMemberMapping(MemberMapping<Object> api, String name);

	/**
	 * method size
	 * 
	 * @return
	 */
	int size();
}
