/**
 * 
 */
package q.serialize.mapping;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import q.util.ReflectKit;


/**
 * pojo/map -> map.
 * 
 * @version 2009-8-16
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class MapMapping extends HasMembersMapping<Map<Object, Object>> {

	/**
	 * 构造函数.
	 */
	public MapMapping() {
	}

	/**
	 * The Constructor.
	 * 
	 * @param mapName
	 *            the map name
	 */
	public MapMapping(String mapName) {
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
		Map<String, MemberMapping<?>> memberMappings = this.getMemberMappings();
		Map<Object, Object> map = new HashMap<Object, Object>(memberMappings.size());
		Class<?> clz = source.getClass();
		// source is a map
		if (Map.class.isAssignableFrom(clz)) {
			@SuppressWarnings("unchecked")
			Map<Object, Object> original = (Map<Object, Object>) source;
			convertMap2Exception(ee, original);
			for (MemberMapping<?> mm : memberMappings.values()) {
				// XXX only suport Map<String,Object>, if key is not a String,
				// use key.toString()
				String name = mm.getName().toString();
				Object value = original.get(name);
				map.put(mm.getMappingName(), mm.mapping(value,
						withArrayItemName));
			}
			// source is a pojo
		} else {
			convertPojo2Exception(ee, source);
			for (MemberMapping<?> mm : memberMappings.values()) {
				String key = mm.getName();
				String[] keys = mm.getNames();
				Object value = null;

				if (keys != null) {
					value = ReflectKit.invoke(source, keys);
				} else if (key != null) {
					Method m = ReflectKit.getCachedGetter(mm, source.getClass(), key);
					value = ReflectKit.invokeGetter(source, m);
				}
				String memberMappingName = mm.getMappingName();
				map
						.put(memberMappingName, mm.mapping(value,
								withArrayItemName));
			}
			if (withArrayItemName && (null != this.getMappingName())) {
				Map<Object, Object> wrap = new HashMap<Object, Object>(1);
				wrap.put(this.getMappingName(), map);
				map = wrap;
			}
		}
		return map;
	}


}
