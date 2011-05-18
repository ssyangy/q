/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

import q.domain.Status;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Mar 23, 2011
 * 
 */
public class PeopleJoinGroupPage extends Page implements Serializable {

	private static final long serialVersionUID = -7459664427897325879L;

	private Long groupId;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	private List<Long> groupIds;

	public List<Long> getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(List<Long> groupIds) {
		this.groupIds = groupIds;
	}

	private Long peopleId;

	public Long getPeopleId() {
		return peopleId;
	}

	public void setPeopleId(Long peopleId) {
		this.peopleId = peopleId;
	}

	private Integer status = Status.COMMON.getValue();

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}