/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 24, 2011
 * 
 */
public class MessageReplyPage extends Page implements Serializable {

	private static final long serialVersionUID = 1067817795733602686L;

	private Long quoteMessageId;

	public Long getQuoteMessageId() {
		return quoteMessageId;
	}

	public void setQuoteMessageId(Long messageId) {
		this.quoteMessageId = messageId;
	}

	/**
	 * @param ids
	 *            the ids to set
	 */
	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	/**
	 * @return the ids
	 */
	public List<Long> getIds() {
		return ids;
	}

	private List<Long> ids;

}
