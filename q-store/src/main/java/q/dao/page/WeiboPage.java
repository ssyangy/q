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

	private Long groupId;

	private Long senderId;

	private List<Long> senderIds;

	public List<Long> getSenderIds() {
		return senderIds;
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

}
