/**
 * 
 */
package q.web.group;

import java.util.ArrayList;
import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.EventPage;
import q.dao.page.PeopleJoinEventPage;
import q.domain.Event;
import q.domain.PeopleJoinEvent;
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
		List<Long> groupIds = this.groupDao.getGroupIdsByJoinPeopleId(loginPeopleId);
		String tab = context.getString("tab");

		if (CollectionKit.isNotEmpty(groupIds)) {
			List<Event> events = null;
			if ("joined".equals(tab)) {
				PeopleJoinEventPage page = new PeopleJoinEventPage();
				page.setSize(20);
				page.setStartIndex(0);
				page.setPeopleId(loginPeopleId);
				page.setGroupIds(groupIds);
				List<PeopleJoinEvent> joins = this.eventDao.getPeopleJoinEventsByPage(page);
				if (CollectionKit.isNotEmpty(joins)) {
					List<Long> eventIds = new ArrayList<Long>(joins.size());
					for (PeopleJoinEvent join : joins) {
						eventIds.add(join.getEventId());
					}
					events = this.eventDao.getEventsByIds(eventIds);
				}
			} else {// feed or created
				EventPage page = new EventPage();
				page.setSize(20);
				page.setStartIndex(0);
				page.setGroupIds(groupIds);
				if ("created".equals(tab)) {
					page.setCreatorId(loginPeopleId);
				}
				events = this.eventDao.getPageEvents(page);
			}
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
