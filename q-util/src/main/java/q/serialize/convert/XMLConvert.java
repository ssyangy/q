package q.serialize.convert;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import q.serialize.mapping.ExceptionExpressInfo;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.OutputHelper;
import q.serialize.mapping.Switcher;
import q.serialize.util.Utils;
import q.util.ReflectKit;
import q.util.StringKit;


/**
 * 
 * @author cilai
 * @since 1.0, 2010-3-25 下午03:02:24
 */

public class XMLConvert extends AbstractConvert {

	public XMLConvert(Writer writer) {
		super(writer);
	}

	public void convertBefore(MemberMapping<?> memberMapping) throws MappingException {
		return;
	}

	public void convertDown(MemberMapping<?> memberMapping) throws MappingException {
		return;
	}

	public void convertSwitcher(Object source, String mappingName, Switcher switcher) throws MappingException {
		try {
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingName.trim()).append(Utils.RIGHT_ANGLE_BRACKET);
			writer.append(switcher.switchCase(source).toString());
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingName.trim()).append(Utils.RIGHT_ANGLE_BRACKET);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	private void convertPrimitiveObject(Object source, String mappingName) throws MappingException, IOException {
		if (mappingName == null) {
			writer.append(source.toString());
			return;
		}
		writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
		writer.append(source.toString());
		writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
	}

	public void convertBoolean(Boolean source, String mappingName) throws MappingException, IOException {
		this.convertPrimitiveObject(source, mappingName);
	}

	public void convertByte(Byte source, String mappingName) throws MappingException, IOException {
		this.convertPrimitiveObject(source, mappingName);
	}

	public void convertDouble(Double source, String mappingName) throws MappingException, IOException {
		this.convertPrimitiveObject(source, mappingName);
	}

	public void convertFloat(Float source, String mappingName) throws MappingException, IOException {
		this.convertPrimitiveObject(source, mappingName);
	}

	public void convertInteger(Integer source, String mappingName) throws MappingException, IOException {
		this.convertPrimitiveObject(source, mappingName);
	}

	public void convertLong(Long source, String mappingName) throws MappingException, IOException {
		this.convertPrimitiveObject(source, mappingName);
	}

	public void convertString(String source, String mappingName) throws MappingException, IOException {
		if (mappingName == null) {
			StringKit.writeEscapeXml(writer, source);
		} else {
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
			StringKit.writeEscapeXml(writer, source);
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
		}
	}

