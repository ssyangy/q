/**
 * 
 */
package q.biz.group;

import q.dao.GroupDao;
import q.domain.Group;
import q.domain.PeopleGroupJoin;
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
		long groupId = context.getResourceLastIdLong();
		log.debug("get groupId", groupId);
		
		PeopleGroupJoin temp=new PeopleGroupJoin();
		temp.setGroupId(Long.parseLong("1298012235530"));
		log.debug("get groupId", groupId);
		temp.setPeopleId(1);
		PeopleGroupJoin peopleGroupJoin = groupDao.whetherJoined(temp);
		if(peopleGroupJoin==null){
			log.debug("get whetherjoined:%s", 0);
			context.setModel("whetherjoined",0);
		}
		else{
			log.debug("get whetherjoined:%s", 1);
			context.setModel("whetherjoined", 1);
		}
		Group group = groupDao.getGroupById(groupId);
		context.setModel("group", group);
	}

}
