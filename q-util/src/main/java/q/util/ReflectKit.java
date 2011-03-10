/**
 * 
 */
package q.util;

import java.lang.reflect.Method;
import java.util.Map;

import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;


/**
 * @author xalinx at gmail dot com
 * @date Apr 1, 2010
 */
public class ReflectKit {
	
	private static Method getGetter(Class<?> clz, String propName) {
		// propName -> PropName
		String keyCap = StringKit.capitalize(propName);
		Method m = null;
		try {
			try {
				// getPropName()
				m = clz.getMethod("get" + keyCap, null);
			} catch (NoSuchMethodException e) {
				// isPropName()
				m = clz.getMethod("is" + keyCap, null);
			}
		} catch (NoSuchMethodException e) {
			// ignore not exsit getter
		}
		if (m != null) {
			m.setAccessible(true);
		}
		return m;
	}

	private static Method getSetter(Class<?> sourceType, String propName,
			Class<?> propType) {
		Method m = null;
		// propName -> PropName
		String keyCap = StringKit.capitalize(propName);
		try {
			// get setter
			m = sourceType.getMethod("set" + keyCap, propType);
			m.setAccessible(true);
		} catch (NoSuchMethodException e) {
		}
		return m;
	}
	
	/**
	 * @param source
	 * @param key
	 * @return
	 * @throws MappingException 
	 */
	public static Object invokeGetter(Object source, String propName) throws MappingException {
		if(source == null) {
			return null;
		}
		Object result = null;
		if (source instanceof Map) {
			Map<String, Object> map = (Map<String, Object>) source;
			result = map.get(propName);
		} else {
			Method m = ReflectKit.getGetter(source.getClass(), propName);
			if(m != null) {
				result = invokeGetter(source, m);
			}
		}
		return result;
	}
	
	/**
	 * @param source
	 * @param m
	 * @return
	 * @throws MappingException
	 */
	public static Object invokeGetter(Object source, Method m)
			throws MappingException {
		if(source == null || m == null) {
			return null;
		}
		Object result = null;
		try {
			result = m.invoke(source, null);
		} catch (Exception e) {
			throw new MappingException("Invoke getter error:", e);
		}
		return result;
	}
	
	public static void invokeSetter(Object source, Method method,
			Object propValue) throws MappingException {
		if(method == null) {
			throw new MappingException("Method not exist");
		}
		try {
			method.invoke(source, new Object[] { propValue });
		} catch (Exception e) {
			throw new MappingException("Invoke setter error:", e);
		}
	}
	

	/**
	 * @param mm
	 * @param mappingType2
	 * @param propName
	 * @param mappingType3
	 * @return
	 */
	public static Method getCachedSetter(MemberMapping<?> mm, Class<?> sourceType,
			String propName, Class<?> propType) {
			Method method = mm.getSetter();
			if(method == null) {
				method = ReflectKit.getSetter(sourceType, propName, propType);
				mm.setSetter(method);
			}
			return method;
	}
	
	public static Method getCachedGetter(MemberMapping<?> mm, Class<?> sourceType,
			String propName) {
			Method method = mm.getGetter();
			if(method == null) {
				method = ReflectKit.getGetter(sourceType, propName);
				mm.setGetter(method);
			}
			return method;
	}

	/**
	 * 利用java反射，取得Bean的属性值，本方法是取嵌套类中的属性， 如album.picture.name.
	 * 
	 * @param source
	 *            源数据
	 * @param keys
	 *            源数据名称，对应上面的例子，如source即为Album，则keys[]应为picture,name
	 * 
	 * @return the object 取得的数据
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public static Object invoke(Object source, String[] keys)
			throws MappingException {
		Object result = null;
		for (String key : keys) {
			result = ReflectKit.invokeGetter(source, key);
			source = result;
		}
		return result;
	}

}
