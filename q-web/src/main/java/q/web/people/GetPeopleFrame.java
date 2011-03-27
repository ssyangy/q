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
		if (peopleId == 0) {
			peopleId = loginPeopleId;
		}
		
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

		PeopleRelationPage followingPage = new PeopleRelationPage();
		followingPage.setFromPeopleId(peopleId);
		followingPage.setStatus(PeopleRelationStatus.FOLLOWING);
		followingPage.setSize(6);
		followingPage.setStartIndex(0);
		List<PeopleRelation> followings = this.peopleDao.getPeopleRelationsByPage(followingPage);
		DaoHelper.injectPeopleRelationsWithToRealName(peopleDao, followings);
		context.setModel("hotFollowings", followings);

		PeopleRelationPage followerPage = new PeopleRelationPage();
		followerPage.setToPeopleId(peopleId);
		followerPage.setStatus(PeopleRelationStatus.FOLLOWING);
		followerPage.setSize(6);
		followerPage.setStartIndex(0);
		List<PeopleRelation> followers = this.peopleDao.getPeopleRelationsByPage(followerPage);
		DaoHelper.injectPeopleRelationsWithFromRealName(peopleDao, followers);
		context.setModel("hotFollowers", followers);
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
