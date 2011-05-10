/**
 *
 */
package q.web.people;

import java.util.ArrayList;
import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.dao.page.PeopleRelationPage;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 23, 2011
 *
 */
public class GetPeopleFollowing extends Resource {
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

	/* (non-Javadoc)
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long fromPeopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();

		GetPeopleFrame frame = new GetPeopleFrame();
		frame.setEventDao(eventDao);
		frame.setGroupDao(groupDao);
		frame.setPeopleDao(peopleDao);
		frame.setWeiboDao(weiboDao);
		frame.validate(context);
		frame.execute(context);
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		PeopleRelationPage page = new PeopleRelationPage();
		page.setFromPeopleId(fromPeopleId);
		int fetchSize = size ;
		page.setSize(fetchSize);
		page.setStatus(PeopleRelationStatus.FOLLOWING);
		if (startId > 0) {
			page.setStartId(startId);
		}
		page.setStartIndex(0);
		List<PeopleRelation> relations = this.peopleDao.getPeopleRelationsByPage(page);
		List<Long> followingIds = new ArrayList<Long>();
		for(PeopleRelation relation: relations) {
			followingIds.add(relation.getToPeopleId());
		}
		List<People> peoples = this.peopleDao.getPeoplesByIds(followingIds);
		if (loginPeopleId > 0) {
			DaoHelper.injectPeoplesWithRelation(peopleDao, peoples, loginPeopleId);
		}
		context.setModel("peoples", peoples);
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
