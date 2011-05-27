/**
 * 
 */
package q.serialize.mapping;

import java.io.IOException;

import q.serialize.convert.Convert;


/**
 * 对象映射成Long.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class LongMapping extends AbstractMapping<Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Long mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Long result = null;
		Class<?> clz = source.getClass();
		if (Long.class.equals(clz)) {
			result = (Long) source;
		} else if (String.class.equals(clz)) {
			result = Long.valueOf((String) source);
		} else if (Integer.class.equals(clz)) {
			result = ((Integer) source).longValue();
		} else if (Short.class.equals(clz)) {
			result = ((Short) source).longValue();
		} else if (Byte.class.equals(clz)) {
			result = ((Byte) source).longValue();
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Long source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException, IOException {
		convert.convertLong(source, mappingName);
	}
	
}
