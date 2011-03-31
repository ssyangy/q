package q.web.group;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Event;
import q.domain.Group;
import q.domain.People;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.domain.Weibo;
import q.web.Resource;
import q.web.ResourceContext;

public class GetGroupFrame extends Resource {
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
		long groupId = context.getResourceIdLong();
		Group group = groupDao.getGroupById(groupId);
		context.setModel("group", group);

		long loginPeopleId = context.getCookiePeopleId();
		if (loginPeopleId > 0) {
			PeopleJoinGroup join = groupDao.getGroupPeople(loginPeopleId, groupId);
			if (join != null && join.getStatus() == Status.COMMON.getValue()) {
				context.setModel("join", join);
			}
		}
		
		List<Event> newEvents = this.eventDao.getEventsByGroupId(groupId, 4, 0);
		context.setModel("newEvents", newEvents);

		List<Long> groupPeopleIds = this.groupDao.getPeopleIdsByGroupId(groupId, 3, 0);
		List<People> groupPeoples = this.peopleDao.getPeoplesByIds(groupPeopleIds);
		context.setModel("newPeoples", groupPeoples);

		List<Long> hotGroupPeopleIds = this.groupDao.getHotGroupPeopleIds(groupId, 3, 0);
		List<People> hotGroupPeoples = this.peopleDao.getPeoplesByIds(hotGroupPeopleIds);
		context.setModel("hotPeoples", hotGroupPeoples);

		List<Weibo> hotWeibos = this.weiboDao.getHotGroupWeibosByGroupId(groupId, 3, 0);
		DaoHelper.injectWeiboModelsWithPeople(peopleDao, hotWeibos);
		context.setModel("hotWeibos", hotWeibos);

		List<Group> recommendGroups = this.groupDao.getRecommendGroupsByGroupId(groupId, 4, 0);
		context.setModel("recommendGroups", recommendGroups);
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
