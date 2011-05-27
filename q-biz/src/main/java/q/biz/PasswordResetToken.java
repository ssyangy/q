/**
 * 
 */
package q.biz;

import java.io.Serializable;

/**
 * @author seanlinwang at gmail dot com
 * @date May 6, 2011
 * 
 */
public class PasswordResetToken implements Serializable {
	private static final long serialVersionUID = -4427047369645031921L;

	private long peopleId;

	private long timestamp;

	public long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(long peopleId) {
		this.peopleId = peopleId;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public PasswordResetToken(long peopleId, long timestamp) {
		this.peopleId = peopleId;
		this.timestamp = timestamp;
	}

}
