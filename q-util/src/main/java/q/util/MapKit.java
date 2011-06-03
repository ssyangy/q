/**
 * 
 */
package q.util;

import java.util.Map;

/**
 * @author seanlinwang at gmail dot com
 * @date Jun 3, 2011
 *
 */
public class MapKit {
	
	public  static boolean isEmpty(Map map) {
		return null == map || map.size() == 0;
	}
	
	public static boolean isNotEmpty(Map map) {
		return !(isEmpty(map));
	}

}
