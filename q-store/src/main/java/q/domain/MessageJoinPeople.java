/**
 * 
 */
package q.domain;

import java.io.Serializable;

import q.commons.domain.AbstractDomain;

/**
 * @author seanlinwang at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class MessageJoinPeople extends AbstractDomain implements Serializable {
	private static final long serialVersionUID = -7972668474310261773L;

	private int status;

	private long messageId;

	private long senderId;

	private long receiverId;

	private long lastReplyId;

	private long lastReplySenderId;

	private int replyNum;

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	@Override
	public String toString() {
		return "MessageJoinPeople [status=" + status + ", messageId=" + messageId + ", senderId=" + senderId + ", receiverId=" + receiverId + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

	/**
	 * @param lastReplyId
	 *            the lastReplyId to set
	 */
	public void setLastReplyId(long lastReplyId) {
		this.lastReplyId = lastReplyId;
	}

	/**
	 * @return the lastReplyId
	 */
	public long getLastReplyId() {
		return lastReplyId;
	}

	/**
	 * @param lastReplySenderId
	 *            the lastReplySenderId to set
	 */
	public void setLastReplySenderId(long lastReplySenderId) {
		this.lastReplySenderId = lastReplySenderId;
	}

	/**
	 * @return the lastReplySenderId
	 */
	public long getLastReplySenderId() {
		return lastReplySenderId;
	}

}
