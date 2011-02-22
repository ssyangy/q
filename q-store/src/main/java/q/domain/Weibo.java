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
public class Weibo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 224241669909941892L;

	private long id;

	private long senderId;

	private String senderRealName;

	private long quoteWeiboId;

	private long quoteSenderId;

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

	public boolean isQuote() {
		return this.quoteWeiboId > 0;
	}

	public String getSenderRealName() {
		return senderRealName;
	}

	public void setSenderRealName(String senderRealName) {
		this.senderRealName = senderRealName;
	}

	@Override
	public String toString() {
		return "Weibo [id=" + id + ", senderId=" + senderId + ", senderRealName" + senderRealName + ", quoteWeiboId=" + quoteWeiboId + ", quoteSenderId=" + quoteSenderId + ", content=" + content + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

}
