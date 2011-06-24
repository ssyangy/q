/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class WeiboPage extends Page implements Serializable {
	private static final long serialVersionUID = 3914161124769051701L;

	private Long id;

	private Long senderId;

	private List<Long> senderIds;

	private Integer newStatus;

	private Integer oldStatus;

	public List<Long> getSenderIds() {
		return senderIds;
	}

	public void setSenderIds(List<Long> senderIds) {
		this.senderIds = senderIds;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param newStatus
	 *            the newStatus to set
	 */
	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	/**
	 * @return the newStatus
	 */
	public Integer getNewStatus() {
		return newStatus;
	}

	/**
	 * @param oldStatus
	 *            the oldStatus to set
	 */
	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}

	/**
	 * @return the oldStatus
	 */
	public Integer getOldStatus() {
		return oldStatus;
	}

}
