package q.web.people;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.PeopleRelationPage;
import q.domain.Event;
import q.domain.Group;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.web.Resource;
import q.web.ResourceContext;

public class GetPeopleFrame extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private EventDao eventDao;

	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	private WeiboDao weiboDao;

	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long peopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		
		People people = peopleDao.getPeopleById(peopleId);
		people.setFollowingNum(peopleDao.getPeopleFollowingNumById(peopleId));
		people.setFollowerNum(peopleDao.getPeopleFollowerNumById(peopleId));
		people.setWeiboNum(weiboDao.getPeopleWeiboNumByPeopleId(peopleId));
		context.setModel("people", people);
		
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
		
		List<Event> events = eventDao.getAllEventsByPeopleId(peopleId);
		context.setModel("newEvents", events);

		PeopleRelationPage page = new PeopleRelationPage();
		page.setFromPeopleId(peopleId);
		page.setStatus(PeopleRelationStatus.FOLLOWING);
		page.setSize(20);
		page.setStartIndex(0);
		List<PeopleRelation> relations = this.peopleDao.getPeopleRelationsByPage(page);
		DaoHelper.injectPeopleRelationsWithToRealName(peopleDao, relations);
		context.setModel("newFriends", relations); //FIXME wrong, wanglin

		context.setModel("hotFriends", relations); //FIXME wrong, wanglin
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
