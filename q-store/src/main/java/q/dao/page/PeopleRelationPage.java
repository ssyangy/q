/**
 * 
 */
package q.dao.page;

import java.io.Serializable;
import java.util.List;

import q.domain.PeopleRelationStatus;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 * 
 */
public class PeopleRelationPage extends Page implements Serializable {
	private static final long serialVersionUID = 660933418448593556L;

	private Long fromPeopleId;

	private Long toPeopleId;

	private PeopleRelationStatus status;

	private List<Long> toPeopleIds;

	public Long getFromPeopleId() {
		return fromPeopleId;
	}

	public void setFromPeopleId(Long fromPeopleId) {
		this.fromPeopleId = fromPeopleId;
	}

	public Long getToPeopleId() {
		return toPeopleId;
	}

	public void setToPeopleId(Long toPeopleId) {
		this.toPeopleId = toPeopleId;
	}

	public PeopleRelationStatus getStatus() {
		return status;
	}

	public void setStatus(PeopleRelationStatus status) {
		this.status = status;
	}

	public void setToPeopleIds(List<Long> toPeopleIds) {
		this.toPeopleIds = toPeopleIds;
	}

	public List<Long> getToPeopleIds() {
		return toPeopleIds;
	}

}
