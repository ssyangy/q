/**
 * 
 */
package q.dao.page;

import java.util.List;

/**
 * @author seanlinwang at gmail dot com
 * @date May 16, 2011
 * 
 */
public class GroupJoinCategoryPage extends Page {

	private static final long serialVersionUID = 8186458581959280224L;

	private List<Long> categoryIds;

	private Integer newStatus;

	private Integer oldStatus;
	
	private Integer status;
	
	private Long groupId;

	private Long id;

	/**
	 * @param categoryIds
	 *            the catIds to set
	 */
	public void setCategoryIds(List<Long> categoryIds) {
		this.categoryIds = categoryIds;
	}

	/**
	 * @return the catIds
	 */
	public List<Long> getCategoryIds() {
		return categoryIds;
	}

	/**
	 * @param newStatus
	 *            the newStatus to set
	 */
	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	/**
	 * @return the newStatus
	 */
	public Integer getNewStatus() {
		return newStatus;
	}

	/**
	 * @param oldStatus
	 *            the oldStatus to set
	 */
	public void setOldStatus(Integer oldStatus) {
		this.oldStatus = oldStatus;
	}

	/**
	 * @return the oldStatus
	 */
	public Integer getOldStatus() {
		return oldStatus;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

}
