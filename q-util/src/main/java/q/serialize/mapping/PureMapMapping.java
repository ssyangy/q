/**
 * 
 */
package q.serialize.mapping;

import java.util.HashMap;
import java.util.Map;

/**
 * map -> map.
 * 
 * @version 2010-8-24
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class PureMapMapping extends HasMembersMapping<Map<Object, Object>> {

	/**
	 * 构造函数.
	 */
	public PureMapMapping() {
	}

	/**
	 * The Constructor.
	 * 
	 * @param mapName
	 *            the map name
	 */
	public PureMapMapping(String mapName) {
		this.setMappingName(mapName);
	}
	/**
	 * TODO
	 * 此处是否需要和ObjectMapping一样，根据多层转换为map,如location.state.@see{ObjectMapping.
	 * mappingInternal,ObjecMapping.readTheInnerObject}
	 */

	/*
	 * 通过输入对象的getter方法映射成map
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.ObjectMapping#mapping(java.lang.Object)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Map<Object, Object> mappingInternal(Object source,
			ExceptionExpressInfo ee, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		Map<Object, Object> map = null;
		Class<?> clz = source.getClass();
		// source is a map
		if (Map.class.isAssignableFrom(clz)) {
			Map<Object, Object> original = (Map<Object, Object>) source;
			convertMap2Exception(ee, original);
			map = new HashMap<Object, Object>();
			for (Map.Entry<Object, Object> entry : original.entrySet()){
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}


}
