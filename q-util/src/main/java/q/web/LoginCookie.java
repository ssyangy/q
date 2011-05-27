/**
 * 
 */
package q.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import q.util.StringKit;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 10, 2011
 * 
 */
public class LoginCookie {

	private String realName;
	private long peopleId;
	private String username;

	public String getRealName() {
		return realName;
	}

	public long getPeopleId() {
		return peopleId;
	}

	public String getUsername() {
		return username;
	}

	private LoginCookie() {
	};

	public static LoginCookie getEmpty() {
		return new LoginCookie();
	}

	public LoginCookie(long peopleId, String realName, String username) {
		if (peopleId <= 0 || StringKit.isEmpty(realName) || StringKit.isEmpty(username)) {
			throw new IllegalStateException();
		}
		this.peopleId = peopleId;
		this.realName = realName;
		this.username = username;
	}

	/**
	 * @param request
	 * @return
	 */
	public static LoginCookie get(HttpServletRequest request, HttpServletResponse response) {
		LoginCookie loginCookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if ("u".equals(cookie.getName())) { // login user
					if (StringKit.isNotEmpty(cookie.getValue())) {
						String token = decodeLoginToken(cookie.getValue());
						String[] segs = StringKit.split(token, ';');
						if (segs.length == 3) {
							loginCookie = new LoginCookie(Long.valueOf(segs[0]), segs[1], segs[2]);
						} else {
							delete(response);
						}
					}
				}
			}
		}
		return loginCookie;
	}

	public static String encodeLoginToken(String input) {
		String token = null;
		try {
			token = URLEncoder.encode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	public static String decodeLoginToken(String input) {
		String token = null;
		try {
			token = URLDecoder.decode(input, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return token;
	}

	/**
	 * @param response
	 */
	public void add(HttpServletResponse response) {
		StringBuilder sb = new StringBuilder();
		sb.append(this.peopleId);
		sb.append(';');
		sb.append(this.realName);
		sb.append(';');
		sb.append(this.username);
		String token = encodeLoginToken(sb.toString());
		Cookie cookie = new Cookie("u", token);
		cookie.setMaxAge(3600 * 24 * 7); // one week
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * @param response
	 */
	public static void delete(HttpServletResponse response) {
		Cookie cookie = new Cookie("u", null);
		cookie.setMaxAge(0);// clear login token
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
