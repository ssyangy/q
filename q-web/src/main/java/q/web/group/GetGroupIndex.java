package q.web.group;

import java.sql.SQLException;
import java.util.List;

import q.dao.CategoryDao;
import q.dao.GroupDao;
import q.domain.Category;
import q.domain.Group;
import q.util.LocationKit;
import q.web.Resource;
import q.web.ResourceContext;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 *
 */
public class GetGroupIndex extends Resource {

	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		List<Category> categorys = categoryDao.getAllCategorys();
		context.setModel("cats", categorys);
		long catId = context.getIdLong("cat");
		if(catId > 0) {
			for(Category cat: categorys) {
				if(cat.getId() == catId) {
					context.setModel("cat", cat);
					break;
				}
			}
			List<Group> groups = groupDao.getAllGroupsByCatId(catId);
			context.setModel("catGroups", groups);
		}
		String method=context.getString("method");
		if(method!=null){
		if(method.equals("getMyGroups")){
			long id=context.getCookiePeopleId();
		List<Group>groups=groupDao.getGroupsByPeopleId(id);
		context.setModel("groups", groups);
		}
		else if(method.equals("getNearGroups")){
			double latitude=Double.parseDouble(context.getString("latitude"));
			double longitude=Double.parseDouble(context.getString("longitude"));
			Group temp=new Group();
			temp.setLatitude(latitude);
			temp.setLongitude(longitude);
			List<Group>groups=groupDao.getGroupsByLocation(temp);
			for(int i=0;i<groups.size();i++){
				double latTemp=groups.get(i).getLatitude();
				double longTemo=groups.get(i).getLongitude();
				if(!LocationKit.isCloser(latitude, longitude, latTemp, longTemo, 1)){
                   groups.remove(i);
				}
			}
			context.setModel("groups", groups);

		}
		}

	}

	/* (non-Javadoc)
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
		// TODO Auto-generated method stub

	}

}
