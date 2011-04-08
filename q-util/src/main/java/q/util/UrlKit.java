/**
 *
 */
package q.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import q.http.JdkHttpClient;

/**
 * @version 2008-7-27
 * @author <a href="mailto:zixue@taobao.com">zixue</a>
 *
 */
public class UrlKit {

	/**
	 *
	 */
	private static final String UTF8 = "utf-8";
	private static final String HTTP = "http://";

	/**
	 * @param query
	 *            k1=v1&k2=v2...
	 * @param parameterName
	 *            k1=
	 * @return
	 */
	public static String extractParameterValue(String query,
			String parameterName) {
		if (query == null) {
			return null;
		}
		int len = query.length();
		if (len == 0) {
			return null;
		}
		int matchFrom = query.indexOf(parameterName);
		if (matchFrom < 0) {
			return null;
		}
		if (!(matchFrom == 0 || query.charAt(matchFrom - 1) == '&')) {
			return null;
		}
		// parameterName=
		matchFrom += parameterName.length() + 1;
		int i = matchFrom;
		while (i < len) {
			if (query.charAt(i) == '&') {
				break;
			}
			i++;
		}
		if (i == matchFrom) {
			return null;
		}
		return query.substring(matchFrom, i);
	}

	/**
	 * @param string
	 * @return
	 */
	public static String extractDomain(String url) {
		if (!url.startsWith(HTTP)) {
			return null;
		}
		int end = url.indexOf('/', HTTP.length());
		if (end < 0) {
			end = url.length();
		}
		return url.substring(0, end);
	}

	public static String extractPath(String url) {
		if (!url.startsWith(HTTP)) {
			return null;
		}
		int start = url.indexOf('/', HTTP.length());
		if (start < 0) {
			return null;
		}
		int end = url.indexOf('?', start);
		if (end < 0) {
			end = url.length();
		}
		return url.substring(start, end);
	}

	public static String extractDomainAndPath(String url) {
		if (!url.startsWith(HTTP)) {
			return null;
		}
		int end = url.indexOf('?');
		if (end < 0) {
			end = url.length();
		}
		return url.substring(0, end);
	}

	public static String extractQuery(String url) {
		int start = url.indexOf('?');
		if (start < 0) {
			return null;
		}
		start++;
		String query = url.substring(start);
		return query;
	}

	public static int findQut(int start, String line) {
		int index = line.indexOf('"', start);
		if (index != 0 && line.charAt(index - 1) == '\\') {
			index = findQut(index + 1, line);
		}
		return index;
	}

	public static String getParameterNameWitchValueStartsWith(String query,
			String starts) {
		if (query == null) {
			return null;
		}
		int len = query.length();
		if (len == 0) {
			return null;
		}
		int nameStartIndex = 0;
		for (int j = 0; j < len; j++) {
			char cha = query.charAt(j);
			if (cha == '&') {
				nameStartIndex = j + 1;
			} else if (cha == '=') {
				String parameterName = query.substring(nameStartIndex, j);
				if (query.substring(j + 1).startsWith(starts)) {
					return parameterName;
				}
			}
		}
		return null;
	}

	public static Map<String, String> getParameterMapFromUrl(String url) {
		try {
			return getParameterMapFromUrl(url, UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Map<String, String> getParameterMapFromUrl(String url,
			String encoding) throws UnsupportedEncodingException {
		int index = url.indexOf('?');
		if (index >= 0) {
			url = url.substring(index + 1);
		}
		return getParameterMapFromQuery(url, encoding);
	}

	public static Map<String, String> getParameterMapFromQuery(String query) {
		try {
			return getParameterMapFromQuery(query, UTF8);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public static Map<String, String> getParameterMapFromQuery(String query,
			String encoding) throws UnsupportedEncodingException {
		String[] array = StringKit.split(query, '&');
		HashMap<String, String> retMap = null;
		if (array != null && array.length != 0) {
			retMap = new HashMap<String, String>(array.length);
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
					retMap.put(key, URLDecoder.decode(value, encoding));
				}
			}
		}
		return retMap;
	}

	public static String replaceUrl(String content, Replace replace) {
		StringBuffer sb = new StringBuffer();
		Pattern p = Pattern
				.compile("http://[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&:]*");
		Matcher m = p.matcher(content);
		while (m.find()) {
			String lurl = content.substring(m.start(), m.end());
			m.appendReplacement(sb, replace.replace(lurl));
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
