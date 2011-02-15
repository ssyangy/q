package q.biz.peoples;
import java.io.*;

import java.sql.SQLException;
import java.util.Date;
import org.apache.commons.validator.GenericValidator;

import q.dao.GroupDao;
import q.dao.PeopleDao;
import q.domain.Gender;
import q.domain.People;
import q.log.Logger;
import q.web.Resource;
import q.web.ResourceContext;
/**
 * @author Zhehao
 * @date Feb 15, 2011
 *
 */
public class GetGroup extends Resource{
private final static Logger log = Logger.getLogger();
	
	private GroupDao groupDao;

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
@Override
public void execute(ResourceContext context) throws SQLException{
  String uid = context.getResourceLastId();

}
}