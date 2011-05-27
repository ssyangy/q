package q.serialize.convert;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import q.serialize.mapping.ExceptionExpressInfo;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MappingException;
import q.serialize.util.Utils;
import q.util.DateKit;

/**
 * NTS
 * 
 * @author cilai
 * @author zixue
 * @since 1.0, 2010-3-26 下午01:37:39
 */

public abstract class AbstractConvert implements Convert {
	protected Writer writer;

	public AbstractConvert(Writer writer) {
		this.writer = writer;
	}

	public Writer getWriter() {
		return writer;
	}

	public void setWriter(Writer writer) {
		this.writer = writer;
	}

	public void convertPrimitiveMap(Mapping<?> componentKeyType, Mapping<?> componentValueType, Map<?, ?> original, ExceptionExpressInfo exceptionExpress, String mappingName) throws MappingException {
		try {
			convertStartTag(mappingName, true);
			Iterator<?> iter = original.entrySet().iterator();
			for (; iter.hasNext();) {
				Map.Entry<Object, Object> mapEntity = (Entry<Object, Object>) iter.next();
				Object key = mapEntity.getKey();
				Object value = mapEntity.getValue();

				Object mappingKey = componentKeyType.mapping(key, exceptionExpress, true);
				Object mappingValue = componentValueType.mapping(value, exceptionExpress, true);
				if (mappingKey != null) {
					if (mappingValue != null) {
						try {
							writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingKey.toString()).append(Utils.RIGHT_ANGLE_BRACKET);
							writer.append(mappingValue.toString());
							writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingKey.toString()).append(Utils.RIGHT_ANGLE_BRACKET);
						} catch (IOException e) {
							throw new MappingException("Error in PrimitiveMapMapping.writeMapToMap", e);
						}
					}
				}
			}
			convertEndTag(mappingName, true);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertPrimitivePojo(Mapping<?> componentKeyType, Mapping<?> componentValueType, String str, ExceptionExpressInfo exceptionExpress, String mappingName) throws MappingException {
		try {
			String[] items = this.getStrings(str);
			if (ArrayUtils.isEmpty(items)) {
				for (String item : items) {
					if (!StringUtils.isBlank(item)) {
						String[] coms = StringUtils.split(item, "=");
						Object mappingKey = componentKeyType.mapping(coms[0], exceptionExpress, true);
						Object mappingValue = componentValueType.mapping(coms[1], exceptionExpress, true);
						if (mappingKey != null) {
							if (mappingValue != null) {
								try {
									writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingKey.toString()).append(Utils.RIGHT_ANGLE_BRACKET);
									writer.append(mappingValue.toString());
									writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingKey.toString()).append(Utils.RIGHT_ANGLE_BRACKET);
								} catch (IOException e) {
									throw new MappingException("Error in PrimitiveMapMapping.writeStringToMap", e);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertDate(Date source, String mappingName) throws MappingException {
		try {
			String date2ymdhms = DateKit.date2Ymdhms(source);
			this.convertString(date2ymdhms, mappingName);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertCollection(Mapping<?> componentMapping, String componentName, Object source, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException {
		try {
			mappingName = convertCollectionBefore(mappingName, isFromCollection, componentName != null);
			if (String.class == source.getClass()) {
				String str = (String) source;
				convertStringToCollection(componentMapping, componentName, str, exceptionExpress, mappingName, isFromCollection);

			} else if (Collection.class.isInstance(source)) {
				Collection<Object> s = (Collection<Object>) source;
				convertCollectionToCollection(componentMapping, componentName, s, exceptionExpress, mappingName, isFromCollection);
			} else if (source.getClass().isArray()) {
				convertArrayToCollection(componentMapping, componentName, source, exceptionExpress, mappingName, isFromCollection);
			}
			this.convertCollectionDown(mappingName, isFromCollection, componentName != null);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public abstract String convertCollectionBefore(String mappingName, boolean isFromCollection, boolean needLB) throws MappingException;

	public abstract void convertCollectionDown(String mappingName, boolean isFromCollection, boolean needLB) throws MappingException;

	/**
	 * 将符合格式的字符串写为数组.
	 * 
	 * @param writer
	 *            不能为空
	 * @param str
	 *            字符串格式同ArrayUtils.toString方法的格式
	 * @param exceptionExpress
	 *            the exception express
	 * @param format
	 *            格式(xml, json)
	 * @param mappingName
	 *            标签名
	 * @param isFromArray
	 *            the is from array
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public abstract void convertStringToCollection(Mapping<?> componentMapping, String componentName, String str, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException;

	/**
	 * 将Collection转为数组写出.
	 * 
	 * @param writer
	 *            不能为空
	 * @param s
	 *            Collection
	 * @param exceptionExpress
	 *            the exception express
	 * @param format
	 *            格式(xml, json)
	 * @param mappingName
	 *            标签名
	 * @param isFromArray
	 *            the is from array
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public abstract void convertCollectionToCollection(Mapping<?> componentMapping, String componentName, Collection<Object> s, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException;

	/**
	 * 将数组写出.
	 * 
	 * @param writer
	 *            不能为空
	 * @param source
	 *            数组Object
	 * @param exceptionExpress
	 *            the exception express
	 * @param format
	 *            格式(xml, json)
	 * @param mappingName
	 *            标签名
	 * @param isFromArray
	 *            the is from array
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public abstract void convertArrayToCollection(Mapping<?> componentMapping, String componentName, Object source, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException;

	/**
	 * 写数组开始标签.
	 * 
	 * @param writer
	 *            不能为空
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public abstract void convertCollectionStart(String mappingName) throws MappingException;

	/**
	 * 写数组结束标签.
	 * 
	 * @param writer
	 *            不能为空
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public abstract void convertCollectionEnd(String mappingName) throws MappingException;

	/**
	 * 将符合格式的字符串解析为字符串数组，如{abc=1,d=2}.
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return the strings
	 */
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

	/**
	 * 将String解析为String的数组，其中的每一项为set中的一项 String格式如：1,2,3.
	 * 
	 * @param string
	 *            the string
	 * 
	 * @return the strings
	 */
	protected String[] getStringsByComma(String string) {
		if (string.matches("\\{.*\\}")) {
			ArrayList<String> al = new ArrayList<String>();
			Stack<Character> stack = new Stack<Character>();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < string.length(); i++) {
				char c = string.charAt(i);
				if (c == '{') {
					stack.push(c);
				} else if (c == '}') {
					stack.pop();
				}

				if (stack.empty()) {
					switch (c) {
					case ',':
					case '\b':
					case '\t':
					case '\n':
						continue;
					default:
						al.add(sb.toString().substring(1));
						sb = new StringBuilder();
						break;
					}
				} else {
					sb.append(c);
				}
			}
			String[] ss = new String[al.size()];
			for (int i = 0; i < al.size(); i++) {
				ss[i] = al.get(i);
			}
			return ss;
		} else {
			return string.split("[,]");
		}
	}
}
