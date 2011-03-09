package q.serialize.mapping;

/**
 * 对象映射成Short,类型覆盖成short
 * 
 * @version 2009-11-19
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class PrimitiveShortMapping extends ShortMapping {

	@Override
	public Class<Short> getMappingType() {
		return short.class;
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
