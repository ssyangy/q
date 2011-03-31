/**
 *
 */
package q.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 */
public class FetchUtil {
	public static String inputStreamToString(InputStream input)
			throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(input,
				"utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = in.readLine()) != null) {
			buffer.append(line);
		}
		return buffer.toString();
	}

	/**
	 * url访问时没有上传文件时的辅助类，将map类型的params转换到url上
	 *
	 * @param entries
	 * @param delimiter
	 * @param equals
	 * @return
	 */
	public static String paramsToBuffer(
			Set<Entry<String, CharSequence>> entries, String delimiter,
			String equals) {
		if (entries == null || entries.isEmpty()) {
			return null;
		}
		StringBuilder buffer = new StringBuilder();
		boolean notFirst = false;
		for (Entry<String, CharSequence> entry : entries) {
			if (notFirst) {
				buffer.append(delimiter);
			} else {
				notFirst = true;
			}
			CharSequence value = entry.getValue();
			try {
				buffer.append(entry.getKey()).append(equals).append(
						URLEncoder.encode(value.toString(), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// System.out.println(buffer.toString());
		return new String(buffer);
	}
}
