/**
 * 
 */
package q.serialize.mapping;

import java.io.IOException;

import q.serialize.convert.Convert;


/**
 * 对象映射成Character.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class CharacterMapping extends AbstractMapping<Character> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Character mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Character result = null;
		Class<?> clz = source.getClass();
		if (Character.class.equals(clz)) {
			result = (Character) source;
		} else if (String.class.equals(clz) && ((String) source).length() > 0) {
			result = ((String) source).charAt(0);
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Character source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException, IOException {
		convert.convertCharacter(source, mappingName);
	}

	
}
