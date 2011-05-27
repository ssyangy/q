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

	private Long groupId;

	private Long senderId;

	private List<Long> senderIds;

	private List<Long> groupIds;
	
	private Integer newStatus;
	
	private Integer oldStatus;

	public List<Long> getSenderIds() {
		return senderIds;
	}

	public List<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}

	public void setSenderIds(List<Long> senderIds) {
		this.senderIds = senderIds;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	@Override
	public String toString() {
		return "WeiboPage [groupId=" + groupId + ", senderId=" + senderId + ", senderIds=" + senderIds + ", groupIds=" + groupIds + "]";
	}

	/**
	 * @param id the id to set
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
	 * @param newStatus the newStatus to set
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
	 * @param oldStatus the oldStatus to set
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
