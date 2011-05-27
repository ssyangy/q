/**
 * 
 */
package q.serialize.mapping;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Map;

import q.serialize.convert.Convert;
import q.serialize.util.Utils;


// TODO: Auto-generated Javadoc
/**
 * 抽象映射类
 * 
 * 持有子类的擦拭泛型（有擦拭的话).
 * 
 * @version 2009-8-24
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:jiatianqing.pt@taobao.com">jiatianqing</a>
 */
public abstract class AbstractMapping<T> implements Mapping<T> {

	/** 映射目标类型. */
	private Class<T> mappingType;

	/**
	 * 构造函数.
	 */
	public AbstractMapping() {
		Type genericSuperclass = getClass().getGenericSuperclass();
		if (genericSuperclass instanceof ParameterizedType) {
			Type typeArg = ((ParameterizedType) genericSuperclass)
					.getActualTypeArguments()[0];
			if (typeArg instanceof Class) {
				mappingType = (Class<T>) typeArg;
			} else if (typeArg instanceof ParameterizedType) {
				mappingType = (Class<T>) ((ParameterizedType) typeArg)
						.getRawType();
			} else if (genericSuperclass instanceof GenericArrayType) {
				mappingType = (Class<T>) Array.newInstance(
						(Class) ((GenericArrayType) genericSuperclass)
								.getGenericComponentType(), 0).getClass();
			} else if (typeArg instanceof TypeVariable) {
				// 泛型没被擦拭，无法持有
			} else {
			}
			// 父类已经被擦拭，如com.taobao.top.traffic.mapping.IntMapping
		} else {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Mapping#mapping(java.lang.Object)
	 */
	public final T mapping(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException {
		if (source == null) {
			if (supportNullSource()) {
				return null;
			} else {
				throw new MappingException(
						"This mapping unsupport null source!");
			}
		}
		if (String.class.equals(source.getClass())
				&& ((String) source).length() == 0) {
			if (supportEmptyStringSource()) {
				return null;
			}
		}
		T rs = null;
		try {
			rs = this.mappingInternal(source, exceptionExpress,
					withArrayItemName);
		} catch (MappingException e) {
			throw e;
		} catch (OperationCodeException e) {
			throw e;
		} catch (Throwable e) {
			throw new MappingException(e);
		}
		return rs;
	}

	protected boolean supportNullSource() {
		return true;
	}

	protected boolean supportEmptyStringSource() {
		return true;
	}

	/**
	 * Mapping internal.
	 * 
	 * @param source
	 *            the source
	 * @param exceptionExpress
	 *            the exception express
	 * @param withArrayItemName
	 *            the with array item name
	 * 
	 * @return the t
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	protected abstract T mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException, OperationCodeException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.ObjectMapping#getMemberMappings()
	 */
	public Map<String, MemberMapping<?>> getMemberMappings() {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.ObjectMapping#getMappingType()
	 */
	public Class<T> getMappingType() {
		return mappingType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Mapping#write(java.io.Writer,
	 * java.lang.Object, com.taobao.top.traffic.mapping.ExceptionExpress,
	 * java.lang.String, java.lang.String, boolean)
	 */
	public void write(Convert convert, Object source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException {
		T result = this.mapping(source, exceptionExpress, true);
		if (null != result) {
			try {
				mappingName = convert.processMappingName(mappingName,
						isFromCollection);
				writeInternal(convert, result, exceptionExpress, mappingName, isFromCollection);
			} catch (Exception e) {
				throw new MappingException("Error in AbstractMapping.write:"
						+ source.toString() + ", " + this.mappingType, e);
			}
		}
	}
	
	protected void writeInternal(Convert convert, T source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException, IOException{};
	
	

	/**
	 * 写数组开始标签.
	 * 
	 * @param writer
	 *            不能为空
	 * @param mappingName
	 *            标签名
	 * @param format
	 *            格式(xml, json)
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	protected void writeArrayStart(Writer writer, String mappingName,
			String format) throws MappingException {
		if (null != mappingName) {
			if (format.equals(Utils.XML_FORMAT)) {
				this.writeArrayStartXml(writer, mappingName);
			} else if (format.equals(Utils.JSON_FORMAT)) {
				this.writeArrayStartJson(writer, mappingName);
			}
		} else {
			if (format.equals(Utils.JSON_FORMAT)) {
				this.writeArrayStartJson(writer, mappingName);
			}
		}
	}

	/**
	 * 写xml数组开始标签:<tagName list="true">.
	 * 
	 * @param writer
	 *            不能为空
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	private void writeArrayStartXml(Writer writer, String mappingName)
			throws MappingException {
		if (null != mappingName) {
			try {
				writer.append(Utils.LEFT_ANGLE_BRACKET).append(
						mappingName.trim()).append(Utils.SPACE).append(
						Utils.LIST).append(Utils.RIGHT_ANGLE_BRACKET);
			} catch (IOException e) {
				throw new MappingException(
						"Error in MappingWriter.writeArrayStartXml", e);
			}
		}
	}

	/**
	 * 写json数组开始标签:"tagName":[.
	 * 
	 * @param writer
	 *            不能为空
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	private void writeArrayStartJson(Writer writer, String mappingName)
			throws MappingException {
		try {
			if (null != mappingName) {
				writer.append(Utils.DOUBLE_QUOTE).append(mappingName).append(
						Utils.DOUBLE_QUOTE).append(Utils.COLON).append(
						Utils.LSB);
			} else {
				writer.append(Utils.LSB);
			}
		} catch (IOException e) {
			throw new MappingException(
					"Error in MappingWriter.writeArrayStartJson", e);
		}
	}

	/**
	 * 写json数组开始标签:].
	 * 
	 * @param writer
	 *            不能为空
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	private void writeArrayEndJson(Writer writer, String mappingName)
			throws MappingException {
		try {
			writer.append(Utils.RSB);
		} catch (IOException e) {
			throw new MappingException(
					"Error in MappingWriter.writeArrayEndJson:" + mappingName,
					e);
		}
	}
}
