package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * 对象被映射为Double，类型被覆盖成double.
 * 
 * @version 2009-08-06
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing.pt</a>
 */
public class PrimitiveDoubleMapping extends DoubleMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<Double> getMappingType() {
		return double.class;
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
