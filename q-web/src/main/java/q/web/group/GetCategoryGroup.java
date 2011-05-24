package q.web.group;

import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import q.dao.GroupDao;
import q.dao.page.GroupJoinCategoryPage;
import q.domain.Group;
import q.domain.Status;
import q.util.CollectionKit;
import q.util.IdCreator;
import q.web.Resource;
import q.web.ResourceContext;
import q.web.exception.RequestParameterInvalidException;

/**
 * @author Zhehao
 * @author seanlinwang
 * @date Feb 15, 2011
 * 
 */
public class GetCategoryGroup extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		int size = context.getInt("size", 10);
		long startId = context.getIdLong("startId", IdCreator.MAX_ID);
		long categoryId = context.getResourceIdLong();
		int type = context.getInt("type", 0);
		int asc = 1;
		GroupJoinCategoryPage page = new GroupJoinCategoryPage();
		if (type == asc) { // 1 indicate asc
			page.setDesc(false);
		} else {
			page.setDesc(true);
		}
		boolean hasPrev = false;
		boolean hasNext = false;
		int fetchSize = size + 1;
		page.setSize(fetchSize);
		page.setCategoryId(categoryId);
		page.setStatus(Status.COMMON.getValue());
		if (startId > 0) {
			page.setStartId(startId);
		}
		List<Long> groupIds = this.groupDao.getGroupIdsByGroupJoinCategoryPageOrderByGroupId(page); // XXX order by group_id not join_id
		List<Group> groups = this.groupDao.getGroupsByIds(groupIds);// not sorted groups
		Map<String, Object> api = new HashMap<String, Object>();
		if (CollectionKit.isNotEmpty(groups)) {
			Collections.sort(groups, idDescComparator);
			if (groups.size() == fetchSize) {
				if (type == asc) {
					groups.remove(0);// remove last one
				} else {
					groups.remove(groups.size() - 1);// remove last one
				}

				if (type == asc) { // more than one previous page
					hasPrev = true;
				} else { // more than one next page
					hasNext = true;
				}
			}
			if (type == asc) { // this action from next page
				hasNext = true;
			} else if (startId != IdCreator.MAX_ID) {// this action from previous page
				hasPrev = true;
			}

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
		long categoryId = context.getResourceIdLong();
		if (IdCreator.isNotValidId(categoryId)) {
			throw new RequestParameterInvalidException("category:invalid");
		}
	}

}
