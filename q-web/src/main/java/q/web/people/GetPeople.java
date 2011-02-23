package q.web.people;

import java.sql.SQLException;
import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.domain.Event;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.Weibo;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Jan 18, 2011
 * 
 */
public class GetPeople extends Resource {
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

	@Override
	public void execute(ResourceContext context) throws SQLException {
		long peopleId = context.getResourceIdLong();

		People people = peopleDao.getPeopleById(peopleId);
		people.setFollowingNum(peopleDao.getPeopleFollowingNumById(peopleId));
		people.setFollowerNum(peopleDao.getPeopleFollowerNumById(peopleId));
		people.setWeiboNum(weiboDao.getPeopleWeiboNumByPeopleId(peopleId));
		context.setModel("people", people);

		WeiboPage page = new WeiboPage();
		page.setSenderId(peopleId);
		page.setSize(20);
		page.setStartIndex(0);
		List<Weibo> weibos = DaoHelper.getPageWeiboWithSenderRealName(peopleDao, weiboDao, page);
		context.setModel("weibos", weibos);
		
		List<Event> events = eventDao.getEventsByParticipantId(peopleId);
		context.setModel("events", events);

		boolean isMe = peopleId == context.getLoginPeopleId();
		context.setModel("isMe", isMe);
		
		boolean isFollowing = false;
		if (isMe == false) {
			PeopleRelation relation = peopleDao.getPeopleRelationByFromIdToId(context.getLoginPeopleId(), peopleId);
			if(relation != null && relation.isFollowing()) {
				isFollowing = true;
			}
		}
		context.setModel("isFollowing", isFollowing);
	}
}
