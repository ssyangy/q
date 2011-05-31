package q.web.group;

import java.util.List;

import q.dao.CategoryDao;
import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Category;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;

public class GetGroupAdmin extends Resource  {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		List<Category> categories = categoryDao.getAllCategorys();
		DaoHelper.injectCategoriesWithPromotedGroups(groupDao, categories);
		context.setModel("cats", categories);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		if (IdCreator.isNotValidId(loginPeopleId)) {
			throw new PeopleNotLoginException();
		}
		People people = peopleDao.getPeopleById(loginPeopleId);
		if (people.isNotAdmin()) {
			throw new PeopleNotPermitException();
		}
	}

}
