package q.serialize.convert;

import java.io.Writer;

/**
 * 
 * @author cilai
 * @since 1.0, 2010-3-25 下午03:05:48
 */

public interface ConvertFactory {

	/**
	 * 获得Convert对象
	 * 
	 * @param format
	 * @param obj
	 * @return
	 */
	public Convert getConvert(String format, Writer obj);

}
