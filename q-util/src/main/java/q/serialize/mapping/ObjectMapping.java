/**
 * 
 */
package q.serialize.mapping;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

import q.util.ReflectKit;


/**
 * Map -> Object.
 * 
 * @author alin mailto:xalinx@gmail.com
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 * @date Aug 20, 2009
 */
public class ObjectMapping<T> extends HasMembersMapping<T> {
	/** mappingType：映射成的类型. */
	private Class<T> mappingType;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<T> getMappingType() {
		return mappingType;
	}
	
	public void setMappingType(Class<T> mappingType) {
		this.mappingType = mappingType;
	}

	/**
	 * 初始化构造函数.
	 * 
	 * @param mappingType
	 *            the mapping type
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public ObjectMapping(Class<T> mappingType){
		if (null == mappingType) {
			throw new IllegalStateException();
		}
		this.mappingType = mappingType;
	}

	/**
	 * 通过类名来初始化.
	 * 
	 * @param clzName
	 *            the clz name
	 * @throws ClassNotFoundException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	public ObjectMapping(String clzName) throws ClassNotFoundException {
		this((Class<T>) Class.forName(clzName));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, boolean)
	 */
	@Override
	protected T mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		T rs = null;
		try {
			rs = this.mappingType.newInstance();
		} catch (Exception e) {
			throw new UnsupportSourceTypeException(e);
		}
		Map<String, MemberMapping<?>> memberMappings = this.getMemberMappings();
		if (Map.class.isAssignableFrom(source.getClass())) {
			// Map -> Object
			map2obj(source, exceptionExpress, withArrayItemName, rs,
					memberMappings);
		} else {
			// Object -> Object
			obj2obj(source, exceptionExpress, withArrayItemName, rs,
					memberMappings);
		}
		return rs;
	}

	private void obj2obj(Object source, ExceptionExpressInfo exceptionExpress,
			boolean withArrayItemName, T rs,
			Map<String, MemberMapping<?>> memberMappings)
			throws OperationCodeException, MappingException {
		convertPojo2Exception(exceptionExpress, source);
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
			if (value == null) {
				continue;
			}
			String mappingName = mm.getMappingName();
			Object mappingValue = mm.mapping(value, withArrayItemName);
			Class<?> mappingType = mm.getMapping().getMappingType();
			Method setter = ReflectKit.getCachedSetter(mm, this.mappingType, mappingName, mappingType);
			ReflectKit.invokeSetter(rs, setter, mappingValue);
		}
	}

	private void map2obj(Object source, ExceptionExpressInfo exceptionExpress,
			boolean withArrayItemName, T rs,
			Map<String, MemberMapping<?>> memberMappings)
			throws MappingException, OperationCodeException {
		Map<String, Object> original = (Map<String, Object>) source;
		for (MemberMapping<?> mm : memberMappings.values()) {
			String key = mm.getName();
			// System.out.println("ObjectMapping:"+key);
			Mapping<?> mapping = mm.getMapping();
			String mappingName = mm.getMappingName();

			// jiangyy :内部嵌套对象,取的对象内容,如：location.state
			if (mapping instanceof ObjectMapping<?>) {// 如果定义了type class类型
				readTheInnerObject(mm, source, exceptionExpress,
						withArrayItemName, mappingName, rs);
				// continue to next
				continue;
			}

			// jiangyy :直接拿数据,如name
			Object value = original.get(key);
			if (value == null) {// 通过文件进来,是通过mappingPreFix读数据,还是通过直接定义key取数据？
				String mappingPreFix = this.getMappingPreFix();
				// //如果是嵌入的对象输入：pricture.id
				if (null != mappingPreFix && !"".equals(mappingPreFix)) {
					// mappingName = getMappingPreFix()+"."+mappingName;
					value = original.get(mappingPreFix + "." + key);
				}
				if (value == null) {
					continue;
				}
			}

			Object mappingValue = mm.mapping(value, withArrayItemName);
			Class<?> mappingType = mm.getMapping().getMappingType();
			Method setter = ReflectKit.getCachedSetter(mm, this.mappingType, mappingName, mappingType);
			ReflectKit.invokeSetter(rs, setter, mappingValue);
		}
	}

	
	/**
	 * param定义了type class类型,用户传进来的数据时location.state,对应映射的对象为Location <member
	 * name="location" apiName="location"> <type
	 * class="com.taobao.top.dal.domain.Location"> <struct> <member name="state"
	 * type="string" apiName="location.state" /> <member name="city"
	 * type="string" apiName="location.city" /> </struct> </type> </member>
	 * 
	 * 
	 * @param mapping
	 *            :内部对象的ObjectMapping
	 * @param source
	 *            :原对象
	 * @param exceptionExpress
	 * @param withArrayItemName
	 * @param mappingName
	 * @param rs
	 *            映射后的对象
	 * @throws Exception
	 */
	private void readTheInnerObject(MemberMapping<?> mm, Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName,
			String mappingName, Object rs) throws MappingException,
			OperationCodeException {
		ObjectMapping<?> mapping = (ObjectMapping<?>) mm.getMapping();

		String realPreFix = mapping.getMappingPreFix();

		// set need prefix
		mapping.setMappingPreFix(mm.getMappingName());

		// get the value
		Object mappingValue = mapping.mapping(source, exceptionExpress,
				withArrayItemName);

		// clear the value
		mapping.setMappingPreFix(realPreFix);

		// set the value
		try {
			PropertyDescriptor write = new PropertyDescriptor(mappingName, rs
					.getClass());
			Method writeMethod = write.getWriteMethod();
			writeMethod.invoke(rs, new Object[] { mappingValue });
		} catch (Exception e) {
			throw new MappingException(String.format(
					"ObjectProperty[name:%s,value:%s,type:%s]", mappingName,
					mappingValue, mappingType), e);
		}
	}

}
