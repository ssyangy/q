/**
 * 
 */
package q.serialize.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * @version 2008-7-27
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 * 
 */
public class UrlKit {
	public static Map<String, String> getMapFromParameters(String url) {
		int index = url.indexOf('?');
		if(index >= 0) {
			url = url.substring(index + 1);
		}
		String[] array = StringUtils.split(url, "&");
		HashMap<String, String> retMap = new HashMap<String, String>();
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				String item = array[i];
				String value = null;
				String key = null;
				int equalId = item.indexOf('=');
				if (equalId > 0) {
					key = item.substring(0, equalId);
					value = item.substring(equalId + 1);
				}
				if (value != null) {
					retMap.put(key, value);
				}
			}
		}
		return retMap;
	}
}
