package q.web.group;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 *
 */
import java.io.IOException;
import java.sql.SQLException;

import q.dao.GroupDao;
import q.domain.Group;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * AddGroup action
 * 
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 19, 2011
 * 
 */
public class AddGroup extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	@Override
	public void execute(ResourceContext context) throws SQLException, IOException {
		Group group = new Group();
		group.setCreatorId(context.getLoginPeopleId()); // set group creator id from cookie
		group.setName(context.getString("name"));
		group.setIntro(context.getString("intro"));
		groupDao.addGroup(group); // create group
		groupDao.addGroupJoinCategory(group.getId(), context.getIdLong("categoryId")); // set group category
		context.redirectServletPath("/group/" + group.getId());
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}
}