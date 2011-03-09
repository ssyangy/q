/**
 * 
 */
package q.serialize.config;

import java.util.HashMap;
import java.util.Map;

import q.serialize.mapping.ArrayMapping;
import q.serialize.mapping.BooleanMapping;
import q.serialize.mapping.ByteMapping;
import q.serialize.mapping.CharMapping;
import q.serialize.mapping.CharacterMapping;
import q.serialize.mapping.DateMapping;
import q.serialize.mapping.DoubleMapping;
import q.serialize.mapping.FloatMapping;
import q.serialize.mapping.HasComponentMapping;
import q.serialize.mapping.IntMapping;
import q.serialize.mapping.IntegerMapping;
import q.serialize.mapping.ListMapping;
import q.serialize.mapping.LongMapping;
import q.serialize.mapping.Mapping;
import q.serialize.mapping.PrimitiveBooleanMapping;
import q.serialize.mapping.PrimitiveByteMapping;
import q.serialize.mapping.PrimitiveDoubleMapping;
import q.serialize.mapping.PrimitiveFloatMapping;
import q.serialize.mapping.PrimitiveLongMapping;
import q.serialize.mapping.PrimitiveObjectMapping;
import q.serialize.mapping.PrimitiveShortMapping;
import q.serialize.mapping.ShortMapping;
import q.serialize.mapping.StringMapping;


/**
 * @author xalinx at gmail dot com
 * @date Nov 25, 2009
 */
public class DefaultMappingFactory implements MappingFactory {
	public static final String ARRAY = "[]";
	public static final String LIST = "list";
	
	/** The Constant integerMapping. */
	protected final static IntegerMapping integerMapping = new IntegerMapping();

	/** The Constant intMapping. */
	protected final static IntMapping intMapping = new IntMapping();

	/** The Constant longMapping. */
	protected final static LongMapping longMapping = new LongMapping();

	/** The Constant primitiveLongMapping. */
	protected final static PrimitiveLongMapping primitiveLongMapping = new PrimitiveLongMapping();

	/** The Constant booleanMapping. */
	protected final static BooleanMapping booleanMapping = new BooleanMapping();

	/** The Constant primitiveBooleanMapping. */
	protected final static PrimitiveBooleanMapping primitiveBooleanMapping = new PrimitiveBooleanMapping();

	protected final static ShortMapping shortMapping = new ShortMapping();

	protected final static PrimitiveShortMapping primitiveShortMapping = new PrimitiveShortMapping();

	/** The Constant byteMapping. */
	protected final static ByteMapping byteMapping = new ByteMapping();

	/** The Constant primitiveByteMapping. */
	protected final static PrimitiveByteMapping primitiveByteMapping = new PrimitiveByteMapping();

	/** The Constant characterMapping. */
	protected final static CharacterMapping characterMapping = new CharacterMapping();

	/** The Constant charMapping. */
	protected final static CharMapping charMapping = new CharMapping();

	/** The Constant floatMapping. */
	protected final static FloatMapping floatMapping = new FloatMapping();

	/** The Constant primitiveFloatMapping. */
	protected final static PrimitiveFloatMapping primitiveFloatMapping = new PrimitiveFloatMapping();

	/** The Constant doubleMapping. */
	protected final static DoubleMapping doubleMapping = new DoubleMapping();

	/** The Constant primitiveDoubleMapping. */
	protected final static PrimitiveDoubleMapping primitiveDoubleMapping = new PrimitiveDoubleMapping();

	/** --- object mapping -- */

	/** The Constant dateMapping. */
	protected final static DateMapping dateMapping = new DateMapping();

	/** The Constant stringMapping. */
	protected final static StringMapping stringMapping = new StringMapping();

	// object
	/** The Constant primitiveObjectMapping. */
	protected final static PrimitiveObjectMapping primitiveObjectMapping = new PrimitiveObjectMapping();

	/**
	 * MemberMappings store
	 */
	Map<String, Mapping<?>> mms = null;

	Map<String, Class<? extends HasComponentMapping>> collMappings = null;

	/**
	 * 
	 */
	public DefaultMappingFactory() {
		initMMS();
	}

