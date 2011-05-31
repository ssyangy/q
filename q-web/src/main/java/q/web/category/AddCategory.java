/**
 *
 */
package q.web.category;

import java.sql.SQLException;

import q.biz.PictureService;
import q.dao.CategoryDao;
import q.dao.PeopleDao;
import q.domain.Category;
import q.domain.People;
import q.util.IdCreator;
import q.util.StringKit;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.PeopleNotLoginException;
import q.web.exception.PeopleNotPermitException;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 * 
 */
public class AddCategory extends Resource {
	private PeopleDao peopleDao;

	public void setPeopleDao(PeopleDao peopleDao) {
		this.peopleDao = peopleDao;
	}

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private PictureService pictureService;

	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		Category category = new Category();
		category.setId(IdCreator.getLongId());
		category.setName(context.getString("name"));
		category.setIntro(context.getString("intro"));
		String avatarPath = context.getString("avatarPath");
		if (StringKit.isEmpty(avatarPath)) {
			category.setAvatarPath(this.pictureService.getDefaultCategoryAvatarPath());
		} else {
			category.setAvatarPath(avatarPath);
		}
		categoryDao.addCategory(category);
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
