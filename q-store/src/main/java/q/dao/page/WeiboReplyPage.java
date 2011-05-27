/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class WeiboReplyPage extends Page implements Serializable {
	private static final long serialVersionUID = -3159535885864621611L;

	private Long quoteWeiboId;

	public Long getQuoteWeiboId() {
		return quoteWeiboId;
	}

	public void setQuoteWeiboId(Long quoteWeiboId) {
		this.quoteWeiboId = quoteWeiboId;
	}

	private Long senderId;

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	private Long quoteSenderId;

	public Long getQuoteSenderId() {
		return quoteSenderId;
	}

	public void setQuoteSenderId(Long quoteSenderId) {
		this.quoteSenderId = quoteSenderId;
	}

	private List<Long> quoteSenderIds;

	public List<Long> getQuoteSenderIds() {
		return quoteSenderIds;
	}

	public void setQuoteSenderIds(List<Long> quoteSenderIds) {
		this.quoteSenderIds = quoteSenderIds;
	}

}
