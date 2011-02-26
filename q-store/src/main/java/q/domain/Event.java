/**
 * 
 */
package q.domain;

import java.io.Serializable;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 17, 2011
 * 
 */
public class Event extends AbstractDomain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 986692036209440550L;

	private long creatorId;

	private long groupId;

	private String name;

	private String intro;

	private int status;

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Event [creatorId=" + creatorId + ", groupId=" + groupId + ", name=" + name + ", intro=" + intro + ", status=" + status + ", id=" + id + ", created=" + created + ", modified=" + modified + "]";
	}

}
