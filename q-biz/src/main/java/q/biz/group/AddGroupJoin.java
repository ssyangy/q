/**
 * 
 */
package q.biz.group;

import java.util.Date;

import q.biz.IdCreator;
import q.dao.GroupDao;
import q.domain.PeopleGroupJoin;
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
		PeopleGroupJoin peopleGroupJoin = new PeopleGroupJoin();
		peopleGroupJoin.setId(IdCreator.getLongId());
		peopleGroupJoin.setCreated(new Date());
		peopleGroupJoin.setPeopleId(1);// FIXME
		peopleGroupJoin.setGroupId(Long.parseLong(context.getString("groupId")));
		peopleGroupJoin.setStatus(0);
		groupDao.addPeopleJoinGroup(peopleGroupJoin);
	}

}
