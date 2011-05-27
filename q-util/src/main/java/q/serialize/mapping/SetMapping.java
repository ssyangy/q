package q.serialize.mapping;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

/**
 * Array、String和Collection转为Set.
 * 
 * @param <T>
 *            *
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 * @author jiangyongyuan.tw:添加ApiArrayItemSupport,以用来在memberMapping中直接给mapping赋值
 * @version 2009-08-24
 */
public class SetMapping<T> extends AbstractMapping<Set<T>> implements HasComponentMapping<Set<T>, T> {

	/** Set子项的mapping. */
	private Mapping<T> componentMapping;

	/** Set子项的名称，对应于配置文件中的apiArrayItemName. */
	private String componentName;

	/**
	 * 获取Set子项名称.
	 * 
	 * @return the component name
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * 设置Set子项名称.
	 * 
	 * @param componentName
	 *            the new component name
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * 初始化SetMapping.
	 */
	public SetMapping() {
		super();
	}

	/**
	 * 使用子项mapping初始化.
	 * 
	 * @param componentMapping
	 *            the component mapping
	 */
	public SetMapping(Mapping<T> componentMapping) {
		this.componentMapping = componentMapping;
	}

	/**
	 * 获取子项mapping.
	 * 
	 * @return the component mapping
	 */
	public Mapping<T> getComponentMapping() {
		return componentMapping;
	}

	/**
	 * 设置子项mapping.
	 * 
	 * @param componentMapping
	 *            the new component mapping
	 */
	public void setComponentMapping(Mapping<T> componentMapping) {
		this.componentMapping = componentMapping;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Set<T> mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		Set<T> rs = null;
		if (Collection.class.isAssignableFrom(source.getClass())) {
			@SuppressWarnings("unchecked")
			Collection<Object> s = (Collection<Object>) source;
			if (!s.isEmpty()) {
				rs = new HashSet<T>(s.size());
				for (Object obj : s) {
					T mobj = componentMapping.mapping(obj, exceptionExpress,
							withArrayItemName);
					rs.add(mobj);
				}
			} 
		} else if (source.getClass().isArray()) {
			int l = Array.getLength(source);
			if (l != 0) {
				rs = new HashSet<T>(l);
				for (int i = 0; i < l; i++) {
					T mitem = componentMapping.mapping(Array.get(source, i),
							null, withArrayItemName);
					rs.add(mitem);
				}
			}

		} else if (String.class.equals(source.getClass())) {
			String str = (String) source;
			String[] items = this.getStrings(str);
			if (!ArrayUtils.isEmpty(items)) {
				rs = new HashSet<T>(items.length);
				for (int i = 0; i < items.length; i++) {
					T item = componentMapping.mapping(items[i].trim(), exceptionExpress,
							withArrayItemName);
					rs.add(item);
				}
			}
		} else {
			throw new UnsupportSourceTypeException(source.getClass().getCanonicalName());
		}
		if(rs == null) {
			rs = new HashSet<T>();
		}
		return rs;
	}

	/**
	 * 将String解析为String的数组
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return the strings
	 */
	private String[] getStrings(String string) {
		return StringUtils.split(string, ',');
	}
}
