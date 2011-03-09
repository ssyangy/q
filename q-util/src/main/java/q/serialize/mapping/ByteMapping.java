/**
 * 
 */
package q.serialize.mapping;

import java.io.IOException;

import q.serialize.convert.Convert;


// TODO: Auto-generated Javadoc
/**
 * 对象映射成Integer.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class ByteMapping extends AbstractMapping<Byte> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Byte mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Byte result = null;
		Class<?> clz = source.getClass();
		if (Byte.class.equals(clz)) {
			result = (Byte) source;
		} else if (String.class.equals(clz)) {
			result = Byte.valueOf((String) source);
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Byte source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			IOException {
		convert.convertByte(source, mappingName);
	}
	
	
}
