/**
 * 
 */
package q.serialize.album;


// TODO: Auto-generated Javadoc
/**
 * The Class AlbumResult.
 * 
 * @author alin mailto:xalinx@gmail.com
 * @date Apr 14, 2009
 */
public class AlbumResult {
	
	/** The success. */
	private boolean success;

	/** The msg. */
	private String msg;

	/** The code. */
	private String code;

	/**
	 * Gets the code.
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 * 
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/** The item. */
	private Album item;

	/**
	 * Checks if is success.
	 * 
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 * 
	 * @param success the new success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets the msg.
	 * 
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * Sets the msg.
	 * 
	 * @param msg the new msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Gets the item.
	 * 
	 * @return the item
	 */
	public Album getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 * 
	 * @param album the new item
	 */
	public void setItem(Album album) {
		this.item = album;
	}
}
