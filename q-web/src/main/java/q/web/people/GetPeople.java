package q.web.people;

import java.sql.SQLException;
import java.util.List;

import q.dao.AreaDao;
import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.WeiboPage;
import q.domain.Event;
import q.domain.Group;
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

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private FavoriteDao favoriteDao;

	public void setFavoriteDao(FavoriteDao favoriteDao) {
		this.favoriteDao = favoriteDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		long peopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();

		People people = peopleDao.getPeopleById(peopleId);
		people.setFollowingNum(peopleDao.getPeopleFollowingNumById(peopleId));
		people.setFollowerNum(peopleDao.getPeopleFollowerNumById(peopleId));
		people.setWeiboNum(weiboDao.getPeopleWeiboNumByPeopleId(peopleId));
		DaoHelper.injectPeopleWithArea(people);
		context.setModel("people", people);

		WeiboPage page = new WeiboPage();
		page.setSenderId(peopleId);
		page.setSize(20);
		page.setStartIndex(0);
		List<Weibo> weibos = weiboDao.getPageWeibo(page);
		DaoHelper.injectWeibosWithSenderRealNameAndFrom(peopleDao, groupDao, weibos);
		if (loginPeopleId > 0) {
			DaoHelper.injectWeibosWithFavorite(favoriteDao, weibos, loginPeopleId);
		}
		context.setModel("weibos", weibos);

		List<Event> events = eventDao.getEventsByParticipantId(peopleId);
		context.setModel("events", events);

		List<Group> groups = groupDao.getGroupsByPeopleId(peopleId);
		context.setModel("groups", groups);

		boolean isMe = false;
		if (loginPeopleId > 0 && peopleId == loginPeopleId) {
			isMe = true;
		}
		context.setModel("isMe", isMe);

		boolean isFollowing = false;
		if (isMe == false) {
			PeopleRelation relation = peopleDao.getPeopleRelationByFromIdToId(loginPeopleId, peopleId);
			if (relation != null && relation.isFollowing()) {
				isFollowing = true;
			}
		}
		context.setModel("isFollowing", isFollowing);
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
