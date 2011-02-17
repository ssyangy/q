package q.biz.group;
import java.io.*;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
import java.sql.SQLException;
import java.util.Date;
import org.apache.commons.validator.GenericValidator;
import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Group;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
public class AddGroup extends Resource{
private final static Logger log = Logger.getLogger();
	
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
  String uid = context.getResourceLastId();
 // Group temp=groupDao.getGroupById(1);
  //log.debug("get group:%s", temp.getName());
  
  
  Group group=new Group();
  group.setId(System.currentTimeMillis());
  group.setName(context.getString("qname"));
  group.setIntro(context.getString("qinformation"));
  group.setStatus("0");
  group.setCreated(new Date());
  groupDao.addGroup(group);
}
}