package q.biz.group;

/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
import java.sql.SQLException;
import java.util.Date;
import q.dao.GroupDao;
import q.domain.Group;
import q.domain.GroupJoinCategory;
import q.web.Resource;
import q.web.ResourceContext;

public class AddGroup extends Resource {

	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	@Override
	public void execute(ResourceContext context) throws SQLException {
		Group group = new Group();
		long groupid=System.currentTimeMillis();
		group.setId(groupid);
		group.setName(context.getString("name"));
		group.setIntro(context.getString("intro"));
		group.setStatus("0");
		group.setCreated(new Date());
		GroupJoinCategory groupcategory=new GroupJoinCategory();
		groupcategory.setId(System.currentTimeMillis());
		groupcategory.setCategoryId(Long.parseLong(context.getString("category")));
		groupcategory.setGroupId(groupid);
		groupcategory.setStatus("0");
		groupcategory.setCreated(new Date());
		groupDao.addGroup(group);
		groupDao.addGroupCategory(groupcategory);
	}
}