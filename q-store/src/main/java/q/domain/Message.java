/**
 * 
 */
package q.domain;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class Message extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -676135026148020795L;

	private int status;

	private String content;

	private long senderId;

	private String senderRealName;

	private long receiverId;

	private String receiverRealName;

	private long quoteMessageId;

	public long getQuoteMessageId() {
		return quoteMessageId;
	}

	public void setQuoteMessageId(long quoteMessageId) {
		this.quoteMessageId = quoteMessageId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getSenderRealName() {
		return senderRealName;
	}

	public void setSenderRealName(String senderRealName) {
		this.senderRealName = senderRealName;
	}

	public String getReceiverRealName() {
		return receiverRealName;
	}

	public void setReceiverRealName(String receiverRealName) {
		this.receiverRealName = receiverRealName;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", status=" + status + ", created=" + created + ", modified=" + modified + ", content=" + content + ", senderId=" + senderId + ", senderRealName=" + senderRealName + ", receiverId=" + receiverId + ", receiverRealName=" + receiverRealName + ", quoteMessageId=" + quoteMessageId + "]";
	}

}
