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
public class MessageReplyPage extends Page implements Serializable {

	private static final long serialVersionUID = 1067817795733602686L;

	private long quoteMessageId;

	public long getQuoteMessageId() {
		return quoteMessageId;
	}

	public void setQuoteMessageId(long messageId) {
		this.quoteMessageId = messageId;
	}

}
