/**
 * 
 */
package q.web.exception;

/**
 * @author seanlinwang at gmail dot com
 * @date Jun 2, 2011
 * 
 */
public class GroupNotExistException extends ErrorCodeException {

	private static final long serialVersionUID = 148726591584705974L;

	public GroupNotExistException() {
		this(null);
	}

	/**
	 * @param errorCode
	 */
	public GroupNotExistException(String errorCode) {
		super(ErrorCode.EC_GROUP_NOT_EXIST, errorCode);
	}

}
