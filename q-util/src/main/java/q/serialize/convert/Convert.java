package q.serialize.convert;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import q.serialize.mapping.ExceptionExpressInfo;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.MappingException;
import q.serialize.mapping.MemberMapping;
import q.serialize.mapping.Switcher;


/**
 * 
 * @author cilai
 * @since 1.0, 2010-3-24 上午10:45:27
 */

public interface Convert {

	/**
	 * 对象转换开始
	 * 
	 * @param memberMapping
	 * @throws MappingException
	 */
	public void convertBefore(MemberMapping<?> memberMapping) throws MappingException;

	/**
	 * 对象转换结束
	 * 
	 * @param memberMapping
	 * @throws MappingException
	 */
	public void convertDown(MemberMapping<?> memberMapping) throws MappingException;

	/**
	 * MemberMapping.switcher不为空时,处理switcher对象
	 * 
	 * @param source
	 * @param mappingName
	 * @param switcher
	 * @throws MappingException
	 */
	public void convertSwitcher(Object source, String mappingName, Switcher switcher) throws MappingException;

	public void convertString(String source, String mappingName) throws MappingException, IOException;

	public void convertCharacter(Character source, String mappingName) throws MappingException, IOException;

	public void convertDate(Date source, String mappingName) throws MappingException, IOException;

	public void convertBoolean(Boolean source, String mappingName) throws MappingException, IOException;

	public void convertByte(Byte source, String mappingName) throws MappingException, IOException;

	public void convertInteger(Integer source, String mappingName) throws MappingException, IOException;

	public void convertLong(Long source, String mappingName) throws MappingException, IOException;

	public void convertFloat(Float source, String mappingName) throws MappingException, IOException;

	public void convertDouble(Double source, String mappingName) throws MappingException, IOException;

	/**
	 * 转换map对象
	 * 
	 * @param memberMappings
	 * @param original
	 * @param exceptionExpress
	 * @param mappingName
	 * @param isFromCollection
	 * @throws MappingException
	 */
	public void convertMap(Map<String, MemberMapping<?>> memberMappings, Map<?, ?> original, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException;

	/**
	 * 转换Pojo对象
	 * 
	 * @param memberMappings
	 * @param source
	 * @param exceptionExpress
	 * @param mappingName
	 * @param mappingPrefixType
	 * @param mappingPreFix
	 * @param isFromCollection
	 * @throws MappingException
	 */
	public void convertPojo(Map<String, MemberMapping<?>> memberMappings, Object source, ExceptionExpressInfo exceptionExpress, String mappingName, String mappingPreFix, boolean isFromCollection) throws MappingException;

	/**
	 * 将基本Map类型写出
	 * 
	 * @param componentKeyType
	 * @param componentValueType
	 * @param original
	 *            源数据Map
	 * @param exceptionExpress
	 *            the exception express
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public void convertPrimitiveMap(Mapping<?> componentKeyType, Mapping<?> componentValueType, Map<?, ?> original, ExceptionExpressInfo exceptionExpress, String mappingName) throws MappingException;

	/**
	 * 将基本的POJO类型转换
	 * 
	 * @param componentKeyType
	 * @param componentValueType
	 * @param str
	 * @param exceptionExpress
	 * @param mappingName
	 * @throws MappingException
	 */
	public void convertPrimitivePojo(Mapping<?> componentKeyType, Mapping<?> componentValueType, String str, ExceptionExpressInfo exceptionExpress, String mappingName) throws MappingException;

	/**
	 * 转换集合类型 包括:array , List 等
	 * 
	 * @param componentMapping
	 * @param componentName
	 * @param source
	 * @param exceptionExpress
	 * @param mappingName
	 * @param isFromCollection
	 * @throws MappingException
	 */
	public void convertCollection(Mapping<?> componentMapping, String componentName, Object source, ExceptionExpressInfo exceptionExpress, String mappingName, boolean isFromCollection) throws MappingException;

	/**
	 * 对一个对象转换之前 需要对mappingName做的操作
	 * 
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public void convertStartTag(String mappingName) throws MappingException;

	/**
	 * 对一个对象转换之后 需要对mappingName做的操作
	 * 
	 * @param mappingName
	 *            标签名
	 * 
	 * @throws MappingException
	 *             the mapping exception
	 */
	public void convertEndTag(String mappingName) throws MappingException;

	/**
	 * 对象flush
	 * 
	 * @throws MappingException
	 */
	public void flush() throws MappingException;

	/**
	 * 如果是JSON格式，并且该Array是一个数组的子项，则mappingName不写出
	 * 
	 * @param isFromCollection
	 */
	public String processMappingName(String mappingName, boolean isFromCollection);

	/**
	 * 获得对象转换的类型
	 */
	public String getFormat();

}
