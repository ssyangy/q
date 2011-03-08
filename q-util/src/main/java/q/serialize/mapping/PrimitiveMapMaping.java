package q.serialize.mapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import q.serialize.convert.Convert;
import q.serialize.util.Utils;


/**
 * 该类暂时未被使用.
 * 
 * @param <T>
 *            * @param <M> *
 * @version 2009-08-11
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class PrimitiveMapMaping<T, M> extends AbstractMapping<Map<T, M>> {

	/** The component key type. */
	private Mapping<T> componentKeyType;

	/** The component value type. */
	private Mapping<M> componentValueType;

	/**
	 * Gets the component key type.
	 * 
	 * @return the component key type
	 */
	public Mapping<T> getComponentKeyType() {
		return componentKeyType;
	}

	/**
	 * Sets the component key type.
	 * 
	 * @param componentKeyType
	 *            the new component key type
	 */
	public void setComponentKeyType(Mapping<T> componentKeyType) {
		this.componentKeyType = componentKeyType;
	}

	/**
	 * Gets the component value type.
	 * 
	 * @return the component value type
	 */
	public Mapping<M> getComponentValueType() {
		return componentValueType;
	}

	/**
	 * Sets the component value type.
	 * 
	 * @param componentValueType
	 *            the new component value type
	 */
	public void setComponentValueType(Mapping<M> componentValueType) {
		this.componentValueType = componentValueType;
	}

	/**
	 * Instantiates a new primitive map maping.
	 */
	public PrimitiveMapMaping() {

	}

	/**
	 * Instantiates a new primitive map maping.
	 * 
	 * @param componentKey
	 *            the component key
	 * @param componentValue
	 *            the component value
	 */
	public PrimitiveMapMaping(Mapping<T> componentKey, Mapping<M> componentValue) {
		this.componentKeyType = componentKey;
		this.componentValueType = componentValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Map<T, M> mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		if (source == null) {
			return null;
		}
		Map<T, M> rs = null;
		if (Map.class.isAssignableFrom(source.getClass())) {
			Map<Object, Object> original = (Map<Object, Object>) source;
			rs = new HashMap<T, M>(original.size());
			convertMap2Exception(exceptionExpress, original);
			Iterator<?> iter = original.entrySet().iterator();
			for (; iter.hasNext();) {
				Map.Entry<Object, Object> mapEntity = (Entry<Object, Object>) iter
						.next();
				Object key = mapEntity.getKey();
				Object value = mapEntity.getValue();
				T mappingKey = this.componentKeyType.mapping(key,
						exceptionExpress, withArrayItemName);
				M mappingValue = this.componentValueType.mapping(value,
						exceptionExpress, withArrayItemName);
				if (mappingKey != null) {
					rs.put(mappingKey, mappingValue);
				}
			}
		} else if (String.class.equals(source.getClass())) {
			String str = (String) source;
			String[] items = this.getStrings(str);
			if (ArrayUtils.isEmpty(items)) {
				rs = new HashMap<T, M>(items.length);
				for (String item : items) {
					if (!StringUtils.isBlank(item)) {
						String[] coms = StringUtils.split(item, "=");
						T mappingKey = this.componentKeyType.mapping(coms[0],
								exceptionExpress, withArrayItemName);
						M mappingValue = this.componentValueType.mapping(
								coms[1], exceptionExpress, withArrayItemName);
						if (mappingKey != null) {
							rs.put(mappingKey, mappingValue);
						}
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
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException {
		if (source != null) {
			if (Utils.JSON_FORMAT.equals(convert.getFormat())
					&& isFromCollection) {
				mappingName = "";
			}
			if (Map.class.isAssignableFrom(source.getClass())) {
				Map<Object, Object> original = (Map<Object, Object>) source;
				convertMap2Exception(exceptionExpress, original);
				convert.convertPrimitiveMap(getComponentKeyType(),
						getComponentValueType(), original, exceptionExpress,
						mappingName);
			} else if (String.class.equals(source.getClass())) {
				String str = (String) source;
				convert.convertPrimitivePojo(getComponentKeyType(),
						getComponentValueType(), str, exceptionExpress,
						mappingName);
			}
		}
	}

	/**
	 * Convert map2 exception.
	 * 
	 * @param ee
	 *            the ee
	 * @param original
	 *            the original
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	private void convertMap2Exception(ExceptionExpressInfo ee,
			Map<?, ?> original) throws OperationCodeException {
		if (null != ee && ee.needException(original.get(ee.getFailKey()))) {
			Object code = original.get(ee.getFailCodeKey());
			String codeStr = code == null ? null : code.toString();
			Object msg = original.get(ee.getFailMsgKey()).toString();
			String msgStr = msg == null ? null : msg.toString();
			throw new OperationCodeException(codeStr, msgStr);
		}
	}

	private String[] getStrings(String string) {
		int count = StringUtils.countMatches(string, "{");
		int count2 = StringUtils.countMatches(string, "{,");
		int count3 = StringUtils.countMatches(string, "{=");
		count = count - count2 - count3;
		if (count > 1) {
			ArrayList<String> al = new ArrayList<String>();
			Stack<Character> stack = new Stack<Character>();
			StringBuilder sb = new StringBuilder();
			char before = 0, after = 0;
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (i < (string.length() - 1)) {
					after = string.charAt(i + 1);
				} else {
					after = 0;
				}

				if (c == '{') {
					if (after == 0 || after != ',' || after != '=') {
						stack.push(c);
					}
				} else if (c == '}') {
					if (before == 0 || before != ',') {
						stack.pop();
					}
				}

				if (stack.size() == 1) {
					switch (c) {
					case ',':
					case '\b':
					case '\t':
					case '\n':
					case '{':
						continue;
					default:
						al.add(sb.toString().substring(1));
						sb = new StringBuilder();
						break;
					}
				} else if (stack.empty()) {
					break;
				} else {
					sb.append(c);
				}
				before = c;
			}
			String[] ss = new String[al.size()];
			for (int i = 0; i < al.size(); i++) {
				ss[i] = al.get(i);
			}
			return ss;
		} else if (count == 1) {
			int i1 = string.indexOf('{');
			int i2 = string.indexOf('}');
			if (i1 < 0) {
				i1 = 0;
			} else {
				i1 = i1 + 1;
			}

			if (i2 < 0)
				i2 = string.length();
			String s = string.substring(i1, i2);
			return StringUtils.split(s, ',');
		} else {
			return StringUtils.split(string, ',');
		}
	}
}
