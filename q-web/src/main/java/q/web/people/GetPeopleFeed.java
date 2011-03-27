package q.web.people;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.FavoriteDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.PeopleRelationPage;
import q.dao.page.WeiboPage;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.domain.Weibo;
import q.util.CollectionKit;
import q.web.Resource;
import q.web.ResourceContext;

public class GetPeopleFeed extends Resource {
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
	public void execute(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		
		People login = this.peopleDao.getPeopleById(loginPeopleId);
		context.setModel("people", login);
		
		PeopleRelationPage followerPage = new PeopleRelationPage();
		followerPage.setToPeopleId(loginPeopleId);
		followerPage.setStatus(PeopleRelationStatus.FOLLOWING);
		followerPage.setSize(7);
		followerPage.setStartIndex(0);
		List<PeopleRelation> followers = this.peopleDao.getPeopleRelationsByPage(followerPage);
		DaoHelper.injectPeopleRelationsWithFromRealName(peopleDao, followers);
		context.setModel("newFollowers", followers);
		
		PeopleRelationPage followingPage = new PeopleRelationPage();
		followingPage.setFromPeopleId(loginPeopleId);
		followingPage.setStatus(PeopleRelationStatus.FOLLOWING);
		followingPage.setSize(7);
		followingPage.setStartIndex(0);
		List<PeopleRelation> followings = this.peopleDao.getPeopleRelationsByPage(followingPage);
		DaoHelper.injectPeopleRelationsWithToRealName(peopleDao, followings);
		context.setModel("newFollowings", followings);
		
		List<Long> senderIds = peopleDao.getAllFollowingId(loginPeopleId);
		senderIds.add(loginPeopleId);//add login people
		WeiboPage page = new WeiboPage();
		page.setStartIndex(0);
		page.setSize(20);
		page.setSenderIds(senderIds);
		List<Weibo> weibos = weiboDao.getPageFollowingWeibos(page);
		DaoHelper.injectWeibosWithSenderRealName(peopleDao, weibos);
		DaoHelper.injectWeibosWithFrom(groupDao, weibos);
		DaoHelper.injectWeibosWithFavorite(favoriteDao, weibos, loginPeopleId);
		context.setModel("weibos", weibos);
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
