package q.web.category;

import java.util.List;

import q.dao.CategoryDao;
import q.dao.DaoHelper;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.dao.page.GroupRecommendPage;
import q.domain.Category;
import q.domain.Group;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;

public class GetCategoryAdmin extends Resource {
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
		if (!context.isApiRequest()) {
			long loginId = context.getCookiePeopleId();
			if (loginId > 0) {
				List<Group> groups = groupDao.getGroupsByJoinPeopleId(loginId);
				context.setModel("groups", groups);
			}
			List<Group> recommendGroups = null;
			GroupRecommendPage page = new GroupRecommendPage();
			if (loginId > 0) {
				page.setPeopleId(loginId);
			}
			recommendGroups = groupDao.getRecommendGroupsByPage(page);
			context.setModel("recommendGroups", recommendGroups);
			context.setModel("cats", categories);
		} else {
			context.setModel("cats", categories);
		}
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
