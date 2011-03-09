/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 对象映射成Boolean,类型被覆盖成boolean.
 * 
 * @version 2009-2-26
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class PrimitiveBooleanMapping extends BooleanMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<Boolean> getMappingType() {
		return boolean.class;
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
