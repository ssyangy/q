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
public class MessageReply extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -8616856343241406248L;

	private int status;

	private String content;

	private long senderId;

	private People sender;

	private long quoteMessageId;

	private long quoteSenderId;

	private long replyMessageId;

	private long replySenderId;

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

	public long getReplyMessageId() {
		return replyMessageId;
	}

	public void setReplyMessageId(long replyMessageId) {
		this.replyMessageId = replyMessageId;
	}

	public long getReplySenderId() {
		return replySenderId;
	}

	public void setReplySenderId(long replySenderId) {
		this.replySenderId = replySenderId;
	}

}
