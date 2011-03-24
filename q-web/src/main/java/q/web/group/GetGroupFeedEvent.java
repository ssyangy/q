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
import q.domain.Event;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class GetGroupFeedEvent extends Resource {

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
		long loginPeopleId = context.getCookiePeopleId();
		List<Long> groupIds = this.groupDao.getGroupIdsByPeopleId(loginPeopleId);
		if (CollectionKit.isNotEmpty(groupIds)) {
			List<Event> events = this.eventDao.getEventsByGroupIds(groupIds, 20);
			DaoHelper.injectEventsWithRealName(peopleDao, events);
			DaoHelper.injectEventsWithGroupName(groupDao, events);
			context.setModel("events", events);
			
			GetGroupFeedFrame frame = new GetGroupFeedFrame();
			frame.setEventDao(eventDao);
			frame.setPeopleDao(peopleDao);
			frame.setGroupDao(groupDao);
			frame.setWeiboDao(weiboDao);
			frame.execute(context);
		}
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