	/**
	 * init MemberMappings store
	 */
	private void initMMS() {
		mms = new HashMap<String, Mapping<?>>();
		collMappings = new HashMap<String, Class<? extends HasComponentMapping>>();
		initStringMMS();
		initIntMMS();
		initLongMMS();
		initBooleanMMS();
		initShortMMS();
		initFloatMMS();
		initDoubleMMS();
		initCharMMS();
		initByteMMS();
		initDateMMS();
	}

	private static final String SHORTEN_STRING = "string";
	private static final String STRING = String.class.getCanonicalName();
	private static final String INT = int.class.getCanonicalName();
	private static final String INTEGER = Integer.class.getCanonicalName();
	String PRI_LONG = long.class.getCanonicalName();
	String LONG = Long.class.getCanonicalName();
	String PRI_DOUBLE = double.class.getCanonicalName();
	String DOUBLE = Double.class.getCanonicalName();
	String PRI_FLOAT = float.class.getCanonicalName();
	String FLOAT = Float.class.getCanonicalName();
	String CHAR = char.class.getCanonicalName();
	String CHARACTER = Character.class.getCanonicalName();
	String PRI_BYTE = byte.class.getCanonicalName();
	String BYTE = Byte.class.getCanonicalName();
	String PRI_BOOLEAN = boolean.class.getCanonicalName();
	String BOOLEAN = Boolean.class.getCanonicalName();
	String PRI_SHORT = short.class.getCanonicalName();
	String SHORT = Short.class.getCanonicalName();
	String DATE = java.util.Date.class.getCanonicalName();

	public Mapping<?> getMappingFromStore(String mappingType)
			throws InstantiationException, IllegalAccessException {
		if (mappingType == null) {
			throw new IllegalArgumentException();
		}
		Class<? extends HasComponentMapping> collMappingClz = this.collMappings
				.get(mappingType);
		if (collMappingClz != null) {
			HasComponentMapping collMapping = collMappingClz.newInstance();
			Mapping<?> mapping = this.mms.get(mappingType);
			if (mapping != null) {
				collMapping.setComponentMapping(mapping);
			}
			return collMapping;
		}
		Mapping<?> mapping = this.mms.get(mappingType);
		if (mapping != null) {
			return mapping;
		}
		throw new IllegalArgumentException("Unsupport mappingType:"
				+ mappingType);
	}

	private void initStringMMS() {
		addMapping(SHORTEN_STRING, stringMapping);
		addMapping(STRING, stringMapping);
	}

	private void initIntMMS() {
		addMapping(INT, intMapping);
		addMapping(INTEGER, integerMapping);
	}

	private void initLongMMS() {
		addMapping(PRI_LONG, primitiveLongMapping);
		addMapping(LONG, longMapping);
	}

	private void initBooleanMMS() {
		addMapping(PRI_BOOLEAN, primitiveBooleanMapping);
		addMapping(BOOLEAN, booleanMapping);
	}

	private void initShortMMS() {
		addMapping(PRI_SHORT, primitiveShortMapping);
		addMapping(SHORT, shortMapping);
	}

	private void initByteMMS() {
		addMapping(PRI_BYTE, primitiveByteMapping);
		addMapping(BYTE, byteMapping);
	}

	private void initCharMMS() {
		addMapping(CHAR, charMapping);
		addMapping(CHARACTER, characterMapping);
	}

	private void initFloatMMS() {
		addMapping(PRI_FLOAT, primitiveFloatMapping);
		addMapping(FLOAT, floatMapping);
	}

	private void initDoubleMMS() {
		addMapping(PRI_DOUBLE, primitiveDoubleMapping);
		addMapping(DOUBLE, doubleMapping);
	}

	private void initDateMMS() {
		addMapping(DATE, dateMapping);
	}

	private void addMapping(String key, Mapping<?> mapping) {
		collMappings.put(nameArray(key), ArrayMapping.class);
		collMappings.put(nameList(key), ListMapping.class);
		mms.put(nameArray(key), mapping);
		mms.put(nameList(key), mapping);
		mms.put(key, mapping);
	}

	private String nameList(String itemName) {
		return itemName + " " + LIST;
	}

	private String nameArray(String itemName) {
		return itemName + ARRAY;
	}

}
