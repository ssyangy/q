package q.serialize.mapping;

/**
 * 对象映射成Short.
 * 
 * @version 2009-11-19
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class ShortMapping extends AbstractMapping<Short> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Short mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Short result = null;
		Class<?> clz = source.getClass();
		if (String.class.equals(clz)) {
			result = Short.valueOf((String) source);
		} else if (Short.class.equals(clz)) {
			result = (Short) source;
		} else if (Byte.class.equals(clz)) {
			result = Short.valueOf((Byte) source);
		} else {
			throw new UnsupportSourceTypeException(source.getClass()
					.getCanonicalName());
		}
		return result;
	}
}
