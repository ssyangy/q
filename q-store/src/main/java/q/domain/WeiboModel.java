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
public abstract class WeiboModel extends AbstractDomain implements Serializable {

	private static final long serialVersionUID = -1302742271799477217L;

	private long senderId;

	private String senderRealName;

	private long quoteWeiboId;

	private long quoteSenderId;
	
	private String quoteSenderRealName;

	private long quoteWeiboContent;

	private int quoteWeiboStatus;

	private long replyWeiboId;

	private long replySenderId;

	private WeiboFromType fromType;

	private long fromId;

	private String content;

	private int status;

	private String fromPostfix;

	private boolean fav;

	public void setFromPostfix(String fromPostfix) {
		this.fromPostfix = fromPostfix;
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

	public long getReplyWeiboId() {
		return replyWeiboId;
	}

	public void setReplyWeiboId(long quoteReplyId) {
		this.replyWeiboId = quoteReplyId;
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

	public boolean isQuote() {
		return this.quoteWeiboId > 0;
	}

	public String getSenderRealName() {
		return senderRealName;
	}

	public void setSenderRealName(String senderRealName) {
		this.senderRealName = senderRealName;
	}

	public long getReplySenderId() {
		return replySenderId;
	}

	public void setReplySenderId(long quoteWeiboSenderId) {
		this.replySenderId = quoteWeiboSenderId;
	}

	public WeiboFromType getFromType() {
		return fromType;
	}

	public void setFromType(WeiboFromType fromType) {
		this.fromType = fromType;
	}

	public long getFromId() {
		return fromId;
	}

	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	public String getFromUrl() {
		return this.fromType.getFromUrl(fromId);
	}

	public String getFromName() {
		return this.fromType.getFromName(fromPostfix);
	}

	public boolean isFromGroup() {
		return this.fromType.isFromGroup();
	}

	public boolean isInGroup() {
		return isFromGroup();
	}

	public boolean isUnFav() {
		return !this.fav;
	}

	public boolean isFav() {
		return this.fav;
	}

	public boolean isDelete() {
		return this.status == Status.DELETE.getValue();
	}

	public boolean isCommon() {
		return this.status == Status.COMMON.getValue();
	}

	public void setFav(boolean fav) {
		this.fav = fav;
	}

	public String getFromPostfix() {
		return fromPostfix;
	}

	public abstract String getViewName();

	public long getQuoteWeiboContent() {
		return quoteWeiboContent;
	}

	public void setQuoteWeiboContent(long quoteContent) {
		this.quoteWeiboContent = quoteContent;
	}

	public int getQuoteWeiboStatus() {
		return quoteWeiboStatus;
	}

	public void setQuoteWeiboStatus(int quoteStatus) {
		this.quoteWeiboStatus = quoteStatus;
	}

	public String getQuoteSenderRealName() {
		return quoteSenderRealName;
	}

	public void setQuoteSenderRealName(String quoteSenderRealName) {
		this.quoteSenderRealName = quoteSenderRealName;
	}

	@Override
	public String toString() {
		return "Weibo [senderId=" + senderId + ", senderRealName=" + senderRealName + ", quoteWeiboId=" + quoteWeiboId + ", quoteSenderId=" + quoteSenderId + ", replyWeiboId=" + replyWeiboId + ", replySenderId=" + replySenderId + ", fromType=" + fromType + ", fromId=" + fromId + ", content=" + content + ", status=" + status + ", fromPostfix=" + fromPostfix + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

}
