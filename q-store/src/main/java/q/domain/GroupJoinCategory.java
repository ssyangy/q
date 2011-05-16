package q.domain;

import java.io.Serializable;
import java.util.Date;

public class GroupJoinCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6882792727642936124L;
	
	private long id;
	
	private long groupId;

	private long categoryId;

	private int status;

	private Date created;

	private Date modified;
	
	private int promote;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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

	@Override
	public String toString() {
		return "Category [id=" + id + ", groupId=" + groupId + ", " + "categoryId=" + categoryId + ", status=" + status + ", created=" + created + ", modified=" + modified + "]";
	}

	/**
	 * @param promote the promote to set
	 */
	public void setPromote(int promote) {
		this.promote = promote;
	}

	/**
	 * @return the promote
	 */
	public int getPromote() {
		return promote;
	}

}
