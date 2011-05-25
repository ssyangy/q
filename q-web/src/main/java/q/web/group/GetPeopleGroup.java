package q.web.group;

import java.sql.SQLException;
import java.util.List;

import q.dao.GroupDao;
import q.domain.Group;
import q.web.Resource;
import q.web.ResourceContext;

public class GetPeopleGroup extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		long loginId = context.getCookiePeopleId();
		long peopleId = context.getResourceIdLong();
		List<Group> groups = null;
		if (peopleId != 0)
			groups = groupDao.getGroupsByJoinPeopleId(Long.valueOf(peopleId));
		else {
			groups = groupDao.getGroupsByJoinPeopleId(loginId);
		}

	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}
}
