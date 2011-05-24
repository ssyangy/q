package q.web.group;

import java.util.List;

import q.dao.DaoHelper;
import q.dao.EventDao;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.WeiboDao;
import q.domain.Group;
import q.domain.People;
import q.domain.PeopleJoinGroup;
import q.domain.Status;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

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
		if (group == null) {
			throw new RequestParameterInvalidException("group:invalid");
		}
		DaoHelper.injectGroupWithCreator(peopleDao, group);
		context.setModel("group", group);

		long loginPeopleId = context.getCookiePeopleId();
		if (loginPeopleId > 0 && group.getCreatorId() != loginPeopleId) {
			PeopleJoinGroup join = groupDao.getJoinPeopleByGroupIdPeopleId(loginPeopleId, groupId);
			if (join != null && join.getStatus() == Status.COMMON.getValue()) {
				context.setModel("join", join);
			}
		}

		long loginId = context.getCookiePeopleId();
		if (loginId > 0) {
			List<Group> groups = groupDao.getGroupsByJoinPeopleId(loginId);
			context.setModel("groups", groups);
		}

		// List<Event> newEvents = this.eventDao.getEventsByGroupId(groupId, 4, 0);
		// context.setModel("newEvents", newEvents);
		//
		// List<Weibo> hotWeibos = this.weiboDao.getHotGroupWeibosByGroupId(groupId, 3, 0);
		// DaoHelper.injectWeiboModelsWithPeople(peopleDao, hotWeibos);
		// context.setModel("hotWeibos", hotWeibos);

		// List<Long> hotGroupPeopleIds = this.groupDao.getJoinPeopleIdsByHotAndGroupId(groupId, 3, 0);
		// List<People> hotGroupPeoples = this.peopleDao.getPeoplesByIds(hotGroupPeopleIds);
		// context.setModel("hotPeoples", hotGroupPeoples);

		List<Long> groupPeopleIds = this.groupDao.getJoinPeopleIdsByGroupId(groupId, 3, 0);
		List<People> groupPeoples = this.peopleDao.getPeoplesByIds(groupPeopleIds);
		context.setModel("newPeoples", groupPeoples);

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
