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
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.page.PeopleRelationPage;
import q.domain.Group;
import q.domain.People;
import q.domain.PeopleRelation;
import q.domain.PeopleRelationStatus;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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
		long fromPeopleId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();

		if (!context.isApiRequest()) {
			People people = peopleDao.getPeopleById(fromPeopleId);
			if (loginPeopleId > 0 && loginPeopleId != people.getId()) {
				DaoHelper.injectPeopleWithVisitorRelation(peopleDao, people, loginPeopleId);
			}
			if (loginPeopleId > 0) {
				DaoHelper.injectPeopleWithSelf(people, loginPeopleId);
			} 
			context.setModel("people", people);
			List<Group> groups = groupDao.getGroupsByJoinPeopleId(fromPeopleId);
			context.setModel("groups", groups);
		} else {
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			PeopleRelationPage page = new PeopleRelationPage();
			page.setFromPeopleId(fromPeopleId);
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
				List<Long> followingIds = new ArrayList<Long>();
				for (PeopleRelation relation : relations) {
					followingIds.add(relation.getToPeopleId());
				}
				List<People> peoples = this.peopleDao.getPeoplesByIds(followingIds);
				if (loginPeopleId > 0) {
					DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
				}
				List<People> orderPeoples = new ArrayList<People>(peoples.size());
				Map<Long, People> peopleId2PeopleMap = new HashMap<Long, People>();
				for (People people : peoples) {
					peopleId2PeopleMap.put(people.getId(), people); // init people map
				}
				for (PeopleRelation relation : relations) {
					People people = peopleId2PeopleMap.get(relation.getToPeopleId());
					relation.setPeople(people);// set people relation
					orderPeoples.add(people);
				}
				api.put("peoples", orderPeoples);
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
		long fromPeopleId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(fromPeopleId)) {
			throw new RequestParameterInvalidException("fromPeopleId:invalid");
		}
	}

}
