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

	private long replyWeiboId;

	private long replySenderId;

	private long groupId;

	private String content;

	private WeiboFromType fromType;

	private long fromId;

	private int status;

	private String fromPostfix;

	private boolean fav = false; //default  is unFav

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

	public String getSenderRealName() {
		return senderRealName;
	}

	public void setSenderRealName(String senderRealName) {
		this.senderRealName = senderRealName;
	}

	public long getReplySenderId() {
		return replySenderId;
	}

	public void setReplySenderId(long replySenderId) {
		this.replySenderId = replySenderId;
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

	public String getFromName() {
		return this.fromType.getFromName(fromPostfix);
	}

	public String getFromUrl() {
		return this.fromType.getFromUrl(fromId);
	}

	public boolean isFromGroup() {
		return this.fromType.isFromGroup();
	}

	public boolean isUnFav() {
		return !this.fav ;
	}

	public boolean isFav() {
		return this.fav;
	}
	
	public void setFav(boolean fav) {
		this.fav = fav;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getFromPostfix() {
		return fromPostfix;
	}

	@Override
	public String toString() {
		return "WeiboReply [senderId=" + senderId + ", senderRealName=" + senderRealName + ", quoteWeiboId=" + quoteWeiboId + ", quoteSenderId=" + quoteSenderId + ", replyWeiboId=" + replyWeiboId + ", replySenderId=" + replySenderId + ", groupId=" + groupId + ", content=" + content + ", fromType=" + fromType + ", fromId=" + fromId + ", status=" + status + ", fromPostfix=" + fromPostfix + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

}
