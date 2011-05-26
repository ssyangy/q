/**
 *
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 *
 */
public class MessageJoinPeoplePage extends Page implements Serializable {

	private static final long serialVersionUID = 1067817795733602686L;

	private Long receiverId;

	private Long messageId;

	private Boolean ignoreReplyNum = true;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	private List<Long> messageIds;

	private Long lastReplyId;

	private Long lastReplySenderId;

	public Long getLastReplyId() {
		return lastReplyId;
	}

	public Long getLastReplySenderId() {
		return lastReplySenderId;
	}

	/**
	 * @param messageIds
	 */
	public void setMessageIds(List<Long> messageIds) {
		this.messageIds = messageIds;
	}

	public List<Long> getMessageIds() {
		return messageIds;
	}

	/**
	 * @param receiverId
	 *            the receiverId to set
	 */
	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * @return the receiverId
	 */
	public Long getReceiverId() {
		return receiverId;
	}

	public void setIgnoreReplyNum(Boolean ignoreReplyNum) {
		this.ignoreReplyNum = ignoreReplyNum;
	}

	public Boolean getIgnoreReplyNum() {
		return ignoreReplyNum;
	}

	public void setLastReplyId(Long id) {
		this.lastReplyId = id;

	}

	public void setLastReplySenderId(Long senderId) {
		this.lastReplySenderId = senderId;
	}

}
