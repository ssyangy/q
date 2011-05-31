package q.web.category;

import q.dao.CategoryDao;
import q.dao.PeopleDao;
import q.domain.Category;
import q.domain.People;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotPermitException;

public class DeleteCategory extends Resource  {
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
		long categoryId = context.getResourceIdLong();
		Category category = new Category();
		category.setId(categoryId);
		this.categoryDao.deleteCategory(category);
	}

	@Override
	public void validate(ResourceContext context) throws Exception {
		long loginPeopleId = context.getCookiePeopleId();
		People people = peopleDao.getPeopleById(loginPeopleId);
		if(people.isNotAdmin()) {
			throw new PeopleNotPermitException("people:无操作权限");
		}
	}

}
