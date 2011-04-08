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
public class MessageJoinPeoplePage extends Page implements Serializable {

	private static final long serialVersionUID = 1067817795733602686L;

	private Long myId;

	public Long getMyId() {
		return myId;
	}

	public void setMyId(Long myId) {
		this.myId = myId;
	}
	
	private Long messageId;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
	
	private List<Long> messageIds;

	/**
	 * @param messageIds
	 */
	public void setMessageIds(List<Long> messageIds) {
		this.messageIds = messageIds;
	}

	public List<Long> getMessageIds() {
		return messageIds;
	}
	
	
	
}
