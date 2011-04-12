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

	private long messageId;

	public long getMessageId() {
		return messageId;
	}

	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}

}
