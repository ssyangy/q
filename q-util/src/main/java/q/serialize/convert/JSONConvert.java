package q.serialize.convert;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;

import q.serialize.mapping.ArrayMapping;
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
 * 将对象转换成JSON格式
 * 
 * @author cilai
 * @since 1.0, 2010-3-24 下午05:44:24
 */

public class JSONConvert extends AbstractConvert {

	public JSONConvert(Writer writer) {
		super(writer);
	}

	public void convertBefore(MemberMapping<?> memberMapping)
			throws MappingException {
		try {
			if (null != memberMapping.getMappingName()
					|| memberMapping.getMapping() instanceof ArrayMapping) {
				writer.append(Utils.LB);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public void convertDown(MemberMapping<?> memberMapping)
			throws MappingException {
		try {
			if (null != memberMapping.getMappingName()
					|| memberMapping.getMapping() instanceof ArrayMapping) {
				writer.append(Utils.RB);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public void convertSwitcher(Object source, String mappingName,
			Switcher switcher) throws MappingException {
		try {
			writer.append(Utils.DOUBLE_QUOTE).append(mappingName.trim())
					.append(Utils.DOUBLE_QUOTE).append(Utils.COLON).append(
							Utils.DOUBLE_QUOTE);
			writer.append(switcher.switchCase(source).toString());
			writer.append(Utils.DOUBLE_QUOTE);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertBoolean(Boolean source, String mappingName)
			throws MappingException {
		try {
			this.appendMappingName(writer, source, mappingName);
			writer.append(source.toString());
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}
	
	private void appendMappingName(Writer writer, Object source, String mappingName) throws IOException {
		if (mappingName != null) {
			writer.append(Utils.DOUBLE_QUOTE).append(mappingName).append(
					Utils.DOUBLE_QUOTE).append(Utils.COLON);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.taobao.top.traffic.convert.Convert#convertByte(java.lang.Byte, java.lang.String)
	 */
	public void convertByte(Byte source, String mappingName)
			throws MappingException, IOException {
			this.appendMappingName(writer, source, mappingName);
			writer.append(source.toString());
	}

	public void convertInteger(Integer source, String mappingName)
			throws MappingException, IOException {
			this.appendMappingName(writer, source, mappingName);
			writer.append(source.toString());
	}
	
	public void convertDouble(Double source, String mappingName)
			throws MappingException, IOException {
			this.appendMappingName(writer, source, mappingName);
			writer.append(source.toString());
	}

	public void convertFloat(Float source, String mappingName)
			throws MappingException, IOException {
			this.appendMappingName(writer, source, mappingName);
			writer.append(source.toString());
	}

	public void convertLong(Long source, String mappingName)
			throws IOException {
			this.appendMappingName(writer, source, mappingName);
			writer.append(source.toString());
	}

	public void convertString(String source, String mappingName)
			throws IOException {
		this.appendMappingName(writer, source, mappingName);
		writer.append(Utils.DOUBLE_QUOTE);
		StringKit.writeEscapeJson(writer, source);
		writer.append(Utils.DOUBLE_QUOTE);
	}

	public void convertCharacter(Character source, String mappingName)
			throws MappingException {
		try {
			if (mappingName != null)
				writer.append(Utils.DOUBLE_QUOTE).append(mappingName).append(
						Utils.DOUBLE_QUOTE).append(Utils.COLON);
			writer.append(Utils.DOUBLE_QUOTE).append(source.toString()).append(
					Utils.DOUBLE_QUOTE);
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertMap(Map<String, MemberMapping<?>> memberMappings,
			Map<?, ?> original, ExceptionExpressInfo exceptionExpress,
			String mappingName, boolean isFromCollection)
			throws MappingException {
		try {
			int count = 0;
			if (original.size() >= 1) {
				convertStartTag(mappingName);
			}
			for (MemberMapping<?> mm : memberMappings.values()) {
				// XXX only suport Map<String,Object>, if key is not a String,
				// use key.toString()
				String name = mm.getName().toString();
				Object value = original.get(name);
				if (value != null) { // value不为空的时候输出
					if (count > 0) {
						writer.append(Utils.COMMA);
					}
					value = OutputHelper.changeValue(value);
					mm.write(this, value, false);
					count++;
				}
			}
			if (original.size() >= 1) {
				convertEndTag(mappingName);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public void convertPojo(Map<String, MemberMapping<?>> memberMappings,
			Object source, ExceptionExpressInfo exceptionExpress,
			String mappingName, 
			String mappingPreFix, boolean isFromCollection)
			throws MappingException {
		try {
			// 如果prefix存在,不写出对象如location:{state:"aaa"},而写出location.state:"aaa"
			boolean outPutPrefix = true;
			if (mappingPreFix == null)
				outPutPrefix = false;
			if (!outPutPrefix)
				convertStartTag(mappingName);

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
						Method m = ReflectKit.getCachedGetter(mm, source
								.getClass(), key);
						value = ReflectKit.invokeGetter(source, m);
					}
					if (value != null) {
						if (count > 0) {// json格式输出逗号
							writer.append(Utils.COMMA);
						}
						// 如果Prefix存在,mappingName需要写出prefix

						// 由于其他Mapping如StringMapping,IntegerMapping不存在prefix,
						// 故临时解决方案为修改MappingName,在write完成后，设置回原来的MappingName
						String realMappingName = mm.getMappingName();
						if (outPutPrefix) {
							mm.setMappingName(mappingPreFix + "."
									+ realMappingName);
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
				convertEndTag(mappingName);

		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertStartTag(String mappingName) throws MappingException {
		try {
			if (null != mappingName) {
				writer.append(Utils.DOUBLE_QUOTE).append(mappingName).append(
						Utils.DOUBLE_QUOTE).append(Utils.COLON)
						.append(Utils.LB);
			} else {
				writer.append(Utils.LB);
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertEndTag(String mappingName) throws MappingException {
		try {
			writer.append(Utils.RB);
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public String convertCollectionBefore(String mappingName,
			boolean isFromCollection) throws MappingException {
		if (isFromCollection) {
			// 如果是JSON格式，并且该Array是一个数组的子项，则mappingName不写出
			mappingName = null;
		} else {
			convertStartTag(mappingName);
		}
		return mappingName;

	}

	public void convertCollectionDown(String mappingName,
			boolean isFromCollection) throws MappingException {

		if (!isFromCollection)
			convertEndTag(mappingName);
	}

	public void convertStringToCollection(Mapping<?> componentMapping,
			String componentName, String str,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException {
		try {
			int count = 0;
			String[] items = this.getStringsByComma(str);
			if (!ArrayUtils.isEmpty(items)) {
				if (!isFromCollection) {
					// 将第一层数组的子项名提出来，写为mappingName:{componentName1:[这种形式。
					this.convertCollectionStart(componentName);
				} else {
					this.convertCollectionStart(mappingName);
				}
				for (int i = 0; i < items.length; i++) {
					if (null != items[i]) {
						if (i > 0) {// json格式输出逗号
							writer.append(Utils.COMMA);
						}
						componentMapping.write(this, items[i].trim(),
								exceptionExpress, componentName, true);
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
				if (!isFromCollection) {
					this.convertCollectionEnd(componentName);
				} else {
					this.convertCollectionEnd(mappingName);
				}
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertCollectionToCollection(Mapping<?> componentMapping,
			String componentName, Collection<Object> s,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException {
		try {
			if (!s.isEmpty()) {
				int count = 0;
				int i = 0;
				if (!isFromCollection) {
					// 将第一层数组的子项名提出来，写为mappingName:{componentName1:[这种形式。
					this.convertCollectionStart(componentName);
				} else {
					this.convertCollectionStart(mappingName);
				}
				for (Object obj : s) {
					if (obj != null) {
						if (i > 0) {// json格式输出逗号
							writer.append(Utils.COMMA);
						}
						componentMapping.write(this, obj, exceptionExpress,
								componentName, true);
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
				if (!isFromCollection) {
					this.convertCollectionEnd(componentName);
				} else {
					this.convertCollectionEnd(mappingName);
				}
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertArrayToCollection(Mapping<?> componentMapping,
			String componentName, Object source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException {
		try {

			int l = Array.getLength(source);
			int count = 0;
			if (l != 0) {
				if (!isFromCollection) {
					// 将第一层数组的子项名提出来，写为mappingName:{componentName1:[这种形式。
					this.convertCollectionStart(componentName);
				} else {
					this.convertCollectionStart(mappingName);
				}
				for (int i = 0; i < l; i++) {
					if (Array.get(source, i) != null) {
						if (i > 0) {// json格式输出逗号
							writer.append(Utils.COMMA);
						}
						componentMapping.write(this, Array.get(source, i),
								exceptionExpress, componentName, true);
						count++;
					}
				}
				if (!isFromCollection) {
					this.convertCollectionEnd(componentName);
				} else {
					this.convertCollectionEnd(mappingName);
				}
			}
		} catch (Exception e) {
			throw new MappingException(e);
		}
	}

	public void convertCollectionEnd(String mappingName)
			throws MappingException {
		try {
			writer.append(Utils.RSB);
		} catch (Exception e) {
			throw new MappingException(e);
		}

	}

	public void convertCollectionStart(String mappingName)
			throws MappingException {
		try {
			if (null != mappingName) {
				writer.append(Utils.DOUBLE_QUOTE).append(mappingName).append(
						Utils.DOUBLE_QUOTE).append(Utils.COLON).append(
						Utils.LSB);
			} else {
				writer.append(Utils.LSB);
			}
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

	public String processMappingName(String mappingName,
			boolean isFromCollection) {
		if (isFromCollection)
			mappingName = null;
		return mappingName;
	}

	public String getFormat() {
		return Utils.JSON_FORMAT;
	}

	

}
