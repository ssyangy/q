/**
 * 
 */
package q.web.group;

import q.dao.GroupDao;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.web.Resource;
import q.web.ResourceContext;

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

		long peopleId = context.getLoginPeopleId();
		long groupId = context.getResourceIdLong();
		PeopleJoinGroup join = groupDao.getPeopleJoinGroup(peopleId, groupId);
		if (join == null) {
			groupDao.addPeopleJoinGroup(peopleId, groupId);
		} else if (join.getStatus() == Status.DELETE.getValue()) {
			groupDao.rejoinPeopleJoinGroup(peopleId, groupId);
		}

		context.redirectPath("/group/" + groupId);
	}

}
