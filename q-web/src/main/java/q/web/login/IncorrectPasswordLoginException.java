/**
 * 
 */
package q.web.login;

/**
 * Resource login exception.
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 20, 2011
 * 
 */
public class IncorrectPasswordLoginException extends Exception {
	private static final long serialVersionUID = -3080434513698835125L;

	/**
	 * login username
	 */
	private String username;

	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public IncorrectPasswordLoginException(String username) {
		this.username = username;
	}

}
