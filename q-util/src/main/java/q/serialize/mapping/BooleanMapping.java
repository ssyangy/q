package q.serialize.mapping;

import java.io.IOException;

import q.serialize.convert.Convert;


/**
 * 对象映射成Boolean.
 * 
 * @version 2009-8-24
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class BooleanMapping extends AbstractMapping<Boolean> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Boolean mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Boolean result = null;
		Class<?> clz = source.getClass();
		if (Boolean.class.equals(clz)) {
			result = (Boolean) source;
		} else if (String.class.equals(clz)) {
			result = Boolean.valueOf((String) source);
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Boolean source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException, IOException{
		convert.convertBoolean(source, mappingName);
		
	}
	
	
}
