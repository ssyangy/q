/**
 *
 */
package q.dao.page;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 *
 */
public class MessageReplyJoinPeoplePage extends Page implements Serializable {
	private static final long serialVersionUID = -7511140430589277045L;

	private Long quoteMessageId;

	private Long receiverId;

	public Long getQuoteMessageId() {
		return quoteMessageId;
	}

	public void setQuoteMessageId(Long messageId) {
		this.quoteMessageId = messageId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public Long getReceiverId() {
		return receiverId;
	}

}
