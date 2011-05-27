/**
 * 
 */
package q.serialize.mapping;

import java.util.Date;

import org.apache.commons.lang.ArrayUtils;

import q.serialize.convert.Convert;
import q.util.DateKit;


/**
 * 对象映射成字符串.
 * 
 * @version 2009-8-24
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class StringMapping extends AbstractMapping<String> {

	@Override
	protected boolean supportEmptyStringSource() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected String mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		String result = null;
		Class<?> clz = source.getClass();
		if (Date.class.isAssignableFrom(clz)) {
			Date d = (Date) source;
			result = DateKit.date2Ymdhms(d);
		} else if (clz.isArray()) {
			result = ArrayUtils.toString(source);
		} else {
			result = source.toString();
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.top.traffic.mapping.Mapping#write(java.io.Writer,
	 * java.lang.Object, com.taobao.top.traffic.mapping.ExceptionExpress,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void write(Convert convert, Object source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException {
		String result = this.mapping(source, exceptionExpress, true);
		if (result != null) {
			try {
				mappingName = convert.processMappingName(mappingName,
						isFromCollection);
				convert.convertString(result, mappingName);
			} catch (Exception e) {
				throw new MappingException("write error:" + source.toString(),
						e);
			}
		}
	}

}
