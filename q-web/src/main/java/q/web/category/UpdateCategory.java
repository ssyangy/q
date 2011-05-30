package q.web.category;

import q.biz.PictureService;
import q.dao.CategoryDao;
import q.dao.PeopleDao;
import q.domain.Category;
import q.domain.People;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotPermitException;

public class UpdateCategory  extends Resource {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@Override
	public void execute(ResourceContext context) throws Exception {
		long categoryId = context.getResourceIdLong();
		Category category = new Category();
		category.setId(categoryId);
		category.setName(context.getString("name"));
		category.setIntro(context.getString("intro"));
		String avatarPath = context.getString("avatarPath");
		if(StringKit.isNotEmpty(avatarPath)) {
			category.setAvatarPath(avatarPath);
		} else {
			category.setAvatarPath(this.pictureService.getDefaultCategoryAvatarPath());
		}
		categoryDao.updateCategory(category);
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
