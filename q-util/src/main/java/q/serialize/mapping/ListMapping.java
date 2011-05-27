/**
 * 
 */
package q.serialize.mapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import q.serialize.convert.Convert;


/**
 * 对象映射成java.util.ArrayList
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class ListMapping<T> extends AbstractMapping<List<T>> implements HasComponentMapping<List<T>, T> {

	/** List子项mapping. */
	private Mapping<T> componentMapping;

	/** List子项的名称. */
	private String componentName;

	/**
	 * 构造函数.
	 */
	public ListMapping() {
	}

	/**
	 * The Constructor.
	 * 
	 * @param itemMapping
	 *            the item mapping
	 */
	public ListMapping(Mapping<T> itemMapping) {
		this.componentMapping = itemMapping;
	}

	/**
	 * Gets the component name.
	 * 
	 * @return componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractObjectMapping#getMemberMappings()
	 */
	@Override
	public Map<String, MemberMapping<?>> getMemberMappings() {
		Map<String, MemberMapping<?>> memberMappings = null;
		if (this.componentMapping != null) {
			memberMappings = this.componentMapping.getMemberMappings();
		}
		return memberMappings;
	}

	/**
	 * Sets the component name.
	 * 
	 * @param componentName
	 *            the new component name
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	/**
	 * Sets the component mapping.
	 * 
	 * @param componentMapping
	 *            the new component mapping
	 */
	public void setComponentMapping(Mapping<T> componentMapping) {
		this.componentMapping = componentMapping;
	}

	/**
	 * Gets the component mapping.
	 * 
	 * @return the itemMapping
	 */
	public Mapping<T> getComponentMapping() {
		return componentMapping;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected List<T> mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		List<T> rs = null;
		if (Collection.class.isInstance(source)) {
			@SuppressWarnings("unchecked")
			Collection<Object> s = (Collection<Object>) source;
			if (!s.isEmpty()) {
				rs = new ArrayList<T>(s.size());
				for (Object obj : s) {
					T mobj = componentMapping.mapping(obj, exceptionExpress, withArrayItemName);
					rs.add(mobj);
				}
			}
		} else if (source.getClass().isArray()) {
			int l = Array.getLength(source);
			if (l != 0) {
				rs = new ArrayList<T>(l);
				for (int i = 0; i < l; i++) {
					T mitem = componentMapping.mapping(Array.get(source, i),
							exceptionExpress, withArrayItemName);
					rs.add(mitem);
				}
			}

		} else if (String.class == source.getClass()) {
			String str = (String) source;
			String[] items = this.getStrings(str);
			if (!ArrayUtils.isEmpty(items)) {
				rs = new ArrayList<T>(items.length);
				for (int i = 0; i < items.length; i++) {
					T item = componentMapping.mapping(items[i].trim(), exceptionExpress,
							withArrayItemName);
					rs.add(item);
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
