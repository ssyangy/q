/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 对象映射成Integer,类型被覆盖成int.
 * 
 * @version 2009-2-17
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class IntMapping extends IntegerMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.ObjectMapping#getMappingType()
	 */
	@Override
	public Class<Integer> getMappingType() {
		return int.class;
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
