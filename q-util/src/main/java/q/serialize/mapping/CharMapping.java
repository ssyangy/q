/**
 * 
 */
package q.serialize.mapping;

// TODO: Auto-generated Javadoc
/**
 * The Class CharMapping.
 * 
 * @version 2009-2-26
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 */
public class CharMapping extends CharacterMapping {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.AbstractMapping#getMappingType()
	 */
	@Override
	public Class<Character> getMappingType() {
		return char.class;
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
