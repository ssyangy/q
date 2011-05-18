/**
 *
 */
package q.web.people;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
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
public class GetPeopleFollower extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
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
		long toPeopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		GetPeopleFrame frame = new GetPeopleFrame();
		frame.setEventDao(eventDao);
		frame.setGroupDao(groupDao);
		frame.setPeopleDao(peopleDao);
		frame.validate(context);
		frame.execute(context);
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId");
		PeopleRelationPage page = new PeopleRelationPage();
		page.setToPeopleId(toPeopleId);
		page.setStatus(PeopleRelationStatus.FOLLOWING);
		int fetchSize = size ;
		page.setSize(fetchSize);
		page.setStartIndex(0);
		if (startId > 0) {
			page.setStartId(startId);
		}
		List<PeopleRelation> relations = this.peopleDao.getPeopleRelationsByPage(page);
		List<Long> followerIds = new ArrayList<Long>();
		for(PeopleRelation relation: relations) {
			followerIds.add(relation.getFromPeopleId());
		}
		List<People> peoples = this.peopleDao.getPeoplesByIds(followerIds);
		if (loginPeopleId > 0) {
			DaoHelper.injectPeoplesWithRelation(peopleDao, peoples, loginPeopleId);
		}
		context.setModel("peoples", peoples);
		Map<String, Object> api = new HashMap<String, Object>();
		api.put("peoples", peoples);
		api.put("relations", relations);
		context.setModel("api", api);
	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
