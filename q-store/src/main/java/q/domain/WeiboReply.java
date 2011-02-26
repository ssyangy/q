/**
 * 
 */
package q.domain;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class WeiboReply extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -3572302391963822701L;

	private long senderId;

	private String senderRealName;

	private long quoteWeiboId;

	private long quoteSenderId;

	private long quoteReplyId;

	private String content;

	private int status;

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getQuoteWeiboId() {
		return quoteWeiboId;
	}

	public void setQuoteWeiboId(long quoteWeiboId) {
		this.quoteWeiboId = quoteWeiboId;
	}

	public long getQuoteSenderId() {
		return quoteSenderId;
	}

	public void setQuoteSenderId(long quoteSenderId) {
		this.quoteSenderId = quoteSenderId;
	}

	public long getQuoteReplyId() {
		return quoteReplyId;
	}

	public void setQuoteReplyId(long quoteReplyId) {
		this.quoteReplyId = quoteReplyId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSenderRealName() {
		return senderRealName;
	}

	public void setSenderRealName(String senderRealName) {
		this.senderRealName = senderRealName;
	}

	@Override
	public String toString() {
		return "WeiboReply [id=" + id + ", senderId=" + senderId + ", senderRealName=" + senderRealName + ", quoteWeiboId=" + quoteWeiboId + ", quoteSenderId=" + quoteSenderId + ", quoteReplyId=" + quoteReplyId + ", content=" + content + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

}
