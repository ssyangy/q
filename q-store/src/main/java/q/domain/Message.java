/**
 * 
 */
package q.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class Message extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -676135026148020795L;

	private int status;

	private long senderId;

	private int replyNum;

	private People sender;

	private List<Long> receiverIds;

	private List<People> receivers;

	private MessageReply lastReply;

	public MessageReply getLastReply() {
		return lastReply;
	}

	public void setLastReply(MessageReply lastReply) {
		this.lastReply = lastReply;
	}

	public List<People> getReceivers() {
		return receivers;
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

	public People getSender() {
		return sender;
	}

	public void setSender(People sender) {
		this.sender = sender;
	}

	public void setReceiverIds(List<Long> receiverIds) {
		this.receiverIds = receiverIds;
	}

	public List<Long> getReceiverIds() {
		return receiverIds;
	}

	@Override
	public String toString() {
		return "Message [status=" + status + ", senderId=" + senderId + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	/**
	 * @param people
	 */
	public void addReceiver(People people) {
		if (this.receivers == null) {
			this.receivers = new ArrayList<People>();
		}
		this.receivers.add(people);
	}

}
