package q.web.category;

import q.dao.CategoryDao;
import q.dao.PeopleDao;
import q.domain.Category;
import q.domain.People;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;

public class GetCategoryEdit extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		Long cid = context.getResourceIdLong();
		Category category = categoryDao.getCategoryById(cid);
		context.setModel("category", category);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
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
