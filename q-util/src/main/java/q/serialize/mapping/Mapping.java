package q.serialize.mapping;

import java.util.Map;

import q.serialize.convert.Convert;


// TODO: Auto-generated Javadoc
/**
 * 对象映射接口.
 * 
 * @param <T>
 *            模板
 * 
 * @version 2009-08-24
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public interface Mapping<T>{

	/**
	 * 输入对象映射成模板.
	 * 
	 * @param source
	 *            the source
	 * @param exceptionExpress
	 *            TODO
	 * @param withArrayItemName
	 *            TODO
	 * 
	 * @return the T
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 * @throws MappingException
	 *             the mapping exception
	 */
	T mapping(Object source, ExceptionExpressInfo exceptionExpress,
			boolean withArrayItemName) throws MappingException,
			OperationCodeException;

	/**
	 * 获取mapping中的子项的Map.
	 * 
	 * @return the member mappings
	 */
	Map<String, MemberMapping<?>> getMemberMappings();

	/**
	 * 获取该mapping的类型.
	 * 
	 * @return the mapping type
	 */
	Class<T> getMappingType();
	
	/**
	 * 将传入的Object写为用户指定的形式.
	 * 
	 * @param Convert
	 *            the convert
	 * @param source
	 *            源数据
	 * @param exceptionExpress
	 *            the exception express
	 * @param format
	 *            数据输出格式
	 * @param mappingName
	 *            数据输出的标签
	 * @param isFromCollection
	 *            是否来自数组 ,set list
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 * @throws OperationCodeException
	 *             the operation code exception
	 * 
	 * @author jiatianqing.pt
	 */
	void write(Convert convert, Object source, ExceptionExpressInfo exceptionExpress,
			 String mappingName , boolean isFromCollection) throws MappingException, OperationCodeException;
}
