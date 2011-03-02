/**
 * 
 */
package q.web.group;

import q.dao.GroupDao;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 18, 2011
 * 
 */
public class DeleteGroupJoin extends Resource {

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
		groupDao.unjoinPeopleJoinGroup(context.getLoginPeopleId(), context.getResourceIdLong());
		context.redirectReffer();
	}

}
