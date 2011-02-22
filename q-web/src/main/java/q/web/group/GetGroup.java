/**
 * 
 */
package q.web.group;

import java.util.List;

import q.dao.GroupDao;
import q.dao.WeiboDao;
import q.domain.Group;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.domain.Weibo;
import q.domain.WeiboPage;
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

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
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
		WeiboPage page = new WeiboPage();
		page.setGroupId(groupId);
		page.setStartId(0);
		page.setSize(20);
		page.setStartIndex(0);
		List<Weibo> weibos = weiboDao.getPageWeibo(page);
		context.setModel("weibos", weibos);
		log.debug("group:%s, join:%s, weibos:%s", group, join, weibos);
	}

}
