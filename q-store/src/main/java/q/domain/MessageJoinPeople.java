/**
 * 
 */
package q.domain;

import java.io.Serializable;

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

}
