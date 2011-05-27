package q.serialize.mapping;

import java.io.IOException;

import q.serialize.convert.Convert;


// TODO: Auto-generated Javadoc
/**
 * The Class FloatMapping.
 * 
 * @version 2009-08-06
 * @author <a href="mailto:jiatianqing.pt@taobao.com">jiatianqing.pt</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class FloatMapping extends AbstractMapping<Float> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Float mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Float result = null;
		Class<?> clz = source.getClass();
		if (Float.class.equals(clz)) {
			result = (Float) source;
		} else if (String.class.equals(clz)) {
			result = Float.valueOf((String) source);
		} else if (Integer.class.equals(clz)) {
			result = ((Integer) source).floatValue();
		} else if (Short.class.equals(clz)) {
			result = ((Short) source).floatValue();
		} else if (Byte.class.equals(clz)) {
			result = ((Byte) source).floatValue();
		} else if (Long.class.equals(clz)) {
			result = ((Long) source).floatValue();
		} else {
			throw new UnsupportSourceTypeException(source.getClass().getCanonicalName());
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Float source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException, IOException {
		convert.convertFloat(source, mappingName);
	}
	
	
}
