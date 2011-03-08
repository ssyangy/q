package q.serialize.convert;

import java.io.Writer;

import q.serialize.util.Utils;


/**
 * 
 * @author cilai
 * @since 1.0, 2010-3-25 下午03:07:56
 */

public class DefaultConvertFactory implements ConvertFactory {

	public Convert getConvert(String format, Writer w) {
		Convert convert = null;
		if (Utils.JSON_FORMAT.equals(format))
			convert = new JSONConvert(w);
		else if (Utils.XML_FORMAT.equals(format))
			convert = new XMLConvert(w);
		else
			convert = new JSONConvert(w);
		return convert;
	}

}
