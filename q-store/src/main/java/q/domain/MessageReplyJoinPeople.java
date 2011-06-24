/**
 * 
 */
package q.domain;

import java.io.Serializable;

import q.commons.domain.AbstractDomain;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class MessageReplyJoinPeople extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -1730869409799078899L;

	private int status;

	private long senderId;
	
	private long receiverId;

	private long replyId;

	private long quoteMessageId;

	private long quoteSenderId;

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

	public long getQuoteMessageId() {
		return quoteMessageId;
	}

	public void setQuoteMessageId(long quoteMessageId) {
		this.quoteMessageId = quoteMessageId;
	}

	public long getQuoteSenderId() {
		return quoteSenderId;
	}

	public void setQuoteSenderId(long quoteSenderId) {
		this.quoteSenderId = quoteSenderId;
	}

	public long getReplyId() {
		return replyId;
	}

	public void setReplyId(long replyId) {
		this.replyId = replyId;
	}

	/**
	 * @param receiverId the receiverId to set
	 */
	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	/**
	 * @return the receiverId
	 */
	public long getReceiverId() {
		return receiverId;
	}

}
