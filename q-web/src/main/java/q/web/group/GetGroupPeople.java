/**
 * 
 */
package q.web.group;

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
import q.dao.WeiboDao;
import q.dao.page.PeopleJoinGroupPage;
import q.domain.People;
import q.domain.PeopleJoinGroup;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author seanlinwang
 * @email xalinx at gmail dot com
 * @date Feb 22, 2011
 * 
 */
public class GetGroupPeople extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#execute(q.web.ResourceContext)
	 */
	@Override
	public void execute(ResourceContext context) throws Exception {
		long groupId = context.getResourceIdLong();
		long loginPeopleId = context.getCookiePeopleId();

		if (!context.isApiRequest()) {
			GetGroupFrame frame = new GetGroupFrame();
			frame.setEventDao(eventDao);
			frame.setGroupDao(groupDao);
			frame.setPeopleDao(peopleDao);
			frame.setWeiboDao(weiboDao);
			frame.execute(context);
		} else {
			int size = context.getInt("size", 10);
			long startId = context.getIdLong("startId", IdCreator.MAX_ID);
			int type = context.getInt("type", 0);
			int asc = 1;
			PeopleJoinGroupPage page = new PeopleJoinGroupPage();
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
			page.setGroupId(groupId);
			List<PeopleJoinGroup> joins = this.groupDao.selectPeopleJoinGroupsByPage(page);
			Map<String, Object> api = new HashMap<String, Object>();
			if (CollectionKit.isNotEmpty(joins)) {
				if (joins.size() == fetchSize) {
					if (type == asc) { // more than one previous page
						hasPrev = true;
					} else { // more than one next page
						hasNext = true;
					}
					joins.remove(joins.size() - 1);// remove last one
				}
				if (type == asc) { // this action from next page
					hasNext = true;
				} else if (startId != IdCreator.MAX_ID) {// this action from previous page
					hasPrev = true;
				}
				if (type == asc) { // reverse asc to desc
					PeopleJoinGroup[] array = joins.toArray(new PeopleJoinGroup[joins.size()]);
					CollectionUtils.reverseArray(array);
					joins = Arrays.asList(array);
				}
				List<Long> peopleIds = new ArrayList<Long>(joins.size());
				for (PeopleJoinGroup join : joins) {
					peopleIds.add(join.getPeopleId());
				}
				List<People> peoples = this.peopleDao.getPeoplesByIds(peopleIds);
				if (CollectionKit.isNotEmpty(peoples)) {
					if (loginPeopleId > 0) {
						DaoHelper.injectPeoplesWithVisitorRelation(peopleDao, peoples, loginPeopleId);
						DaoHelper.injectPeoplesWithSelf(peoples, loginPeopleId);
					}
					List<People> orderPeoples = new ArrayList<People>(peoples.size());
					Map<Long, People> peopleId2PeopleMap = new HashMap<Long, People>();
					for (People people : peoples) {
						peopleId2PeopleMap.put(people.getId(), people); // init people map
					}
					for (PeopleJoinGroup relation : joins) {
						People people = peopleId2PeopleMap.get(relation.getPeopleId());
						relation.setPeople(people);// set people relation
						orderPeoples.add(people);
					}
					api.put("peoples", orderPeoples);
				}
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
		// TODO Auto-generated method stub

	}

}
