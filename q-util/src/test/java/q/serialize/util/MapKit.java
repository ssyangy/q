package q.serialize.util;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class MapKit {

	/**
	 * 仅在测试时使用
	 * 
	 * @param map
	 * @return
	 */
	public static String dumpMap(Map<String, Object> map) {
		if (map == null ) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		Iterator<Entry<String, Object>> itt = map.entrySet().iterator();
		for (;itt.hasNext();) {
			Entry<String, Object> entry = itt.next();
			sb.append(entry.getKey());
			sb.append('=');
			Object value = entry.getValue();
			if (value != null) {
				if (value.getClass().isArray()) {
					sb.append('[');
					int l = Array.getLength(value);
					for (int i = 0; i < l; i++) {
						sb.append(Array.get(value, i));
						if (i != l - 1) {
							sb.append(", ");
						}
					}
					sb.append(']');
				} else {
					sb.append(value);
				}
			} else {
				sb.append("null");
			}
			if(itt.hasNext()) {
				sb.append(", ");
			}
		}
		sb.append('}');
		return sb.toString();
	}
}
