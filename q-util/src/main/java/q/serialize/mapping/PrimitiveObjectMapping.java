package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * Object -> Object复制.
 * 
 * @version 2009-8-11
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class PrimitiveObjectMapping extends AbstractMapping<Object> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Object mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		return source;
	}
}
