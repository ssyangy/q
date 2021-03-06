/**
 *
 */
package q.web.group;

import java.sql.SQLException;

import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Group;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.RequestParameterInvalidException;

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

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
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
			peopleDao.incrPeopleGroupNumberByPeopleId(peopleId);
		} else if (oldRelation.getStatus() == Status.DELETE.getValue()) {
			int rowEffected = groupDao.updatePeopleJoinGroupStatusByIdAndOldStatus(oldRelation.getId(), Status.COMMON, Status.DELETE);
			if (rowEffected > 0) {
				groupDao.incrGroupJoinNumByGroupId(groupId);
				peopleDao.incrPeopleGroupNumberByPeopleId(peopleId);
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
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long groupId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(groupId)) {
			throw new RequestParameterInvalidException("group:invalid");
		}
		Group group = groupDao.getGroupById(groupId);
		if (null == group) {
			throw new RequestParameterInvalidException("group:invalid");
		}
		if (loginPeopleId == group.getCreatorId()) {
			throw new RequestParameterInvalidException("join:不需要加入您自己创建的圈子");
		}
	}

}
