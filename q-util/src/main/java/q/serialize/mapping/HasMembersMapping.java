/**
 * 
 */
package q.serialize.mapping;

import java.util.Map;
import java.util.TreeMap;

import q.serialize.convert.Convert;
import q.util.ReflectKit;


// TODO: Auto-generated Javadoc
/**
 * Mapping for object which has any members.
 * 
 * @author alin mailto:xalinx@gmail.com
 * @date Apr 13, 2009
 */
public abstract class HasMembersMapping<T> extends AbstractMapping<T> {

	/** 存储MemberMappings. */
	private Map<String, MemberMapping<?>> memberMappings;

	/**
	 * 一个Object通过MapMapping输出时,需要有object的前缀,如location.state,
	 * 则location是这个state属性的前缀
	 */
	String mappingPreFix;

	/** 映射名称. */
	private String mappingName;

	/**
	 * Gets the mapping name.
	 * 
	 * @return the mappingName
	 */
	public String getMappingName() {
		return mappingName;
	}

	public void setMappingName(String mappingName) {
		this.mappingName = mappingName;
	}

	public String getMappingPreFix() {
		return mappingPreFix;
	}

	public void setMappingPreFix(String mappingPreFix) {
		this.mappingPreFix = mappingPreFix;
	}

	/**
	 * 添加MemberMapping.
	 * 
	 * @param mem
	 *            the mem
	 */
	public void addMemberMapping(MemberMapping<?> mem) {
		if (mem == null || mem.getName() == null) {
			throw new IllegalArgumentException(String.format(
					"MemberMapping invalid! dump:%s", mem));
		}
		if (this.memberMappings == null) {
			this.memberMappings = new TreeMap<String, MemberMapping<?>>();
		}
		this.memberMappings.put(mem.getName(), mem);
	}

	/**
	 * Gets the member mappings.
	 * 
	 * @return the memberMappings
	 */
	@Override
	public Map<String, MemberMapping<?>> getMemberMappings() {
		return memberMappings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Mapping#writeToXml(java.io.Writer,
	 * java.lang.Object, com.taobao.top.traffic.mapping.ExceptionExpress,
	 * boolean, java.lang.String)
	 */
	@Override
	public void write(Convert convert, Object source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException {
		if (source != null) {
			mappingName = convert.processMappingName(mappingName,
					isFromCollection);
			try {
				Class<?> clz = source.getClass();
				if (Map.class.isAssignableFrom(clz)) {
					Map<Object, Object> original = (Map<Object, Object>) source;
					convertMap2Exception(exceptionExpress, original);
					convert.convertMap(this.getMemberMappings(), original,
							exceptionExpress, mappingName, isFromCollection);
					// source is a pojo
				} else {
					convertPojo2Exception(exceptionExpress, source);
					convert.convertPojo(this.getMemberMappings(), source,
							exceptionExpress, mappingName,
							this.getMappingPreFix(), isFromCollection);
				}
				convert.flush();
			} catch (OperationCodeException e) {
				throw e;
			} catch (Exception e) {
				throw new MappingException("write error:" + source.toString(),
						e);
			}
		}
	}

	/**
	 * 若ExceptionExpress非空，将Map转为Exception并将其抛出， 如果ExceptionExpress为null，则直接返回.
	 * 
	 * @param ee
	 *            the ExceptionExpress
	 * @param original
	 *            源数据
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 */
	protected void convertMap2Exception(ExceptionExpressInfo ee, Map original)
			throws OperationCodeException {
		if (null != ee && ee.needException(original.get(ee.getFailKey()))) {
			Object code = original.get(ee.getFailCodeKey());
			String codeStr = code == null ? null : code.toString();
			Object msg = original.get(ee.getFailMsgKey()).toString();
			String msgStr = msg == null ? null : msg.toString();
			throw new OperationCodeException(codeStr, msgStr);
		}
	}

	/**
	 * 若ExceptionExpress非空，将Bean转为Exception并将其抛出， 如果ExceptionExpress为null，则直接返回.
	 * 
	 * @param ee
	 *            the ExceptionExpress
	 * @param original
	 *            源数据
	 * 
	 * @throws OperationCodeException
	 *             the operation code exception
	 * @throws MappingException
	 *             the mapping exception
	 */
	protected void convertPojo2Exception(ExceptionExpressInfo ee,
			Object original) throws OperationCodeException, MappingException {
		if (null != ee && ee.needException(ReflectKit.invokeGetter(original, ee.getFailKey()))) {
			Object code = ReflectKit.invokeGetter(original, ee.getFailCodeKey());
			String codeStr = code == null ? null : code.toString();
			Object msg = ReflectKit.invokeGetter(original, ee.getFailMsgKey());
			String msgStr = msg == null ? null : msg.toString();
			throw new OperationCodeException(codeStr, msgStr);
		}
	}
	

}
