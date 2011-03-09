package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 对象被映射为Float，类型被覆盖成float.
 * 
 * @version 2009-08-06
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing.pt</a>
 */
public class PrimitiveFloatMapping extends FloatMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<Float> getMappingType() {
		return float.class;
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