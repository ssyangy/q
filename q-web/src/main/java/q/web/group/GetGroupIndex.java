package q.web.group;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.dao.CategoryDao;
import q.dao.GroupDao;
import q.domain.Group;
import q.util.CollectionKit;
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
		String method = context.getString("method");
		List<Group> groups = null;
		if (method == null) {
			long catId = context.getIdLong("catId");
			if (catId > 0) {
				groups = groupDao.getAllGroupsByCatId(catId);
			}
		} else if (method.equals("getMyGroups")) {
			long id = context.getCookiePeopleId();
			groups = groupDao.getGroupsByPeopleId(id);
		} else if (method.equals("getNearGroups")) {
			double latitude = Double.parseDouble(context.getString("latitude"));
			double longitude = Double.parseDouble(context.getString("longitude"));
			Group temp = new Group();
			temp.setLatitude(latitude);
			temp.setLongitude(longitude);
			groups = groupDao.getGroupsByLocation(temp);
			for (int i = 0; i < groups.size(); i++) {
				double latTemp = groups.get(i).getLatitude();
				double longTemo = groups.get(i).getLongitude();
				if (!LocationKit.isCloser(latitude, longitude, latTemp, longTemo, 1)) {
					groups.remove(i);
				}
			}
		}
		boolean hasPrev = true;
		boolean hasNext = false;
		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(groups)) {
			api.put("groups", groups);
		}
		api.put("hasPrev", hasPrev);
		api.put("hasNext", hasNext);
		context.setModel("api", api);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see q.web.Resource#validate(q.web.ResourceContext)
	 */
	@Override
	public void validate(ResourceContext context) throws Exception {
	}

}
