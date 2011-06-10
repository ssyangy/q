/**
 * 
 */
package q.web.exception;

/**
 * @author seanlinwang at gmail dot com
 * @date Jun 2, 2011
 * 
 */
public class WeiboNotExistException extends ErrorCodeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5715076339187265180L;

	public WeiboNotExistException() {
		this(null);
	}

	/**
	 * @param errorCode
	 */
	public WeiboNotExistException(String errorCode) {
		super(ErrorCode.EC_WEIBO_NOT_EXIST, errorCode);
	}

}
