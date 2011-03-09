/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 对象映射成Long,类型被覆盖成long.
 * 
 * @version 2009-2-26
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class PrimitiveLongMapping extends LongMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<Long> getMappingType() {
		return long.class;
	}
	
	@Override
	protected boolean supportEmptyStringSource() {
		return false;
	}

	@Override
	protected boolean supportNullSource() {
		return false;
	}
	
	
}
