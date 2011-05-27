/**
 * 
 */
package q.web.category;

import java.sql.SQLException;

import q.biz.PictureService;
import q.dao.CategoryDao;
import q.domain.Category;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 * 
 */
public class AddCategory extends Resource {

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
		category.setAvatarPath(this.pictureService.getDefaultCategoryAvatarPath());
		categoryDao.addCategory(category);
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
