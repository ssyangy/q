package q.serialize.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import q.serialize.mapping.ArrayMapping;
import q.serialize.mapping.DefaultSwitcher;
import q.serialize.mapping.ExceptionExpressInfo;
import q.serialize.mapping.HasMembersMapping;
import q.serialize.mapping.ListMapping;
import q.serialize.mapping.MapMapping;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.MethodCallMapping;
import q.serialize.mapping.MethodMapping;
import q.serialize.mapping.MethodResponseMapping;
import q.serialize.mapping.ObjectMapping;
import q.serialize.mapping.OperationCodeException;
import q.serialize.mapping.PureMapMapping;
import q.serialize.mapping.SetMapping;
import q.util.StringKit;

/**
 * 默认的映射配置文件读取器.
 * 
 * @version 2009-2-16
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class MappingConfigReader extends MappingReader {
	private MappingFactory mappingFactory = new DefaultMappingFactory();

	/**
	 * Instantiates a new mapping config reader.
	 */
	public MappingConfigReader() {
	}

	/**
	 * Read.
	 * 
	 * @param is
	 *            the inputStream
	 * 
	 * @return the method mapping< object>
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	public MethodMapping<Object> read(InputStream is) throws IOException, MappingFormatException {
		return read(is, DEFAULT_CONFIG_ENCODE);
	}

	/**
	 * Read.
	 * 
	 * @param is
	 *            the inputStream
	 * @param encode
	 *            the encode
	 * 
	 * @return the method mapping< object>
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	public MethodMapping<Object> read(InputStream is, String encode) throws IOException, MappingFormatException {
		MethodMapping<Object> methodMapping = new MethodMapping<Object>();
		InputStreamReader ir = new InputStreamReader(is, encode);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(ir);
			methodMapping.setMappingConfig(document.asXML());
			Element root = document.getRootElement();
			methodMapping.setInterfaceName(StringKit.trim(root.attributeValue("interfaceName")));
			methodMapping.setInterfaceVersion(StringKit.trim(root.attributeValue("interfaceVersion")));
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				if (element.getName().equals("methodCall")) {
					try {
						parseMethodCall(methodMapping, element);
					} catch (Exception e) {
						throw new IllegalStateException(e);
					}
				} else if (element.getName().equals("methodResponse")) {
					try {
						parseMethodResponse(methodMapping, element);
					} catch (Exception e) {
						throw new IllegalStateException(e);
					}
				}
			}
		} catch (DocumentException e) {
			throw new MappingFormatException(e);
		}
		return methodMapping;
	}

	/**
	 * Read member mapping.
	 * 
	 * @param is
	 *            the inputStream
	 * 
	 * @return the member mapping< object>
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	public MemberMapping<Object> readMemberMapping(InputStream is) throws IOException, MappingFormatException {
		try {
			return readMemberMapping(is, DEFAULT_CONFIG_ENCODE);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Read member mapping.
	 * 
	 * @param is
	 *            the inputStream
	 * @param encode
	 *            the encode
	 * 
	 * @return the member mapping< object>
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public MemberMapping<Object> readMemberMapping(InputStream is, String encode) throws IOException, MappingFormatException, InstantiationException, IllegalAccessException {
		MemberMapping<Object> memberMapping = null;
		InputStreamReader ir = new InputStreamReader(is, encode);
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(ir);
			Element root = document.getRootElement();
			if (root.getName().equals("param")) {
				MethodResponseMapping mrm = new MethodResponseMapping();
				parseMember(true, null, mrm, null, root);
				memberMapping = mrm.getParameterMapping();
			}
		} catch (DocumentException e) {
			throw new MappingFormatException(e);
		}
		return memberMapping;
	}

	/**
	 * Parses the method call.
	 * 
	 * @param mm
	 *            the methodMapping
	 * @param ele
	 *            the element
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void parseMethodCall(MethodMapping<Object> mm, Element ele) throws MappingFormatException, InstantiationException, IllegalAccessException {
		MethodCallMapping mcm = new MethodCallMapping();
		mcm.setName(ele.attributeValue("name"));
		mcm.setMappingName(StringKit.trim(ele.attributeValue("apiName")));
		for (Iterator<?> i = ele.elementIterator(); i.hasNext();) {
			Element item = (Element) i.next();
			if (item.getName().equals("params")) {
				parseMethodParams(mcm, item);
			}
		}
		mm.setMethodCallMapping(mcm);
	}

	/**
	 * Parses the method params.
	 * 
	 * @param mcm
	 *            the methodCallMapping
	 * @param ele
	 *            the element
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void parseMethodParams(MethodCallMapping mcm, Element ele) throws MappingFormatException, InstantiationException, IllegalAccessException {
		for (Iterator<?> i = ele.elementIterator(); i.hasNext();) {
			Element item = (Element) i.next();
			if (item.getName().equals("param")) {
				// XXX mapping reverse
				parseMember(false, mcm, null, null, item);
			}
		}

	}

	/**
	 * Parses the method response.
	 * 
	 * @param mm
	 *            the methodMapping
	 * @param ele
	 *            the element
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void parseMethodResponse(MethodMapping<Object> mm, Element ele) throws MappingFormatException, InstantiationException, IllegalAccessException {
		MethodResponseMapping mrm = new MethodResponseMapping();
		Element member = ele.element("param");
		if (member != null) {
			parseMember(true, null, mrm, null, member);
		}
		mm.setMethodResponseMapping(mrm);
	}

	/**
	 * 处理成员节点，可能父节点有3种来源.
	 * 
	 * @param mcm
	 *            the methodCallMapping
	 * @param mrm
	 *            the methodResponseMapping
	 * @param mapMapping
	 *            the map mapping
	 * @param ele
	 *            the element
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private void parseMember(boolean mappingDirection, MethodCallMapping mcm, MethodResponseMapping mrm, HasMembersMapping mapMapping, Element ele) throws MappingFormatException, InstantiationException, IllegalAccessException {
		String apiType = ele.attributeValue("apiType");
		String apiName = ele.attributeValue("apiName");
		apiName = StringKit.stripStart(apiName, "$");

		String name = ele.attributeValue("name");
		String type = ele.attributeValue("type");
		String apiArrayItemName = ele.attributeValue("apiArrayItemName");
		Attribute apiIgnoreAttr = ele.attribute("apiIgnore");
		boolean apiIgnore = apiIgnoreAttr == null ? false : true;
		String exceptionExpress = ele.attributeValue("exceptionExpress");
		Element typeEle = ele.element("type");
		typeEle = typeEle == null ? null : (Element) typeEle.elements().get(0);
		if (log.isDebugEnabled()) {
			log.debug(String.format("member type:%s name:%s apiType:%s apiName:%s apiArrayItemName:%s", type, name, apiType, apiName, apiArrayItemName));
		}
		Element switchEle = ele.element("switch");

		// XXX mapping reverse
		if (!mappingDirection) {
			String tmp = name;
			name = apiName;
			apiName = tmp;
			tmp = type;
			type = apiType;
			apiType = tmp;
		}
		MemberMapping<?> memberMapping = getMemberMapping(mappingDirection, name, apiName, type, typeEle, switchEle, apiType, apiArrayItemName, exceptionExpress, apiIgnore);

		if (memberMapping != null) {
			if (mapMapping != null) {
				mapMapping.addMemberMapping(memberMapping);
			} else if (mcm != null) {
				mcm.addParameterMapping(memberMapping);
			} else if (mrm != null) {
				mrm.setParameterMapping(memberMapping);
			}
			/** 修改以支持1.0协议2.0协议节点名字不一样 */
			String outputApiName = ele.attributeValue("outputApiName");
			memberMapping.setOutputApiName(outputApiName);
		}
	}

	/**
	 * 从array、struct或者member节点中读取一个MemberMapping.
	 * 
	 * @param name
	 *            成员名
	 * @param apiName
	 *            成员对应的api名
	 * @param type
	 *            成员类型
	 * @param apiTypeEle
	 *            成员类型节点
	 * @param mappingType
	 *            the mapping type
	 * @param apiArrayItemName
	 *            the api array item name
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param switchEle
	 *            the switch element
	 * @param exceptionExpress
	 *            the exceptionExpress
	 * @param apiIgnore
	 *            the api ignore
	 * 
	 * @return the member mapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private <T, M> MemberMapping<?> getMemberMapping(boolean mappingDirection, String name, String apiName, String type, Element apiTypeEle, Element switchEle, String mappingType, String apiArrayItemName, String exceptionExpress, boolean apiIgnore) throws MappingFormatException, InstantiationException, IllegalAccessException {
		MemberMapping<?> memberMapping = null;
		if (apiTypeEle != null) {
			// 支持<type class=""><struct>
			String tmp = apiTypeEle.getParent().attributeValue("class");
			if (!StringKit.isBlank(tmp) && "struct".equals(apiTypeEle.getName())) {
				mappingType = tmp;
			}
			if (mappingType == null) {
				if ("struct".equals(apiTypeEle.getName())) {
					HasMembersMapping mapMapping = getStructMapping(apiArrayItemName, mappingDirection, apiTypeEle);
					memberMapping = new MemberMapping<Map<String, ?>>(mappingType, mapMapping);
				} else if ("array".equals(apiTypeEle.getName())) {
					// 支持<type class=""><array>
					String className = apiTypeEle.getParent().attributeValue("class");
					String temp = apiTypeEle.attributeValue("apiArrayItemName");
					if (!StringKit.isBlank(temp)) {
						apiArrayItemName = temp;
					}
					if (className == null || className.equals("")) {
						ArrayMapping<T, M> arrayMapping = this.getArrayMapping(apiArrayItemName, mappingDirection, apiTypeEle);
						memberMapping = new MemberMapping<T>(mappingType, arrayMapping);
					} else {
						try {
							Class<?> clazz = Class.forName(className);
							if (java.util.List.class.isAssignableFrom(clazz)) {
								ListMapping<T> listMapping = getListMapping(apiArrayItemName, mappingDirection, apiTypeEle);
								memberMapping = new MemberMapping<List<T>>(mappingType, listMapping);
							} else if (java.util.Set.class.isAssignableFrom(clazz)) {
								SetMapping<T> setMapping = getSetMapping(apiArrayItemName, mappingDirection, apiTypeEle);
								memberMapping = new MemberMapping<Set<T>>(mappingType, setMapping);
							}
							/*
							 * primitiveMapMapping暂不支持 else if (java.util.Map.class .isAssignableFrom(clazz)) { PrimitiveMapMaping<T, M> mapMapping = getMapMapping( apiArrayItemName, mappingDirection, apiTypeEle); memberMapping = new MemberMapping<Map<T, M>>( mappingType, mapMapping); }
							 */
						} catch (ClassNotFoundException e) {
							throw new MappingFormatException("class " + className + " not found", e);
						}
					}
				}
			} else {
				if ("struct".equals(apiTypeEle.getName())) {
					ObjectMapping<Object> om = this.getObjectMapping(mappingType, mappingDirection, apiTypeEle);
					memberMapping = new MemberMapping<Object>(mappingType, om);
				}
			}
		} else if (mappingType != null) {
			Mapping<?> mapping = this.mappingFactory.getMappingFromStore(mappingType);
			memberMapping = new MemberMapping(mapping);
		}
		if (null != memberMapping) {
			if (name != null) {
				String[] names = StringKit.split(name, '.');
				if (names.length > 1) {
					memberMapping.setNames(names);
				}
			}
			memberMapping.setName(name);
			memberMapping.setMappingName(this.getString(apiName));
			memberMapping.setMappingComponentName(this.getString(apiArrayItemName));
			memberMapping.setApiIgnore(apiIgnore);
			if (exceptionExpress != null) {
				ExceptionExpressInfo ee = ExceptionExpressInfo.create(exceptionExpress);
				memberMapping.setExceptionExpress(ee);
			}
			if (null != switchEle) {
				initSwitcher(mappingDirection, memberMapping, switchEle);
			}
		}
		return memberMapping;
	}

	/**
	 * 检查一个字符串是否为一个类的名称.
	 * 
	 * @param clz
	 *            类
	 * @param clzName
	 *            所需检查的类名字符串
	 * 
	 * @return true, if is canonical
	 */
	private boolean isCanonical(Class<?> clz, String clzName) {
		return clz.getCanonicalName().equals(clzName);
	}

	/**
	 * 获取switcher.
	 * 
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param memMapping
	 *            the member mapping
	 * @param switchEle
	 *            the switch element
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	private void initSwitcher(boolean mappingDirection, MemberMapping memMapping, Element switchEle) throws MappingFormatException {
		DefaultSwitcher sw = new DefaultSwitcher();
		List<Element> cases = switchEle.elements("case");
		for (Element caseEle : cases) {
			String apiType = caseEle.attributeValue("apiType");
			String apiValue = caseEle.attributeValue("apiValue");
			String value = caseEle.attributeValue("value");
			String type = caseEle.attributeValue("type");
			String caseSrc = null;
			String caseSrcType = null;
			String caseTarget = null;
			if (mappingDirection) {
				caseSrc = value;
				caseSrcType = type;
				caseTarget = apiValue;
			} else {
				caseSrc = apiValue;
				caseSrcType = apiType;
				caseTarget = value;
			}
			try {
				Object target = memMapping.mapping(caseTarget, false);
				if ("string".equals(caseSrcType) || isCanonical(String.class, caseSrcType)) {
					sw.addCase(caseSrc, target);
				} else if (isCanonical(int.class, caseSrcType) || isCanonical(Integer.class, caseSrcType)) {
					sw.addCase(Integer.valueOf(caseSrc), target);
				} else if (isCanonical(long.class, caseSrcType) || isCanonical(Long.class, caseSrcType)) {
					sw.addCase(Long.valueOf(caseSrc), target);
				} else if (isCanonical(boolean.class, caseSrcType) || isCanonical(Boolean.class, caseSrcType)) {
					sw.addCase(Boolean.valueOf(caseSrc), target);
				} else if (isCanonical(byte.class, caseSrcType) || isCanonical(Byte.class, caseSrcType)) {
					sw.addCase(Byte.valueOf(caseSrc), target);
				} else if (isCanonical(char.class, caseSrcType) || isCanonical(Character.class, caseSrcType)) {
					sw.addCase(String.valueOf(caseSrc).trim().charAt(0), target);
				} else if (isCanonical(float.class, caseSrcType) || isCanonical(Float.class, caseSrcType)) {
					sw.addCase(Float.valueOf(caseSrc), target);
				} else if (isCanonical(double.class, caseSrcType) || isCanonical(Double.class, caseSrcType)) {
					sw.addCase(Double.valueOf(caseSrc), target);
				} else {
					throw new MappingException("the class in switch is not supported:" + caseSrcType);
				}
			} catch (MappingException e) {
				throw new MappingFormatException(e);
			} catch (OperationCodeException e) {
				throw new MappingFormatException(e);
			}
		}
		Element defaultEle = switchEle.element("default");
		if (defaultEle != null) {
			try {
				String apiValue = defaultEle.attributeValue("apiValue");
				String value = defaultEle.attributeValue("value");
				String defaultCaseTarget = null;
				if (mappingDirection) {
					defaultCaseTarget = apiValue;
				} else {
					defaultCaseTarget = value;
				}
				Object defaultTarget = memMapping.mapping(defaultCaseTarget, false);
				sw.setDefaultTarget(defaultTarget);
			} catch (MappingException e) {
				throw new MappingFormatException(e);
			} catch (OperationCodeException e) {
				throw new MappingFormatException(e);
			}
		}
		memMapping.setSwitcher(sw);
	}

	/**
	 * 获取ListMapping.
	 * 
	 * @param arrayItemMappingName
	 *            the array item mapping name
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param array
	 *            arrayElement <array></array> (该节点的父节点为<type class="java.util.List">或者为其他继承了list接口的list类型)
	 * 
	 * @return ListMapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private <T> ListMapping<T> getListMapping(String arrayItemMappingName, boolean mappingDirection, Element array) throws MappingFormatException, InstantiationException, IllegalAccessException {
		ListMapping<T> lm = new ListMapping<T>();
		String componentName = arrayItemMappingName;
		String tmp = array.attributeValue("apiArrayItemName");
		if (!StringKit.isBlank(tmp)) {
			componentName = tmp;
		}
		Element typeElement = array.element("type");
		Element arrayItem = null;
		if (typeElement.elements().size() >= 1) {
			arrayItem = (Element) typeElement.elements().get(0);
		}
		Mapping<T> comMapping = this.getMapping(typeElement, componentName, mappingDirection, arrayItem);
		if (comMapping != null) {
			lm.setComponentMapping(comMapping);
			// lm.setComponentName(this.getString(componentName));
		} else {
			throw new MappingFormatException("listmapping has a empty component");
		}
		return lm;
	}

	/**
	 * Gets the sets the mapping.
	 * 
	 * @param arrayItemMappingName
	 *            the array item mapping name
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param array
	 *            arrayElement <array></array> (该节点的父节点为<type class="java.util.Set">或者为其他继承了set接口的set类型)
	 * 
	 * @return SetMapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private <T, M> SetMapping<T> getSetMapping(String arrayItemMappingName, boolean mappingDirection, Element array) throws MappingFormatException, InstantiationException, IllegalAccessException {
		SetMapping<T> sm = new SetMapping<T>();
		String componentName = arrayItemMappingName;
		String tmp = array.attributeValue("apiArrayItemName");
		if (!StringKit.isBlank(tmp)) {
			componentName = tmp;
		}
		Element typeElement = array.element("type");
		Element arrayItem = null;
		if (typeElement.elements().size() >= 1) {
			arrayItem = (Element) typeElement.elements().get(0);
		}
		Mapping<T> comMapping = this.getMapping(typeElement, componentName, mappingDirection, arrayItem);
		if (comMapping != null) {
			sm.setComponentMapping(comMapping);
			// sm.setComponentName(this.getString(componentName));
		} else {
			throw new MappingFormatException("setmapping has a empty component");
		}
		return sm;
	}

	/**
	 * 获取一个MapMapping.
	 * 
	 * @param mappingName
	 *            mappingName
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param struct
	 *            structElement <struct></struct> (该节点的父节点为<type>)
	 * 
	 * @return MapMapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private HasMembersMapping getStructMapping(String mappingName, boolean mappingDirection, Element struct) throws MappingFormatException, InstantiationException, IllegalAccessException {
		HasMembersMapping mapMapping = null;
		if (struct == null || struct.elements().size() == 0) {
			mapMapping = new PureMapMapping(mappingName);
		} else {
			mapMapping = new MapMapping(mappingName);
			for (Iterator<?> i = struct.elementIterator(); i.hasNext();) {
				Element item = (Element) i.next();
				if (item.getName().equals("member")) {
					parseMember(mappingDirection, null, null, mapMapping, item);
				}
			}
		}
		return mapMapping;
	}

	/**
	 * 获取ObjectMapping.
	 * 
	 * @param clzName
	 *            the clz name
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param struct
	 *            structElement <struct></struct> (该Element的父节点为<type class="">)
	 * 
	 * @return the object mapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private ObjectMapping<Object> getObjectMapping(String clzName, boolean mappingDirection, Element struct) throws MappingFormatException, InstantiationException, IllegalAccessException {
		ObjectMapping<Object> om = null;
		try {
			om = new ObjectMapping<Object>(clzName);
		} catch (ClassNotFoundException e) {
			log.warn("" + e.getMessage());
		}
		for (Iterator<?> i = struct.elementIterator(); i.hasNext();) {
			Element item = (Element) i.next();
			if (item.getName().equals("member")) {
				parseMember(mappingDirection, null, null, om, item);
			}
		}
		return om;
	}

	/**
	 * 获取数组的ArrayMapping.
	 * 
	 * @param arrayItemMappingName
	 *            子项名称
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param array
	 *            arrayElement <array></array> (该节点的父节点为<type>)
	 * 
	 * @return arrayMapping 返回读取的ArrayMapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private <T, M> ArrayMapping<T, M> getArrayMapping(String arrayItemMappingName, boolean mappingDirection, Element array) throws MappingFormatException, InstantiationException, IllegalAccessException {
		ArrayMapping<T, M> arrayMapping = null;
		String componentName = arrayItemMappingName;
		String tmp = array.attributeValue("apiArrayItemName");
		if (!StringKit.isBlank(tmp)) {
			componentName = tmp;
		}
		Element typeElement = array.element("type");
		Element arrayItem = null;
		if (typeElement.elements().size() >= 1) {
			arrayItem = (Element) typeElement.elements().get(0);
		}
		Mapping<M> comMapping = this.getMapping(typeElement, componentName, mappingDirection, arrayItem);
		if (comMapping != null) {
			arrayMapping = new ArrayMapping<T, M>(comMapping);
			arrayMapping.setComponentName(this.getString(componentName));
		} else {
			throw new MappingFormatException("arraymapping has a empty component");
		}
		return arrayMapping;
	}

	/**
	 * 获取基本的map类型，即在不知道map内部各个Member类型的情况下，只知道 key和value的类型，进行建模，模型为primitiveMapMapping 此处现在废弃，暂不支持该做法，如需用map，则必须知道其内部各项的类型.
	 * 
	 * @param arrayItemMappingName
	 *            the array item mapping name
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param typeElement
	 *            the type element
	 * @param arrayItem
	 *            the array item
	 * 
	 * @return the map mapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	// private <T, M> PrimitiveMapMaping<T, M> getMapMapping(
	// String arrayItemMappingName, boolean mappingDirection, Element array)
	// throws MappingFormatException {
	// PrimitiveMapMaping<T, M> pmm = new PrimitiveMapMaping<T, M>();
	// List<Element> es = array.elements("type");
	// Element keyType = es.get(0);
	// Element valueType = es.get(1);
	// Element keyItem = (Element) keyType.elements().get(0);
	// Element valueItem = (Element) valueType.elements().get(1);
	// Mapping<T> keyMapping = this.getMapping(keyType, arrayItemMappingName,
	// mappingDirection, keyItem);
	// Mapping<M> valueMapping = this.getMapping(valueType,
	// arrayItemMappingName, mappingDirection, valueItem);
	// if (keyMapping == null || valueMapping == null) {
	// throw new MappingFormatException(
	// "primitiveMapMapping have a empty component");
	// } else {
	// pmm.setComponentKeyType(keyMapping);
	// pmm.setComponentValueType(valueMapping);
	// }
	// return pmm;
	// }
	/**
	 * array、set、list的公共部分，即其中获取componentMapping的部分.
	 * 
	 * @param typeElement
	 *            typeElement:<type class=??(该属性可以为空)></type>
	 * @param arrayItemMappingName
	 *            子项名称
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * @param arrayItem
	 *            arrayElement
	 * 
	 * @return the mapping
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private <T, M, E> Mapping<T> getMapping(Element typeElement, String arrayItemMappingName, boolean mappingDirection, Element arrayItem) throws MappingFormatException, InstantiationException, IllegalAccessException {
		String className = typeElement.attributeValue("class");
		if (typeElement.elements().size() <= 0) {
			MemberMapping<T> memberMapping = this.getMemberMapping(className, mappingDirection);
			return memberMapping.getMapping();
		}
		String typeName = arrayItem.getName();
		if ("struct".equals(typeName)) {
			if (className == null) {
				HasMembersMapping mapMapping = getStructMapping(arrayItemMappingName, mappingDirection, arrayItem);
				return (Mapping<T>) mapMapping;
			} else {
				ObjectMapping<T> objectMapping = (ObjectMapping<T>) getObjectMapping(className, mappingDirection, arrayItem);
				return objectMapping;
			}
		} else if ("array".equals(typeName)) {
			if (className == null || className.equals("")) {
				ArrayMapping<T, M> arrayMapping = this.getArrayMapping("", mappingDirection, arrayItem);
				return arrayMapping;
			} else {
				Class<?> clazz;
				try {
					clazz = Class.forName(className);
					if (java.util.List.class.isAssignableFrom(clazz)) {
						ListMapping<M> listMapping = (ListMapping<M>) this.getListMapping("", mappingDirection, arrayItem);
						return (Mapping<T>) listMapping;
					} else if (java.util.Set.class.isAssignableFrom(clazz)) {
						SetMapping<M> setMapping = (SetMapping<M>) this.getSetMapping("", mappingDirection, arrayItem);
						return (Mapping<T>) setMapping;
					}
					/*
					 * primitiveMapMapping暂不支持 else if (java.util.Map.class.isAssignableFrom(clazz)) { PrimitiveMapMaping<M, E> mapMapping = (PrimitiveMapMaping<M, E>) this .getMapMapping("", mappingDirection, arrayItem); return (Mapping<T>) mapMapping; }
					 */
				} catch (ClassNotFoundException e) {
					throw new MappingFormatException(e);
				}
			}
		}
		return null;
	}

	/**
	 * Gets the member mapping.
	 * 
	 * @param type
	 *            the type
	 * @param mappingDirection
	 *            即是methodCallMapping还是methodResponseMapping，二者的区别在于一个是type和apiType的意义不同 ，二是name与apiName的意义也相反，同时switch中的value和apiValue也相反
	 * 
	 * @return the member mapping
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws MappingFormatException
	 * 
	 * @throws MappingFormatException
	 *             the mapping format exception
	 */
	private <T> MemberMapping<T> getMemberMapping(String type, boolean mappingDirection) throws MappingFormatException, InstantiationException, IllegalAccessException {
		return (MemberMapping<T>) this.getMemberMapping(mappingDirection, "", "", type, null, null, type, "", null, false);
	}
}
