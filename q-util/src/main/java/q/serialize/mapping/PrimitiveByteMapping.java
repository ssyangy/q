/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 对象映射成Byte,类型被覆盖成byte.
 * 
 * @version 2009-2-25
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class PrimitiveByteMapping extends ByteMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<Byte> getMappingType() {
		return byte.class;
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
