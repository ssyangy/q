/**
 * 
 */
package q.web.group;

import java.sql.SQLException;

import q.dao.GroupDao;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotPermitException;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 18, 2011
 * 
 */
public class AddGroupJoin extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getCookiePeopleId();
		long groupId = context.getResourceIdLong();
		addPeopleJoinGroup(peopleId, groupId);
	}

	public void addPeopleJoinGroup(long peopleId, long groupId) throws SQLException {
		PeopleJoinGroup oldRelation = groupDao.getJoinPeopleByGroupIdPeopleId(peopleId, groupId);
		if (oldRelation == null) {
			groupDao.addPeopleJoinGroup(peopleId, groupId);
			groupDao.incrGroupJoinNumByGroupId(groupId);
		} else if (oldRelation.getStatus() == Status.DELETE.getValue()) {
			int rowEffected = groupDao.updatePeopleJoinGroupStatusByIdAndOldStatus(oldRelation.getId(), Status.COMMON, Status.DELETE);
			if (rowEffected > 0) {
				groupDao.incrGroupJoinNumByGroupId(groupId);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		if (context.getCookiePeopleId() <= 0) {
			throw new PeopleNotPermitException("login:无操作权限");
		}
	}

}
