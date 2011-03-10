/**
 * 
 */
package q.serialize.mapping;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;

import q.serialize.convert.Convert;


/**
 * 对象成员映射.
 * 
 * @version 2009-8-16
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class MemberMapping<T> implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4733839138031821002L;

	/** The switcher. */
	private Switcher switcher;

	/** The enable fileds. */
	private boolean enableFileds = true;

	/** The mapping type. */
	// private String mappingType;

	/** The prefix for the mapping */
	private String mappingPrefix;

	/** The exception express. */
	private ExceptionExpressInfo exceptionExpress;

	/** ignore 实际在配置文件中并没有对应字段，在Annotation中为Ignore与之对应. */
	private boolean apiIgnore = false;

	/** The name. */
	private String name;

	/** The names. */
	private String[] names;

	/** The mapping name. */
	private String mappingName;

	/** The mapping. */
	private Mapping<T> mapping;

	/** 2.0协议使用的名字 */
	private String outputApiName;

	/** reflected method cache by zixue */
	private transient Method setter;

	private transient Method getter;

	/**
	 * Gets the switcher.
	 * 
	 * @return the switcher
	 */
	public Switcher getSwitcher() {
		return switcher;
	}

	/**
	 * Sets the switcher.
	 * 
	 * @param switcher
	 *            the new switcher
	 */
	public void setSwitcher(Switcher switcher) {
		this.switcher = switcher;
	}

	/**
	 * Checks if is enable fileds.
	 * 
	 * @return the enableFileds
	 */
	public boolean isEnableFileds() {
		return enableFileds;
	}

	/**
	 * Sets the enable fileds.
	 * 
	 * @param enableFileds
	 *            the enableFileds to set
	 */
	public void setEnableFileds(boolean enableFileds) {
		this.enableFileds = enableFileds;
	}

	/**
	 * Gets the mapping type.
	 * 
	 * @return the type
	 */
	public String getMappingType() {
		return this.mapping.getMappingType().getName();
	}

	//
	// /**
	// * Sets the mapping type.
	// *
	// * @param mappingType
	// * the mapping type
	// */
	// public void setMappingType(String mappingType) {
	// this.mappingType = mappingType;
	// }

	/**
	 * Gets the exception express.
	 * 
	 * @return the exception express
	 */
	public ExceptionExpressInfo getExceptionExpress() {
		return exceptionExpress;
	}

	/**
	 * Sets the exception express.
	 * 
	 * @param exceptionExpress
	 *            the new exception express
	 */
	public void setExceptionExpress(ExceptionExpressInfo exceptionExpress) {
		this.exceptionExpress = exceptionExpress;
	}

	/**
	 * Checks if is api ignore.
	 * 
	 * @return true, if is api ignore
	 */
	public boolean isApiIgnore() {
		return apiIgnore;
	}

	/**
	 * Sets the api ignore.
	 * 
	 * @param ignore
	 *            the new api ignore
	 */
	public void setApiIgnore(boolean ignore) {
		this.apiIgnore = ignore;
	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the names.
	 * 
	 * @return the names
	 */
	public String[] getNames() {
		return names;
	}

	/**
	 * Sets the names.
	 * 
	 * @param names
	 *            the new names
	 */
	public void setNames(String[] names) {
		this.names = names;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.ObjectMapping#getMappingName()
	 */
	/**
	 * Gets the mapping name.
	 * 
	 * @return the mapping name
	 */
	public String getMappingName() {
		return this.mappingName;
	}

	/**
	 * Sets the mapping name.
	 * 
	 * @param mappingName
	 *            the mappingName to set
	 */
	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	/**
	 * Gets the mapping.
	 * 
	 * @return the value
	 */
	public Mapping<T> getMapping() {
		return mapping;
	}

	/**
	 * Sets the mapping.
	 * 
	 * @param value
	 *            the value to set
	 */
	public void setMapping(Mapping<T> value) {
		this.mapping = value;
	}

	/**
	 * Gets the mapping array item name.
	 * 
	 * @return the apiArrayItemName
	 */
	public String getMappingComponentName() {
		if (mapping instanceof HasComponentMapping) {
			return ((HasComponentMapping) mapping).getComponentName();
		}
		return null;
	}

	/**
	 * Sets the mapping array item name.
	 * 
	 * @param mappingComponentName
	 *            the apiArrayItemName to set
	 */
	public void setMappingComponentName(String mappingComponentName) {
		/**
		 * jiangyy: 在此设置componentName
		 */
		if (mapping instanceof HasComponentMapping) {
			((HasComponentMapping) mapping).setComponentName(mappingComponentName);
		}
	}

	/**
	 * 映射.
	 * 
	 * @param source
	 *            the source
	 * @param withArrayItemName
	 *            是否将数组、list、set的子项名称进行映射
	 * 
	 * @return 映射后的目标
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	public T mapping(Object source, boolean withArrayItemName) throws MappingException, OperationCodeException {
		// use switcher
		if (this.switcher != null) {
			Switcher switcher = this.getSwitcher();
			return (T) switcher.switchCase(source);
		}

		// FIXME ?没看明白什么意思 zixue
		setTheMappingPrefix();

		// if no switcher, use Mapping
		if (mapping != null) {
			T rs = null;
			try {
				rs = mapping.mapping(source, this.exceptionExpress, withArrayItemName);
			} catch (MappingException e) {
				throw new MappingException(toString(), e);
			}
			return rs;
		}

		return null;
	}

	/**
	 * 在map和write时候,需要用到此参数,已获得数据, TODO 确定ObjectMapping内的方法与此方法的区别
	 */
	private void setTheMappingPrefix() {
		// 特殊处理,如果该mapping是嵌套的,并且有前缀,则需要输入前缀,如location.state
		if ((mapping instanceof MapMapping || mapping instanceof ObjectMapping) && mappingPrefix != null && mappingPrefix.length() > 0) {
			if (mapping instanceof MapMapping) {
				((MapMapping) mapping).setMappingPreFix(mappingPrefix);
			} else {
				// XXX Object Mapping.setMappingPreFix未经过测试
				((ObjectMapping) mapping).setMappingPreFix(mappingPrefix);
			}
		}
	}

	/**
	 * 将object映射为所目标数据写为xml或json.
	 * 
	 * @param convert
	 *            the Convert
	 * @param source
	 *            所要映射写出的object
	 * @param isFromCollection
	 *            判断该mapping是否为数组、list或者set的子项
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	public void write(Convert convert, Object source, boolean isFromCollection) throws MappingException, OperationCodeException {
		try {
			String mappingNameTemp = this.mappingName;

			mappingNameTemp = convert.processMappingName(mappingNameTemp, isFromCollection);
			if (this.switcher != null) {
				// 输出switcher代码
				convert.convertSwitcher(source, mappingNameTemp, this.getSwitcher());
				return;
			}

			if (getOutputApiName() != null) {
				mappingNameTemp = getOutputApiName();
			}

			setTheMappingPrefix();
			mapping.write(convert, source, this.exceptionExpress, mappingNameTemp, false);
			convert.flush();
		} catch (OperationCodeException e) {
			throw e;
		} catch (Exception e) {
			throw new MappingException("write error:" + source.toString(), e);
		}
	}

	/**
	 * Instantiates a new member mapping.
	 * 
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param mappingName
	 *            the mapping name
	 * @param ComponentMappingName
	 *            the mapping array item name
	 * @param mapping
	 *            the mapping
	 */
	public MemberMapping(String name, String mappingName, String ComponentMappingName, Mapping<T> mapping) {
		this.name = name;
		this.mappingName = mappingName;
		this.mapping = mapping;
		this.setMappingComponentName(ComponentMappingName);
	}

	/**
	 * Instantiates a new member mapping.
	 * 
	 * @param name
	 *            the name
	 * @param mappingName
	 *            the mapping name
	 * @param mapping
	 *            the mapping
	 */
	public MemberMapping(String name, String mappingName, Mapping<T> mapping) {
		this(name, mappingName, null, mapping);
	}

	public MemberMapping(String mappingName, Mapping<T> mapping) {
		this(null, mappingName, mapping);
	}

	/**
	 * The Constructor.
	 * 
	 * @param mapping
	 *            the mapping
	 */
	public MemberMapping(Mapping<T> mapping) {
		this(null, mapping);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String getMappingPrefix() {
		return mappingPrefix;
	}

	@Override
	public String toString() {
		return "MemberMapping [apiIgnore=" + apiIgnore + ", enableFileds=" + enableFileds + ", exceptionExpress=" + exceptionExpress + ", getter=" + getter + ", mapping=" + mapping + ", mappingName=" + mappingName + ", mappingPrefix=" + mappingPrefix + ", name=" + name + ", names=" + Arrays.toString(names) + ", outputApiName=" + outputApiName + ", setter=" + setter + ", switcher=" + switcher + "]";
	}

	public void setMappingPrefix(String mappingPrefix) {
		this.mappingPrefix = mappingPrefix;
	}

	public String getOutputApiName() {
		return outputApiName;
	}

	public void setOutputApiName(String outputApiName) {
		this.outputApiName = outputApiName;
	}

	public Method getSetter() {
		return setter;
	}

	public void setSetter(Method method) {
		this.setter = method;
	}

	public Method getGetter() {
		return getter;
	}

	public void setGetter(Method getter) {
		this.getter = getter;
	}

}