	public void convertCharacter(Character source, String mappingName) throws MappingException {
		try {
			if (mappingName == null) {
				writer.append(source.toString());
				return;
			}
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
			writer.append(source.toString());
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertMap(Map<String, MemberMapping<?>> memberMappings, Map<?, ?> original, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException {

		try {
			int count = 0;
			if (original.size() >= 1) {
				convertStartTag(mappingName, false);
			}
			for (MemberMapping<?> mm : memberMappings.values()) {
				// XXX only suport Map<String,Object>, if key is not a String,
				// use key.toString()
				String name = mm.getName().toString();
				Object value = original.get(name);
				if (value != null) { // value不为空的时候输出
					value = OutputHelper.changeValue(value);
					mm.write(this, value, false);
					count++;
				}
			}
			if (original.size() >= 1) {
				convertEndTag(mappingName, false);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertPojo(Map<String, MemberMapping<?>> memberMappings, Object source, ExceptionExpressInfo exceptionExpress, String mappingName, String mappingPreFix, boolean isFromCollection) throws MappingException {
		try {
			// 如果prefix存在,不写出对象如location:{state:"aaa"},而写出location.state:"aaa"
			boolean outPutPrefix = true;
			if (mappingPreFix == null)
				outPutPrefix = false;

			if (!outPutPrefix)
				convertStartTag(mappingName, false);

			if (memberMappings != null && memberMappings.size() != 0) {
				int count = 0;
				for (MemberMapping<?> mm : memberMappings.values()) {
					String key = mm.getName();

					// keys:这里是是的location.city输出。不过这里只支持一种输出,需要改进
					String[] keys = mm.getNames();
					Object value = null;
					if (keys != null) {
						value = ReflectKit.invoke(source, keys);
					} else if (key != null) {
						Method m = ReflectKit.getCachedGetter(mm, source.getClass(), key);
						value = ReflectKit.invokeGetter(source, m);
					}
					if (value != null) {
						// 如果Prefix存在,mappingName需要写出prefix

						// 由于其他Mapping如StringMapping,IntegerMapping不存在prefix,
						// 故临时解决方案为修改MappingName,在write完成后，设置回原来的MappingName
						String realMappingName = mm.getMappingName();
						if (outPutPrefix) {
							mm.setMappingName(mappingPreFix + "." + realMappingName);
						}

						value = OutputHelper.changeValue(value);

						mm.write(this, value, false);
						// 由于其他Mapping如StringMapping,IntegerMapping不存在prefix,
						// 故临时解决方案为修改MappingName,在write完成后，设置回原来的MappingName
						if (outPutPrefix) {
							mm.setMappingName(realMappingName);
						}
						count++;
					}
				}
			}
			// 如果prefix存在,不写出对象如location:{state:"aaa"},而写出location.state:"aaa"
			if (!outPutPrefix)
				convertEndTag(mappingName, false);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertStartTag(String mappingName, boolean isArray) throws MappingException {
		if (mappingName == null)
			return;
		try {
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertEndTag(String mappingName, boolean isArray) throws MappingException {
		if (mappingName == null)
			return;
		try {
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public String convertCollectionBefore(String mappingName, boolean isFromCollection, boolean isArray) throws MappingException {
		return mappingName;
	}

	public void convertCollectionDown(String mappingName, boolean isFromCollection) throws MappingException {
		// TODO Auto-generated method stub
		return;
	}

	public void convertStringToCollection(Mapping<?> componentMapping, String componentName, String str, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException {
		try {
			int count = 0;
			String[] items = getStringsByComma(str);
			if (!ArrayUtils.isEmpty(items)) {
				convertCollectionStart(mappingName);
				for (int i = 0; i < items.length; i++) {
					if (null != items[i]) {
						componentMapping.write(this, items[i].trim(), exceptionExpress, componentName, true);
						count++;
					}
					// 此处被注释掉了，原是担心writer的buffer会溢出
					// （曾经做过测试，用top-mapping-performance中的那个List，
					// 当list的size为100000的时候，java的heap就会溢出），
					// 因此再每读入一定数量的数据后，就flush，
					// 注释掉是因为发现这种flush其实也是要消耗一定时间的。
					// if (count > 10) {
					// try {
					// writer.flush();
					// count = 0;
					// } catch (IOException e) {
					// throw new MappingException(
					// "Error in ArrayMapping.WriteStringToArray", e);
					// }
					// }
				}
				convertCollectionEnd(mappingName);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertCollectionToCollection(Mapping<?> componentMapping, String componentName, Collection<Object> s, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException {
		try {
			if (!s.isEmpty()) {
				int count = 0;
				int i = 0;
				convertCollectionStart(mappingName);
				for (Object obj : s) {
					if (obj != null) {
						componentMapping.write(this, obj, exceptionExpress, componentName, true);
						count++;
						i++;
					}
					// 此处被注释掉了，原是担心writer的buffer会溢出
					// （曾经做过测试，用top-mapping-performance中的那个List，
					// 当list的size为100000的时候，java的heap就会溢出），
					// 因此再每读入一定数量的数据后，就flush，
					// 注释掉是因为发现这种flush其实也是要消耗一定时间的。
					// if (count > 10) {
					// try {
					// writer.flush();
					// count = 0;
					// } catch (IOException e) {
					// throw new MappingException(
					// "Error in ArrayMapping.WriteCollectionToArray",
					// e);
					// }
					// }
				}
				convertCollectionEnd(mappingName);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertArrayToCollection(Mapping<?> componentMapping, String componentName, Object source, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException {
		try {
			int l = Array.getLength(source);
			int count = 0;
			if (l != 0) {
				convertCollectionStart(mappingName);
				for (int i = 0; i < l; i++) {
					if (Array.get(source, i) != null) {
						componentMapping.write(this, Array.get(source, i), exceptionExpress, componentName, true);
						count++;
					}
				}
				convertCollectionEnd(mappingName);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertCollectionEnd(String mappingName) throws MappingException {
		try {
			if (mappingName == null)
				return;
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(Utils.BACK_SLASH).append(mappingName).append(Utils.RIGHT_ANGLE_BRACKET);
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public void convertCollectionStart(String mappingName) throws MappingException {
		try {
			if (mappingName == null)
				return;
			writer.append(Utils.LEFT_ANGLE_BRACKET).append(mappingName.trim()).append(Utils.SPACE).append(Utils.LIST).append(Utils.RIGHT_ANGLE_BRACKET);
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public void flush() throws MappingException {
		try {
			writer.flush();
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public String processMappingName(String mappingName, boolean isFromCollection) {
		return mappingName;
	}

	public String getFormat() {
		return Utils.XML_FORMAT;
	}

}
