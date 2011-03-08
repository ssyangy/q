/**
 * 
 */
package q.serialize.mapping;

import java.io.IOException;

import q.serialize.convert.Convert;


/**
 * The Class DoubleMapping.
 * 
 * @version 2009-2-20
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class DoubleMapping extends AbstractMapping<Double> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Double mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Double result = null;
		Class<?> clz = source.getClass();
		if (Double.class.equals(clz)) {
			result = (Double) source;
		} else if (String.class.equals(clz)) {
			result = Double.valueOf((String) source);
		} else if (Float.class.equals(clz)) {
			result = ((Float) source).doubleValue();
		} else if (Integer.class.equals(clz)) {
			result = ((Integer) source).doubleValue();
		} else if (Short.class.equals(clz)) {
			result = ((Short) source).doubleValue();
		} else if (Byte.class.equals(clz)) {
			result = ((Byte) source).doubleValue();
		} else if (Long.class.equals(clz)) {
			result = ((Long) source).doubleValue();
		} else {
			throw new UnsupportSourceTypeException(source.getClass().getCanonicalName());
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Double source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException, IOException{
		convert.convertDouble(source, mappingName);
	}
	
	
}
