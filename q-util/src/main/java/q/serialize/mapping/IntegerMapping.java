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
public class IntegerMapping extends AbstractMapping<Integer> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Integer mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Integer result = null;
		Class<?> clz = source.getClass();
		if (Integer.class.equals(clz)) {
			result = (Integer) source;
		} else if (String.class.equals(clz)) {
			try {
				result = Integer.valueOf((String) source);
			} catch (NumberFormatException e) {
				throw new MappingException(e);
			}
		} else if (Short.class.equals(clz)) {
			result = ((Short) source).intValue();
		} else if (Byte.class.equals(clz)) {
			result = ((Byte) source).intValue();
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Integer source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException, IOException{
		convert.convertInteger(source, mappingName);
	}
	
	
}
