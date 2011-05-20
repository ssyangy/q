/**
 * 
 */
package q.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 * 
 */
public class PeopleRelation implements Serializable {

	private static final long serialVersionUID = 306552784695603323L;

	private long id;

	private long fromPeopleId;

	private String fromPeopleRealName;

	private long toPeopleId;

	private String toPeopleRealName;

	private String alias;

	private PeopleRelationStatus status;

	private PeopleRelationStatus oldStatus;

	private Date created;

	private Date modified;

	public PeopleRelation() {
	}

	public PeopleRelation(long fromPeopleId, long toPeopleId) {
		this.fromPeopleId = fromPeopleId;
		this.toPeopleId = toPeopleId;
	}

	public PeopleRelation(long fromPeopleId, long toPeopleId, PeopleRelationStatus status) {
		this(fromPeopleId, toPeopleId);
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromPeopleId() {
		return fromPeopleId;
	}

	public void setFromPeopleId(long fromPeopleId) {
		this.fromPeopleId = fromPeopleId;
	}

	public long getToPeopleId() {
		return toPeopleId;
	}

	public void setToPeopleId(long toPeopleId) {
		this.toPeopleId = toPeopleId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public PeopleRelationStatus getStatus() {
		return status;
	}

	public void setStatus(PeopleRelationStatus status) {
		this.status = status;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String getFromPeopleRealName() {
		return fromPeopleRealName;
	}

	public void setFromPeopleRealName(String fromPeopleRealName) {
		this.fromPeopleRealName = fromPeopleRealName;
	}

	public String getToPeopleRealName() {
		return toPeopleRealName;
	}

	public void setToPeopleRealName(String toPeopleRealName) {
		this.toPeopleRealName = toPeopleRealName;
	}

	/**
	 * @return
	 */
	public boolean isStranger() {
		return this.status.isStranger();
	}

	/**
	 * @return
	 */
	public boolean isFollowing() {
		return this.status.isFollowing();
	}

	public PeopleRelationStatus getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(PeopleRelationStatus oldStatus) {
		this.oldStatus = oldStatus;
	}

	@Override
	public String toString() {
		return "PeopleRelation [id=" + id + ", fromPeopleId=" + fromPeopleId + ", fromPeopleRealName=" + fromPeopleRealName + ", toPeopleId=" + toPeopleId + ", toPeopleRealName=" + toPeopleRealName + ", alias=" + alias + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

	/**
	 * @param people
	 */
	public void setPeople(People people) {
		if(people != null) {
			people.setRelation(this);
		}
	}

}
