/**
 * 
 */
package q.serialize.mapping;

import java.lang.reflect.Array;
import java.util.Collection;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import q.serialize.convert.Convert;


/**
 * 集合映射成数组.
 * 
 * @param <T>
 *            数组类型 T == M[]
 * @param <M>
 *            数组元素类型
 * 
 * @version 2009-8-24
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class ArrayMapping<T, M> extends AbstractMapping<T> implements HasComponentMapping<T, M> {

	/** The component mapping. */
	private Mapping<M> componentMapping;

	/** The mapping type. */
	private Class<T> mappingType;

	/**
	 * The Constructor.
	 * 
	 * @param componentMapping
	 *            the component mapping
	 */
	public ArrayMapping() {
	}
	
	public ArrayMapping(Mapping<M> componentMapping) {
		this.setComponentMapping(componentMapping);
	}
	
	public void setComponentMapping(Mapping<M> componentMapping) {
		this.componentMapping = componentMapping;
		Class<?> compType = this.componentMapping.getMappingType();
		this.mappingType = (Class<T>) Array.newInstance(compType, 0).getClass();
	}



	/**
	 * Gets the component mapping.
	 * 
	 * @return the componentMapping
	 */
	public Mapping<M> getComponentMapping() {
		return componentMapping;
	}
	
	/** The component name. */
	private String componentName;

	/**
	 * Gets the component name.
	 * 
	 * @return the component name
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * Sets the component name.
	 * 以下中,arrays为mappingName,array为componentName
	 * arrays:{array:[]}
	 * 
	 * @param componentName
	 *            the new component name
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<T> getMappingType() {
		return this.mappingType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected T mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		T rs = null;
		if (String.class == source.getClass()) {
			String str = (String) source;
			String[] items = StringUtils.split(str, ',');
			//String[] items = this.getStrings(str);

			if (!ArrayUtils.isEmpty(items)) {
				rs = (T) Array.newInstance(componentMapping.getMappingType(),
						items.length);
				for (int i = 0; i < items.length; i++) {
					M item = componentMapping.mapping(items[i].trim(), exceptionExpress,
							withArrayItemName);
					Array.set(rs, i, item);
				}
			}
		} else if (Collection.class.isInstance(source)) {
			Collection<Object> s = (Collection<Object>) source;
			if (!s.isEmpty()) {
				rs = (T) Array.newInstance(componentMapping.getMappingType(), s
						.size());
				int i = 0;
				for (Object obj : s) {
					M item = componentMapping.mapping(obj, exceptionExpress, withArrayItemName);
					Array.set(rs, i++, item);
				}
			}
		} else if (source.getClass().isArray()) {
			int l = Array.getLength(source);
			if (l != 0) {
				// 数组元素类型相同，直接返回
				if (source.getClass().getComponentType() == componentMapping
						.getMappingType()) {
					rs = (T) source;
					// 数组元素类型不同，逐个转换
				} else {
					rs = (T) Array.newInstance(componentMapping
							.getMappingType(), l);
					for (int i = 0; i < l; i++) {
						M item = componentMapping.mapping(Array.get(source, i),
								exceptionExpress, withArrayItemName);
						Array.set(rs, i, item);
					}
				}
			}

		}
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Mapping#write(java.io.Writer,
	 * java.lang.Object, com.taobao.top.traffic.mapping.ExceptionExpress,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void write(Convert convert, Object source,
			ExceptionExpressInfo exceptionExpress, String mappingName,boolean isFromCollection) throws MappingException {
		if (source != null) {
				convert.convertCollection(componentMapping, componentName, source, exceptionExpress, mappingName, isFromCollection);
		}
	}



}
