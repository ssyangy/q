/**
 * 
 */
package q.serialize.mapping;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import q.serialize.convert.Convert;
import q.util.DateKit;


// TODO: Auto-generated Javadoc
/**
 * Object to Date.
 * 
 * @version 2009-2-18
 * @author <a href="mailto:xalinx@gmail.com">alin</a>
 * @author <a href="mailto:juppwo@gmail.com">jiatianqing</a>
 */
public class DateMapping extends AbstractMapping<java.util.Date> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.top.traffic.mapping.AbstractMapping#mappingInternal(java.lang
	 * .Object, com.taobao.top.traffic.mapping.ExceptionExpress, boolean)
	 */
	@Override
	protected Date mappingInternal(Object source,
			ExceptionExpressInfo exceptionExpress, boolean withArrayItemName)
			throws MappingException {
		Date result = null;
		Class<?> clz = source.getClass();
		if (clz.equals(java.util.Date.class)) {
			result = (Date) source;
		} else if (clz.equals(String.class)) {
			try {
				result = DateKit.ymdOrYmdhms2Date((String) source);
			} catch (ParseException e) {
				throw new MappingException(e);
			}
		}
		return result;
	}

	@Override
	protected void writeInternal(Convert convert, Date source,
			ExceptionExpressInfo exceptionExpress, String mappingName,
			boolean isFromCollection) throws MappingException,
			OperationCodeException, IOException {
		convert.convertDate(source, mappingName);
	}

	
}
