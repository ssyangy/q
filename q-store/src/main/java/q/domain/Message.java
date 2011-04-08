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

	private String content;

	private long senderId;

	private People sender;

	private List<Long> receiverIds;
	
	private List<People> receivers;
	
	public List<People> getReceivers() {
		return receivers;
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
		return "Message [status=" + status + ", content=" + content + ", senderId=" + senderId + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

	/**
	 * @param people
	 */
	public void addReceiver(People people) {
		if(this.receivers == null) {
			this.receivers = new ArrayList<People>();
		}
		this.receivers.add(people);
	}

}
