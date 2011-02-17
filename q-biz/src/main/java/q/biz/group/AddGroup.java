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
import q.web.Resource;
import q.web.ResourceContext;
public class AddGroup extends Resource{
	
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
  Group group=new Group();
  group.setId(System.currentTimeMillis());
  group.setName(context.getString("qname"));
  group.setIntro(context.getString("qinformation"));
  group.setStatus("0");
  group.setCreated(new Date());
  groupDao.addGroup(group);
}
}