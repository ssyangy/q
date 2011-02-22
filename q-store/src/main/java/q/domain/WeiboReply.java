/**
 * 
 */
package q.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class WeiboReply implements Serializable {

	private static final long serialVersionUID = -3572302391963822701L;

	private long id;

	private long senderId;

	private long quoteWeiboId;

	private long quoteSenderId;

	private long quoteReplyId;

	private String content;

	private int status;

	private Date created;

	private Date modified;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	@Override
	public String toString() {
		return "WeiboReply [id=" + id + ", senderId=" + senderId + ", quoteWeiboId=" + quoteWeiboId + ", quoteSenderId=" + quoteSenderId + ", quoteReplyId=" + quoteReplyId + ", content=" + content + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

}
