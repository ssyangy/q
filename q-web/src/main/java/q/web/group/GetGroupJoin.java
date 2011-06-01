package q.web.group;

import q.dao.GroupDao;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

public class GetGroupJoin extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getIdLong("peopleId");
		long groupId = context.getIdLong("groupId");
		PeopleJoinGroup join = groupDao.getJoinPeopleByGroupIdPeopleId(peopleId, groupId);
		if (join != null && join.getStatus() == Status.COMMON.getValue()) {
			context.setModel("isJoined", join);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long peopleId = context.getIdLong("peopleId");
		long groupId = context.getIdLong("groupId");
		if (!IdCreator.isValidIds(peopleId, groupId)) {
			throw new RequestParameterInvalidException("null:无操作权限");
		}

	}

}
