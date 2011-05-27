/**
 * 
 */
package q.web.group;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class GetGroupPeople extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long groupId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		List<Long> peopleIds = this.groupDao.getJoinPeopleIdsByGroupId(groupId, 20, 0);
		List<People> peoples = this.peopleDao.getPeoplesByIds(peopleIds);
		if (loginPeopleId > 0) {
			DaoHelper.injectPeoplesWithRelation(peopleDao, peoples, loginPeopleId);
		}
		context.setModel("peoples", peoples);

		GetGroupFrame frame = new GetGroupFrame();
		frame.setEventDao(eventDao);
		frame.setGroupDao(groupDao);
		frame.setPeopleDao(peopleDao);
		frame.setWeiboDao(weiboDao);
		frame.execute(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
