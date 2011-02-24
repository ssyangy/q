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
public class MessagePage extends Page implements Serializable {

	private static final long serialVersionUID = 1067817795733602686L;

	private Long myId;// my peopleId;

	public Long getMyId() {
		return myId;
	}

	public void setMyId(Long myId) {
		this.myId = myId;
	}

}
