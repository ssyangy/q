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

import q.biz.CacheService;
import q.dao.DaoHelper;
import q.dao.PeopleDao;
import q.dao.page.PeoplePage;
import q.dao.page.PeopleRelationPage;
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
public class GetPeopleFollower extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private CacheService cacheService;

	public void setCacheService(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long toPeopleId = context.getResourceIdLong();
		this.cacheService.clearFoNotify(toPeopleId);

		long loginPeopleId = context.getCookiePeopleId();
		if (!context.isApiRequest()) {
			People people = peopleDao.getPeopleById(toPeopleId);
			context.setModel("people", people);
			PeoplePage page = new PeoplePage();
			page.setSize(6);
			List<People> recommendPeoples = peopleDao.getPeoplesByPage(page);
			context.setModel("recommendPeoples", recommendPeoples);
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
				for (PeopleRelation relation : relations) {
					followerIds.add(relation.getFromPeopleId());
				}
				List<People> peoples = this.peopleDao.getPeoplesByIds(followerIds);
				if (loginPeopleId > 0) {
					DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
				}
				List<People> orderPeoples = new ArrayList<People>(peoples.size());
				Map<Long, People> peopleId2PeopleMap = new HashMap<Long, People>();
				for (People people : peoples) {
					peopleId2PeopleMap.put(people.getId(), people); // init people map
				}
				for (PeopleRelation relation : relations) {
					People people = peopleId2PeopleMap.get(relation.getFromPeopleId());
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
		long toPeopleId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(toPeopleId)) {
			throw new RequestParameterInvalidException("toPeopleId:invalid");
		}
	}

}
