package q.web.group;

import java.util.ArrayList;
import java.util.List;

import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.page.PeopleRelationPage;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetPeopleFeedFrame extends Resource {

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	// private EventDao eventDao;
	//
	// public void setEventDao(EventDao eventDao) {
	// this.eventDao = eventDao;
	// }
	//
	// private WeiboDao weiboDao;
	//
	// public void setWeiboDao(WeiboDao weiboDao) {
	// this.weiboDao = weiboDao;
	// }
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		People login = this.peopleDao.getPeopleById(loginPeopleId);
		context.setModel("people", login);
		context.setModel("selectGroups", this.groupDao.getGroupsByJoinPeopleId(loginPeopleId));

		PeopleRelationPage followerPage = new PeopleRelationPage();
		followerPage.setToPeopleId(loginPeopleId);
		followerPage.setStatus(PeopleRelationStatus.FOLLOWING);
		followerPage.setSize(7);
		followerPage.setStartIndex(0);
		List<PeopleRelation> followers = this.peopleDao.getPeopleRelationsByPage(followerPage);
		if (CollectionKit.isNotEmpty(followers)) {
			List<Long> followerIds = new ArrayList<Long>(followers.size());
			for (PeopleRelation pr : followers) {
				followerIds.add(pr.getFromPeopleId());
			}
			List<People> followerPeoples = this.peopleDao.getPeoplesByIds(followerIds);
			context.setModel("newFollowers", followerPeoples);
		}

		PeopleRelationPage followingPage = new PeopleRelationPage();
		followingPage.setFromPeopleId(loginPeopleId);
		followingPage.setStatus(PeopleRelationStatus.FOLLOWING);
		followingPage.setSize(7);
		followingPage.setStartIndex(0);
		List<PeopleRelation> followings = this.peopleDao.getPeopleRelationsByPage(followingPage);
		if (CollectionKit.isNotEmpty(followings)) {
			List<Long> followingIds = new ArrayList<Long>(followings.size());
			for (PeopleRelation pr : followings) {
				followingIds.add(pr.getToPeopleId());
			}
			List<People> followingPeoples = this.peopleDao.getPeoplesByIds(followingIds);
			context.setModel("newFollowings", followingPeoples);
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
