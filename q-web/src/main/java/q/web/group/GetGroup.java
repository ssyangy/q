/**
 * 
 */
package q.web.group;

import q.dao.GroupDao;
import q.domain.Group;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 16, 2011
 * 
 */
public class GetGroup extends Resource {
	private GroupDao groupDao;
	private final static Logger log = Logger.getLogger();

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
		long groupId = context.getResourceIdLong();
		long peopleId = context.getLoginPeopleId();
		Group group = groupDao.getGroupById(groupId);
		context.setModel("group", group);
		PeopleJoinGroup join = groupDao.getPeopleJoinGroup(peopleId, groupId);
		if (join != null && join.getStatus() == Status.COMMON.getValue()) {
			context.setModel("join", join);
		}
		log.debug("group:%s, join:%s", group, join);
	}

}
