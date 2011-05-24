package q.domain;

import java.io.Serializable;

import q.commons.domain.AbstractDomain;

public class PeopleJoinGroup  extends AbstractDomain implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3633470537876252110L;

	private long peopleId;
	
	private long groupId;
	
	private int status;
	
	public long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(long peopleId) {
		this.peopleId = peopleId;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
	public PeopleJoinGroup() {
	}
	
	public PeopleJoinGroup(long peopleId, long groupId) {
		this.peopleId = peopleId;
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "EventJoin [id=" + id + ", peopleId=" + peopleId + ", groupId=" + groupId + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

	/**
	 * @param people
	 */
	public void setPeople(People people) {
		if(people != null) {
			people.setJoinGroup(this);
		}
	}

}
