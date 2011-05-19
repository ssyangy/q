/**
 *
 */
package q.web.people;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.page.PeopleRelationPage;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.util.CollectionKit;
import q.util.IdCreator;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long toPeopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();
		if (!context.isApiRequest()) {
			GetPeopleFrame frame = new GetPeopleFrame();
			frame.setEventDao(eventDao);
			frame.setGroupDao(groupDao);
			frame.setPeopleDao(peopleDao);
			frame.validate(context);
			frame.execute(context);
		} else {
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			PeopleRelationPage page = new PeopleRelationPage();
			page.setToPeopleId(toPeopleId);
			page.setStatus(PeopleRelationStatus.FOLLOWING);
			int asc = 1;
			if (type == asc) { // 1 indicate asc
				page.setDesc(false);
			} else {
				page.setDesc(true);
			}
			boolean hasPrev = false;
			boolean hasNext = false;
			int fetchSize = size + 1;
			page.setSize(fetchSize);
			if (startId > 0) {
				page.setStartId(startId);
			}
			if (startId > 0) {
				page.setStartId(startId);
			}
			List<PeopleRelation> relations = this.peopleDao.getPeopleRelationsByPage(page);
			Map<String, Object> api = new HashMap<String, Object>();
			if (CollectionKit.isNotEmpty(relations)) {
				if (relations.size() == fetchSize) {
					if (type == asc) { // more than one previous page
						hasPrev = true;
					} else { // more than one next page
						hasNext = true;
					}
					relations.remove(relations.size() - 1);// remove last one
				}
				if (type == asc) { // this action from next page
					hasNext = true;
				} else if (startId != IdCreator.MAX_ID) {// this action from previous page
					hasPrev = true;
				}
				if (type == asc) { // reverse asc to desc
					PeopleRelation[] array = relations.toArray(new PeopleRelation[relations.size()]);
					CollectionUtils.reverseArray(array);
					relations = Arrays.asList(array);
				}
				List<Long> followerIds = new ArrayList<Long>();
				Map<Long, PeopleRelation> peopleId2RelationMap = new HashMap<Long, PeopleRelation>();
				for (PeopleRelation relation : relations) {
					followerIds.add(relation.getFromPeopleId());
					peopleId2RelationMap.put(relation.getFromPeopleId(), relation);
				}
				List<People> peoples = this.peopleDao.getPeoplesByIds(followerIds);
				if (loginPeopleId > 0) {
					DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
				}
				for (People people : peoples) {
					people.setRelation(peopleId2RelationMap.get(people.getId()));
				}
				api.put("peoples", peoples);
			}
			api.put("hasPrev", hasPrev);
			api.put("hasNext", hasNext);
			context.setModel("api", api);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
	}

}
