package q.web.group;

import q.dao.GroupDao;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.RequestParameterInvalidException;

public class DeleteGroupAdmin extends Resource {
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long categoryId = context.getIdLong("categoryId");
		long groupId = context.getIdLong("groupId");
		groupDao.deleteGroupJoinCategoryForAdmin(groupId, categoryId);
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

}
