package q.web.group;

import java.util.List;

import q.dao.GroupDao;
import q.domain.GroupJoinCategory;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.RequestParameterInvalidException;

public class AddGroupAdmin extends Resource{
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long groupId = context.getIdLong("groupId");
		long categoryId = context.getIdLong("categoryId");
		int promote = context.getInt("promote", 1);
		GroupJoinCategory gjc = groupDao.selectGroupIdByCatIdAndGroupId(categoryId, groupId);
		List<GroupJoinCategory> joins = groupDao.getGroupJoinCategoriesByGroupIdAndStatus(groupId, null); // get joins ignore status
		if(gjc == null && this.isContain(categoryId, joins)) { //no such record
			groupDao.addGroupJoinCategoryForAdmin(groupId, categoryId, promote); // set group category
		}
		if(gjc != null && gjc.getPromote() == 0) { //got certain record, but it's promote equals 0
			groupDao.updateGroupJoinCategoryForAdmin(groupId, categoryId, promote);
		}
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		long categoryId = context.getIdLong("categoryId");
		if (IdCreator.isNotValidId(categoryId)) {
			throw new RequestParameterInvalidException("category:invalid");
		}
	}

	public boolean isContain(long categoryId, List<GroupJoinCategory> joins) {
		boolean contain = false;
		for(GroupJoinCategory join : joins) {
			if(join.getCategoryId() == categoryId) {
				contain = true;
				break;
			}
		}
		return contain;
	}

}
